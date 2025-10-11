package mx.up.redes.whatsup.client;

import mx.up.redes.whatsup.util.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;

public class MessageListener implements Runnable {
    private static final Logger logger = LogManager.getLogger(MessageListener.class);
    private final BufferedReader in;

    public MessageListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        logger.info("Hilo de escucha de mensajes iniciado");
        try {
            String message;
            while ((message = in.readLine()) != null) {
                logger.debug("Mensaje recibido del servidor: {}", message);
                processServerMessage(message);
            }
        } catch (IOException e) {
            logger.warn("Conexión con el servidor perdida: {}", e.getMessage());
            System.out.println("\n[Sistema] Conexión con el servidor perdida.");
        } finally {
            logger.info("Hilo de escucha de mensajes terminado");
        }
    }

    private void processServerMessage(String message) {
        String[] parts = message.split(" ", 3);
        String command = parts[0];

        switch (command) {
            case Protocol.USERS:
                handleUserList(parts);
                break;
            case Protocol.MESSAGE:
                handleIncomingMessage(parts);
                break;
            case Protocol.ERROR:
                handleError(parts);
                break;
            default:
                logger.debug("Mensaje no reconocido del servidor: {}", message);
                System.out.println("\n[Servidor] " + message);
        }
        System.out.print("> "); // Mostrar prompt de nuevo
        System.out.flush();
    }

    private void handleUserList(String[] parts) {
        if (parts.length >= 2) {
            String userList = parts[1];
            logger.debug("Lista de usuarios recibida: {}", userList);
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║         USUARIOS CONECTADOS          ║");
            System.out.println("╠══════════════════════════════════════╣");

            if (userList.isEmpty()) {
                System.out.println("║  No hay otros usuarios conectados   ║");
            } else {
                String[] users = userList.split(",");
                for (String user : users) {
                    System.out.printf("║  • %-30s ║%n", user.trim());
                }
            }
            System.out.println("╚══════════════════════════════════════╝");
        }
    }

    private void handleIncomingMessage(String[] parts) {
        if (parts.length >= 3) {
            String sender = parts[1];
            String messageText = parts[2];
            logger.info("Mensaje recibido de {}: {}", sender, messageText);
            System.out.println("\n📨 [" + sender + "] " + messageText);
        }
    }

    private void handleError(String[] parts) {
        if (parts.length >= 2) {
            String errorMessage = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
            logger.warn("Error recibido del servidor: {}", errorMessage);
            System.out.println("\n❌ Error: " + errorMessage);
        }
    }
}