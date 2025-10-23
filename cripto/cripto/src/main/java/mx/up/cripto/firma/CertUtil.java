package mx.up.cripto.firma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

/**
 * Utilería para:
 * - generar KeyPair RSA
 * - crear certificado X.509 auto-firmado (usando BouncyCastle)
 * - guardar/leer certificado en PEM
 * - firmar/validar datos con Signature (SHA256withRSA)
 *
 * Comentarios explicativos en cada línea para docencia.
 */
public class CertUtil {

    // Logger de la clase para salidas y debug (log4j2)
    private static final Logger logger = LogManager.getLogger(CertUtil.class);

    /**
     * Genera un par de llaves RSA (privada + pública)
     *
     * @param keySize tamaño de la llave en bits (ej. 2048)
     * @return KeyPair generado
     * @throws NoSuchAlgorithmException si RSA no está disponible (muy improbable)
     */
    public static KeyPair generateRSAKeyPair(int keySize) throws NoSuchAlgorithmException {
        // Se obtiene un generador de llaves para RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        // Se inicializa con el tamaño de llave solicitado (2048 recomendado)
        keyGen.initialize(keySize);
        // Se genera y retorna el par de llaves
        return keyGen.generateKeyPair();
    }

    /**
     * Crea un certificado X.509 auto-firmado (válido por "days" días)
     * Utiliza BouncyCastle para construir el certificado.
     *
     * @param keyPair par de llaves RSA
     * @param subjectDN Distinguished Name del sujeto (ej: "CN=Alumno, O=UP, C=MX")
     * @param days     validez en días
     * @return X509Certificate auto-firmado
     * @throws Exception si falla la construcción (depende de BC)
     */
    public static X509Certificate generateSelfSignedCertificate(KeyPair keyPair, String subjectDN, int days) throws Exception {
        // Definimos la entidad (subject / issuer) usando X500Name
        X500Name issuer = new X500Name(subjectDN);
        X500Name subject = issuer; // auto-firmado → issuer == subject

        // Fecha de inicio de validez: ahora
        Date notBefore = new Date();

        // Fecha de expiración: now + days
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(notBefore);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date notAfter = calendar.getTime();

        // Número de serie aleatorio (no debe repetirse)
        BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());

        // Construimos el certificado (X509 v3)
        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA")
                .build(keyPair.getPrivate()); // signer que usará la clave privada para la firma

