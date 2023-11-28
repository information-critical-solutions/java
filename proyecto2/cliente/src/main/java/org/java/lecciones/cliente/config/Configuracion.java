package org.java.lecciones.cliente.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class Configuracion {

    // Crear una instancia de Properties para almacenar las configuraciones
    private final Properties prop = new Properties();

    private Configuracion() {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            // Cargar las configuraciones desde el archivo
            prop.load(input);
        } catch (IOException e) {
            // Manejar cualquier excepción de entrada/salida (IOException)
            e.printStackTrace();
        }

    }

    public static Configuracion getInstance() {
        return ConfiguracionHolder.INSTANCE;
    }

    private static class ConfiguracionHolder {

        private static final Configuracion INSTANCE = new Configuracion();
    }

    public Short getTipoOperacion() {
        String to = prop.getProperty("tipoOperacion");
        return Short.valueOf(to);
    }

    public String getHost() {
        return prop.getProperty("host");
    }

    public Integer getPort() {
        String to = prop.getProperty("port");
        return Integer.valueOf(to);
    }
}
