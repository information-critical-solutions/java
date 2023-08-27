package org.java.lecciones.leccion2;

import java.util.TreeSet;

/**
 * Clase que demuestra el uso de TreeSet en Java para almacenar elementos en
 * orden ascendente o descendente. También muestra cómo verificar si un elemento
 * existe en el TreeSet.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeTreeSet {

    public static void main(String[] args) {
        // Crear un TreeSet de números enteros en orden ascendente
        TreeSet<Integer> numerosAscendentes = new TreeSet<>();
        numerosAscendentes.add(5);
        numerosAscendentes.add(2);
        numerosAscendentes.add(8);
        numerosAscendentes.add(1);
        numerosAscendentes.add(3);

        System.out.println("Números en orden ascendente: " + numerosAscendentes); // Salida: Números en orden ascendente: [1, 2, 3, 5, 8]

        // Crear un TreeSet de strings en orden descendente
        TreeSet<String> palabrasDescendentes = new TreeSet<>((s1, s2) -> s2.compareTo(s1));
        palabrasDescendentes.add("manzana");
        palabrasDescendentes.add("banana");
        palabrasDescendentes.add("pera");
        palabrasDescendentes.add("uva");

        System.out.println("Palabras en orden descendente: " + palabrasDescendentes); // Salida: Palabras en orden descendente: [uva, pera, manzana, banana]

        // Verificar si un elemento existe en el TreeSet
        boolean existeManzana = palabrasDescendentes.contains("manzana");
        System.out.println("¿Existe 'manzana' en el TreeSet? " + existeManzana); // Salida: ¿Existe 'manzana' en el TreeSet? true
    }
}
