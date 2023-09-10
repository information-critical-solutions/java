package org.java.lecciones.clase5.concurrencia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Esta clase representa un ejemplo de manejo concurrente de una cuenta bancaria
 * mediante hilos.
 *
 * @author Sebastian Godinez Borja
 */
public class EjemploManejoConcurrente {

    private static final Logger LOGGER = LogManager.getLogger(EjemploManejoConcurrente.class);

    public static void main(String[] args) throws InterruptedException {
        CuentaBancaria cuenta = new CuentaBancaria();

        // Registrar el saldo inicial de la cuenta
        LOGGER.info("Saldo inicial de la cuenta: " + cuenta);

        // Crear dos hilos que realizan operaciones bancarias en la misma cuenta
        Thread hilo1 = new Thread(new OperacionBancaria(cuenta));
        Thread hilo2 = new Thread(new OperacionBancaria(cuenta));

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

        // Esperar a que ambos hilos terminen su ejecución
        hilo1.join();
        hilo2.join();

        // Registrar el saldo final de la cuenta
        LOGGER.info("Saldo final de la cuenta: " + cuenta);
    }
}
