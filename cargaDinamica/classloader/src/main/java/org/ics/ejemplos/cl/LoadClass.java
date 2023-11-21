package org.ics.ejemplos.cl;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class LoadClass {

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        URLClassLoader urlcl = URLClassLoader.newInstance(new URL[]{
            Paths.get("dc/sumar-1.0.0.jar").toUri().toURL()
        }, ClassLoader.getSystemClassLoader());
        Class<?> clazz = urlcl.loadClass("org.ics.ejemplos.cl.Sumar");
        Class<? extends OperacionAritmetica> oaClass = clazz.asSubclass(OperacionAritmetica.class);
        OperacionAritmetica operacionAritmetica = oaClass.getConstructor().newInstance();
        System.out.println("op 5, 3: " + operacionAritmetica.resuelve(5, 3));
    }

}
