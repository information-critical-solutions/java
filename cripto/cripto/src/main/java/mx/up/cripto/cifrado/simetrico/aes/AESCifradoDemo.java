package mx.up.cripto.cifrado.simetrico.aes;

import javax.crypto.Cipher;                  // Clase principal para cifrado y descifrado
import javax.crypto.KeyGenerator;            // Generador de llaves simétricas
import javax.crypto.SecretKey;               // Representa la llave simétrica
import java.util.Base64;                     // Para codificar en Base64
import org.apache.logging.log4j.LogManager;  // LogManager para crear logger
import org.apache.logging.log4j.Logger;      // Logger de log4j2

public class AESCifradoDemo {

    // Logger de la clase
    private static final Logger LOGGER = LogManager.getLogger(AESCifradoDemo.class);

    public static void main(String[] args) {
        try {
            // Texto original que vamos a cifrar
            String textoOriginal = "Hola UP, cifrado simétrico con AES";

            // Mostramos el texto original
            LOGGER.info("Texto original: {}", textoOriginal);

            // Generamos una clave secreta para AES de 128 bits
            KeyGenerator generador = KeyGenerator.getInstance("AES"); // Usamos el algoritmo AES
            generador.init(128); // Tamaño de clave: 128 bits
            SecretKey claveSecreta = generador.generateKey(); // Generamos la clave

            // Creamos un objeto Cipher para cifrar
            Cipher cifrador = Cipher.getInstance("AES"); // Instancia con algoritmo AES
            cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta); // Inicializamos en modo cifrado con la clave

            // Ciframos el texto (se transforma a bytes)
            byte[] textoCifrado = cifrador.doFinal(textoOriginal.getBytes("UTF-8"));

            // Codificamos en Base64 para que sea legible
            String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado);

            // Mostramos el texto cifrado
            LOGGER.info("Texto cifrado (Base64): {}", textoCifradoBase64);

            // Creamos un objeto Cipher para descifrar
            Cipher descifrador = Cipher.getInstance("AES"); // Otra instancia de AES
            descifrador.init(Cipher.DECRYPT_MODE, claveSecreta); // Inicializamos en modo descifrado con la misma clave

            // Desciframos el texto cifrado
            byte[] textoDescifradoBytes = descifrador.doFinal(Base64.getDecoder().decode(textoCifradoBase64));

            // Convertimos los bytes a String
            String textoDescifrado = new String(textoDescifradoBytes, "UTF-8");

            // Mostramos el texto descifrado
            LOGGER.info("Texto descifrado: {}", textoDescifrado);

        } catch (Exception e) {
            // Capturamos cualquier excepción y la mostramos con log de error
            LOGGER.error("Error en el cifrado/descifrado: {}", e.getMessage());
        }
    }
}
