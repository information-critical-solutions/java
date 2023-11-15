package org.java.lecciones.cliente.conexion;

import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 * Interfaz para definir el procesamiento de mensajes. Implemente esta interfaz
 * para manejar mensajes de acuerdo a sus necesidades.
 *
 * @author Sebastian Godinez Borja
 */
public interface ProcesaMensaje {

    /**
     * Procesa un mensaje especificado.
     *
     * @param m El mensaje a procesar.
     */
    void procesa(Mensaje m);
}
