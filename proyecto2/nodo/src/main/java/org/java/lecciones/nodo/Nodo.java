package org.java.lecciones.nodo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;
import org.java.lecciones.libreriacomunicacion.TipoConexion;
import org.java.lecciones.libreriacomunicacion.TipoOperacion;
import org.java.lecciones.libreriacomunicacion.error.ConexionException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.List;

/**
 * Clase que representa un nodo del servidor. El nodo escucha a los clientes y
 * reenvía mensajes a todos los clientes conectados.
 *
 * @author Sebastian Godinez Borja
 */
public class Nodo {

    /**
     * Bitácora de la clase.
     */
    private static final Logger LOGGER = LogManager.getLogger(Nodo.class);

    /**
     * Puerto que utiliza el nodo.
     */
    private static final Integer PUERTO_INICIAL = 12345;
    private static final Integer RANGO = 4;

    private static ServerSocket serverSocket;

    /**
     * Método de entrada del programa.
     *
     * @param args Arreglo de strings de entrada.
     */
    public static void main(String[] args) throws ConexionException {
        LOGGER.info("Iniciando el nodo!");
        serverSocket = iniciarSocket();


        // recibir a los clientes
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    LOGGER.info("Llegó el cliente " + socket.getInetAddress());
                    // se le indica que yo soy nodo
                    Mensaje mensaje = new Mensaje();
                    mensaje.setTipoOperacion(TipoOperacion.TIPO);
                    mensaje.setDatos(TipoConexion.NODO.name().getBytes());
                    // si acepta la conexion informa que es un nodo
                    DecoderEncoder.escribir(socket, mensaje);
                    // Hilo de atención de mensajes de llegada
                    new Thread(new Encoder(socket)).start();
                } catch (IOException iOException) {
                }
            }
        }).start();

        // conectar con otros clientes
        conectaConOtrosNodos();

        // imprimiendo conexiones cada cierto tiempo
        new Thread(() -> {
            while (true) {
                LOGGER.info("Soy el puerto: " + serverSocket.getLocalPort());
                List<Socket> clientesConectados = Conexiones.getInstance().getClientesConectados();
                List<Socket> nodosConectados = Conexiones.getInstance().getNodosConectados();
                LOGGER.info("Clientes conectados: " + clientesConectados.size());
                clientesConectados.forEach(LOGGER::info);
                LOGGER.info("Nodos conectados: " + nodosConectados.size());
                nodosConectados.forEach(LOGGER::info);
                try {
                    Thread.sleep(Duration.ofSeconds(3));
                } catch (InterruptedException ex) {
                    LOGGER.error("Al dormir rl hilo que imprime los clientes y nodos ", ex);
                }
            }
        }).start();
    }

    private static ServerSocket iniciarSocket() throws ConexionException {
        for (int i = PUERTO_INICIAL; i < (PUERTO_INICIAL + RANGO); i++) {
            ServerSocket serverSocket;
            try {
                LOGGER.info("Intentando tomar el puerto (" + i + ")");
                serverSocket = new ServerSocket(i);
                LOGGER.info("Esperando clientes en el puerto (" + i + ")");
                return serverSocket;
            } catch (IOException iOException) {
                LOGGER.error("El puerto (" + i + ") esta ocupado");
            }
        }
        throw new ConexionException("Todos los puertos estan ocupados");
    }

    private static void conectaConOtrosNodos() {
        for (int i = PUERTO_INICIAL; i < (PUERTO_INICIAL + RANGO); i++) {
            if (serverSocket.getLocalPort() == i) {
                continue;
            }
            Socket socket;
            try {
                LOGGER.info("Intentando la conexion con (localhost:" + i + ")");
                socket = new Socket("localhost", i);
                // ya hay conexion
                Mensaje mensaje = new Mensaje();
                mensaje.setTipoOperacion(TipoOperacion.TIPO);
                mensaje.setDatos(TipoConexion.NODO.name().getBytes());
                // si acepta la conexion informa que es un nodo
                DecoderEncoder.escribir(socket, mensaje);
                // se crea el hilo que procesa los mensjes de entrada
                new Thread(new Encoder(socket)).start();
            } catch (IOException iOException) {
                LOGGER.error("En el puerto (" + i + ") no hay nodo");
            }
        }
    }
}
