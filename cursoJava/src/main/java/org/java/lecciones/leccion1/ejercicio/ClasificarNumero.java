package org.java.lecciones.leccion1.ejercicio;

import java.util.Scanner;

/**
 * Este programa determina si un número ingresado por el usuario es positivo,
 * negativo o cero. Imprime un mensaje correspondiente basado en la verificación
 * del número.
 *
 * @author Sebastian Godinez Borja
 */
public class ClasificarNumero {

    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese un número
        System.out.print("Ingrese un número: ");
        double numero = scanner.nextDouble();

        // Cerrar el objeto Scanner después de usarlo
        scanner.close();

        // Verificar si el número es positivo, negativo o cero
        if (numero > 0) {
            System.out.println("El número ingresado es positivo.");
        } else if (numero < 0) {
            System.out.println("El número ingresado es negativo.");
        } else {
            System.out.println("El número ingresado es cero.");
        }
    }
}
