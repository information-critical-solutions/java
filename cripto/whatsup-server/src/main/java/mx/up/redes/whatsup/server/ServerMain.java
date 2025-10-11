package mx.up.redes.whatsup.server;

import mx.up.redes.whatsup.util.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    private static final Logger LOGGER = LogManager.getLogger(ServerMain.class);

    public static void main(String[] args) {
        LOGGER.info("Iniciando servidor WhatsApp Clone en puerto {}", Protocol.DEFAULT_PORT);

//        UserRegistry registry = new UserRegistry();

        try (ServerSocket serverSocket = new ServerSocket(Protocol.DEFAULT_PORT)) {
            LOGGER.info("Servidor escuchando en puerto {}", Protocol.DEFAULT_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info("Nuevo cliente conectado desde: {}", clientSocket.getInetAddress());

                // Crear hilo para manejar cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            LOGGER.error("Error crítico en el servidor: {}", e.getMessage(), e);
        }
    }
}
