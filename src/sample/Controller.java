package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public JFXComboBox<String> fractalsBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fractalsBox.getItems().add("Fractal 1");
        fractalsBox.getItems().add("Fractal 2");
        fractalsBox.getItems().add("Fractal 3");
        fractalsBox.getItems().add("Fractal 4");
        fractalsBox.getItems().add("Fractal 5");
        fractalsBox.getItems().add("Fractal 6");
    }

}
