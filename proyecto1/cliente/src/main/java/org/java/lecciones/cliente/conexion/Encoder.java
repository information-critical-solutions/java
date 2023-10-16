package org.java.lecciones.cliente.conexion;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 * Clase que maneja la decodificación de mensajes y ejecuta acciones basadas en
 * el tipo de operación del mensaje.
 *
 * @author Sebastian Godinez Borja
 */
public class Encoder implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Encoder.class);
    private final DataInputStream dis;
    private final Map<Short, ProcesaMensaje> acciones = new ConcurrentHashMap<>();

    /**
     * Constructor de la clase Encoder.
     *
     * @param dis El flujo de entrada de datos.
     */
    public Encoder(DataInputStream dis) {
        this.dis = dis;
    }

    /**
     * Inicia la ejecución del hilo para procesar mensajes.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Mensaje mensajeRecibido = DecoderEncoder.leer(dis);
                ProcesaMensaje procesaMensaje = acciones.getOrDefault(mensajeRecibido.getTipoOperacion(), (m) -> {
                    LOGGER.info("Mensaje recibido: " + m);
                });
                procesaMensaje.procesa(mensajeRecibido);
            }
        } catch (IOException ex) {
            LOGGER.error("Error al leer los mensajes", ex);
        }
    }

    /**
     * Inserta una acción para procesar un mensaje en función de su tipo de
     * operación.
     *
     * @param tipoOperacion El tipo de operación del mensaje.
     * @param pm El objeto ProcesaMensaje que manejará la operación.
     */
    public void insertaAccionProcesaMensaje(Short tipoOperacion, ProcesaMensaje pm) {
        acciones.put(tipoOperacion, pm);
    }
}
