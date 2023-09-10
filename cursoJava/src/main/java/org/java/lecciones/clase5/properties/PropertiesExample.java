package org.java.lecciones.clase5.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Este ejemplo muestra cómo leer configuraciones desde un archivo .properties
 * en Java. El archivo config.properties debe existir en el directorio de
 * trabajo. Contiene configuraciones relacionadas con la base de datos. Las
 * configuraciones se leen y se muestran por pantalla. Si hay un error durante
 * la lectura, se maneja y se imprime la excepción.
 *
 * @author Sebastian Godinez Borja
 */
public class PropertiesExample {

    public static void main(String[] args) {
        // Crear una instancia de Properties para almacenar las configuraciones
        Properties prop = new Properties();

        try ( FileInputStream input = new FileInputStream("config.properties")) {
            // Cargar las configuraciones desde el archivo
            prop.load(input);

            // Obtener las configuraciones de la base de datos
            String dbUrl = prop.getProperty("database.url");
            String dbUsername = prop.getProperty("database.username");
            String dbPassword = prop.getProperty("database.password");

            // Mostrar las configuraciones por pantalla
            System.out.println("Database URL: " + dbUrl);
            System.out.println("Database Username: " + dbUsername);
            System.out.println("Database Password: " + dbPassword);
        } catch (IOException e) {
            // Manejar cualquier excepción de entrada/salida (IOException)
            e.printStackTrace();
        }
    }
}
