package org.java.lecciones.clase5.concurrencia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Esta clase representa una cuenta bancaria que admite operaciones de depósito
 * y retiro de fondos utilizando ReentrantLock para la sincronización.
 *
 * @author Sebastian Godinez Borja
 */
public class CuentaBancaria {

    private static final Logger LOGGER = LogManager.getLogger(CuentaBancaria.class);
    private double saldo = 1000.0; // Saldo inicial
    private final Lock lock = new ReentrantLock(); // Lock para garantizar la exclusión mutua

    /**
     * Realiza un depósito en la cuenta bancaria.
     *
     * @param cantidad El monto a depositar.
     */
    public void depositar(double cantidad) {
        lock.lock(); // Bloquear el recurso compartido
        try {
            saldo += cantidad;
            LOGGER.info("Depósito de " + cantidad + ". Saldo total: " + saldo);
        } finally {
            lock.unlock(); // Desbloquear el recurso compartido
        }
    }

    /**
     * Realiza un retiro de la cuenta bancaria.
     *
     * @param cantidad El monto a retirar.
     */
    public void retirar(double cantidad) {
        lock.lock(); // Bloquear el recurso compartido
        try {
            if (saldo >= cantidad) {
                saldo -= cantidad;
                LOGGER.info("Retiro de " + cantidad + ". Saldo total: " + saldo);
            } else {
                LOGGER.info("Fondos insuficientes para el retiro de " + cantidad);
            }
        } finally {
            lock.unlock(); // Desbloquear el recurso compartido
        }
    }

    /**
     * Devuelve una representación de cadena de la cuenta bancaria.
     *
     * @return Una cadena que representa el saldo actual de la cuenta bancaria.
     */
    @Override
    public String toString() {
        return "CuentaBancaria{" + "saldo=" + saldo + '}';
    }
}
