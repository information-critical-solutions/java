package mx.up.distribuido.nodo.manejador;

import mx.up.distribuido.util.ClienteInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manejador de cliente individual que se ejecuta en un hilo separado
 */
public class ClienteHandler implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(ClienteHandler.class);
    private final ClienteInfo clienteInfo;

    public ClienteHandler(ClienteInfo clienteInfo) {
        this.clienteInfo = clienteInfo;
    }
    
    @Override
    public void run() {
        Socket socket = clienteInfo.getSocket();
        String clienteId = clienteInfo.getId();
        
        LOGGER.info("Iniciando manejo de cliente: {}", clienteId);
        
        try (PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
            
            // Enviar mensaje de bienvenida
            enviarMensajeBienvenida(salida, clienteId);
            
            // Mantener la conexión activa
            mantenerConexion(socket, clienteId);
            
        } catch (IOException e) {
            LOGGER.error("Error de comunicación con cliente {}: {}", clienteId, e.getMessage());
        } finally {
            // Limpiar al desconectarse
            limpiarConexion(clienteId);
        }
    }
    
    private void enviarMensajeBienvenida(PrintWriter salida, String clienteId) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        String mensajeBienvenida = String.format(
            "¡BIENVENIDO AL NODO DISTRIBUIDO!\n" +
            "================================\n" +
            "ID Cliente: %s\n" +
            "Servidor: Puerto 31010\n" +
            "Hora conexión: %s\n" +
            "Estado: CONECTADO\n" +
            "Clientes totales: %d\n" +
            "================================",
            clienteId, timestamp, ControlConexiones.getInstance().getClientesConectados().size()
        );
        
        salida.println(mensajeBienvenida);
        salida.flush();
        
        LOGGER.info("Mensaje de bienvenida enviado a cliente: {}", clienteId);
        LOGGER.debug("Contenido del mensaje para {}: {}", clienteId, mensajeBienvenida.replace("\n", " | "));
    }
    
    private void mantenerConexion(Socket socket, String clienteId) {
        LOGGER.debug("Manteniendo conexión activa para cliente: {}", clienteId);
        try {
            // Mantener la conexión activa hasta que el cliente se desconecte
            while (socket.isConnected() && !socket.isClosed()) {
                Thread.sleep(1000); // Verificar cada segundo
            }
            LOGGER.info("Cliente {} cerró la conexión", clienteId);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warn("Hilo interrumpido para cliente: {}", clienteId);
        }
    }
    
    private void limpiarConexion(String clienteId) {
        try {
            // Remover del registro
            ClienteInfo clienteRemovido = ControlConexiones.getInstance().eliminarCliente(clienteId);
            
            if (clienteRemovido != null) {
                // Cerrar socket si aún está abierto
                Socket socket = clienteRemovido.getSocket();
                if (!socket.isClosed()) {
                    socket.close();
                }
                
                LOGGER.info("Cliente desconectado y removido del registro: {}", clienteId);
                LOGGER.info("Clientes restantes en el servidor: {}", ControlConexiones.getInstance().getClientesConectados().size());
                
                // Mostrar clientes activos
                if (!ControlConexiones.getInstance().getClientesConectados().isEmpty()) {
                    LOGGER.info("Clientes activos:");
                    ControlConexiones.getInstance().getClientesConectados().values().forEach(cliente ->
                        LOGGER.info("  • {}", cliente.getId()));
                } else {
                    LOGGER.info("No hay clientes conectados al servidor");
                }
            }
            
        } catch (IOException e) {
            LOGGER.error("Error al limpiar conexión de cliente {}: {}", clienteId, e.getMessage());
        }
    }
}