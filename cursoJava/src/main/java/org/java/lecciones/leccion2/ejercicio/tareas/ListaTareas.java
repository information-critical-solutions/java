package org.java.lecciones.leccion2.ejercicio.tareas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que permite gestionar una lista de tareas. Permite agregar tareas,
 * marcar tareas como completadas, eliminar tareas y mostrar la lista de tareas.
 * Utiliza la clase Tarea para representar cada tarea.
 *
 * @author Sebastian Godinez Borja
 */
public class ListaTareas {

    public static void main(String[] args) {
        List<Tarea> listaTareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Marcar tarea como completada");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Mostrar tareas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();
                    Tarea t = new Tarea(descripcion);
                    listaTareas.add(t);
                    System.out.println("Tarea agregada. " + t);
                    break;
                case 2:
                    System.out.print("Ingrese el índice de la tarea a marcar como completada: ");
                    int indiceCompletada = scanner.nextInt();
                    if (indiceCompletada >= 0 && indiceCompletada < listaTareas.size()) {
                        listaTareas.get(indiceCompletada).marcarComoCompletada();
                        System.out.println("Tarea marcada como completada.");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el índice de la tarea a eliminar: ");
                    int indiceEliminar = scanner.nextInt();
                    if (indiceEliminar >= 0 && indiceEliminar < listaTareas.size()) {
                        listaTareas.remove(indiceEliminar);
                        System.out.println("Tarea eliminada.");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 4:
                    System.out.println("Lista de tareas:");
                    for (int i = 0; i < listaTareas.size(); i++) {
                        Tarea tarea = listaTareas.get(i);
                        System.out.println(i + ". " + tarea.getDescripcion() + " - Completada: " + tarea.isCompletada());
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
