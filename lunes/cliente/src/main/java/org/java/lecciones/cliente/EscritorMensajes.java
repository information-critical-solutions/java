package org.java.lecciones.cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class EscritorMensajes {

    private static final Logger LOGGER = LogManager.getLogger(EscritorMensajes.class);
    private final DataOutputStream dos;

    public EscritorMensajes(Socket socket) throws IOException {
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    public void enviarMensaje(Mensaje mensaje) throws IOException {
        DecoderEncoder.escribir(dos, mensaje);
    }

}
