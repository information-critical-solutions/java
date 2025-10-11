package mx.up.distribuido.nodo;

import mx.up.distribuido.nodo.manejador.ClienteHandler;
import mx.up.distribuido.nodo.manejador.ControlConexiones;
import mx.up.distribuido.util.ClienteInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Nodo (Servidor) que acepta conexiones de múltiples clientes
 * Puerto: 31010
 */
public class NodoMain {

    private static final Logger LOGGER = LogManager.getLogger(NodoMain.class);
    private static final int PUERTO_INICIAL = 31010;
    private static final int PUERTO_FINAL = 31013;
    private static ServerSocket serverSocket;
    private static final AtomicInteger contadorClientes = new AtomicInteger(0);

    /**
     * Este método nos permite obtener la conexión en un puerto del rango conocido y si no hay ninguno disponible arroja error.
     *
     * @return ServerSocket con la conexion
     * @throws Exception En caso de que no exista ningun puerto
     */
    private static ServerSocket getServerSocket() throws Exception {
        for (int i = PUERTO_INICIAL; i <= PUERTO_FINAL; i++) {
            try {
                LOGGER.info("Iniciando el servidor en el puerto: " + i);
                ServerSocket serverSocket = new ServerSocket(i);
                LOGGER.info("ServerSocket creado exitosamente en puerto {}", i);
                return serverSocket;
            } catch (Throwable e) {
                LOGGER.error("Error al iniciar el servidor en el puerto: " + i, e);
            }
        }
        throw new Exception("Imposible inicializar el nodo, todos los puertos estan utilizados");
    }

    /**
     * Intenta la conexion con otros nodos
     */
    private static void conectarConNodos() {
        for (int i = PUERTO_INICIAL; i <= PUERTO_FINAL; i++) {
            try {
                LOGGER.info("Conectando con: " + i);
                Thread.sleep(2000);
                // validamos que no nos intentamos conectar con nosotros mismos
                if(serverSocket.getLocalPort() == i){
                    LOGGER.info("No intento conectarme conmigo, yo tengo el puerto: " + i);
                    continue;
                }
                // o se toma el socket, o salimos con un error
                Socket soc = new Socket("localhost", i);
                LOGGER.info("Se logro la conexion con el nodo {}", i);
                // sucede la autenticacion/identificacion

                // almacenamos la conexion en nuestro gestor
                ControlConexiones.getInstance()
                        .registrarNodo(soc);
                List<Socket> nodoConectados = ControlConexiones.getInstance()
                        .getNodoConectados();
                LOGGER.info("Tam de la malla / campo de datos {}", nodoConectados.size());
                for (Socket socket : nodoConectados) {
                    LOGGER.info("Conexion con {}", socket.getInetAddress());
                }
            } catch (Throwable e) {
                LOGGER.error("No se pudo conectar con el nodo: " + i, e);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        LOGGER.info("=================================================");
        LOGGER.info("          NODO SERVIDOR INICIADO");
        LOGGER.info("=================================================");
        LOGGER.info("Puertos en el campo de datos: " + PUERTO_INICIAL + " - " + PUERTO_FINAL);
        LOGGER.info("Esperando conexiones de clientes...");
        LOGGER.info("=================================================");

        // obtenemos el server socket o arrojamos una excepcion
        serverSocket = getServerSocket();
        // intentar la conexion con la malla
        conectarConNodos();


        while (true) {
            LOGGER.info("Esperando a los clientes...");
            // Acepta conexiones entrantes
            Socket clienteSocket = serverSocket.accept();

            // Genera ID único para el cliente
            String clienteId = "CLIENTE_" + contadorClientes.incrementAndGet();

            // Crear información del cliente
            ClienteInfo clienteInfo = new ClienteInfo(clienteId, clienteSocket);

            // Agregar al registro de clientes
            ControlConexiones.getInstance()
                    .registrarCliente(clienteId, clienteInfo);

            LOGGER.info("Nueva conexión aceptada: {}", clienteInfo);
            LOGGER.info("Total clientes conectados: {}", ControlConexiones.getInstance()
                    .getClientesConectados()
                    .size());

            // Crear hilo para manejar el cliente
            Thread hiloCliente = new Thread(new ClienteHandler(clienteInfo));
            hiloCliente.setName("Thread-" + clienteId);
            hiloCliente.start();

            LOGGER.debug("Hilo creado para cliente: {}", clienteId);

            // Mostrar estado actual
            mostrarEstadoServidor();
        }

    }

    private static void mostrarEstadoServidor() {
        LOGGER.info("--- ESTADO DEL SERVIDOR ---");
        LOGGER.info("Clientes activos: {}", ControlConexiones.getInstance()
                .getClientesConectados()
                .size());
        ControlConexiones.getInstance()
                .getClientesConectados()
                .values()
                .forEach(cliente ->
                        LOGGER.info("  • {}", cliente));
        LOGGER.info("---------------------------");
    }
}