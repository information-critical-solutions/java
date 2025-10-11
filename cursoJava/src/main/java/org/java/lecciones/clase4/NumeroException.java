package org.java.lecciones.clase4;

/**
 * Clase de excepción personalizada que representa una situación de inventario
 * insuficiente. Esta excepción se lanza cuando no hay suficientes existencias
 * de un artículo en un inventario. Puede llevar un mensaje adicional para
 * proporcionar información sobre la situación.
 *
 * @author Sebastian Godinez Borja
 */
public class NumeroException extends Exception {

    public NumeroException() {
    }

    public NumeroException(String message) {
        super(message);
    }

    public NumeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumeroException(Throwable cause) {
        super(cause);
    }
}
