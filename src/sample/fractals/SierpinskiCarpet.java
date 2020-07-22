package sample.fractals;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SierpinskiCarpet extends Fractal {

    public SierpinskiCarpet(Pane pane) {
        super(pane);
        Rectangle2D rec = new Rectangle2D(90.0, 5.0, 490.0, 490.0);
    }

    @Override
    public Timeline getAnimation(double speed, String color, int depth) {

        Timeline timeline = new Timeline();
        Duration timepoint = Duration.ZERO;
        Duration pause = Duration.millis(speed);

        List<Rectangle> rectangles = new ArrayList<Rectangle>();

        this.calculateRectangles(
                90.0,
                5.0,
                490.0,
                rectangles,
                depth,
                color
        );


        for (Rectangle rectangle: rectangles) {
            timepoint = timepoint.add(pause);
            KeyFrame keyFrame = new KeyFrame(timepoint, e->super.getPane().getChildren().add(rectangle));
            timeline.getKeyFrames().add(keyFrame);
        }

        return timeline;

    }

    private void calculateRectangles(double minX, double minY, double size, List<Rectangle> rectangles, int depth, String color) {
        if (depth > 1) {

            double newSize = size/3;
            Rectangle newRectangle = new Rectangle(
              minX + newSize,
              minY + newSize,
                    newSize,
                    newSize
            );

            newRectangle.setStyle("-fx-stroke: #" + color);
            newRectangle.setFill(Color.valueOf(color));
            rectangles.add(newRectangle);

            // 1.
            this.calculateRectangles(
                    minX,
                    minY,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 2.
            this.calculateRectangles(
                    minX,
                    minY + newSize,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 3.
            this.calculateRectangles(
                    minX,
                    minY + newSize*2,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 4.
            this.calculateRectangles(
                    minX + newSize,
                    minY + newSize*2,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 5.
            this.calculateRectangles(
                    minX + newSize*2,
                    minY + newSize*2,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 6.
            this.calculateRectangles(
                    minX + newSize*2,
                    minY + newSize,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 7.
            this.calculateRectangles(
                    minX + newSize*2,
                    minY,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
            // 8.
            this.calculateRectangles(
                    minX + newSize,
                    minY,
                    newSize,
                    rectangles,
                    depth-1,
                    color
            );
        }
    }

}
