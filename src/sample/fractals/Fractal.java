package sample.fractals;

import javafx.animation.Timeline;

public interface Fractal {

    public Timeline getAnimation(double speed, String color, int depth);

}
