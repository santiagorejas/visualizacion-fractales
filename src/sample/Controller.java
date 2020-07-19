package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.fractals.Fractal;
import sample.fractals.SierpinskiTriangle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public JFXComboBox<String> fractalsBox;
    public Pane animationPane;

    public JFXSlider speedSlider;
    public JFXSlider depthSlider;

    public ColorPicker strokeColor;
    public ColorPicker backgroundColor;

    public JFXButton stopBtn;
    public JFXButton startBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stopBtn.setDisable(true);

        depthSlider.setMin(1);
        //speedSlider.setMin(1);

        fractalsBox.getItems().add("Fractal 1");
        fractalsBox.getItems().add("Fractal 2");
        fractalsBox.getItems().add("Fractal 3");
        fractalsBox.getItems().add("Fractal 4");
        fractalsBox.getItems().add("Fractal 5");
        fractalsBox.getItems().add("SierpinskiTriangle");
    }


    public void start() {
        animationPane.getChildren().clear();
        SierpinskiTriangle f = new SierpinskiTriangle(animationPane);
        Timeline timeline = f.getAnimation(
                speedSlider.getMax() - speedSlider.getValue() + 1,
                strokeColor.getValue().toString().substring(2),
                (int) depthSlider.getValue()
        );
        startBtn.setDisable(true);
        stopBtn.setDisable(false);
        timeline.setOnFinished(e-> {
            startBtn.setDisable(false);
            stopBtn.setDisable(true);
        });
        timeline.play();
        stopBtn.setOnAction(e-> {
            timeline.stop();
            stopBtn.setDisable(true);
            startBtn.setDisable(false);
        });

    }

    private Fractal getFractal() {
        switch(fractalsBox.getValue()) {
            case "SierpinskiTriangle":
                return new SierpinskiTriangle(animationPane);
            /*case y:
                // code block
                break;*/
            default:
                return null;
        }
    }

    public void fractalSelected() {
        switch(fractalsBox.getValue()) {
            case "SierpinskiTriangle":
                depthSlider.setMax(7);
                speedSlider.setMax(75);
            /*case y:
                // code block
                break;*/
            default:
                return;
        }
    }

    public void backgroundColorChange() {
        System.out.println(backgroundColor.getValue().toString().substring(2));
        animationPane.setStyle("-fx-background-color: #" + backgroundColor.getValue().toString().substring(2));
    }


    public void cleanAnimation() {
        animationPane.getChildren().clear();
    }

    public void viewInfo() {
        System.out.println("Hola");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Creado por Santiago Orejas.");

        alert.showAndWait();
    }

    public void exitApp() {
        Platform.exit();
        System.exit(0);
    }
}
