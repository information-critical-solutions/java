package org.java.lecciones.clase6.proyecto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Esta clase representa el servidor de la aplicación distribuida. Los clientes
 * se conectan a este servidor para realizar operaciones aritméticas básicas. El
 * servidor acepta múltiples conexiones de clientes y redirige mensajes entre
 * ellos. Se utiliza un arreglo para mantener un registro de las conexiones
 * entrantes.
 *
 * @author Sebastian Godinez Borja
 */
public class ServidorSocket {

    // Bitácpra de la clase
    private static final Logger LOGGER = LogManager.getLogger(ServerSocket.class);

    public static void main(String[] args) {
        final int puerto = 12345;
        try {
            // Se crea un socket servidor para aceptar conexiones en el puerto especificado
            ServerSocket servidorSocket = new ServerSocket(puerto);
            LOGGER.info("Servidor en línea. Esperando conexiones...");
            while (true) {
                // Espera y acepta una conexión entrante de un cliente
                Socket clienteSocket = servidorSocket.accept();
                ClientesConectados.getInstance().addClienteConectado(clienteSocket);
                LOGGER.info("Nuevo cliente conectado: " + clienteSocket.getInetAddress());
                // hilo de recepción
                new Thread(() -> {
                    try (DataInputStream dataInputStream = new DataInputStream(clienteSocket.getInputStream())) {
                        while (true) {
                            // Leer el tipo de operación como short
                            Short tipoOperacion = dataInputStream.readShort();
                            // Leer el tamaño del arreglo de datos como short
                            Short tam = dataInputStream.readShort();
                            // Leer el arreglo de datos
                            byte[] datos = new byte[tam];
                            dataInputStream.readFully(datos);
                            Mensaje mensaje = new Mensaje(tipoOperacion, datos);
                            LOGGER.info("Mensaje recibido : " + mensaje + " desde el socket: " + clienteSocket);
                            List<Socket> clientesConectados = ClientesConectados.getInstance().getClientesConectados();
                            for (Socket clientesConectado : clientesConectados) {
                                sendMessage(clienteSocket, mensaje);
                            }
                        }
                    } catch (IOException e) {
                        // El cliente se desconectó, se maneja la excepción y se sale del bucle
                        LOGGER.warn("Cliente desconectado.");
                    }
                    //el cliente se fue
                    ClientesConectados.getInstance().removeClienteConectado(clienteSocket);
                    List<Socket> clientesConectados = ClientesConectados.getInstance().getClientesConectados();
                    LOGGER.info("El cliente " + clienteSocket + " se fue, ahora quedan: " + clientesConectados.size());
                    clientesConectados.forEach(LOGGER::info);
                }).start();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private static void sendMessage(Socket socket, Mensaje mensaje) throws IOException {
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // Enviar el tipo de operación
        dos.writeShort(mensaje.getTipoOperacion());
        // Enviar el tamaño del arreglo de datos
        dos.writeShort(mensaje.getData().length);
        // Enviar el arreglo de datos al servidor
        dos.write(mensaje.getData());
    }

}
