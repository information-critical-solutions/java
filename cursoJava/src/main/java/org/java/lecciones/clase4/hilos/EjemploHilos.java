package org.java.lecciones.clase4.hilos;

/**
 * Clase de ejemplo que muestra cómo usar hilos en Java para imprimir números
 * pares e impares de manera concurrente.
 *
 * @author Sebastian Godinez Borja
 */
public class EjemploHilos {

    public static void main(String[] args) {
        // Crear dos objetos de la clase que implementa Runnable
        Runnable imprimirPares = new ImprimirPares();
        Runnable imprimirImpares = new ImprimirImpares();

        // Crear dos hilos y asignarles las tareas
        Thread hiloPares = new Thread(imprimirPares);
        Thread hiloImpares = new Thread(imprimirImpares);

        // Iniciar los hilos
        hiloPares.start(); // Inicia el hilo para imprimir números pares
        hiloImpares.start(); // Inicia el hilo para imprimir números impares
    }
}
