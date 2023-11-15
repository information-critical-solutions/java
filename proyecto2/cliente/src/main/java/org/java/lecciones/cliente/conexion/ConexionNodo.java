package org.java.lecciones.cliente.conexion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

import java.io.IOException;
import java.net.Socket;

/**
 * Clase que representa una conexión a un nodo remoto. Permite establecer una
 * conexión a través de un socket y enviar mensajes.
 *
 * @author Sebastian Godinez Borja
 */
public class ConexionNodo {

    /**
     * Bitácora de la clase.
     */
    private static final Logger LOGGER = LogManager.getLogger(ConexionNodo.class);

    /**
     * Servidor al que se conecta el cliente.
     */
    private final String HOST;

    /**
     * Puerto que utiliza el nodo.
     */
    private final Integer PUERTO;

    /**
     * Socket de conexión.
     */
    private Socket socket;

    /**
     * Hilo que maneja la codificación y decodificación de mensajes.
     */
    private Thread hiloEncoder;

    /**
     * Instancia del encoder utilizado para procesar los mensajes.
     */
    private Encoder encoder;

    /**
     * Constructor de la clase ConexionNodo.
     *
     * @param HOST La dirección del servidor al que se conectará el cliente.
     * @param PUERTO El puerto que utilizará la conexión.
     */
    public ConexionNodo(String HOST, Integer PUERTO) {
        this.HOST = HOST;
        this.PUERTO = PUERTO;
    }

    /**
     * Establece una conexión con el servidor especificado.
     */
    public void conectar() {
        try {
            socket = new Socket(HOST, PUERTO);
            encoder = new Encoder(socket);
            hiloEncoder = new Thread(encoder, "ENCODER");
            hiloEncoder.start();
            LOGGER.info("Cliente conectado con (" + HOST + ":" + PUERTO + ")");
        } catch (IOException iOException) {
            LOGGER.error("Algo pasó con el nodo", iOException);
        }
    }

    /**
     * Envía un mensaje al servidor.
     *
     * @param mensaje El mensaje a enviar.
     * @throws IOException Si ocurre un error de E/S durante el envío.
     */
    public void enviarMensaje(Mensaje mensaje) throws IOException {
        DecoderEncoder.escribir(socket, mensaje);
    }

    /**
     * Inserta una acción para procesar un mensaje en el encoder.
     *
     * @param tipoOperacion El tipo de operación que se procesará.
     * @param pm El objeto ProcesaMensaje que manejará la operación.
     */
    public void insertaAccionProcesaMensaje(Short tipoOperacion, ProcesaMensaje pm) {
        encoder.insertaAccionProcesaMensaje(tipoOperacion, pm);
    }
}
