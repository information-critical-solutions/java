package org.java.lecciones.clase5.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    // Lista para almacenar las conexiones de los clientes
    private static List<Socket> clientesConectados = new ArrayList<>();
    
    public static void main(String[] args) {
        final int puerto = 12345;
        
        try {
            // Se crea un socket servidor para aceptar conexiones en el puerto especificado
            ServerSocket servidorSocket = new ServerSocket(puerto);
            LOGGER.info("Servidor en línea. Esperando conexiones...");
            
            while (true) {
                // Espera y acepta una conexión entrante de un cliente
                Socket clienteSocket = servidorSocket.accept();
                clientesConectados.add(clienteSocket);
                System.out.println("Nuevo cliente conectado: " + clienteSocket.getInetAddress());

                // Aquí puedes implementar la lógica para manejar la conexión con el cliente
                // Por ejemplo, crear un hilo para cada cliente para gestionar su comunicación individual.
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
