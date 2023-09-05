package org.java.lecciones.clase4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class LeerEscribirArchivoTextoConBuffers {

    public static void main(String[] args) {
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        // Leer el archivo de entrada usando BufferedReader
        try ( BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // Imprimir cada línea en la consola
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribir en el archivo de salida usando BufferedWriter
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            String contenido = "Este es un ejemplo de escritura en un archivo de texto.\n";
            bw.write(contenido);
            System.out.println("\nContenido escrito en el archivo de salida.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
