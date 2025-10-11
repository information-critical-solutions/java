package mx.up.cripto.cifrado.asimetrico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSADemo {
    private static final Logger logger = LogManager.getLogger(RSADemo.class);

    // Método para generar un par de llaves (pública y privada)
    public static KeyPair generarLlaves() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); // Generador de llaves RSA
        keyGen.initialize(2048); // Tamaño de llave recomendado
        return keyGen.generateKeyPair(); // Regresa par de llaves
    }

    // Método para cifrar un mensaje con la llave pública
    public static String cifrar(String mensaje, PublicKey llavePublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Crear instancia RSA
        cipher.init(Cipher.ENCRYPT_MODE, llavePublica); // Iniciar en modo cifrado
        byte[] bytesCifrados = cipher.doFinal(mensaje.getBytes()); // Cifrar mensaje
        return Base64.getEncoder()
                .encodeToString(bytesCifrados); // Retornar en Base64
    }

    // Método para descifrar un mensaje con la llave privada
    public static String descifrar(String mensajeCifrado, PrivateKey llavePrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA"); // Crear instancia RSA
        cipher.init(Cipher.DECRYPT_MODE, llavePrivada); // Iniciar en modo descifrado
        byte[] bytesDescifrados = cipher.doFinal(Base64.getDecoder()
                .decode(mensajeCifrado)); // Descifrar
        return new String(bytesDescifrados); // Regresar texto original
    }

    // Programa principal
    public static void main(String[] args) {
        try {
            // Generar llaves
            KeyPair parDeLlaves = generarLlaves();
            PublicKey llavePublica = parDeLlaves.getPublic();
            PrivateKey llavePrivada = parDeLlaves.getPrivate();

            logger.info("Llave pública: " + Base64.getEncoder()
                    .encodeToString(llavePublica.getEncoded()));
            logger.info("Llave privada: " + Base64.getEncoder()
                    .encodeToString(llavePrivada.getEncoded()));

            // Mensaje a cifrar
            String mensajeOriginal = "Criptografía y Seguridad en Redes UP";
            logger.info("Mensaje original: " + mensajeOriginal);

            // Cifrado
            String mensajeCifrado = cifrar(mensajeOriginal, llavePublica);
            logger.info("Mensaje cifrado: " + mensajeCifrado);

            // Descifrado
            String mensajeDescifrado = descifrar(mensajeCifrado, llavePrivada);
            logger.info("Mensaje descifrado: " + mensajeDescifrado);

        } catch (Exception e) {
            logger.error("Error en la ejecución: ", e);
        }
    }
}
