package org.java.lecciones.nodo;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 *
 * @author sebastian
 */
public class DespachadorMensajes implements Runnable {

    // bitacora de la clase
    private static final Logger LOGGER = LogManager.getLogger(DespachadorMensajes.class);
    // socket que se gestionara
    private final Socket socket;

    public DespachadorMensajes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // recibir la informacion y enviarla a todos los demas clientes
        try {
            // se envia el mensaje de identicacion de nodo
            Mensaje m = new Mensaje();
            m.setTipoOperacion((short) 0);
            m.setDatos("N".getBytes());
            DecoderEncoder.escribir(socket, m);
            while (true) {
                Mensaje mensaje = DecoderEncoder.leer(socket);
                LOGGER.info("se recibio el mensaje: " + mensaje + ", del cliente: " + socket);
                // se procesa el mensaje
                switch (mensaje.getTipoOperacion()) {
                    case (short) 0: // mensaje de identificacion
                        String indicador = new String(mensaje.getDatos());
                        if (indicador.compareTo("N") == 0) {
                            Conexiones.getInstance().addNodo(socket);
                        } else {
                            Conexiones.getInstance().addCliente(socket);
                        }
                        break;
                    case (short) 1, (short) 2: // mensaje de negocio
                        // si de un cliente se envia a todos los nodos
                        if (!Conexiones.getInstance().isNodo(socket)) {
                            for (Socket nodosConectado : Conexiones.getInstance().getNodosConectados()) {
                                DecoderEncoder.escribir(nodosConectado, mensaje);
                            }
                        }
                        // siempre se distribuye a los clientes
                        for (Socket clienteConectado : Conexiones.getInstance().getClientesConectados()) {
                            DecoderEncoder.escribir(clienteConectado, mensaje);
                        }
                        break;
                    default: // mensaje desconocido
                        LOGGER.warn("Mensaje no implementado en el protocolo");
                }
            }
        } catch (IOException ex) {
            LOGGER.warn("Cliente desconectado", ex);
            // se saca de los clientes conectados
            Conexiones.getInstance().remove(socket);
            LOGGER.info("La conexion: " + socket + ", se fue");
            List<Socket> clientesConectados = Conexiones.getInstance().getClientesConectados();
            LOGGER.info("Ahora quedan: " + clientesConectados.size() + " clientes");
            clientesConectados.forEach(LOGGER::info);
            List<Socket> nodosConectados = Conexiones.getInstance().getNodosConectados();
            LOGGER.info("Ahora quedan: " + nodosConectados.size() + " nodos");
            nodosConectados.forEach(LOGGER::info);
        }
    }

}
