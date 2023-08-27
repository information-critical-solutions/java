package org.java.lecciones.leccion1.ejercicio;

/**
 * Este programa muestra la tabla de multiplicar del número 7 utilizando un
 * bucle for.
 *
 * @author Sebastian Godinez Borja
 */
public class TablaMultiplicar {

    public static void main(String[] args) {
        // Número para el cual se mostrará la tabla de multiplicar
        int numero = 7;

        // Imprimir el encabezado de la tabla
        System.out.println("Tabla de multiplicar del " + numero + ":");

        // Utilizando un bucle for para mostrar la tabla de multiplicar
        for (int i = 0; i <= 10; i++) {
            // Calcular el resultado de la multiplicación
            int resultado = numero * i;

            // Imprimir la línea de la tabla con el resultado de la multiplicación
            System.out.println(numero + " x " + i + " = " + resultado);
        }
    }
}
