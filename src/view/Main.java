package view;

import games.pong.model.Pong;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameLoop;

/**
 *
 * @author Uellington Conceição
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pong pong = new Pong("Pong");
        GameLoop gameLoop = new GameLoop(pong);
        
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(pong.getCanvas());
        Scene scene = new Scene(borderPane);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(pong.getName());
        primaryStage.setResizable(false);
        primaryStage.show();

        scene.setOnKeyPressed(pong);

        gameLoop.start();
        primaryStage.setOnCloseRequest(e -> gameLoop.stop());
    }
}
