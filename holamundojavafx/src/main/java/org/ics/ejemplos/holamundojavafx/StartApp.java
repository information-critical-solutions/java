package org.ics.ejemplos.holamundojavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ics.ejemplos.holamundojavafx.controller.MainController;

/**
 *
 * @author Sebastian Godinez Borja
 */
public class StartApp extends Application {

    /**
     * Bitácora de la clase
     */
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    /**
     * Ruta del controlador
     */
    private final String PATH_FILE = "/fxml/MainPage.fxml";
    /**
     * Controlador de la pantalla principal
     */
    private final MainController mainController = new MainController();

    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PATH_FILE));
        fxmlLoader.setController(mainController);
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent, Color.DARKGREY);
        primaryStage.setTitle("");
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        LOGGER.trace("Loading windows...");
    }

    public static void run() {
        launch();
    }

}
