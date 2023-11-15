package org.java.lecciones.nodo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;
import org.java.lecciones.libreriacomunicacion.TipoConexion;
import org.java.lecciones.libreriacomunicacion.TipoOperacion;

import java.net.Socket;
import java.util.Objects;

/**
 * @author Sebastian Godinez Borja
 */
public class Encoder implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Encoder.class);

    private final Socket socket;

    public Encoder(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Recibir la información y enviarla a todos los demás clientes
        try {
            while (true) {
                Mensaje mensaje = DecoderEncoder.leer(socket);
                LOGGER.info("Se recibió el mensaje: " + mensaje + ", del cliente: " + socket);
                // Aquí se envía a todos los clientes
                // si es del tipo se agrega en una lista especifica
                if (Objects.requireNonNull(mensaje.getTipoOperacion()) == TipoOperacion.TIPO) {
                    TipoConexion tipoConexion = TipoConexion.valueOf(new String(mensaje.getDatos()));
                    if (tipoConexion.compareTo(TipoConexion.NODO) == 0) {
                        Conexiones.getInstance().addNodoConectado(socket);
                    } else {
                        Conexiones.getInstance().addClienteConectado(socket);
                    }
                } else {// si no es nodo se envia a otros nodos
                    if (!Conexiones.getInstance().esNodo(socket)) {
                        for (Socket socketEnTurno : Conexiones.getInstance().getNodosConectados()) {
                            LOGGER.info("Se reenvía el mensaje al socket-nodo: " + socketEnTurno);
                            DecoderEncoder.escribir(socketEnTurno, mensaje);
                        }
                    }
                    // se envia a todos los clientes
                    for (Socket socketEnTurno : Conexiones.getInstance().getClientesConectados()) {
                        LOGGER.info("Se reenvía el mensaje al socket-cliente: " + socketEnTurno);
                        DecoderEncoder.escribir(socketEnTurno, mensaje);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.warn("Cliente desconectado", ex);
            Conexiones.getInstance().removeConexion(socket);
            DecoderEncoder.removeSocket(socket);
            LOGGER.warn("Ahora quedan " + Conexiones.getInstance().getClientesConectados().size()
                    + " clientes y " + Conexiones.getInstance().getNodosConectados().size() + " nodos");
        }
    }

}
