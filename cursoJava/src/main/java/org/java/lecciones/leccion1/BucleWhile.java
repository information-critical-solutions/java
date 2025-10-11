package org.java.lecciones.leccion1;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.Socket;

/**
 * Este programa utiliza un bucle while para realizar iteraciones y muestra un
 * mensaje en cada iteración.
 *
 * @author Sebastian Godinez Borja
 */
public class BucleWhile {

    public static void main(String[] args) throws IOException {
        // Inicialización del contador
        int contador = 0;

        // Utilizando un bucle while para imprimir las iteraciones
        while (contador < 3) {
            // Imprime un mensaje con el valor actual del contador
            System.out.println("Contador: " + contador);

            // Incrementa el contador en 1 para la próxima iteración
            contador++;
        }
        System.out.println("Contador final: " + contador);



    }


    public Socket getSocket() throws Exception {
        Boolean desconectado = true;
        Integer intentos = 10;
        Integer puerto = 2020;
        while (desconectado && intentos > 0) {
            try {
                return new Socket("ip", puerto);
            } catch (IOException e) {
                intentos--;
                puerto++;
                System.out.println("Fallé");
            }
        }
        throw new Exception("No se ha encontrado ningun SOCKET de escucha para conectar");
    }
}
