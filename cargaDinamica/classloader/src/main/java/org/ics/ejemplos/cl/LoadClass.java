package org.ics.ejemplos.cl;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class LoadClass {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        String jarFile = "";
        String classImp = "";
        
        // se lee la configuracion
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("cfg/config.properties")) {
            // Cargar las configuraciones desde el archivo
            prop.load(input);
            
            //  leer el jar
            jarFile = prop.getProperty("jar");
            classImp = prop.getProperty("class");

            // Mostrar las configuraciones por pantalla
            System.out.println("Jar configurado: " + jarFile);
            System.out.println("Clase configurada: " + classImp);
        } catch (IOException e) {
            // Manejar cualquier excepción de entrada/salida (IOException)
            e.printStackTrace();
        }

        URLClassLoader urlcl = URLClassLoader.newInstance(new URL[]{
            Paths.get(jarFile).toUri().toURL()
        }, ClassLoader.getSystemClassLoader());
        Class<?> clazz = urlcl.loadClass(classImp);
        Class<? extends OperacionAritmetica> oaClass = clazz.asSubclass(OperacionAritmetica.class);
        OperacionAritmetica operacionAritmetica = oaClass.getConstructor().newInstance();
        
        // aqui llegan las solicutudes al servidor y se resuelven 
        System.out.println("op 5, 3: " + operacionAritmetica.resuelve(5, 3));
    }

}
