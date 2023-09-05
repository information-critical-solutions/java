package org.java.lecciones.clase4;

import java.io.File;
import java.io.IOException;

/**
 * Ejemplo de uso de la clase File para trabajar con archivos y directorios en
 * Java. Este programa verifica, crea, lista y elimina archivos y directorios.
 *
 * @author Sebastian Godinez Borja
 */
public class EjemploClaseFile {

    public static void main(String[] args) {
        // Crear un objeto File que representa un archivo existente
        File archivo = new File("miarchivo.txt");

        // Verificar si el archivo existe
        if (archivo.exists()) {
            System.out.println("El archivo existe.");
            System.out.println("Nombre del archivo: " + archivo.getName());
            System.out.println("Ruta absoluta del archivo: " + archivo.getAbsolutePath());
            System.out.println("Tamaño del archivo en bytes: " + archivo.length() + " bytes");

            // Verificar si es un archivo o un directorio
            if (archivo.isFile()) {
                System.out.println("Es un archivo.");
            } else if (archivo.isDirectory()) {
                System.out.println("Es un directorio.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }

        // Crear un directorio
        File directorio = new File("micarpeta");

        // Verificar si el directorio existe
        if (!directorio.exists()) {
            if (directorio.mkdir()) {
                System.out.println("Directorio creado exitosamente.");
            } else {
                System.out.println("Error al crear el directorio.");
            }
        } else {
            System.out.println("El directorio ya existe.");
        }

        // Listar archivos y directorios en un directorio
        File directorioActual = new File(".");
        String[] archivosEnDirectorio = directorioActual.list();

        System.out.println("Archivos y directorios en el directorio actual:");
        for (String nombre : archivosEnDirectorio) {
            System.out.println(nombre);
        }

        // Eliminar un archivo
        File archivoAEliminar = new File("archivoEliminar.txt");
        try {
            if (archivoAEliminar.createNewFile()) {
                System.out.println("Archivo creado para eliminar.");
                if (archivoAEliminar.delete()) {
                    System.out.println("Archivo eliminado exitosamente.");
                } else {
                    System.out.println("Error al eliminar el archivo.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
