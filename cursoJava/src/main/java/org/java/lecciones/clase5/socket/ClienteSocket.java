package org.java.lecciones.clase5.socket;

import java.io.IOException;
import java.net.Socket;
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

    public static void main(String[] args) {
        final String servidorIP = "127.0.0.1"; // Cambia esta dirección al IP del servidor
        final int puerto = 12345;

        try {
            // Crear un socket y conectarse al servidor en la dirección y puerto especificados
            Socket socket = new Socket(servidorIP, puerto);
            LOGGER.info("Conectado al servidor.");

            // Aquí puedes implementar la lógica para enviar y recibir mensajes
            // utilizando socket.getInputStream() y socket.getOutputStream().
            // Cerrar la conexión cuando hayas terminado
            socket.close();
            LOGGER.info("Conexión cerrada.");
        } catch (IOException e) {
            // Manejar excepciones de E/S (por ejemplo, si no se puede conectar al servidor)
            LOGGER.error(e);
        }
    }
}
