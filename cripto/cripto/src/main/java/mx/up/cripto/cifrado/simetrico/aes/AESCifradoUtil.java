package mx.up.cripto.cifrado.simetrico.aes;

import javax.crypto.Cipher;                  // Para operaciones de cifrado y descifrado
import javax.crypto.SecretKey;               // Representa la clave simétrica
import javax.crypto.spec.SecretKeySpec;      // Para reconstruir la clave desde bytes
import java.util.Base64;                     // Para codificar y decodificar en Base64
import org.apache.logging.log4j.LogManager;  // LogManager de log4j2
import org.apache.logging.log4j.Logger;      // Logger de log4j2

public class AESCifradoUtil {

    // Logger de la clase
    private static final Logger logger = LogManager.getLogger(AESCifradoUtil.class);

    /**
     * Método que cifra y descifra un texto con AES a partir de una llave en Base64
     *
     * @param llaveBase64  Llave AES en formato Base64 (debe ser de 16, 24 o 32 bytes)
     * @param textoOriginal Texto plano a cifrar
     */
    public static void procesarAES(String llaveBase64, String textoOriginal) {
        try {
            // Mostramos el texto original y la llave
            logger.info("Texto original: {}", textoOriginal);
            logger.info("Llave AES proporcionada (Base64): {}", llaveBase64);

            // Reconstruimos la clave secreta desde el string Base64
            byte[] llaveBytes = Base64.getDecoder().decode(llaveBase64);
            SecretKey claveSecreta = new SecretKeySpec(llaveBytes, "AES");

            // ---------- CIFRADO ----------
            Cipher cifrador = Cipher.getInstance("AES"); // Creamos instancia AES
            cifrador.init(Cipher.ENCRYPT_MODE, claveSecreta); // Inicializamos en modo cifrado

            byte[] textoCifrado = cifrador.doFinal(textoOriginal.getBytes("UTF-8")); // Ciframos
            String textoCifradoBase64 = Base64.getEncoder().encodeToString(textoCifrado); // Codificamos en Base64

            logger.info("Texto cifrado (Base64): {}", textoCifradoBase64);

            // ---------- DESCIFRADO ----------
            Cipher descifrador = Cipher.getInstance("AES"); // Otra instancia de AES
            descifrador.init(Cipher.DECRYPT_MODE, claveSecreta); // Inicializamos en modo descifrado

            byte[] textoDescifradoBytes = descifrador.doFinal(Base64.getDecoder().decode(textoCifradoBase64)); // Desciframos
            String textoDescifrado = new String(textoDescifradoBytes, "UTF-8"); // Convertimos a String

            logger.info("Texto descifrado: {}", textoDescifrado);

        } catch (Exception e) {
            logger.error("Error en el cifrado/descifrado: {}", e.getMessage());
        }
    }

    // Método main para probar la utilidad
    public static void main(String[] args) {
        // Llave de ejemplo en Base64 ("1234567890123456" en texto plano → Base64)
        String llaveBase64 = Base64.getEncoder().encodeToString("1234567890123456".getBytes());
        String texto = "Hola UP con AES en un método";

        // Llamamos a nuestro método utilitario
        procesarAES(llaveBase64, texto);
    }
}
