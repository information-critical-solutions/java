package mx.up.redes.whatsup.client;

import mx.up.redes.whatsup.util.Protocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = Protocol.DEFAULT_PORT;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private String username;
    private boolean connected = false;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;

        ClientMain client = new ClientMain();
        client.start(host, port);
    }

    public void start(String host, int port) {
        logger.info("Iniciando cliente WhatsApp. Conectando a {}:{}", host, port);
        try {
            connectToServer(host, port);
            registerUser();
            startMessageListener();
            handleUserInput();
        } catch (IOException e) {
            logger.error("Error de conexión: {}", e.getMessage(), e);
            System.err.println("Error de conexión: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void connectToServer(String host, int port) throws IOException {
        System.out.println("Conectando a " + host + ":" + port + "...");
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        scanner = new Scanner(System.in);
        connected = true;
        logger.info("Conexión establecida exitosamente a {}:{}", host, port);
        System.out.println("Conectado al servidor!");
    }

    private void registerUser() throws IOException {
        System.out.print("Ingrese su nombre de usuario: ");
        username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            logger.error("Usuario intentó registrarse con nombre vacío");
            throw new IOException("El nombre de usuario no puede estar vacío");
        }

        // Enviar comando de registro
        logger.debug("Enviando comando de registro para usuario: {}", username);
        out.println(Protocol.REGISTER + " " + username);

        // Esperar respuesta del servidor
        String response = in.readLine();
        if (response == null) {
            logger.error("El servidor cerró la conexión durante el registro");
            throw new IOException("El servidor cerró la conexión");
        }

        logger.debug("Respuesta de registro recibida: {}", response);

        if (response.equals(Protocol.OK)) {
            logger.info("Usuario registrado exitosamente: {}", username);
            System.out.println("✓ Registrado exitosamente como: " + username);
            showHelp();
        } else if (response.startsWith(Protocol.ERROR)) {
            String errorMsg = response.substring(Protocol.ERROR.length() + 1);
            logger.warn("Error de registro: {}", errorMsg);
            throw new IOException("Error de registro: " + errorMsg);
        } else {
            logger.error("Respuesta inesperada del servidor durante registro: {}", response);
            throw new IOException("Respuesta inesperada del servidor: " + response);
        }
    }

    private void startMessageListener() {
        Thread listenerThread = new Thread(new mx.up.redes.whatsup.client.MessageListener(in));
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    private void handleUserInput() {
        System.out.println("\nEsperando comandos...");

        while (connected) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            processCommand(input);
        }
    }

    private void processCommand(String input) {
        if (input.startsWith("/")) {
            handleSlashCommand(input);
        } else {
            System.out.println("Comando no reconocido. Use /help para ver los comandos disponibles.");
        }
    }

    private void handleSlashCommand(String command) {
        String[] parts = command.split(" ", 3);
        String cmd = parts[0].toLowerCase();

        switch (cmd) {
            case "/help":
            case "/ayuda":
                showHelp();
                break;
            case "/list":
            case "/lista":
                requestUserList();
                break;
            case "/send":
            case "/enviar":
                handleSendCommand(parts);
                break;
            case "/quit":
            case "/salir":
                quit();
                break;
            default:
                System.out.println("Comando desconocido: " + cmd);
                System.out.println("Use /help para ver los comandos disponibles.");
        }
    }

    private void handleSendCommand(String[] parts) {
        if (parts.length < 3) {
            System.out.println("Formato: /send <destinatario> <mensaje>");
            return;
        }

        String recipient = parts[1];
        String message = parts[2];

        logger.info("Enviando mensaje a {}: {}", recipient, message);
        out.println(Protocol.SEND + " " + recipient + " " + message);
        System.out.println("Mensaje enviado a " + recipient + ": " + message);
    }

    private void requestUserList() {
        logger.debug("Solicitando lista de usuarios al servidor");
        out.println(Protocol.LIST);
    }

    private void quit() {
        logger.info("Usuario {} iniciando desconexión", username);
        System.out.println("Desconectando...");
        connected = false;
        out.println(Protocol.QUIT);
    }

    private void showHelp() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║          COMANDOS DISPONIBLES        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ /help                - Esta ayuda    ║");
        System.out.println("║ /list                - Lista usuarios║");
        System.out.println("║ /send <user> <msg>   - Enviar mensaje║");
        System.out.println("║ /quit                - Salir         ║");
        System.out.println("╚══════════════════════════════════════╝\n");
    }

    private void disconnect() {
        connected = false;
        logger.info("Iniciando proceso de desconexión para usuario: {}", username);
        try {
            if (scanner != null) scanner.close();
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
            logger.info("Desconexión completada para usuario: {}", username);
        } catch (IOException e) {
            logger.error("Error durante desconexión para usuario {}: {}", username, e.getMessage());
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
        System.out.println("Desconectado del servidor. ¡Hasta luego!");
    }
}