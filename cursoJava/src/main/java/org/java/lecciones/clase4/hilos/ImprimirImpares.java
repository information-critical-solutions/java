package org.java.lecciones.clase4.hilos;

/**
 * Clase que implementa la interfaz Runnable para imprimir números impares.
 *
 * @author Sebastian Godinez Borja
 */
class ImprimirImpares implements Runnable {

    /**
     * Método run() que se ejecutará cuando el hilo comience.
     */
    public void run() {
        for (int i = 1; i <= 9; i += 2) {
            System.out.println("Impar: " + i); // Imprime números impares
            try {
                Thread.sleep(500); // Simula un proceso que toma tiempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
