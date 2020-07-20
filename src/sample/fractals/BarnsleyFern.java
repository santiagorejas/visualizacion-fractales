package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class BarnsleyFern extends Fractal {

    public BarnsleyFern(Pane pane) {
        super(pane);
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        for (Ellipse point: this.getPoints(depth, "-fx-stroke: #" + color)) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(point));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;
    }

    private List<Ellipse> getPoints(int depth, String color) {

        List<Ellipse> points = new ArrayList<Ellipse>();

        double x = 0.0;
        double y = 0.0;

        double w = super.getPane().getWidth();
        double h = super.getPane().getHeight();

        for (int i = 0; i < depth; i++) {
            Ellipse point = new Ellipse(
                    w/2 + x*w/10,
                    h-y*h/10,
                    0.1,
                    0.1);
            point.setStyle(color);
            points.add(point);

            double random = Math.random();

            if (random <= 0.01) {
                x = 0.0;
                y = 0.16*y;
            } else if (random <= 0.08) {
                x = 0.2 * x - 0.26 * y;
                y = 0.23 * x + 0.22 * y + 1.6;
            } else if (random <= 0.15) {
                x = -0.15 * x + 0.28 * y;
                y = 0.26 * x + 0.24 * y + 0.44;
            } else {
                x = 0.85 * x + 0.04 * y;
                y = -0.04 * x + 0.85 * y + 1.6;
            }
        }

        return points;
    }

}
