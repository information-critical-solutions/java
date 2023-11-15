package org.java.lecciones.libreriacomunicacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sebastian Godinez Borja
 */
public class DecoderEncoder {

    private static final Logger LOGGER = LogManager.getLogger(DecoderEncoder.class);

    private static final Map<Socket, DataInputStream> isMap = new ConcurrentHashMap<>();
    private static final Map<Socket, DataOutputStream> osMap = new ConcurrentHashMap<>();

    public static synchronized Mensaje leer(Socket socket) throws IOException {
        DataInputStream dis = isMap.getOrDefault(socket, new DataInputStream(socket.getInputStream()));
        isMap.put(socket, dis);
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
        m.setTipoOperacion(TipoOperacion.valueOf(tipoOperacion));
        m.setDatos(datos);
//        LOGGER.info("Mensaje recibido: " + m);
        return m;
    }

    public static synchronized void escribir(Socket socket, Mensaje mensaje) throws IOException {
        DataOutputStream dos = osMap.getOrDefault(socket, new DataOutputStream(socket.getOutputStream()));
        osMap.put(socket, dos);
        // tam del arreglo
        Short tam = (short) mensaje.getDatos().length;
        // enviar el tipo de operacion
        dos.writeShort(mensaje.getTipoOperacion().getTipo());
        // enviar el tam del mensaje
        dos.writeShort(tam);
        // enviar el mensaje en bytes
        dos.write(mensaje.getDatos());
//        LOGGER.info("Mensaje enviado: " + mensaje);
    }

    public static void removeSocket(Socket socket) {
        isMap.remove(socket);
        osMap.remove(socket);
    }

}
