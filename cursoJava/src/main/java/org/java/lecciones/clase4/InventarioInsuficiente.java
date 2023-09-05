package org.java.lecciones.clase4;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class InventarioInsuficiente extends Exception {

    public InventarioInsuficiente() {
    }

    public InventarioInsuficiente(String message) {
        super(message);
    }

    public InventarioInsuficiente(String message, Throwable cause) {
        super(message, cause);
    }

    public InventarioInsuficiente(Throwable cause) {
        super(cause);
    }

}
