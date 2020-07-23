package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class KochSnowflake extends Fractal{

    private final int angle = 60;

    public KochSnowflake(Pane pane) {
        super(pane);
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();
        calculateLines(100.0, 163.0, 580.0, 163.0, lines, depth, color);
        calculateLines(580.0, 163.0, 335.0, 490.0, lines, depth, color);
        calculateLines(335.0, 490.0, 100.0, 163.0, lines, depth, color);

        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(line));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;
    }

    private void calculateLines(double x1, double y1, double x2, double y2, List<Line> lines, int depth, String color) {
        if (depth > 0) {

            double ax = (2 * x1 + x2)/3;
            double ay = (2 * y1 + y2)/3;

            double cx = (2 * x2 + x1)/3;
            double cy = (2 * y2 + y1)/3;

            double ang = Math.toRadians(this.angle);

            double bx = ax + (cx - ax) * Math.cos(ang) + (cy - ay) * Math.sin(ang);
            double by = ay - (cx - ax) * Math.sin(ang) + (cy - ay) * Math.cos(ang);

            this.calculateLines(x1, y1, ax, ay, lines, depth - 1, color);
            this.calculateLines(ax, ay, bx, by, lines, depth - 1, color);
            this.calculateLines(bx, by, cx, cy, lines, depth - 1, color);
            this.calculateLines(cx, cy, x2, y2, lines, depth - 1, color);

        } else {
            Line line = new Line(x1, y1, x2, y2);
            line.setStyle("-fx-stroke: #" + color);
            lines.add(line);
        }
    }



}
