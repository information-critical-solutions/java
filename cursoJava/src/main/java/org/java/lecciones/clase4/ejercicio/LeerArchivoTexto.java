package org.java.lecciones.clase4.ejercicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Este programa lee un archivo de texto llamado "datos.txt" que contiene
 * conjuntos de números separados por comas. Encuentra el número máximo en cada
 * conjunto de números y lo muestra en la consola.
 *
 * @author Sebastian Godinez Borja
 */
public class LeerArchivoTexto {

    public static void main(String[] args) {
        // Nombre del archivo a leer
        String nombreArchivo = "datos.txt";

        try ( BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            // Iterar sobre las líneas del archivo
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en números utilizando coma como separador
                String[] numerosStr = linea.split(",");

                // Encontrar el número máximo en el conjunto de números
                int maximo = Integer.MIN_VALUE; // Inicializar con el valor mínimo de enteros

                // Iterar sobre los números en el conjunto
                for (String numStr : numerosStr) {
                    int numero = Integer.parseInt(numStr.trim()); // Convertir a entero

                    if (numero > maximo) {
                        maximo = numero;
                    }
                }

                // Mostrar el número máximo en el conjunto
                System.out.println("Número máximo en el conjunto: " + maximo);
            }
        } catch (IOException e) {
            // Manejar excepciones de lectura de archivos
            e.printStackTrace();
        }
    }
}
