package org.java.lecciones.leccion2;

import java.util.ArrayList;

/**
 * Clase que demuestra el uso básico de la clase ArrayList en Java. Se crean,
 * agregan, modifican, eliminan y recorren elementos en un ArrayList. Además, se
 * muestra cómo obtener el tamaño del ArrayList.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeArrayList {

    public static void main(String[] args) {
        // Crear un ArrayList de enteros
        ArrayList<Integer> numeros = new ArrayList<>();

        // Agregar elementos al ArrayList
        numeros.add(10);
        numeros.add(20);
        numeros.add(30);
        numeros.add(40);
        numeros.add(50);

        // Acceso a elementos por índice
        System.out.println("El tercer número es: " + numeros.get(2)); // Salida: El tercer número es: 30

        // Modificar un elemento
        numeros.set(0, 5); // Cambiar el primer elemento de 10 a 5

        // Eliminar un elemento
        numeros.remove(3); // Eliminar el elemento en la posición 3

        // Tamaño del ArrayList
        System.out.println("Tamaño del ArrayList: " + numeros.size()); // Salida: Tamaño del ArrayList: 4

        // Iterar a través del ArrayList
        System.out.println("Números en el ArrayList:");
        for (int numero : numeros) {
            System.out.println(numero);
        }
    }
}
