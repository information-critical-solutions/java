package org.java.lecciones.clase4.hilos;

import java.net.Socket;

/**
 * Clase que implementa la interfaz Runnable para imprimir números pares.
 *
 * @author Sebastian Godinez Borja
 */
class ImprimirPares implements Runnable {


    /**
     * Método run() que se ejecutará cuando el hilo comience.
     */
    @Override
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Par: " + i); // Imprime números pares
            try {
                Thread.sleep(500); // Simula un proceso que toma tiempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
