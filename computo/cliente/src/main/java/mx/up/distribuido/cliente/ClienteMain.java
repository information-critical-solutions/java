package mx.up.distribuido.cliente;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Cliente que se conecta al Nodo (Servidor) en puerto 31010
 * Recibe mensaje de bienvenida y lo registra en bitácora usando Log4j2
 */
public class ClienteMain {
    private static final Logger LOGGER = LogManager.getLogger(ClienteMain.class);
    private static final String SERVIDOR_HOST = "localhost";
    private static final int SERVIDOR_PUERTO = 31010;
    
    public static void main(String[] args) {
        // Identificar instancia del cliente
        String nombreCliente = "Cliente-" + System.currentTimeMillis();
        
        iniciarBitacora(nombreCliente);
        
        try {
            conectarAlServidor(nombreCliente);
        } catch (IOException e) {
            LOGGER.error("No se pudo conectar al servidor: {}", e.getMessage(), e);
        }
    }
    
    private static void iniciarBitacora(String nombreCliente) {
        LOGGER.info("=================================================");
        LOGGER.info("           CLIENTE DISTRIBUIDO");
        LOGGER.info("=================================================");
        LOGGER.info("Nombre: {}", nombreCliente);
        LOGGER.info("Servidor: {}:{}", SERVIDOR_HOST, SERVIDOR_PUERTO);
        LOGGER.info("=================================================");
        
        LOGGER.info("Cliente iniciado: {}", nombreCliente);
        LOGGER.info("Intentando conectar a {}:{}", SERVIDOR_HOST, SERVIDOR_PUERTO);
    }
    
    private static void conectarAlServidor(String nombreCliente) throws IOException {
        LOGGER.info("Estableciendo conexión...");
        
        try (Socket socket = new Socket(SERVIDOR_HOST, SERVIDOR_PUERTO);
             BufferedReader entrada = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))) {
            
            LOGGER.info("¡CONEXIÓN ESTABLECIDA EXITOSAMENTE!");
            LOGGER.info("Socket local: {}", socket.getLocalSocketAddress());
            LOGGER.info("Socket remoto: {}", socket.getRemoteSocketAddress());
            LOGGER.debug("Timeout del socket: {}", socket.getSoTimeout());
            
            // Recibir mensaje de bienvenida del servidor
            recibirMensajeBienvenida(entrada);
            
            // Mantener conexión activa
            mantenerConexionActiva(socket, nombreCliente);
            
        } catch (IOException e) {
            LOGGER.error("Fallo en la conexión: {}", e.getMessage());
            throw e;
        }
    }
    
    private static void recibirMensajeBienvenida(BufferedReader entrada) throws IOException {
        LOGGER.info("Esperando mensaje de bienvenida del servidor...");
        
        StringBuilder mensajeCompleto = new StringBuilder();
        String linea;
        
        // Leer el mensaje de bienvenida línea por línea
        while ((linea = entrada.readLine()) != null) {
            mensajeCompleto.append(linea).append("\n");
            
            // El mensaje termina con la línea de separación
            if (linea.contains("================================") && 
                mensajeCompleto.toString().contains("BIENVENIDO")) {
                break;
            }
        }
        
        if (mensajeCompleto.length() > 0) {
            LOGGER.info("MENSAJE DE BIENVENIDA RECIBIDO:");
            LOGGER.info("================================");
            
            // Registrar cada línea del mensaje en la bitácora
            String[] lineasMensaje = mensajeCompleto.toString().split("\n");
            for (String lineaMensaje : lineasMensaje) {
                if (!lineaMensaje.trim().isEmpty()) {
                    LOGGER.info(">>> {}", lineaMensaje);
                }
            }
            
            LOGGER.info("================================");
            LOGGER.info("Mensaje registrado exitosamente en bitácora");
            
        } else {
            LOGGER.warn("No se recibió mensaje de bienvenida del servidor");
        }
    }
    
    private static void mantenerConexionActiva(Socket socket, String nombreCliente) {
        LOGGER.info("Manteniendo conexión activa...");
        LOGGER.info("Presiona Ctrl+C para desconectar");
        
        try {
            // Mantener la conexión activa por un tiempo determinado
            // En un escenario real, aquí podrían estar las operaciones del cliente
            LOGGER.debug("Iniciando sleep de 30 segundos para mantener conexión");
            Thread.sleep(30000); // 30 segundos
            
            LOGGER.info("Cerrando conexión después de 30 segundos...");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.warn("Conexión interrumpida por el usuario");
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                    LOGGER.info("Socket cerrado correctamente");
                }
            } catch (IOException e) {
                LOGGER.error("Error al cerrar socket: {}", e.getMessage());
            }
            
            LOGGER.info("=== SESIÓN TERMINADA ===");
            LOGGER.info("Cliente {} finalizó su ejecución", nombreCliente);
        }
    }
}