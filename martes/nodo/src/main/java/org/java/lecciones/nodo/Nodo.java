package org.java.lecciones.nodo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
    /**
     * puerto de escucha del nodo
     */
    private static final Integer PUERTO = 12345;

    /**
     * metodo principal de entrada al programa
     *
     * @param args argumentos de entrada al programa
     */
    public static void main(String[] args) {
        LOGGER.info("Iniciando el nodo");
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            LOGGER.info("Esperando clientes en el puerto (" + PUERTO + ")");

            // atiende las llegadas de los nuevos clientes
            while (true) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Llego el cliente (" + socket.getInetAddress() + ")");
                ClientesConectados.getInstance().addCliente(socket);
                // hilo de recepcion de mensajes
                new Thread(() -> {
                    try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
                        while (true) {
                            // se lee el protocolo
                            // se lee el tipo de operacion
                            Short tipoOperacion = dis.readShort();
                            // se lee el tam de la informacion asociada al to
                            Short tam = dis.readShort();
                            // leer la informacion
                            byte[] datos = new byte[tam];
                            dis.readFully(datos);
                            String mensaje = new String(datos);
                            LOGGER.info("El cliente " + socket + "dice lo siguiente:");
                            LOGGER.info("Tipo de operacion: " + tipoOperacion);
                            LOGGER.info("Tam del arreglo: " + tam);
                            LOGGER.info("Mensaje recibido: " + mensaje);
                        }
                    } catch (IOException ex) {
                        LOGGER.warn("Cliente desconectado " + socket);
                    } finally {
                        ClientesConectados.getInstance().removeCliente(socket);
                        List<Socket> clientesConectados = ClientesConectados.getInstance().getClientesConectados();
                        LOGGER.info("El cliente " + socket + " se fue, ahora quedan: " + clientesConectados.size());
                        clientesConectados.forEach(LOGGER::info);
                    }
                }).start();
            }

        } catch (IOException ex) {
            LOGGER.error("Error en el server", ex);
        }

    }
}
