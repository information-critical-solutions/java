package org.java.lecciones.libreriacomunicacion.error;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class ConexionException extends Exception {

    public ConexionException() {
    }

    public ConexionException(String message) {
        super(message);
    }

    public ConexionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConexionException(Throwable cause) {
        super(cause);
    }

}
