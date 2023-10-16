package org.java.lecciones.clase6.proyecto;

import java.io.Serializable;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Mensaje implements Serializable {

    private short tipoOperacion;
    private byte[] data;

    public Mensaje(short tipoOperacion, byte[] data) {
        this.tipoOperacion = tipoOperacion;
        this.data = data;
    }

    public short getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(short tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "tipoOperacion=" + tipoOperacion + ", data=" + data + '}';
    }

}
