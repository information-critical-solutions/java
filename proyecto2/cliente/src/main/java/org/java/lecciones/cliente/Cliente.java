package org.java.lecciones.cliente;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.java.lecciones.cliente.conexion.ConexionNodo;
import org.java.lecciones.cliente.controller.MainPageController;

/**
 * Clase principal de la aplicación del cliente. Esta clase inicia la interfaz
 * de usuario y la conexión al servidor.
 *
 * @author Sebastian Godinez Borja
 */
public class Cliente extends Application {

    /**
     * Ruta del controlador.
     */
    private final String PATH_FILE = "/fxml/MainPage.fxml";

    /**
     * Controlador de la pantalla principal.
     */
    private MainPageController mpc;

    private ConexionNodo cn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        cn = new ConexionNodo("localhost", 12345);
        cn.conectar();
        mpc = new MainPageController(cn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_FILE));
        fxmlLoader.setController(mpc);
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent, Color.DARKGREY);
        primaryStage.setTitle("Cliente de Mensajería"); // Establece el título de la ventana
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Método principal de la aplicación. Inicia la aplicación JavaFX.
     */
    public static void run() {
        launch();
    }
}
