package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class PythagorasTree extends Fractal{

    public PythagorasTree(Pane pane) {
        super(pane);
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();
        calculateLines(285.0, 490.0, 385.0, 490.0, lines, depth, color);

        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(line));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;
    }

    private void calculateLines(double ax, double ay, double bx, double by, List<Line> lines, int depth, String color) {

        if (depth > 0) {

            double cx = bx - (ay -  by);
            double cy = by - (bx - ax);
            double dx = ax - (ay -  by);
            double dy = ay - (bx - ax);
            double ex = dx +  ( bx - ax - (ay -  by) ) / 2;
            double ey = dy -  ( bx - ax + ay -  by ) / 2;

            Line l1, l2, l3, l4;

            l1 = new Line(ax, ay, bx, by);
            l2 = new Line(cx, cy, bx, by);
            l3 = new Line(cx, cy, dx, dy);
            l4 = new Line(ax, ay, dx, dy);

            l1.setStyle("-fx-stroke: #" + color);
            l2.setStyle("-fx-stroke: #" + color);
            l3.setStyle("-fx-stroke: #" + color);
            l4.setStyle("-fx-stroke: #" + color);

            lines.add(l1);
            lines.add(l2);
            lines.add(l3);
            lines.add(l4);

            this.calculateLines(dx, dy, ex, ey, lines, depth - 1, color);
            this.calculateLines(ex, ey, cx, cy, lines, depth - 1, color);

        }
    }


}
