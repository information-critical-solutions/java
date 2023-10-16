package org.java.lecciones.libreriacomunicacion;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Mensaje {

    Short tipoOperacion;
    byte[] datos;

    public Short getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Short tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "tipoOperacion=" + tipoOperacion + '}';
    }

}
