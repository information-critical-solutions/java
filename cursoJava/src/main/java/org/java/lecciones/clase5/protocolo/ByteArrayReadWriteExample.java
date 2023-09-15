package org.java.lecciones.clase5.protocolo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Este programa demuestra cómo escribir y leer un arreglo de bytes con un
 * formato específico. Utiliza Log4j para registrar eventos en lugar de
 * System.out.println. Los datos escritos incluyen un short para el tipo de
 * operación y otro short para la longitud de los datos. Luego, se leen los
 * datos desde el arreglo de bytes y se registran utilizando Log4j.
 *
 * @author Sebastian Godinez Borja
 */
public class ByteArrayReadWriteExample {

    private static final Logger LOGGER = LogManager.getLogger(ByteArrayReadWriteExample.class);

    public static void main(String[] args) {
        // Ejemplo de datos para escribir en el arreglo de bytes
        short tipoOperacion = 1; // Tipo de operación (por ejemplo, 1 para "suma") <- header
        byte[] datos = {10, 20}; // Datos a enviar <- data del servicio
        // Escribir los datos en un arreglo de bytes
        byte[] bytes = escribirDatos(tipoOperacion, datos);
        // Leer los datos desde el arreglo de bytes
        DatosLeidos resultado = leerDatos(bytes);
        // Mostrar los resultados utilizando Log4j
        LOGGER.info("Tipo de Operación: " + resultado.tipoOperacion);
        LOGGER.info("Datos Leídos: ");
        for (byte valor : resultado.datos) {
            LOGGER.info(valor + " ");
        }
    }

    // Método para escribir los datos en un arreglo de bytes
    public static byte[] escribirDatos(short tipoOperacion, byte[] datos) {
        try ( ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)) {
            // Escribir el tipo de operación como short
            dataOutputStream.writeShort(tipoOperacion);
            // Escribir la longitud de los datos como short
            dataOutputStream.writeShort(datos.length);
            // Escribir los datos en el arreglo
            dataOutputStream.write(datos);
            // Obtener el arreglo de bytes resultante
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.error("Error al escribir datos en el arreglo de bytes: " + e.getMessage());
            return null;
        }
    }

    // Método para leer los datos desde un arreglo de bytes
    public static DatosLeidos leerDatos(byte[] bytes) {
        try ( ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);  DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream)) {
            // Leer el tipo de operación como short
            short tipoOperacion = dataInputStream.readShort();
            // Leer la longitud de los datos como short
            short longitud = dataInputStream.readShort();
            // Leer los datos en un nuevo arreglo
            byte[] datos = new byte[longitud];
            dataInputStream.readFully(datos);
            return new DatosLeidos(tipoOperacion, datos);

        } catch (IOException e) {
            LOGGER.error("Error al leer datos desde el arreglo de bytes: " + e.getMessage());
            return null;
        }
    }
}
