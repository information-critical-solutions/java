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
        int diaSemana = 3;
        String nombreDia;

        // Utilizando switch para asignar nombres a los días de la semana
        switch (diaSemana) {
            case 1:
                nombreDia = "Lunes";
                break;
            case 2:
                nombreDia = "Martes";
                break;
            case 3:
                nombreDia = "Miércoles";
                break;
            case 4:
                nombreDia = "Jueves";
                break;
            case 5:
                nombreDia = "Viernes";
                break;
            case 6:
                nombreDia = "Sábado";
                break;
            case 7:
                nombreDia = "Domingo";
                break;
            default:
                nombreDia = "Día no válido";
        }

        // Imprime el resultado de la asignación del nombre del día de la semana
        System.out.println("El día de la semana es: " + nombreDia);
    }
}
