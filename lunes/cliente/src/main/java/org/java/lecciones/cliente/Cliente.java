package org.java.lecciones.cliente;

import java.io.DataInputStream;
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
public class Cliente {

    /**
     * bitacora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(Cliente.class);
    /**
     * servidor al que se conecta el cliente
     */
    private static final String HOST = "localhost";
    /**
     * puerto que utiliza el nodo
     */
    private static final Integer PUERTO = 12345;

    private static EscritorMensajes escritorMensajes;

    /**
     * metodo de entrada del programa
     *
     * @param args arreglo de strings de entrada
     */
    public static void main(String[] args) {
        LOGGER.info("Iniciando el cliente!");
        try {
            Socket socket = new Socket(HOST, PUERTO);
            LOGGER.info("Cliente conectado con (" + HOST + ":" + PUERTO + ")");

            // hilo que envia informacion
            escritorMensajes = new EscritorMensajes(socket);

//            new Thread(() -> {
//                try  {
//                    Integer cuantos = 0;
//                    while (true) {
//                        String m = "2,2";
//                        Mensaje mensaje = new Mensaje();
//                        mensaje.setTipoOperacion((short) 1);
//                        mensaje.setDatos(m.getBytes());
//                        escritorMensajes.enviarMensaje(mensaje);
//                        Thread.sleep(Duration.ofSeconds(3l));
//                    }
//                } catch (IOException | InterruptedException ex) {
//                    LOGGER.info("Cliente desconectado");
//                }
//            }).start();
            // hilo que recibe los mensajes
            new Thread(() -> {
                try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
                    while (true) {
                        Mensaje mensajeRecibido = DecoderEncoder.leer(dis);
                        LOGGER.info("mensaje recibido: " + mensajeRecibido);
                    }
                } catch (IOException ex) {
                    LOGGER.info("Cliente desconectado");
                }
            }).start();

        } catch (IOException iOException) {
            LOGGER.error("Algo paso con el nodo", iOException);
        }

    }

    public static EscritorMensajes getEscritorMensajes() {
        return escritorMensajes;
    }

}
