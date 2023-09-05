package org.java.lecciones.clase4.ejercicio;

import java.io.File;
import java.util.Scanner;

/**
 * Esta clase implementa un explorador de archivos básico en Java utilizando la
 * clase File. Permite navegar por directorios, ver información detallada, crear
 * directorios, eliminar archivos o directorios y salir del programa.
 *
 * @author Sebastian Godinez Borja
 */
public class ExploradorDeArchivos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File directorioActual = new File("."); // Directorio actual
        mostrarContenidoDirectorio(directorioActual);

        while (true) {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Navegar a un directorio");
            System.out.println("2. Mostrar información detallada");
            System.out.println("3. Crear un nuevo directorio");
            System.out.println("4. Eliminar un archivo o directorio");
            System.out.println("5. Salir");
            System.out.print("Ingrese el número de la opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la ruta del directorio a navegar: ");
                    String ruta = scanner.nextLine();
                    File nuevoDirectorio = new File(ruta);
                    if (nuevoDirectorio.exists() && nuevoDirectorio.isDirectory()) {
                        directorioActual = nuevoDirectorio;
                        mostrarContenidoDirectorio(directorioActual);
                    } else {
                        System.out.println("El directorio especificado no existe.");
                    }
                    break;
                case 2:
                    mostrarInformacionDetallada(directorioActual);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del nuevo directorio: ");
                    String nombreDirectorio = scanner.nextLine();
                    File nuevoDirectorio2 = new File(directorioActual, nombreDirectorio);
                    if (nuevoDirectorio2.mkdir()) {
                        System.out.println("Directorio creado exitosamente.");
                    } else {
                        System.out.println("Error al crear el directorio.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del archivo o directorio a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    File archivoEliminar = new File(directorioActual, nombreEliminar);
                    if (archivoEliminar.exists()) {
                        if (archivoEliminar.isDirectory()) {
                            eliminarDirectorio(archivoEliminar);
                        } else {
                            archivoEliminar.delete();
                            System.out.println("Archivo eliminado exitosamente.");
                        }
                    } else {
                        System.out.println("El archivo o directorio especificado no existe.");
                    }
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
            }
        }
    }

    // Método para mostrar el contenido del directorio actual
    private static void mostrarContenidoDirectorio(File directorio) {
        System.out.println("\nContenido del directorio actual:");
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            System.out.println(archivo.getName());
        }
    }

    // Método para mostrar información detallada de archivos y directorios en el directorio actual
    private static void mostrarInformacionDetallada(File directorio) {
        System.out.println("\nInformación detallada del directorio actual:");
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            System.out.println("Nombre: " + archivo.getName());
            System.out.println("Tipo: " + (archivo.isDirectory() ? "Directorio" : "Archivo"));
            System.out.println("Tamaño: " + archivo.length() + " bytes");
            System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
            System.out.println();
        }
    }

    // Método para eliminar un directorio y su contenido recursivamente
    private static void eliminarDirectorio(File directorio) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    eliminarDirectorio(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        directorio.delete();
        System.out.println("Directorio eliminado exitosamente.");
    }
}
