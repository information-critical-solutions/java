package mx.up.redes.whatsup.util;

/**
 * Protocolo de comunicación para WhatsApp Clone
 * Comandos de texto plano separados por espacios
 */
public final class Protocol {
    public static final int DEFAULT_PORT = 5050;

    // Comandos Cliente -> Servidor
    public static final String REGISTER = "REGISTER";  // REGISTER <nombre>
    public static final String SEND = "SEND";          // SEND <destinatario> <mensaje>
    public static final String LIST = "LIST";          // LIST
    public static final String QUIT = "QUIT";          // QUIT

    // Respuestas Servidor -> Cliente
    public static final String OK = "OK";              // Registro exitoso
    public static final String ERROR = "ERROR";        // ERROR <descripcion>
    public static final String USERS = "USERS";        // USERS <lista_csv>
    public static final String MESSAGE = "MESSAGE";    // MESSAGE <remitente> <texto>

    private Protocol() {}
}