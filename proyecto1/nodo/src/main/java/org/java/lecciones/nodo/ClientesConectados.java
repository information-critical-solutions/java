package org.java.lecciones.nodo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase que gestiona la lista de clientes conectados al nodo. Permite agregar,
 * eliminar y obtener información sobre los clientes.
 *
 * Esta clase utiliza bloqueos para garantizar la seguridad en hilos múltiples.
 *
 * @author Sebastian Godinez Borja
 */
public class ClientesConectados {

    /**
     * Lista de clientes conectados con el nodo.
     */
    private static final List<Socket> CLIENTES_CONECTADOS = new ArrayList<>();

    /**
     * Bloqueos para garantizar la seguridad en hilos múltiples.
     */
    private static final Lock LOCK = new ReentrantLock();

    private ClientesConectados() {
    }

    /**
     * Obtiene una instancia única de la clase ClientesConectados.
     *
     * @return Una instancia de ClientesConectados.
     */
    public static ClientesConectados getInstance() {
        return ClientesConectadosHolder.INSTANCE;
    }

    private static class ClientesConectadosHolder {

        private static final ClientesConectados INSTANCE = new ClientesConectados();
    }

    /**
     * Agrega un cliente a la lista de clientes conocidos.
     *
     * @param socket El socket del cliente a agregar.
     */
    public void addClienteConectado(Socket socket) {
        LOCK.lock();
        try {
            CLIENTES_CONECTADOS.add(socket);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Elimina un cliente de la lista de clientes conocidos.
     *
     * @param socket El socket del cliente a eliminar.
     * @return `true` si el cliente se eliminó con éxito, `false` si no se
     * encontró.
     */
    public Boolean removeClienteConectado(Socket socket) {
        LOCK.lock();
        try {
            return CLIENTES_CONECTADOS.remove(socket);
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
        return new ArrayList<>(CLIENTES_CONECTADOS);
    }
}
