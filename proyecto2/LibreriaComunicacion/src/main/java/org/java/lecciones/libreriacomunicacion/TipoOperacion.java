package org.java.lecciones.libreriacomunicacion;

/**
 *
 * @author Sebastian Godinez Borja
 */
public enum TipoOperacion {

    TIPO((short) 0, "Tipo de cliente"),
    SUMA((short) 1, "Suma"),
    RESTA((short) 2, "Resta"),
    MULTIPLICACION((short) 3, "Multiplicacion"),
    DIVISION((short) 4, "Division"),
    RESULTADO((short) 5, "Resultado");

    private final Short tipo;
    private final String descripcion;

    private TipoOperacion(Short tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Short getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoOperacion valueOf(Short tipoOperacion) {
        for (TipoOperacion value : values()) {
            if (value.getTipo().compareTo(tipoOperacion) == 0) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TipoMensaje{" + "tipo=" + tipo + ", descripcion=" + descripcion + '}';
    }

}
