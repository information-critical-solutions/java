package org.java.lecciones.leccion1;

/**
 * Esta clase ilustra los diferentes niveles de accesibilidad en Java. Se
 * destacan los atributos y métodos con diversos niveles de acceso.
 *
 * @author Sebastian Godinez Borja
 */
public class Accesibilidad {

    // Atributo público: Puede ser accedido desde cualquier lugar
    public int variablePublica;

    // Atributo protegido: Accesible dentro del mismo paquete y subclases
    protected int variableProtegida;

    // Atributo de paquete (default): Accesible en el mismo paquete
    int variableDefault;

    // Atributo privado: Solo accesible dentro de esta clase
    private int variablePrivada;

    /**
     * Método público: Puede ser llamado desde cualquier parte del programa.
     */
    public void metodoPublico() {
        // Código aquí
    }

    /**
     * Método protegido: Accesible dentro del mismo paquete y subclases.
     */
    protected void metodoProtegido() {
        // Código aquí
    }

    /**
     * Método de paquete (default): Accesible en el mismo paquete.
     */
    void metodoDefault() {
        // Código aquí
    }

    /**
     * Método privado: Solo accesible dentro de esta clase.
     */
    private void metodoPrivado() {
        // Código aquí
    }
}
