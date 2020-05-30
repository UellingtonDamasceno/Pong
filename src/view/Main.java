package view;

import game.model.Pong;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
//        Label label = new Label("X : 0 || Y : 0");
//        
//        pong.getCanvas().setOnMouseMoved((event) -> {
//            label.setText("X : "+ event.getX() + " || " + "Y : "+ event.getY());
//        });
//        
//        pong.getCanvas().setOnMouseExited(event -> label.setText("X : 0 || Y : 0"));
//        
//        HBox hbox = new HBox(label);
//        hbox.setAlignment(Pos.CENTER);

        borderPane.setCenter(pong.getCanvas());
//        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(pong.getKeyListener());
        scene.setOnKeyReleased(pong.getKeyListener());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(pong.getName());
        primaryStage.setResizable(false);
        primaryStage.show();

        gameLoop.start();
        primaryStage.setOnCloseRequest(e -> gameLoop.stop());
    }
}
