package view;

import games.pong.model.Pong;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;
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
        Game pong = new Pong();
        GameLoop gameLoop = new GameLoop(pong);

        Scene scene = new Scene(new Pane(pong.getCanvas()));
        primaryStage.setScene(scene);
        primaryStage.show();
        gameLoop.start();
        
        primaryStage.setOnCloseRequest(e -> gameLoop.stop());
    }
}
