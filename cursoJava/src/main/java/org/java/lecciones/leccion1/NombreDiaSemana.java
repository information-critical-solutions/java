package org.java.lecciones.leccion1;

/**
 * Este programa utiliza un switch para asignar nombres a los días de la semana
 * basados en un valor numérico proporcionado.
 *
 * @author Sebastian Godinez Borja
 */
public class NombreDiaSemana {

    public static void main(String[] args) {
        // Definición del valor numérico que representa un día de la semana
        int diaSemana = 5;
        String nombreDia;

        // Utilizando switch para asignar nombres a los días de la semana
        nombreDia = switch (diaSemana) {
            case 1 -> "Lunes";
            case 2 -> "Martes";
            case 3 -> "Miércoles";
            case 4 -> "Jueves";
            case 5 -> "Viernes";
            case 6 -> "Sábado";
            case 7 -> "Domingo";
            default -> "Día no válido";
        };

        // Imprime el resultado de la asignación del nombre del día de la semana
        System.out.println("El día de la semana es: " + nombreDia);
    }
}
