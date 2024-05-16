package org.java.lecciones.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
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
    // puertos de inicio y fin 
    private static final Integer PUERTO_INICIAL = 12345;
    private static final Integer PUERTO_FINAL = 12349;
    // para los numeros random
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static Socket socket;

    private static void establecerConexion() throws Exception {
        for (int i = PUERTO_INICIAL; i <= PUERTO_FINAL; i++) {
            try {
                socket = new Socket("localhost", i);
                LOGGER.info("Conexion establecida con el nodo en el puerto: " + i);
                // se envia un mensaje de identificacion de cliente
                Mensaje mensaje = new Mensaje();
                mensaje.setTipoOperacion((short) 0);
                mensaje.setDatos("C".getBytes());
                DecoderEncoder.escribir(socket, mensaje);
                return;
            } catch (IOException iOException) {
                LOGGER.warn("El puerto: " + i + " esta ocupado");
            }
        }
        throw new Exception("Ningun nodo disponible");
    }

    /**
     * metodo de entrada del programa
     *
     * @param args arreglo de strings de entrada
     */
    public static void main(String[] args) throws Exception {
        // se estabelce la conexion
        establecerConexion();
        // manda un mensaje cada X segundos
        new Thread(() -> {
            try {
                do {
                    Mensaje mensaje = new Mensaje();
                    mensaje.setTipoOperacion((short) 1);

                    mensaje.setDatos((RANDOM.nextInt(100) + ":" + RANDOM.nextInt(100)).getBytes());
                    DecoderEncoder.escribir(socket, mensaje);
                    Thread.sleep(10000L);
                } while (true);
            } catch (IOException | InterruptedException iOException) {
                LOGGER.error("Durmiendo o enviando un mensaje", iOException);
            }
        }).start();
        // hilo que recibe los mensajes
        new Thread(() -> {
            try {
                while (true) {
                    Mensaje mensaje = DecoderEncoder.leer(socket);
                    LOGGER.info("se recibio el mensaje: " + mensaje + ", del cliente: " + socket);
                    // se procesa el mensaje
                    switch (mensaje.getTipoOperacion()) {
                        case (short) 1: // solicitud de suma
                            // si de un cliente se envia a todos los nodos
                            String datos = new String(mensaje.getDatos());
                            String[] split = datos.split(":");
                            Integer a = Integer.valueOf(split[0]);
                            Integer b = Integer.valueOf(split[1]);
                            Integer resultado = a + b;
                            Mensaje respuestaMensaje = new Mensaje();
                            respuestaMensaje.setTipoOperacion((short) 2);
                            respuestaMensaje.setDatos(resultado.toString().getBytes());
                            DecoderEncoder.escribir(socket, respuestaMensaje);
                            break;
                        case (short) 2: // resultado
                            // si de un cliente se envia a todos los nodos
                            LOGGER.info("Resultado: " + new String(mensaje.getDatos()));
                            break;
                        default: // mensaje desconocido
                            LOGGER.warn("Mensaje no implementado en el protocolo");
                    }
                }
            } catch (IOException ex) {
                LOGGER.info("Cliente desconectado");
            }
        }).start();

    }

}