        // Builder para el certificado
        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                issuer,        // issuer DN
                serial,        // serial number
                notBefore,     // notBefore
                notAfter,      // notAfter
                subject,       // subject DN
                keyPair.getPublic() // public key del sujeto
        );

        // Convertimos el holder a certificado X509 de Java
        X509CertificateHolder certHolder = certBuilder.build(signer);
        X509Certificate cert = new JcaX509CertificateConverter()
                .getCertificate(certHolder);

        // Retornamos el certificado auto-firmado
        return cert;
    }

    /**
     * Guarda un certificado X.509 en formato PEM en un archivo
     *
     * @param cert     certificado a guardar
     * @param filePath ruta del archivo PEM de salida
     * @throws IOException si falla la escritura
     */
    public static void writeCertToPEM(X509Certificate cert, String filePath) throws IOException {
        // Abrimos writer al archivo de salida
        try (JcaPEMWriter pemWriter = new JcaPEMWriter(new FileWriter(filePath))) {
            // Escribimos el objeto certificado en formato PEM
            pemWriter.writeObject(cert);
            // Flush implícito en try-with-resources
        }
        logger.info("Certificado guardado en PEM: {}", filePath);
    }

    /**
     * Lee un certificado X.509 desde un archivo (PEM o DER)
     *
     * @param filePath ruta del archivo
     * @return X509Certificate leído
     * @throws Exception si falla la lectura/parsing
     */
    public static X509Certificate readCertFromFile(String filePath) throws Exception {
        // Leemos todos los bytes del archivo
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));

        // Intentamos construir CertificateFactory para X.509
        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        // Si el archivo es PEM (texto con encabezado -----BEGIN CERTIFICATE-----),
        // CertificateFactory puede leerlo si le pasamos InputStream con el contenido.
        try (InputStream in = new ByteArrayInputStream(bytes)) {
            Certificate cert = factory.generateCertificate(in);
            return (X509Certificate) cert;
        }
    }

    /**
     * Firma datos en memoria usando SHA256withRSA y la clave privada proporcionada.
     *
     * @param data       bytes a firmar
     * @param privateKey clave privada para firmar
     * @return arreglo de bytes con la firma
     * @throws Exception si falla el proceso de firma
     */
    public static byte[] signData(byte[] data, PrivateKey privateKey) throws Exception {
        // Creamos instancia de Signature con algoritmo SHA256 + RSA
        Signature signature = Signature.getInstance("SHA256withRSA");
        // Inicializamos en modo firma con la clave privada
        signature.initSign(privateKey);
        // Alimentamos los datos a firmar
        signature.update(data);
        // Generamos la firma y la retornamos
        return signature.sign();
    }

    /**
     * Verifica la firma de unos datos usando la clave pública (o certificado).
     *
     * @param data      bytes originales
     * @param sig       bytes de la firma
     * @param publicKey clave pública para verificar
     * @return true si la firma es válida, false en caso contrario
     * @throws Exception si falla el proceso de verificación
     */
    public static boolean verifySignature(byte[] data, byte[] sig, PublicKey publicKey) throws Exception {
        // Instanciamos Signature con el mismo algoritmo usado para firmar
        Signature signature = Signature.getInstance("SHA256withRSA");
        // Inicializamos en modo verificación con la clave pública
        signature.initVerify(publicKey);
        // Alimentamos los datos originales
        signature.update(data);
        // Verificamos la firma
        return signature.verify(sig);
    }

    /**
     * Guarda un KeyPair en un KeyStore PKCS12 (archivo .p12)
     *
     * @param keyPair     par de llaves (contiene la privada)
     * @param cert        certificado (X509) correspondiente a la clave pública
     * @param alias       alias dentro del keystore
     * @param password    password del keystore
     * @param outputP12   ruta de salida del PKCS12
     * @throws Exception si falla la operación
     */
    public static void saveKeyPairToPKCS12(KeyPair keyPair, X509Certificate cert,
                                           String alias, char[] password, String outputP12) throws Exception {
        // Creamos un KeyStore de tipo PKCS12
        KeyStore pkcs12 = KeyStore.getInstance("PKCS12");
        // Inicializamos el keystore vacío
        pkcs12.load(null, null);

        // Guardamos la clave privada (y la cadena de certificados si aplica)
        Certificate[] chain = new Certificate[]{cert};
        pkcs12.setKeyEntry(alias, keyPair.getPrivate(), password, chain);

        // Escribimos el keystore en archivo
        try (FileOutputStream fos = new FileOutputStream(outputP12)) {
            pkcs12.store(fos, password);
        }

        logger.info("Keystore PKCS12 guardado en: {}", outputP12);
    }

    /**
     * Lee un KeyStore PKCS12 y retorna la PrivateKey y X509Certificate para el alias indicado.
     *
     * @param p12Path  ruta del archivo PKCS12
     * @param password contraseña del keystore
     * @param alias    alias de la entrada
     * @return Object[] {PrivateKey, X509Certificate}
     * @throws Exception si falla la lectura
     */
    public static Object[] readKeyPairFromPKCS12(String p12Path, char[] password, String alias) throws Exception {
        // Cargamos el KeyStore desde archivo
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(p12Path)) {
            ks.load(fis, password);
        }

        // Obtenemos la clave privada (necesitamos la password para la clave)
        Key key = ks.getKey(alias, password);
        if (!(key instanceof PrivateKey)) {
            throw new IllegalStateException("La entrada no contiene PrivateKey");
        }
        PrivateKey privateKey = (PrivateKey) key;

        // Obtenemos el certificado asociado
        Certificate cert = ks.getCertificate(alias);
        X509Certificate x509 = (X509Certificate) cert;

        return new Object[]{privateKey, x509};
    }

    /**
     * Método main de demostración:
     * - Genera par de llaves y certificado auto-firmado
     * - Guarda certificado en PEM
     * - Guarda keystore PKCS12 con la privada
     * - Firma un mensaje y verifica la firma
     */
    public static void main(String[] args) {
        try {
            // 1) Generar par de llaves RSA 2048 bits
            logger.info("Generando par de llaves RSA 2048 bits...");
            KeyPair keyPair = generateRSAKeyPair(2048);

            // 2) Crear certificado X.509 auto-firmado válido 365 días
            logger.info("Generando certificado X.509 auto-firmado...");
            X509Certificate cert = generateSelfSignedCertificate(keyPair, "CN=Sebas, O=UP, C=MX", 365);

            // 3) Guardar certificado en PEM (para exportar a otros)
            String certPemPath = "certificado_alumno.pem";
            writeCertToPEM(cert, certPemPath);

            // 4) Guardar par en PKCS12 (keystore) para uso posterior
            String p12Path = "keystore_alumno.p12";
            char[] p12Password = "changeit".toCharArray(); // contraseña de ejemplo (NO usar en producción)
            saveKeyPairToPKCS12(keyPair, cert, "alumno", p12Password, p12Path);

            // 5) Mensaje de ejemplo a firmar
            String mensaje = "Mensaje para firmar: Seguridad en Redes UP";
            byte[] mensajeBytes = mensaje.getBytes("UTF-8");
            logger.info("Mensaje original: {}", mensaje);

            // 6) Firmar con la clave privada
            logger.info("Firmando el mensaje con la clave privada...");
            byte[] firma = signData(mensajeBytes, keyPair.getPrivate());
            String firmaBase64 = java.util.Base64.getEncoder().encodeToString(firma);
            logger.info("Firma (Base64): {}", firmaBase64);

            // 7) Verificar la firma usando la clave pública del certificado
            logger.info("Verificando la firma con la clave pública del certificado...");
            boolean valido = verifySignature(mensajeBytes, firma, cert.getPublicKey());
            logger.info("Firma válida: {}", valido);

            // 8) Demostración: leer keystore y firmar/validar desde allí
            logger.info("Leyendo keystore PKCS12 y verificando firma desde él...");
            Object[] keyPairRead = readKeyPairFromPKCS12(p12Path, p12Password, "alumno");
            PrivateKey pkFromStore = (PrivateKey) keyPairRead[0];
            X509Certificate certFromStore = (X509Certificate) keyPairRead[1];

            // 9) Firmar con la clave privada leída del keystore
            byte[] firma2 = signData(mensajeBytes, pkFromStore);
            boolean valido2 = verifySignature(mensajeBytes, firma2, certFromStore.getPublicKey());
            logger.info("Firma desde keystore válida: {}", valido2);

            // 10) Leer certificado desde el archivo PEM y verificar firma previamente generada
            logger.info("Leyendo certificado desde archivo PEM y verificando firma original...");
            X509Certificate certFromFile = readCertFromFile(certPemPath);
            boolean valido3 = verifySignature(mensajeBytes, firma, certFromFile.getPublicKey());
            logger.info("Verificación con certificado desde archivo (original): {}", valido3);

        } catch (Exception e) {
            // En caso de error lo logueamos con stacktrace para facilitar debugging en clase
            logger.error("Error en demo de firma electrónica: ", e);
        }
    }
}
