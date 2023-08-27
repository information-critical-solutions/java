package org.java.lecciones.leccion2;

/**
 * Clase que ejemplifica el uso básico de arrays en Java, incluyendo su
 * declaración, inicialización, acceso a elementos y recorrido a través de ellos
 * utilizando bucles for-each. Además, se demuestra cómo calcular la suma de los
 * elementos en un array de enteros.
 *
 * @author Sebastian Godinez Borja
 */
public class UsoDeArrays {

    public static void main(String[] args) {
        // Declaración e inicialización de un array de enteros
        int[] numeros = new int[5];
        numeros[0] = 10;
        numeros[1] = 20;
        numeros[2] = 30;
        numeros[3] = 40;
        numeros[4] = 50;

        // Acceso a elementos del array
        System.out.println("El segundo número es: " + numeros[1]); // Salida: El segundo número es: 20

        // Declaración e inicialización de un array de strings
        String[] nombres = {"Alice", "Bob", "Charlie", "David"};

        // Iterar a través de un array usando un bucle for y la longitud del arreglo
        System.out.println("Nombres en el array:");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i]);
        }

        // Iterar a través de un array usando un bucle for-each
        System.out.println("Nombres en el array:");
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        // Calcular la suma de los elementos en el array de enteros
        int suma = 0;
        for (int numero : numeros) {
            suma += numero;
        }
        System.out.println("La suma de los números es: " + suma); // Salida: La suma de los números es: 150
    }
}
