package org.java.lecciones.leccion2.ejercicio.tareas;

/**
 * Clase que representa una tarea con una descripción y su estado de completado.
 * Permite marcar la tarea como completada y proporciona métodos para acceder a
 * su información.
 *
 * @author Sebastian Godinez Borja
 */
public class Tarea {

    private String descripcion;
    private boolean completada;

    /**
     * Constructor para crear una nueva tarea con la descripción especificada.
     * Inicializa la tarea como no completada por defecto.
     *
     * @param descripcion La descripción de la tarea.
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    /**
     * Obtiene la descripción de la tarea.
     *
     * @return La descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Verifica si la tarea está completada.
     *
     * @return true si la tarea está completada, false en caso contrario.
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * Marca la tarea como completada.
     */
    public void marcarComoCompletada() {
        completada = true;
    }

    /**
     * Devuelve una representación en forma de cadena de la tarea.
     *
     * @return Una cadena que representa la tarea con su descripción y estado de
     * completado.
     */
    @Override
    public String toString() {
        return "Tarea{" + "descripcion=" + descripcion + ", completada=" + completada + '}';
    }

}
