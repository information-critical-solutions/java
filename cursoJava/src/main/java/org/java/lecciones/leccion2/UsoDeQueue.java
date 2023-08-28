package org.java.lecciones.leccion2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeQueue {

    public static void main(String[] args) {
        // Crear una cola utilizando LinkedList
        Queue<String> cola = new LinkedList<>();

        // Agregar elementos a la cola
        cola.offer("Manzana");
        cola.offer("Banana");
        cola.offer("Naranja");

        System.out.println("Elementos en la cola: " + cola); // Salida: Elementos en la cola: [Manzana, Banana, Naranja]

        // Acceder y eliminar el primer elemento de la cola
        String primero = cola.poll();
        System.out.println("Elemento eliminado: " + primero); // Salida: Elemento eliminado: Manzana

        System.out.println("Elementos restantes en la cola: " + cola); // Salida: Elementos restantes en la cola: [Banana, Naranja]

        // Acceder al primer elemento sin eliminarlo
        String primerElemento = cola.peek();
        System.out.println("Primer elemento en la cola: " + primerElemento); // Salida: Primer elemento en la cola: Banana
    }
}
