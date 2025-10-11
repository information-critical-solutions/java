package org.java.lecciones.leccion1;

import java.io.IOException;
import java.net.Socket;

public class EjemploCrearSocket {
    private CrearSockets crearSockets;
    private String identificador;

    public static void main(String[] args) throws IOException {
        CrearSockets crearSockets = new CrearSockets();
        Socket socket = crearSockets.getSocket();
        double numero = 123;
    }
}
