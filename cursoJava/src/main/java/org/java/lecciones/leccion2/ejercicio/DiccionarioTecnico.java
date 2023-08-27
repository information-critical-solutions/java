package org.java.lecciones.leccion2.ejercicio;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Clase que implementa un diccionario técnico utilizando un HashMap en Java.
 * Permite agregar términos y definiciones, buscar definiciones por término,
 * eliminar términos y definiciones, y salir del programa.
 *
 * @author Sebastian Godinez Borja
 */
public class DiccionarioTecnico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> diccionario = new HashMap<>();

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("1. Agregar término y definición");
            System.out.println("2. Buscar definición por término");
            System.out.println("3. Eliminar término y definición");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el término: ");
                    String termino = scanner.nextLine();
                    System.out.print("Ingrese la definición: ");
                    String definicion = scanner.nextLine();
                    diccionario.put(termino, definicion);
                    System.out.println("Término agregado al diccionario.");
                    break;
                case 2:
                    System.out.print("Ingrese el término a buscar: ");
                    String terminoBusqueda = scanner.nextLine();
                    String definicionEncontrada = diccionario.get(terminoBusqueda);
                    if (definicionEncontrada != null) {
                        System.out.println("Definición: " + definicionEncontrada);
                    } else {
                        System.out.println("Término no encontrado en el diccionario.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el término a eliminar: ");
                    String terminoEliminar = scanner.nextLine();
                    String definicionEliminar = diccionario.remove(terminoEliminar);
                    if (definicionEliminar != null) {
                        System.out.println("Término y definición eliminados del diccionario.");
                    } else {
                        System.out.println("Término no encontrado en el diccionario.");
                    }
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
