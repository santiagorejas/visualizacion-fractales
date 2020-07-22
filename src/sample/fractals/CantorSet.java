package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class CantorSet extends Fractal {

    private final int distance = 25;

    public CantorSet(Pane pane) {
        super(pane);
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();

        this.calculateLines(
                10.0,
                660.0,
                200.0,
                lines,
                depth,
                color
        );


        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(line));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;

    }

    private void calculateLines(double x1, double x2, double y, List<Line> lines, int depth, String color) {
        if (depth > 0) {
            Line line = new Line(x1, y, x2, y);
            line.setStyle("-fx-stroke: #" + color + ";" + "-fx-stroke-width: " + 1 + ";");
            lines.add(line);
            double third = Math.abs(x2-x1)/3;
            this.calculateLines(
                    x1,
                    x1 + third,
                    y + this.distance,
                    lines,
                    depth - 1,
                    color
            );
            this.calculateLines(
                    x1 + third*2,
                    x2,
                    y + this.distance,
                    lines,
                    depth - 1,
                    color
            );
        }
    }

}
