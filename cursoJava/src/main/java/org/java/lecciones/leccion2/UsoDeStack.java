package org.java.lecciones.leccion2;

import java.util.Stack;

/**
 * Clase que demuestra el uso básico de Stack en Java, incluyendo su
 * declaración, inicialización, agregar y retirar elementos, verificar si está
 * vacía y mostrar el contenido.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeStack {

    public static void main(String[] args) {
        // Crear una instancia de Stack
        Stack<String> pila = new Stack<>();

        // Agregar elementos a la pila
        pila.push("Elemento 1");
        pila.push("Elemento 2");
        pila.push("Elemento 3");

        // Mostrar el contenido de la pila
        System.out.println("Contenido de la pila: " + pila);

        // Retirar elementos de la pila
        String elemento = pila.pop(); // Retira el último elemento agregado
        System.out.println("Elemento retirado: " + elemento);

        // Mostrar el contenido actualizado de la pila
        System.out.println("Contenido de la pila después de retirar: " + pila);

        // Verificar si la pila está vacía
        System.out.println("¿La pila está vacía? " + pila.isEmpty()); // Debería ser false
    }
}
