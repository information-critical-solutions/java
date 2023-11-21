package org.ics.ejemplos.cl;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Sumar implements OperacionAritmetica {

    @Override
    public Integer resuelve(Integer n1, Integer n2) {
        return n1 + n2;
    }

}
