package org.java.lecciones.clase4.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase que implementa un cliente de sockets en Java. Se conecta a un servidor
 * en una dirección y puerto específicos y envía un mensaje.
 *
 * @author Sebastian Godinez Borja
 */
public class ClienteSocket {

    public static void main(String[] args) {
        final String host = "localhost"; // Dirección del servidor
        final int puerto = 12345; // Puerto en el que el servidor está escuchando

        try {
            Socket cliente = new Socket(host, puerto); // Conectar al servidor

            // Flujo de salida para enviar datos al servidor
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            // Enviar un mensaje al servidor
            salida.println("Hola, servidor!");

            // Cerrar el cliente
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
