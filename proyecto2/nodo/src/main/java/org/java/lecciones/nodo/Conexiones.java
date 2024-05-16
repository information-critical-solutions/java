package org.java.lecciones.nodo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Conexiones {

    // socket a otros nodos
    private static final List<Socket> NODOS = new ArrayList<>();
    // socket a los clientes
    private static final List<Socket> CLIENTES = new ArrayList<>();

    private Conexiones() {
    }

    public static synchronized Conexiones getInstance() {
        return ClientesConectadosHolder.INSTANCE;
    }

    private static class ClientesConectadosHolder {

        private static final Conexiones INSTANCE = new Conexiones();
    }

    public Boolean isNodo(Socket socket) {
        return NODOS.contains(socket);
    }

    public void addNodo(Socket socket) {
        NODOS.add(socket);
    }

    public List<Socket> getNodosConectados() {
        return new ArrayList<>(NODOS);
    }

    public void addCliente(Socket socket) {
        CLIENTES.add(socket);
    }

    public void remove(Socket socket) {
        CLIENTES.remove(socket);
        NODOS.remove(socket);
    }

    public List<Socket> getClientesConectados() {
        return new ArrayList<>(CLIENTES);
    }

}
