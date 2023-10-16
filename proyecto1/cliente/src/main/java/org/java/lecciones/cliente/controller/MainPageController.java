package org.java.lecciones.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.java.lecciones.cliente.conexion.ConexionNodo;
import org.java.lecciones.libreriacomunicacion.Mensaje;

/**
 * Controlador de la página principal de la interfaz de usuario. Permite enviar
 * mensajes a través de una conexión y muestra los mensajes recibidos.
 *
 * @author Sebastian Godinez Borja
 */
public class MainPageController implements Initializable {

    @FXML
    private Button enviarButton;

    @FXML
    private Button limpiarButton;

    @FXML
    private TextField mensajeTextField;

    @FXML
    private TextArea mensajesRecibidosTextArea;

    private final ConexionNodo cn;

    /**
     * Constructor del controlador.
     *
     * @param cn La instancia de ConexionNodo para la comunicación.
     */
    public MainPageController(ConexionNodo cn) {
        this.cn = cn;
    }

    /**
     * Inicializa el controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        enviarButton.setOnAction((t) -> {
            String mensaje = mensajeTextField.getText();
            if (mensaje.isBlank()) {
                showAlert(Alert.AlertType.ERROR, "Enviando un mensaje", "El mensaje a enviar no puede ser nulo");
            }
            Mensaje m = new Mensaje();
            m.setTipoOperacion((short) 1);
            m.setDatos(mensaje.getBytes());
            try {
                cn.enviarMensaje(m);
            } catch (IOException ex) {
                showAlert(Alert.AlertType.ERROR, "Enviando un mensaje", "Al escribir el mensaje sobre el socket");
            }
        });

        limpiarButton.setOnAction((t) -> {
            mensajesRecibidosTextArea.clear();
        });

        cn.insertaAccionProcesaMensaje((short) 1, (m) -> {
            mensajesRecibidosTextArea.setText(mensajesRecibidosTextArea.getText() + "\n" + m.getTipoOperacion() + "-" + new String(m.getDatos()));
        });
    }

    private void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
