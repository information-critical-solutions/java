package org.java.lecciones.leccion2;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que demuestra el uso básico de TreeMap en Java, incluyendo su
 * declaración, inicialización, agregar elementos, acceder a valores, iterar a
 * través de claves y valores, y verificar la existencia de claves.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeTreeMap {

    public static void main(String[] args) {
        // Crear un TreeMap para almacenar nombres y edades
        Map<String, Integer> edades = new TreeMap<>((o1, o2) ->  o2.compareTo(o1));

        // Agregar elementos al TreeMap
        edades.put("David", 22);
        edades.put("Alice", 25);
        edades.put("Charlie", 28);
        edades.put("Bob", 30);

        // Acceder a elementos del TreeMap
        System.out.println("Edad de Bob: " + edades.get("Bob")); // Salida: Edad de Bob: 30

        // Iterar a través de las claves y valores del TreeMap
        System.out.println("Nombres y edades en el TreeMap:");
        for (String nombre : edades.keySet()) {
            int edad = edades.get(nombre);
            System.out.println(nombre + ": " + edad);
        }

        // Verificar si el TreeMap contiene una clave específica
        boolean contieneAlice = edades.containsKey("Alice");
        System.out.println("¿Contiene la llave Alice? " + contieneAlice); // Salida: ¿Contiene la clave Alice? true
    }
}
