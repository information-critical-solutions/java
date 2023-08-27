package org.ics.ejemplos.holamundojavafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class MainController implements Initializable {

    /**
     * Bitacora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @FXML
    private Button buttonPresioname;

    @FXML
    private TextField textA;

    @FXML
    private TextField textB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonPresioname.setOnAction((t) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStyleClass().add("confirmation-dialog");
            alert.setTitle("Hola mundo ");
            alert.setHeaderText("Hola mundo");
            alert.setContentText("Hola mundo desde Java FX");
            alert.showAndWait();
        });
    }

}
