package gui.view;

import gui.model.JFLine;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class LineDrawer {

    public static void drawLine(JFLine circlesLine, Group pane) {
        //we need the coordinates of start and end point

        Circle firstCircle = circlesLine.getAlignedCircles().get(0);
        Circle lastCircle = new Circle();

        for (Circle c : circlesLine.getAlignedCircles()) {
            lastCircle = c;

        }

        Line line = new Line(firstCircle.getCenterX(), firstCircle.getCenterY(), lastCircle.getCenterX(), lastCircle.getCenterY());
        //Line line = new Line(55, 50, 500, 500);
        line.setStroke(Color.RED);
        line.setStrokeWidth(4);
        pane.getChildren().add(line);
    }
}
