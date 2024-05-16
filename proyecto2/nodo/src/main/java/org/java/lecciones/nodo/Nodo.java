package org.java.lecciones.nodo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Nodo {

    /**
     * bitacora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(Nodo.class);
    // puertos de inicio y fin 
    private static final Integer PUERTO_INICIAL = 12345;
    private static final Integer PUERTO_FINAL = 12349;

    private static ServerSocket serverSocket;

    private static void getServerSocket() throws Exception {
        for (int i = PUERTO_INICIAL; i <= PUERTO_FINAL; i++) {
            try {
                serverSocket = new ServerSocket(i);
                LOGGER.info("Escuchando en el puerto: " + serverSocket.getLocalPort());
                return;
            } catch (IOException iOException) {
                LOGGER.warn("El puerto: " + i + " esta ocupado");
            }
        }
        throw new Exception("Ningun puerto disponible");
    }

    private static void conectarConOtrosNodos() {
        for (int i = PUERTO_INICIAL; i <= PUERTO_FINAL; i++) {
            if (serverSocket.getLocalPort() == i) {
                continue;
            }
            try {
                Socket socket = new Socket("localhost", i);
                // se inicia el hilo para recibir los mensajes
                new Thread(new DespachadorMensajes(socket)).start();
            } catch (IOException iOException) {
                LOGGER.warn("No hay nodo en: " + i + " esta ocupado");
            }
        }
    }

    /**
     * metodo de entrada del programa
     *
     * @param args arreglo de strings de entrada
     */
    public static void main(String[] args) throws Exception {
        // ocupar un puerto del rango
        getServerSocket();
        // se estabelce la conexion con otros nodos
        conectarConOtrosNodos();
        // esperar las conexiones de otros clientes
        while (true) {
            Socket socket = serverSocket.accept();
            LOGGER.info("Llego el cliente " + socket.getInetAddress());
            // hilo de atencion de mensajes de llegada
            new Thread(new DespachadorMensajes(socket)).start();
        }

    }

}
