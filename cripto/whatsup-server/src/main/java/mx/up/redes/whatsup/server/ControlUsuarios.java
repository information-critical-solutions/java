package mx.up.redes.whatsup.server;

import mx.up.redes.whatsup.util.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author sebastian
 */
public class ControlUsuarios {
    
    private ControlUsuarios() {
    }
    
    public static ControlUsuarios getInstance() {
        return ControlUsuariosHolder.INSTANCE;
    }
    
    private static class ControlUsuariosHolder {
        private static final ControlUsuarios INSTANCE = new ControlUsuarios();
    }

    private static final Logger LOGGER = LogManager.getLogger(ControlUsuarios.class);
    private final ConcurrentHashMap<String, ClientHandler> users = new ConcurrentHashMap<>();

    public boolean registerUser(String username, ClientHandler handler) {
        boolean success = users.putIfAbsent(username, handler) == null;
        if (success) {
            LOGGER.info("Usuario agregado al registry: {}. Total usuarios: {}", username, users.size());
        } else {
            LOGGER.debug("Intento de registro duplicado rechazado: {}", username);
        }
        return success;
    }

    public void unregisterUser(String username) {
        ClientHandler removed = users.remove(username);
        if (removed != null) {
            LOGGER.info("Usuario removido del registry: {}. Total usuarios: {}", username, users.size());
        }
    }

    public boolean sendMessage(String from, String to, String message) {
        ClientHandler recipient = users.get(to);
        if (recipient != null) {
            recipient.sendMessage(Protocol.MESSAGE + " " + from + " " + message);
            LOGGER.info("Mensaje entregado exitosamente de {} a {}", from, to);
            return true;
        }
        LOGGER.warn("Mensaje no pudo ser entregado de {} a {}: usuario no encontrado", from, to);
        return false;
    }

    public void broadcastUserList() {
        String userList = getUserListCsv();
        String broadcastMessage = Protocol.USERS + " " + userList;

        LOGGER.debug("Broadcasting lista de usuarios: [{}]", userList);

        for (ClientHandler handler : users.values()) {
            handler.sendMessage(broadcastMessage);
        }
    }

    public String getUserListCsv() {
        return users.keySet().stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    public int getUserCount() {
        return users.size();
    }

}
