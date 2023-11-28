package org.java.lecciones.cliente.conexion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java.lecciones.libreriacomunicacion.DecoderEncoder;
import org.java.lecciones.libreriacomunicacion.Mensaje;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.java.lecciones.cliente.config.Configuracion;

/**
 * Clase que maneja la decodificación de mensajes y ejecuta acciones basadas en
 * el tipo de operación del mensaje.
 *
 * @author Sebastian Godinez Borja
 */
public class Encoder implements Runnable {
    
    private static final Logger LOGGER = LogManager.getLogger(Encoder.class);
    private final Socket socket;
    private final Map<Short, ProcesaMensaje> acciones = new ConcurrentHashMap<>();

    /**
     * Constructor de la clase Encoder.
     *
     * @param socket
     */
    public Encoder(Socket socket) throws IOException {
        this.socket = socket;
    }

    /**
     * Inicia la ejecución del hilo para procesar mensajes.
     */
    @Override
    public void run() {
        while (true) {
            Mensaje mensajeRecibido;
            try {
                mensajeRecibido = DecoderEncoder.leer(socket);
                if(mensajeRecibido.getTipoOperacion().getTipo().compareTo(Configuracion.getInstance().getTipoOperacion()) != 0){
                    continue;
                }
                
                // se forma el mensaje en la cola de entrada
                ProcesaMensaje procesaMensaje = acciones.getOrDefault(mensajeRecibido.getTipoOperacion(), (m) -> {
                    LOGGER.info("Mensaje recibido: " + m);
                });
                procesaMensaje.procesa(mensajeRecibido);
            } catch (IOException ex) {
                LOGGER.error("Al procesar un mensaje de llegada", ex);
            }
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
