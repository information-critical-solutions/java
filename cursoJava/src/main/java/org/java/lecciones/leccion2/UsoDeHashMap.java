package org.java.lecciones.leccion2;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que ejemplifica el uso básico de HashMap en Java, incluyendo su
 * declaración, inicialización, agregar, acceder y eliminar elementos, y
 * recorrido a través de las claves y valores utilizando bucles for-each.
 * Además, se muestra cómo verificar la existencia de una clave en el HashMap y
 * obtener su tamaño.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeHashMap {

    public static void main(String[] args) {
        // Declaración e inicialización de un HashMap de nombres y edades
        Map<String, Integer> edades = new HashMap<>();

        // Agregar elementos al HashMap
        edades.put("Alice", 25);
        edades.put("Bob", 30);
        edades.put("Charlie", 28);

        // Acceder a elementos del HashMap usando claves
        System.out.println("La edad de Bob es: " + edades.get("Bob")); // Salida: La edad de Bob es: 30

        // Verificar si una clave existe en el HashMap
        if (edades.containsKey("David")) {
            System.out.println("La edad de David es: " + edades.get("David"));
        } else {
            System.out.println("No se encontró la edad de David.");
        }

        // Iterar a través del HashMap usando un bucle for-each
        System.out.println("Nombres y edades en el HashMap:");
        for (String nombre : edades.keySet()) {
            int edad = edades.get(nombre);
            System.out.println(nombre + ": " + edad + " años");
        }
        
        
        // Iterar a través del HashMap usando un bucle for-each
        System.out.println("Nombres y edades en el HashMap:");
        for (String nombre : edades.keySet()) {
            edades.put(nombre, 0);
        }
        // Iterar a través del HashMap usando un bucle for-each
        System.out.println("Nombres y edades en el HashMap:");
        for (String nombre : edades.keySet()) {
            int edad = edades.get(nombre);
            System.out.println(nombre + ": " + edad + " años");
        }

        // Eliminar un elemento del HashMap
        edades.remove("Alice");

        // Tamaño del HashMap
        System.out.println("Tamaño del HashMap: " + edades.size()); // Salida: Tamaño del HashMap: 2
    }
}
