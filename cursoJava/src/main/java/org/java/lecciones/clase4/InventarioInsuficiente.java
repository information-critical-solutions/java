package org.java.lecciones.clase4;

/**
 * Clase de excepción personalizada que representa una situación de inventario
 * insuficiente. Esta excepción se lanza cuando no hay suficientes existencias
 * de un artículo en un inventario. Puede llevar un mensaje adicional para
 * proporcionar información sobre la situación.
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
