package org.java.lecciones.leccion1;

/**
 * Este programa realiza varias operaciones matemáticas y lógicas, y muestra los
 * resultados. También demuestra el uso de variables y operadores en Java.
 *
 * @author Sebastian Godinez Borja
 */
public class Operaciones {

    public static void main(String[] args) {
        // Realizando la operación de suma
        int suma = 5 + 3;

        // Realizando la operación de división
        double division = 10.0 / 2;

        // Definiendo la edad y verificando si es mayor de edad
        int edad = 20;
        boolean esMayor = edad > 18;

        // Calculando el residuo de 10 dividido entre 3
        int residuo = 10 % 3;

        // Imprimiendo los resultados de las operaciones
        System.out.println("Resultado de la suma: " + suma);
        System.out.println("Resultado de la división: " + division);
        System.out.println("¿Es mayor de edad? " + esMayor);
        System.out.println("Residuo de 10 % 3: " + residuo);
    }
}
