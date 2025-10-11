package mx.up.redes.whatsup.server;

import mx.up.redes.whatsup.util.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);
    private final Socket socket;
    private final UserRegistry registry;
    private String username;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, UserRegistry registry) {
        this.socket = socket;
        this.registry = registry;
    }

    @Override
    public void run() {
        String remoteAddress = socket.getRemoteSocketAddress().toString();
        logger.info("Procesando conexión de cliente: {}", remoteAddress);

        try {
            // Configurar streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Procesar registro
            if (!processRegistration()) {
                logger.warn("Registro fallido para cliente: {}", remoteAddress);
                return;
            }

            // Loop principal de mensajes
            processMessages();

        } catch (IOException e) {
            logger.error("Error de comunicación con cliente {}: {}", username, e.getMessage());
        } finally {
            cleanup();
        }
    }

    private boolean processRegistration() throws IOException {
        String registrationLine = in.readLine();
        if (registrationLine == null || !registrationLine.startsWith(Protocol.REGISTER + " ")) {
            logger.debug("Cliente envió comando de registro inválido: {}", registrationLine);
            sendError("Debe enviar: REGISTER <nombre>");
            return false;
        }

        username = registrationLine.substring(Protocol.REGISTER.length() + 1).trim();

        if (username.isEmpty()) {
            logger.debug("Cliente intentó registrarse con nombre vacío");
            sendError("El nombre no puede estar vacío");
            return false;
        }

        if (!registry.registerUser(username, this)) {
            logger.warn("Intento de registro con nombre duplicado: {}", username);
            sendError("Nombre ya está en uso");
            return false;
        }

        out.println(Protocol.OK);
        logger.info("Usuario registrado exitosamente: {}", username);
        registry.broadcastUserList();
        return true;
    }

    private void processMessages() throws IOException {
        String message;
        while ((message = in.readLine()) != null) {
            processCommand(message);
        }
    }

    private void processCommand(String command) {
        logger.debug("Comando recibido de {}: {}", username, command);
        String[] parts = command.split(" ", 3);
        String cmd = parts[0];

        switch (cmd) {
            case Protocol.LIST:
                handleListCommand();
                break;
            case Protocol.SEND:
                handleSendCommand(parts);
                break;
            case Protocol.QUIT:
                logger.info("Usuario {} solicita desconexión", username);
                return; // Salir del loop
            default:
                logger.warn("Comando desconocido de {}: {}", username, cmd);
                sendError("Comando desconocido: " + cmd);
        }
    }

    private void handleListCommand() {
        logger.debug("Usuario {} solicita lista de usuarios", username);
        String userList = registry.getUserListCsv();
        out.println(Protocol.USERS + " " + userList);
    }

    private void handleSendCommand(String[] parts) {
        if (parts.length < 3) {
            logger.debug("Usuario {} envió comando SEND con formato incorrecto", username);
            sendError("Formato: SEND <destinatario> <mensaje>");
            return;
        }

        String recipient = parts[1];
        String messageText = parts[2];

        logger.info("Mensaje de {} para {}: {}", username, recipient, messageText);
        boolean sent = registry.sendMessage(username, recipient, messageText);
        if (!sent) {
            logger.warn("Mensaje no entregado: usuario {} no encontrado", recipient);
            sendError("Usuario no encontrado: " + recipient);
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void sendError(String errorMessage) {
        out.println(Protocol.ERROR + " " + errorMessage);
    }

    private void cleanup() {
        try {
            if (username != null) {
                registry.unregisterUser(username);
                registry.broadcastUserList();
                logger.info("Usuario desconectado y removido del registry: {}", username);
            }
            if (socket != null) socket.close();
            if (in != null) in.close();
            if (out != null) out.close();
        } catch (IOException e) {
            logger.error("Error al cerrar recursos para usuario {}: {}", username, e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }
}
