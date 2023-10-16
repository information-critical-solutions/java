package org.java.lecciones.cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.Duration;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Cliente {

    /**
     * bitacora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(Cliente.class);

    /**
     * host del nodo
     */
    private static final String HOST = "localhost";
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
        LOGGER.info("Iniciando el cliente");
        try {
            Socket socket = new Socket(HOST, PUERTO);
            LOGGER.info("Conectado con el nodo (" + HOST + ":" + PUERTO + ")");

            new Thread(() -> {
                try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                    Integer numEnvio = 0;
                    while (true) {
                        // tipo de operacion
                        Short tipoOperacion = 1;
                        // mensaje
                        String mensaje = "Hola servidor, este es el mensaje #" + ++numEnvio;
                        byte[] mensajeEnBytes = mensaje.getBytes();
                        Short tam = (short) mensajeEnBytes.length;
                        // escribir el tipo de operacion
                        dos.writeShort(tipoOperacion);
                        // escribir el tam
                        dos.writeShort(tam);
                        // escribir la informacion asociada al to
                        dos.write(mensajeEnBytes);
                        LOGGER.info("Mensaje enviado al servidor: " + mensaje);
                        Thread.sleep(Duration.ofSeconds(5L));
                    }
                } catch (IOException ex) {
                    LOGGER.warn("Cliente desconectado");
                } catch (InterruptedException ex) {
                    LOGGER.error("Fallo al dormir el hilo de envio de mensajes", ex);
                }
            }).start();

        } catch (IOException ex) {
            LOGGER.error("Error en el server", ex);
        }

    }
}
