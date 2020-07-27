package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Tree extends Fractal {

    private int forkAngle;
    private final double base = 10;

    public Tree(Pane pane, int forkAngle) {
        super(pane);
        this.forkAngle = forkAngle;
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();

        this.getLines(
                335.0,
                490.0,
                lines,
                depth,
                -90,
                color
        );

        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(line));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;
    }

    private void getLines(double x, double y, List<Line> lines, int depth, int angle, String color) {
        if (depth > 0) {
            double x2 = x + Math.cos(Math.toRadians(angle)) * depth * base;
            double y2 = y + Math.sin(Math.toRadians(angle)) * depth * base;
            Line line = new Line(x, y, x2, y2);
            line.setStyle("-fx-stroke: #" + color);
            lines.add(line);
            this.getLines(x2, y2, lines, depth - 1, angle + forkAngle, color);
            this.getLines(x2, y2, lines, depth - 1, angle - forkAngle, color);
        }
    }

}
