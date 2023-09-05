package org.java.lecciones.clase4.ejercicio.productos;

import java.io.Serializable;

/**
 * Clase que representa un producto y es serializable.
 *
 * @author Sebastian Godinez Borja
 */
public class Producto implements Serializable {

    private String nombre;   // Nombre del producto
    private double precio;   // Precio del producto
    private int stock;       // Cantidad en stock del producto

    /**
     * Constructor para crear un objeto Producto.
     *
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param stock Cantidad en stock del producto.
     */
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad en stock del producto.
     *
     * @return Cantidad en stock del producto.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sobrescribe el método toString para representar el objeto Producto como
     * una cadena.
     *
     * @return Cadena que representa el objeto Producto.
     */
    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
    }
}
