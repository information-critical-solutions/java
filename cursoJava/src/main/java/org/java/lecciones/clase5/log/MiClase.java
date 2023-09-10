package org.java.lecciones.clase5.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Esta es una clase de ejemplo que muestra cómo utilizar Log4j para registrar
 * mensajes. Log4j es una biblioteca de registro (logging) que permite registrar
 * mensajes con diferentes niveles de gravedad. En este ejemplo, se crea un
 * Logger específico para esta clase y se registran varios mensajes de ejemplo.
 *
 * @author Sebastian Godinez Borja
 */
public class MiClase {

    // Obtiene una instancia de Logger específica para esta clase
    private static final Logger logger = LogManager.getLogger(MiClase.class);

    public static void main(String[] args) {
        // Registra mensajes usando Log4j

        // Este es un mensaje de depuración (nivel de gravedad DEBUG)
        logger.debug("Este es un mensaje de depuración.");

        // Este es un mensaje informativo (nivel de gravedad INFO)
        logger.info("Este es un mensaje informativo.");

        // Este es un mensaje de advertencia (nivel de gravedad WARN)
        logger.warn("Este es un mensaje de advertencia.");

        // Este es un mensaje de error (nivel de gravedad ERROR)
        logger.error("Este es un mensaje de error.");

        // Este es un mensaje fatal (nivel de gravedad FATAL)
        logger.fatal("Este es un mensaje fatal.");
    }
}
