package org.java.lecciones.leccion1;

/**
 * Este programa utiliza un bucle for para realizar iteraciones y muestra un
 * mensaje en cada iteración.
 *
 * @author Sebastian Godinez Borja
 */
public class BucleFor {

    public static void main(String[] args) {
        // Utilizando un bucle for para imprimir las iteraciones
        for (int i = 100; i >= 15; i = i - 20) {
            // Imprime un mensaje con el número de iteración
            System.out.println("Iteración " + i);
        }


    }
}
