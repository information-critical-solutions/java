package org.java.lecciones.clase5.protocolo;

/**
 * Esta clase representa una estructura de datos para almacenar los resultados
 * de la lectura. Contiene un short para el tipo de operación y un arreglo de
 * bytes para los datos. Se utiliza para estructurar los datos leídos desde un
 * arreglo de bytes con formato específico.
 *
 * @author Sebastian Godinez Borja
 */
public class DatosLeidos {

    short tipoOperacion; // Almacena el tipo de operación leído
    byte[] datos; // Almacena los datos leídos

    /**
     * Constructor de la clase que inicializa los valores de tipo de operación y
     * datos.
     *
     * @param tipoOperacion El tipo de operación leído desde el arreglo de
     * bytes.
     * @param datos Los datos leídos desde el arreglo de bytes.
     */
    public DatosLeidos(short tipoOperacion, byte[] datos) {
        this.tipoOperacion = tipoOperacion;
        this.datos = datos;
    }
}
