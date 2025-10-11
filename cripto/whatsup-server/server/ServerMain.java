package mx.up.redes.whatsup.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    private static final Logger logger = LogManager.getLogger(ServerMain.class);
    private static final int PORT = 5050;

    public static void main(String[] args) {
        logger.info("Iniciando servidor WhatsApp Clone en puerto {}", PORT);

        mx.up.redes.whatsup.server.UserRegistry registry = new mx.up.redes.whatsup.server.UserRegistry();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Servidor escuchando en puerto {}", PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Nuevo cliente conectado desde: {}", clientSocket.getInetAddress());

                // Crear hilo para manejar cliente
                Thread clientThread = new Thread(new mx.up.redes.whatsup.server.ClientHandler(clientSocket, registry));
                clientThread.start();
            }
        } catch (IOException e) {
            logger.error("Error crítico en el servidor: {}", e.getMessage(), e);
        }
    }
}
