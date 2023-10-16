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
     * clientes conectados con el nodo
     */
    private static final List<Socket> CLIENTES_CONECTADOS
            = new ArrayList<>();

    /**
     * bloqueos
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
     * Agrega un cliente a la lista de los conocidos
     *
     * @param socket
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
     * Elimina un cliente de los conocidos
     *
     * @param socket
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
     * Devuelve una copia de la lista de los clientes conocidos
     *
     * @return
     */
    public List<Socket> getClientesConectados() {
        return new ArrayList<>(CLIENTES_CONECTADOS);
    }

}
