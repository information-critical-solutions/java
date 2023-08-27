package org.java.lecciones.leccion2.ejercicio;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Clase que permite al usuario ingresar números enteros y muestra los números
 * primos ingresados. Utiliza un HashSet para almacenar los números primos
 * únicos ingresados. También incluye una función para verificar si un número es
 * primo.
 *
 * @author Sebastian Godinez Borja
 */
public class NumerosPrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Integer> numerosPrimos = new HashSet<>();

        System.out.println("Ingrese números enteros (ingrese -1 para detenerse):");

        while (true) {
            int numero = scanner.nextInt();
            if (numero == -1) {
                break;
            }

            if (esPrimo(numero)) {
                numerosPrimos.add(numero);
            }
        }

        System.out.println("Números primos ingresados:");
        for (int primo : numerosPrimos) {
            System.out.println(primo);
        }

        scanner.close();
    }

    // Función para verificar si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
