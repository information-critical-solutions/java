package org.java.lecciones.nodo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class ClientesConectados {

    /**
     * clientes conocidos por el nodo
     */
    private static final List<Socket> clientesConectados = new ArrayList<>();

    /**
     * bloqueo
     */
    private static final Lock LOCK = new ReentrantLock();

    private ClientesConectados() {
    }

    public static ClientesConectados getInstance() {
        return ClientesConectadosHolder.INSTANCE;
    }

    private static class ClientesConectadosHolder {

        private static final ClientesConectados INSTANCE = new ClientesConectados();
    }

    /**
     * Agrega un cliente a los conocidos
     *
     * @param socket
     */
    public void addCliente(Socket socket) {
        LOCK.lock();
        try {
            clientesConectados.add(socket);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Elimina un cliente de los conocidos
     *
     * @param socket
     */
    public void removeCliente(Socket socket) {
        LOCK.lock();
        try {
            clientesConectados.remove(socket);
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * Devuelve una copia de los clientes conocidos
     *
     * @return
     */
    public List<Socket> getClientesConectados() {
        return new ArrayList<>(clientesConectados);
    }

}
