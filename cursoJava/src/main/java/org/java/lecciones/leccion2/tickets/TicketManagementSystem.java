package org.java.lecciones.leccion2.tickets;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Clase que demuestra el funcionamiento de un sistema de gestión de tickets
 * utilizando colas y pilas. Los tickets se agregan a una cola y luego se
 * resuelven y mueven a una pila de tickets resueltos. La cola representa la
 * lista de tickets pendientes y la pila los tickets resueltos (último en ser
 * resuelto, primero en salir).
 *
 * @author Sebastian Godinez Borja
 */
public class TicketManagementSystem {

    public static void main(String[] args) {
        Queue<Ticket> ticketQueue = new LinkedList<>(); // Cola para tickets pendientes
        Stack<Ticket> resolvedTickets = new Stack<>(); // Pila para tickets resueltos

        // Agregar tickets a la cola
        for (int i = 1; i <= 5; i++) {
            ticketQueue.add(new Ticket(i));
        }

        // Resolver algunos tickets y moverlos a la pila de resueltos
        resolveTicket(ticketQueue, resolvedTickets);
        resolveTicket(ticketQueue, resolvedTickets);
        resolveTicket(ticketQueue, resolvedTickets);
        resolveTicket(ticketQueue, resolvedTickets);

        // Mostrar el estado de los tickets en la cola y la pila
        System.out.println("Tickets en cola:");
        for (Ticket ticket : ticketQueue) {
            System.out.println(ticket);
        }

        System.out.println("\nTickets resueltos (último en ser resuelto, primero en salir):");
        while (!resolvedTickets.isEmpty()) {
            System.out.println(resolvedTickets.pop());
        }
    }

    /**
     * Método para resolver un ticket y moverlo de la cola a la pila de tickets
     * resueltos.
     *
     * @param queue Cola de tickets pendientes
     * @param stack Pila de tickets resueltos
     */
    private static void resolveTicket(Queue<Ticket> queue, Stack<Ticket> stack) {
        if (!queue.isEmpty()) {
            Ticket resolvedTicket = queue.poll();
            stack.push(resolvedTicket);
            System.out.println("Ticket resuelto: " + resolvedTicket);
        } else {
            System.out.println("No hay más tickets para resolver.");
        }
    }
}
