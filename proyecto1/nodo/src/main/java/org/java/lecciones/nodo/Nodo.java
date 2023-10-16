package org.java.lecciones.nodo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

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
    private static final Integer PUERTO = 12345;

    /**
     * Método de entrada del programa.
     *
     * @param args Arreglo de strings de entrada.
     */
    public static void main(String[] args) {
        LOGGER.info("Iniciando el nodo!");
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            LOGGER.info("Nodo en línea, en el puerto (" + PUERTO + ")");

            // Recibir a los clientes
            while (true) {
                Socket socket = serverSocket.accept();
                ClientesConectados.getInstance().addClienteConectado(socket);
                LOGGER.info("Llegó el cliente " + socket.getInetAddress());

                // Hilo de atención de mensajes de llegada
                new Thread(() -> {
                    // Recibir la información y enviarla a todos los demás clientes
                    try (DataInputStream dis = new DataInputStream(socket.getInputStream()); DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                        while (true) {
                            Mensaje mensaje = DecoderEncoder.leer(dis);
                            LOGGER.info("Se recibió el mensaje: " + mensaje + ", del cliente: " + socket);

                            // Aquí se envía a todos los clientes
                            for (Socket socketEnTurno : ClientesConectados.getInstance().getClientesConectados()) {
                                LOGGER.info("Se reenvía el mensaje al socket: " + socketEnTurno);
                                DecoderEncoder.escribir(new DataOutputStream(socketEnTurno.getOutputStream()), mensaje);
                            }
                        }
                    } catch (IOException ex) {
                        LOGGER.warn("Cliente desconectado", ex);
                    } finally {
                        // Se saca de los clientes conectados
                        ClientesConectados.getInstance().removeClienteConectado(socket);
                        List<Socket> clientesConectados = ClientesConectados.getInstance().getClientesConectados();
                        LOGGER.info("El cliente " + socket + " se fue, ahora quedan: " + clientesConectados.size());
                        clientesConectados.forEach(LOGGER::info);
                    }

                }).start();
            }

        } catch (IOException iOException) {
            LOGGER.error("Algo pasó con el nodo", iOException);
        }
    }
}
