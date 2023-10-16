package org.java.lecciones.clase6.proyecto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Esta clase representa un cliente socket que se conecta a un servidor en un
 * puerto específico. El cliente se conecta al servidor y puede enviar y recibir
 * mensajes.
 *
 * @author Sebastian Godinez Borja
 */
public class ClienteSocket {

    private static final Logger LOGGER = LogManager.getLogger(ClienteSocket.class);

    public static void main(String[] args) throws IOException {
        final String servidorIP = "localhost"; // Cambia esta dirección al IP del servidor
        final int puerto = 12345;
        Socket socket = new Socket(servidorIP, puerto);
        LOGGER.info("Conectado al servidor.");
        // hilo de envío
        new Thread(() -> {
            try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                Integer envio = 0;
                while (true) {
                    Mensaje mensaje = new Mensaje((short) 1, "Hola, servidor!".getBytes());
                    // Enviar el tipo de operación
                    dos.writeShort(mensaje.getTipoOperacion());
                    // Enviar el tamaño del arreglo de datos
                    dos.writeShort(mensaje.getData().length);
                    // Enviar el arreglo de datos al servidor
                    dos.write(mensaje.getData());
                    LOGGER.info("Mensaje enviado " + ++envio);
                    Thread.sleep(Duration.ofSeconds(3L));
                }
            } catch (IOException | InterruptedException e) {
                // El cliente se desconectó, se maneja la excepción y se sale del bucle
                LOGGER.warn("Cliente desconectado.");
            }
        }).start();
        // hilo de recepción
        new Thread(() -> {
            try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
                while (true) {
                    // Leer el tipo de operación como short
                    Short tipoOperacion = dataInputStream.readShort();
                    // Leer el tamaño del arreglo de datos como short
                    Short tam = dataInputStream.readShort();
                    // Leer el arreglo de datos
                    byte[] datos = new byte[tam];
                    dataInputStream.readFully(datos);
                    Mensaje mensaje = new Mensaje(tipoOperacion, datos);
                    LOGGER.info("Mensaje recibido : " + mensaje);
                }
            } catch (IOException e) {
                // El cliente se desconectó, se maneja la excepción y se sale del bucle
                LOGGER.warn("Cliente desconectado.");
            }
        }).start();
    }

}
