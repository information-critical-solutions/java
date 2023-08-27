package org.java.lecciones.leccion2.tickets;

/**
 * Clase que representa un ticket en un sistema de gestión de tickets. Cada
 * ticket tiene un número de ticket único.
 *
 * @author Sebastian Godinez Borja
 */
public class Ticket {

    private final int ticketNumber; // Número de ticket

    /**
     * Constructor para crear un nuevo ticket con el número proporcionado.
     *
     * @param ticketNumber Número de ticket
     */
    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    /**
     * Método para obtener el número de ticket.
     *
     * @return Número de ticket
     */
    public int getTicketNumber() {
        return ticketNumber;
    }

    /**
     * Sobrescritura del método toString para mostrar el formato del ticket.
     *
     * @return Representación del ticket en formato de cadena
     */
    @Override
    public String toString() {
        return "Ticket #" + ticketNumber;
    }
}
