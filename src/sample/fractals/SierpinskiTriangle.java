package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SierpinskiTriangle implements Fractal{

    private GraphicsContext canvas;
    private Pane pane;

    public SierpinskiTriangle(Pane pane) {
        this.canvas = canvas;
        this.pane = pane;
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        System.out.println("Speed: " + speed);
        System.out.println("Depth: " + depth);

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Line> lines = new ArrayList<Line>();

        this.getLines(
                new Point2D(5.0, 490),
                new Point2D(330.0, 5.0),
                new Point2D(665.0, 490.0),
                0,
                lines,
                color,
                depth
        );

        for (Line line: lines) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->pane.getChildren().add(line));
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

    private void getLines(Point2D a, Point2D b, Point2D c, int depth, List<Line> lines, String color, int depthLimit) {

        addTriangle(a, b, c, lines, color);
        if (depth < depthLimit) {
            getLines(
                    a,
                    getMidPoint(a, b),
                    getMidPoint(a, c),
                    depth + 1,
                    lines,
                    color,
                    depthLimit
            );
            getLines(
                    b,
                    getMidPoint(a, b),
                    getMidPoint(b, c),
                    depth + 1,
                    lines,
                    color,
                    depthLimit
            );
            getLines(
                    c,
                    getMidPoint(c, b),
                    getMidPoint(a, c),
                    depth + 1,
                    lines,
                    color,
                    depthLimit
            );
        }

    }

}
