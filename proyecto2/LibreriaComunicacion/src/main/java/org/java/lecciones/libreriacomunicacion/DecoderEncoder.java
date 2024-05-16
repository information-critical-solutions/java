package org.java.lecciones.libreriacomunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class DecoderEncoder {

    private static final Logger LOGGER = LogManager.getLogger(DecoderEncoder.class);

    public static Mensaje leer(DataInputStream dis) throws IOException {
        // se lee el tipo de operacion
        Short tipoOperacion = dis.readShort();
        // se lee el tam del arreglo
        Integer tam = dis.readInt();
        // leer el arreglo de datos
        byte[] datos = new byte[tam];
        dis.readFully(datos);
        Mensaje m = new Mensaje();
        m.setTipoOperacion(tipoOperacion);
        m.setDatos(datos);
        return m;
    }

    public static void escribir(DataOutputStream dos, Mensaje mensaje) throws IOException {
        // tam del arreglo
        Integer tam = mensaje.getDatos().length;
        // enviar el tipo de operacion
        dos.writeShort(mensaje.getTipoOperacion());
        // enviar el tam del mensaje
        dos.writeInt(tam);
        // enviar el mensaje en bytes
        dos.write(mensaje.getDatos());
    }

    public static Mensaje leer(Socket socket) throws IOException {
        // se lee el tipo de operacion
        return leer(new DataInputStream(socket.getInputStream()));
    }

    public static void escribir(Socket socket, Mensaje mensaje) throws IOException {
        escribir(new DataOutputStream(socket.getOutputStream()), mensaje);
    }

}
