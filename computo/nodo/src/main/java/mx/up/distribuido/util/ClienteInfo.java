package mx.up.distribuido.util;

import java.net.Socket;
import java.time.LocalDateTime;

/**
 * Clase para almacenar información de clientes conectados
 */
public class ClienteInfo {
    private final String id;
    private final Socket socket;
    private final LocalDateTime fechaConexion;
    private final String direccionIP;
    
    public ClienteInfo(String id, Socket socket) {
        this.id = id;
        this.socket = socket;
        this.fechaConexion = LocalDateTime.now();
        this.direccionIP = socket.getInetAddress().getHostAddress();
    }
    
    public String getId() { return id; }
    public Socket getSocket() { return socket; }
    public LocalDateTime getFechaConexion() { return fechaConexion; }
    public String getDireccionIP() { return direccionIP; }
    
    @Override
    public String toString() {
        return String.format("Cliente[ID=%s, IP=%s, Conectado=%s]", 
                           id, direccionIP, fechaConexion);
    }
}