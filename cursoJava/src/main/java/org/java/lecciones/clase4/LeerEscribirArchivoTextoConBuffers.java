package org.java.lecciones.clase4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Ejemplo de lectura y escritura de archivos de texto utilizando BufferedReader
 * y BufferedWriter. El programa lee un archivo de entrada línea por línea y lo
 * imprime en la consola. Luego, escribe contenido en un archivo de salida.
 *
 * @author Sebastian Godinez Borja
 */
public class LeerEscribirArchivoTextoConBuffers {

    public static void main(String[] args) {
        String archivoEntrada = "entrada.txt"; // Nombre del archivo de entrada
        String archivoSalida = "salida.txt";   // Nombre del archivo de salida

        // Escribir en el archivo de salida usando BufferedWriter
        try ( FileWriter fw = new FileWriter(archivoSalida);
              BufferedWriter bw = new BufferedWriter(fw)) {
            String contenido = "Este es un ejemplo de escritura en un archivo de texto.\n";
            bw.write(contenido); // Escribir el contenido en el archivo
            System.out.println("\nContenido escrito en el archivo de salida.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Leer el archivo de entrada usando BufferedReader
        try (FileReader fr = new FileReader(archivoEntrada);
                BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); // Imprimir cada línea en la consola
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
