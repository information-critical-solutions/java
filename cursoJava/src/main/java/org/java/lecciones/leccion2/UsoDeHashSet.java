package org.java.lecciones.leccion2;

import java.util.HashSet;

/**
 * Clase que demuestra el uso básico de HashSet en Java, incluyendo su
 * declaración, inicialización, agregar elementos (evitando duplicados),
 * verificar la existencia de elementos y eliminar elementos. Además, se muestra
 * cómo iterar a través del HashSet para mostrar su contenido.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeHashSet {

    public static void main(String[] args) {
        // Crear un HashSet de strings
        HashSet<String> nombres = new HashSet<>();

        // Agregar elementos al HashSet
        nombres.add("Alice");
        nombres.add("Bob");
        nombres.add("Charlie");
        nombres.add("David");
        nombres.add("Bob"); // No se agregarán duplicados

        // Mostrar el contenido del HashSet
        System.out.println("Nombres en el HashSet:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Verificar si un elemento está en el HashSet
        System.out.println("¿Alice está en el HashSet? " + nombres.contains("Alice")); // Salida: ¿Alice está en el HashSet? true

        // Eliminar un elemento del HashSet
        nombres.remove("Charlie");

        // Mostrar el contenido actualizado del HashSet
        System.out.println("Nombres en el HashSet después de eliminar a Charlie:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }
}
