package org.java.lecciones.clase5.concurrencia.ejercicio;

import java.util.LinkedList;
import java.util.Queue;

// Clase que representa un almacén compartido
class Almacen {

    private Queue<Integer> elementos = new LinkedList<>();
    private int capacidad;

    public Almacen(int capacidad) {
        this.capacidad = capacidad;
    }

    // Método para colocar un elemento en el almacén
    public synchronized void colocarElemento(int elemento) throws InterruptedException {
        while (elementos.size() == capacidad) {
            // Esperar si el almacén está lleno
            wait();
        }
        elementos.offer(elemento);
        System.out.println("Productor colocó elemento: " + elemento);
        // Notificar a los consumidores que hay elementos disponibles
        notifyAll();
    }

    // Método para retirar un elemento del almacén
    public synchronized int retirarElemento() throws InterruptedException {
        while (elementos.isEmpty()) {
            // Esperar si el almacén está vacío
            wait();
        }
        int elemento = elementos.poll();
        System.out.println("Consumidor retiró elemento: " + elemento);
        // Notificar a los productores que hay espacio disponible
        notifyAll();
        return elemento;
    }
}

// Clase que representa un productor
class Productor implements Runnable {

    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                almacen.colocarElemento(i);
                Thread.sleep(100); // Simular producción
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Clase que representa un consumidor
class Consumidor implements Runnable {

    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                int elemento = almacen.retirarElemento();
                Thread.sleep(200); // Simular consumo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Almacen almacen = new Almacen(3); // Capacidad del almacén

        // Crear hilos productores y consumidores
        Thread productor1 = new Thread(new Productor(almacen));
        Thread productor2 = new Thread(new Productor(almacen));
        Thread consumidor1 = new Thread(new Consumidor(almacen));
        Thread consumidor2 = new Thread(new Consumidor(almacen));

        // Iniciar los hilos
        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();

        try {
            // Esperar a que todos los hilos terminen
            productor1.join();
            productor2.join();
            consumidor1.join();
            consumidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
