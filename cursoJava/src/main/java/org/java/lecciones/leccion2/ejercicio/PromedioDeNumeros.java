package org.java.lecciones.leccion2.ejercicio;

import java.util.Scanner;

/**
 * Clase que permite al usuario ingresar 5 números y calcula el promedio de esos
 * números. Utiliza un array para almacenar los números ingresados. Muestra el
 * promedio calculado al final.
 *
 * @author Sebastian Godinez Borja
 */
public class PromedioDeNumeros {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear un array para almacenar los números
        double[] numeros = new double[5];

        // Recibir los números del usuario
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Ingrese el número #" + (i + 1) + ": ");
            numeros[i] = scanner.nextDouble();
        }

        // Calcular el promedio de los números
        double suma = 0;
        for (double numero : numeros) {
            suma += numero;
        }
        double promedio = suma / numeros.length;

        // Mostrar el promedio
        System.out.println("El promedio de los números es: " + promedio);

        scanner.close();
    }
}
