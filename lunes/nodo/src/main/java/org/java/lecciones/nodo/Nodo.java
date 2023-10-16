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
 *
 * @author Sebastian Godinez Borja
 */
public class Nodo {

    /**
     * bitacora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(Nodo.class);
    /**
     * puerto que utiliza el nodo
     */
    private static final Integer PUERTO = 12345;

    /**
     * metodo de entrada del programa
     *
     * @param args arreglo de strings de entrada
     */
    public static void main(String[] args) {
        LOGGER.info("Iniciando el nodo!");
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            LOGGER.info("Nodo en linea, en el puerto (" + PUERTO + ")");
            // recibir a los clientes
            while (true) {
                Socket socket = serverSocket.accept();
                ClientesConectados.getInstance().addClienteConectado(socket);
                LOGGER.info("Llego el cliente " + socket.getInetAddress());
                // hilo de atencion de mensajes de llegada
                new Thread(() -> {
                    // recibir la informacion y enviarla a todos los demas clientes
                    try (DataInputStream dis = new DataInputStream(socket.getInputStream()); DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                        while (true) {
                            Mensaje mensaje = DecoderEncoder.leer(dis);
                            LOGGER.info("se recibio el mensaje: " + mensaje + ", del cliente: " + socket);
                            // aqui se envia a todos los clientes
                            for (Socket socketEnTurno : ClientesConectados.getInstance().getClientesConectados()) {
                                LOGGER.info("se reenvia el mensaje al socket: " + socketEnTurno);
                                DecoderEncoder.escribir(dos, mensaje);
                            }
                        }
                    } catch (IOException ex) {
                        LOGGER.warn("Cliente desconectado", ex);
                    } finally {
                        // se saca de los clientes conectados
                        ClientesConectados.getInstance().removeClienteConectado(socket);
                        List<Socket> clientesConectados = ClientesConectados.getInstance().getClientesConectados();
                        LOGGER.info("El cliente " + socket + " se fue, ahora quedan: " + clientesConectados.size());
                        clientesConectados.forEach(LOGGER::info);
                    }

                }).start();
            }

        } catch (IOException iOException) {
            LOGGER.error("Algo paso con el nodo", iOException);
        }

    }

}
