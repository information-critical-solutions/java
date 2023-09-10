package org.java.lecciones.clase5.concurrencia;

import java.util.Random;

/**
 * Esta clase representa una operación bancaria que puede ser ejecutada por un
 * hilo.
 *
 * @author Sebastian Godinez Borja
 */
public class OperacionBancaria implements Runnable {

    private final Random random = new Random(System.nanoTime());
    private final CuentaBancaria cuenta;

    /**
     * Constructor de la clase que recibe la cuenta bancaria en la que se
     * realizarán las operaciones.
     *
     * @param cuenta La cuenta bancaria en la que se realizarán las operaciones.
     */
    public OperacionBancaria(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Método que representa la ejecución de la operación bancaria por parte de
     * un hilo.
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // Genera un número aleatorio entre 0 y 100
            double next = random.nextDouble() * 100.0;
            if (random.nextBoolean()) {
                cuenta.depositar(next); // Realizar depósito
            } else {
                cuenta.retirar(next);   // Realizar retiro
            }
        }
    }
}
