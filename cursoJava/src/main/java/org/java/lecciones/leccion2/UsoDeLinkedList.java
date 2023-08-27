package org.java.lecciones.leccion2;

import java.util.LinkedList;

/**
 * Clase que demuestra el uso básico de LinkedList en Java, incluyendo su
 * declaración, inicialización, agregar elementos al principio y final, eliminar
 * elementos del principio y final, y mostrar el contenido.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeLinkedList {

    public static void main(String[] args) {
        // Crear una LinkedList de strings
        LinkedList<String> nombres = new LinkedList<>();

        // Agregar elementos a la lista
        nombres.add("Alice");
        nombres.add("Bob");
        nombres.add("Charlie");
        nombres.add("David");

        // Mostrar la lista completa
        System.out.println("Nombres en la lista:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Agregar un elemento al inicio de la lista
        nombres.addFirst("Eve");

        // Agregar un elemento al final de la lista
        nombres.addLast("Frank");

        // Mostrar la lista después de las adiciones
        System.out.println("Nombres después de las adiciones:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Eliminar el primer elemento de la lista
        nombres.removeFirst();

        // Eliminar el último elemento de la lista
        nombres.removeLast();

        // Mostrar la lista después de las eliminaciones
        System.out.println("Nombres después de las eliminaciones:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}
