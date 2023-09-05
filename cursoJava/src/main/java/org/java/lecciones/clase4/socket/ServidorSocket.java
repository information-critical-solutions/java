package org.java.lecciones.clase4.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que implementa un servidor de sockets en Java. Escucha en un puerto
 * específico y muestra mensajes de clientes conectados.
 *
 * @author Sebastian Godinez Borja
 */
public class ServidorSocket {

    public static void main(String[] args) {
        final int puerto = 12345; // Puerto en el que el servidor escuchará

        try {
            ServerSocket servidor = new ServerSocket(puerto); // Crea un servidor de sockets en el puerto especificado
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                Socket cliente = servidor.accept(); // Aceptar una conexión entrante
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());

                // Flujo de entrada para recibir datos del cliente
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                // Leer el mensaje enviado por el cliente
                String mensajeCliente = entrada.readLine();
                System.out.println("Mensaje del cliente: " + mensajeCliente);

                // Cerrar el cliente
                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
