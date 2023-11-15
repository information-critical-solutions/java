package org.java.lecciones.nodo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que gestiona la lista de clientes conectados al nodo. Permite agregar,
 * eliminar y obtener información sobre los clientes.
 * <p>
 * Esta clase utiliza bloqueos para garantizar la seguridad en hilos múltiples.
 *
 * @author Sebastian Godinez Borja
 */
public class Conexiones {

    /**
     * Lista de clientes conectados con el nodo.
     */
    private static final List<Socket> CLIENTES = new ArrayList<>();
    /**
     * Lista de nodos conectados con el nodo.
     */
    private static final List<Socket> NODOS = new ArrayList<>();

    /**
     * Bloqueos para garantizar la seguridad en hilos múltiples.
     */
    private static final Lock LOCK = new ReentrantLock();

    private Conexiones() {
    }

    /**
     * Obtiene una instancia única de la clase Conexiones.
     *
     * @return Una instancia de Conexiones.
     */
    public static Conexiones getInstance() {
        return ClientesConectadosHolder.INSTANCE;
    }

    /**
     * Agrega un cliente a la lista de clientes conocidos.
     *
     * @param socket El socket del cliente a agregar.
     */
    public void addClienteConectado(Socket socket) {
        LOCK.lock();
        try {
            CLIENTES.add(socket);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Elimina un cliente de la lista de clientes conocidos.
     *
     * @param socket El socket del cliente a eliminar.
     */
    public void removeConexion(Socket socket) {
        LOCK.lock();
        try {
            if (!CLIENTES.remove(socket)) {
                NODOS.remove(socket);
            }
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Devuelve una copia de la lista de los clientes conocidos.
     *
     * @return Una lista que contiene copias de los sockets de los clientes
     * conocidos.
     */
    public List<Socket> getClientesConectados() {
        return Collections.unmodifiableList(CLIENTES);
    }

    /**
     * Agrega un nodo.
     *
     * @param socket El socket del cliente a agregar.
     */
    public void addNodoConectado(Socket socket) {
        LOCK.lock();
        try {
            NODOS.add(socket);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Devuelve una copia de la lista de los clientes conocidos.
     *
     * @return Una lista que contiene copias de los sockets de los clientes
     * conocidos.
     */
    public List<Socket> getNodosConectados() {
        return Collections.unmodifiableList(NODOS);
    }

    /**
     * Determina si es un nodo
     */
    public Boolean esNodo(Socket socket) {
        return NODOS.contains(socket);
    }

    private static class ClientesConectadosHolder {

        private static final Conexiones INSTANCE = new Conexiones();
    }
}
