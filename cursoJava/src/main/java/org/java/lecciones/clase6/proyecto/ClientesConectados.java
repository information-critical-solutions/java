package org.java.lecciones.clase6.proyecto;

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

    // Lista para almacenar las conexiones de los clientes
    private static final List<Socket> CLIENTES_CONECTADOS = new ArrayList<>();
    // bloqueo 
    private static final Lock lock = new ReentrantLock();

    private ClientesConectados() {
    }

    public static ClientesConectados getInstance() {
        return ClientesConectadosHolder.INSTANCE;
    }

    private static class ClientesConectadosHolder {

        private static final ClientesConectados INSTANCE = new ClientesConectados();
    }

    public void addClienteConectado(Socket socket) {
        lock.lock();
        try {
            CLIENTES_CONECTADOS.add(socket);
        } finally {
            lock.unlock();
        }
    }

    public void removeClienteConectado(Socket socket) {
        lock.lock();
        try {
            CLIENTES_CONECTADOS.remove(socket);
        } finally {
            lock.unlock();
        }
    }

    public List<Socket> getClientesConectados() {
        return new ArrayList<>(CLIENTES_CONECTADOS);
    }
}
