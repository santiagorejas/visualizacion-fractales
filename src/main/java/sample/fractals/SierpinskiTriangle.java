package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SierpinskiTriangle extends Fractal{

    private Pane pane;

    public SierpinskiTriangle(Pane pane) {
        super(pane);
        this.pane = pane;
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();

        this.getLines(
                new Point2D(5.0, 490),
                new Point2D(330.0, 5.0),
                new Point2D(665.0, 490.0),
                depth,
                lines,
                color
        );

        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(line));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;
    }

    private void addTriangle(Point2D a, Point2D b, Point2D c, List<Line> lines, String color) {
        Line lineAB = new Line(a.getX(), a.getY(), b.getX(), b.getY());
        Line lineBC = new Line(b.getX(), b.getY(), c.getX(), c.getY());
        Line lineCA = new Line(c.getX(), c.getY(), a.getX(), a.getY());
        lineAB.setStyle("-fx-stroke: #" + color);
        lineBC.setStyle("-fx-stroke: #" + color);
        lineCA.setStyle("-fx-stroke: #" + color);
        lines.add(lineAB);
        lines.add(lineBC);
        lines.add(lineCA);
    }

    private Point2D getMidPoint(Point2D a, Point2D b) {
        return new Point2D(
                (a.getX() + b.getX()) / 2,
                (a.getY() + b.getY()) / 2
        );
    }

    private void getLines(Point2D a, Point2D b, Point2D c, int depth, List<Line> lines, String color) {

        addTriangle(a, b, c, lines, color);
        if (depth > 0) {
            getLines(
                    a,
                    getMidPoint(a, b),
                    getMidPoint(a, c),
                    depth - 1,
                    lines,
                    color
            );
            getLines(
                    b,
                    getMidPoint(a, b),
                    getMidPoint(b, c),
                    depth - 1,
                    lines,
                    color
            );
            getLines(
                    c,
                    getMidPoint(c, b),
                    getMidPoint(a, c),
                    depth - 1,
                    lines,
                    color
            );
        }

    }

}
