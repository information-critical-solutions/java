package org.java.lecciones.clase4.ejercicio.productos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que demuestra la serialización y deserialización de una lista
 * de productos. Esta clase contiene el método principal.
 *
 * @author Sebastian Godinez Borja
 */
public class Main {

    public static void main(String[] args) {
        // Crear algunos productos
        Producto producto1 = new Producto("Laptop", 999.99, 10);
        Producto producto2 = new Producto("Teléfono", 299.99, 50);
        Producto producto3 = new Producto("Tablet", 199.99, 30);

        // Crear una lista de productos
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto1);
        listaProductos.add(producto2);
        listaProductos.add(producto3);

        // Serializar la lista de productos y guardarla en un archivo
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.ser"))) {
            out.writeObject(listaProductos);
            System.out.println("Lista de productos serializada y guardada en productos.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializar la lista de productos desde el archivo
        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.ser"))) {
            List<Producto> listaDeserializada = (List<Producto>) in.readObject();
            System.out.println("Lista de productos deserializada desde productos.ser:");
            for (Producto producto : listaDeserializada) {
                System.out.println(producto);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
