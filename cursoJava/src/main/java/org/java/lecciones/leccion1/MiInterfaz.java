package org.java.lecciones.leccion1;

/**
 * Esta es una interfaz llamada MiInterfaz. Proporciona definiciones de métodos
 * y una constante.
 *
 * Autor: Sebastian Godinez Borja
 */
public interface MiInterfaz {

    // Constante que se puede acceder desde cualquier lugar
    String data = "datos";

    // Método público que debe ser implementado por las clases que implementan esta interfaz
    public void metodoPublico();

    // Método "default" (por defecto) que puede ser utilizado o reemplazado por las clases que implementan la interfaz
    void metodoDefault();

    // Método privado que solo puede ser utilizado dentro de esta interfaz
    private void metodoPrivado() {
        System.out.println("Método privado");
    }
}
