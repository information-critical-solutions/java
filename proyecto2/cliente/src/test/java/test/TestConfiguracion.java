package test;

import org.java.lecciones.cliente.config.Configuracion;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class TestConfiguracion {
    
    public static void main(String[] args) {
        System.out.println(Configuracion.getInstance().getTipoOperacion());
        System.out.println(Configuracion.getInstance().getHost());
        System.out.println(Configuracion.getInstance().getPortInicial());
        System.out.println(Configuracion.getInstance().getPortFinal());
    }
    
}
