package sample.fractals;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

public abstract class Fractal {

    private Pane pane;

    public Fractal(Pane pane) {
        this.pane = pane;
    }

    public abstract Timeline getAnimation(double speed, String color, int depth);

    public Pane getPane() {
        return this.pane;
    }
}
