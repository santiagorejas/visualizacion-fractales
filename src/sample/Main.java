package sample;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public JFXButton exitBtn;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("resources/sample.fxml"));
        primaryStage.setTitle("Visualiziador de fractales");
        int width = 890;
        int height = 500;
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("resources/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setResizable(false);
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
