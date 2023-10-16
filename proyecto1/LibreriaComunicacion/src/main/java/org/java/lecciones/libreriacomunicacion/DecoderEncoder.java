package org.java.lecciones.libreriacomunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class DecoderEncoder {

    private static final Logger LOGGER = LogManager.getLogger(DecoderEncoder.class);

    public static Mensaje leer(DataInputStream dis) throws IOException {
        // ciclo de lectura
        // se lee el tipo de operacion
        Short tipoOperacion = dis.readShort();
        // se lee el tam del arreglo
        Short tam = dis.readShort();
        // leer el arreglo de datos
        byte[] datos = new byte[tam];
        dis.readFully(datos);
        // se imprime en pantalla
        String mensaje = new String(datos);

        Mensaje m = new Mensaje();
        m.setTipoOperacion(tipoOperacion);
        m.setDatos(datos);
//        LOGGER.info("Mensaje recibido: " + m);
        return m;
    }

    public static void escribir(DataOutputStream dos, Mensaje mensaje) throws IOException {
        // tam del arreglo
        Short tam = (short) mensaje.getDatos().length;
        // enviar el tipo de operacion
        dos.writeShort(mensaje.getTipoOperacion());
        // enviar el tam del mensaje
        dos.writeShort(tam);
        // enviar el mensaje en bytes
        dos.write(mensaje.getDatos());
//        LOGGER.info("Mensaje enviado: " + mensaje);
    }

}
