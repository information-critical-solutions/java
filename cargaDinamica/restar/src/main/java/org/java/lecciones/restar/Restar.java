package org.java.lecciones.restar;

import org.ics.ejemplos.cl.OperacionAritmetica;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Restar implements OperacionAritmetica {

    @Override
    public Integer resuelve(Integer n1, Integer n2) {
        return n1 - n2;
    }

}
