package mx.up.cripto.hash;

import java.security.MessageDigest;   // Importamos la clase para calcular funciones hash
import java.security.NoSuchAlgorithmException; // Excepción si el algoritmo no existe

import org.apache.logging.log4j.LogManager;    // LogManager para crear instancias de logger
import org.apache.logging.log4j.Logger;        // Logger de log4j2

public class HashDemo {

    // Creamos un logger para esta clase usando log4j2
    private static final Logger LOGGER = LogManager.getLogger(HashDemo.class);

    public static void main(String[] args) {
        // Texto de entrada que queremos hashear
        String texto = "Hola UP, esto es seguridad en Java";

        // Mostramos el texto original en log
        LOGGER.info("Texto original: {}", texto);

        // Probamos con diferentes algoritmos de hash
        calcularHash("MD5", texto);
        calcularHash("SHA-1", texto);
        calcularHash("SHA-256", texto);
        calcularHash("SHA-512", texto);
    }

    /**
     * Método que calcula el hash de un texto con un algoritmo dado
     *
     * @param algoritmo Algoritmo de hash (ej. MD5, SHA-1, SHA-256, SHA-512)
     * @param texto     Texto a convertir en hash
     */
    private static void calcularHash(String algoritmo, String texto) {
        try {
            // Creamos una instancia de MessageDigest con el algoritmo especificado
            MessageDigest digest = MessageDigest.getInstance(algoritmo);

            // Convertimos el texto a bytes en UTF-8 y lo pasamos a digest
            byte[] hashBytes = digest.digest(texto.getBytes("UTF-8"));

            // Convertimos el arreglo de bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                // Formateamos cada byte como dos caracteres hexadecimales
                sb.append(String.format("%02x", b));
            }
            // Mostramos el algoritmo y el hash calculado usando log
            LOGGER.info("Algoritmo {} → {}", algoritmo, sb.toString());

        } catch (NoSuchAlgorithmException e) {
            // Si el algoritmo no existe, mostramos error
            LOGGER.error("Algoritmo {} no soportado: {}", algoritmo, e.getMessage());
        } catch (Exception e) {
            // Captura genérica para cualquier otro error
            LOGGER.error("Error calculando hash con {}: {}", algoritmo, e.getMessage());
        }
    }
}