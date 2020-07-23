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
import javafx.scene.paint.Color;
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

    private Alert infoAlert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fractalsBox.setValue("Sierpinski Carpet");
        depthSlider.setMin(1);
        depthSlider.setMax(6);
        speedSlider.setMin(1);
        speedSlider.setMax(100);

        backgroundColor.setValue(Color.valueOf("#2c3e50"));


        stopBtn.setDisable(true);

        fractalsBox.getItems().add("Barnsley Fern");
        fractalsBox.getItems().add("Cantor Set");
        fractalsBox.getItems().add("Fractal tree 30º");
        fractalsBox.getItems().add("Koch Snowflake");
        fractalsBox.getItems().add("Pythagoras Tree");
        fractalsBox.getItems().add("Sierpinski Carpet");
        fractalsBox.getItems().add("Sierpinski Triangle");

        this.infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("About");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText("Creado por Santiago Orejas.\n" + "WWW.github.com/santiagorejas");

    }

    private void disableButtons(boolean bool) {

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
            case "Barnsley Fern": {
                return new BarnsleyFern(animationPane);
            }
            case "Cantor Set": {
                return new CantorSet(animationPane);
            }
            case "Fractal tree 30º": {
                return new Tree(animationPane, 30);
            }
            case "Koch Snowflake": {
                return new KochSnowflake(animationPane);
            }
            case "Pythagoras Tree": {
                return new PythagorasTree(animationPane);
            }
            case "Sierpinski Carpet": {
                return new SierpinskiCarpet(animationPane);
            }
            case "Sierpinski Triangle": {
                return new SierpinskiTriangle(animationPane);
            }
            default:
                return null;
        }
    }

    public void fractalSelected() {

        switch(fractalsBox.getValue()) {
            case "Barnsley Fern": {
                depthSlider.setMin(1000);
                depthSlider.setMax(25000);
                speedSlider.setMin(90);
                speedSlider.setMax(100);
                break;
            }
            case "Cantor Set": {
                depthSlider.setMin(1);
                depthSlider.setMax(7);
                speedSlider.setMin(1);
                speedSlider.setMax(100);
                break;
            }
            case "Fractal tree 30º": {
                depthSlider.setMin(1);
                depthSlider.setMax(8);
                speedSlider.setMin(1);
                speedSlider.setMax(100);
                break;
            }
            case "Koch Snowflake": {
                depthSlider.setMin(1);
                depthSlider.setMax(6);
                speedSlider.setMin(25);
                speedSlider.setMax(100);
                break;
            }
            case "Pythagoras Tree": {
                depthSlider.setMin(1);
                depthSlider.setMax(9);
                speedSlider.setMin(1);
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
            case "Sierpinski Triangle": {
                depthSlider.setMin(1);
                depthSlider.setMax(7);
                speedSlider.setMin(1);
                speedSlider.setMax(75);
                break;
            }
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
        infoAlert.showAndWait();
    }

    public void exitApp() {
        Platform.exit();
        System.exit(0);
    }
}
