package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import sample.fractals.*;

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

        fractalsBox.getItems().add("Cantor Set");
        fractalsBox.getItems().add("Sierpinski Carpet");
        fractalsBox.getItems().add("Barnsley Fern");
        fractalsBox.getItems().add("Fractal tree");
        fractalsBox.getItems().add("Sierpinski Triangle");
    }


    public void start() {
        animationPane.getChildren().clear();
        Fractal fractal = this.getFractal();
        Timeline timeline = fractal.getAnimation(
                speedSlider.getMax() - speedSlider.getValue() + 1,
                strokeColor.getValue().toString().substring(2),
                (int)Math.round(depthSlider.getValue())
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
            case "Sierpinski Triangle": {
                return new SierpinskiTriangle(animationPane);
            }
            case "Fractal tree": {
                return new Tree(animationPane, 30);
            }
            case "Barnsley Fern": {
                return new BarnsleyFern(animationPane);
            }
            case "Sierpinski Carpet": {
                return new SierpinskiCarpet(animationPane);
            }
            case "Cantor Set": {
                return new CantorSet(animationPane);
            }
            default:
                return null;
        }
    }

    public void fractalSelected() {

        /*
        depthSlider.setMin();
        depthSlider.setMax();
        speedSlider.setMin();
        speedSlider.setMax();
        */

        switch(fractalsBox.getValue()) {
            case "Sierpinski Triangle": {
                depthSlider.setMin(1);
                depthSlider.setMax(7);
                speedSlider.setMin(1);
                speedSlider.setMax(75);
                break;
            }
            case "Fractal tree": {
                depthSlider.setMin(1);
                depthSlider.setMax(8);
                speedSlider.setMin(1);
                speedSlider.setMax(100);
                break;
            }
            case "Barnsley Fern": {
                this.depthSlider.setMin(1000);
                this.depthSlider.setMax(30000);
                speedSlider.setMin(90);
                speedSlider.setMax(100);
                break;
            }
            case "Sierpinski Carpet": {
                depthSlider.setMin(1);
                depthSlider.setMax(6);
                speedSlider.setMin(1);
                speedSlider.setMax(100);
                break;
            }
            case "Cantor Set": {
                depthSlider.setMin(1);
                depthSlider.setMax(6);
                speedSlider.setMin(1);
                speedSlider.setMax(100);
                break;
            }
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
