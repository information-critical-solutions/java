package mx.up.distribuido.nodo.manejador;

import mx.up.distribuido.util.ClienteInfo;

import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControlConexiones {

    private ControlConexiones() {
    }

    public static ControlConexiones getInstance() {
        return ControlUsuariosHolder.INSTANCE;
    }

    private static class ControlUsuariosHolder {
        private static final ControlConexiones INSTANCE = new ControlConexiones();
    }

    // Registro de clientes conectados (Thread-safe)
    private static final ConcurrentHashMap<String, ClienteInfo> clientesConectados =
            new ConcurrentHashMap<>();

    // Registro de clientes conectados (Thread-safe)
    private static final List<Socket> nodoConectados =
            new ArrayList<>();

    public void registrarCliente(String id, ClienteInfo clienteInfo) {
        clientesConectados.put(id, clienteInfo);
    }

    public ClienteInfo eliminarCliente(String id) {
        return clientesConectados.remove(id);
    }

    public Map<String, ClienteInfo> getClientesConectados() {
        return new ConcurrentHashMap<>(clientesConectados);
    }

    public void registrarNodo(Socket socket) {
        nodoConectados.add(socket);
    }

    public void eliminarNodo(Socket socket) {
        nodoConectados.remove(socket);
    }

    public List<Socket> getNodoConectados() {
        return new ArrayList<>(nodoConectados);
    }

}
