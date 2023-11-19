package gui.view.util;

import gui.model.JFCircle;
import gui.model.JFLine;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineDrawer {

    public static void drawLine(JFLine circlesLine, Group pane) {
        //we need the coordinates of start and end point
        JFCircle firstCircle = circlesLine.getAlignedCircles().get(0);
        JFCircle lastCircle = null;

        for (JFCircle c : circlesLine.getAlignedCircles()) {
            lastCircle = c;
        }

        Line line = new Line(firstCircle.getCircle().getCenterX(), firstCircle.getCircle().getCenterY(), lastCircle.getCircle().getCenterX(), lastCircle.getCircle().getCenterY());
        line.setStroke(Color.RED);
        line.setStrokeWidth(4);
        pane.getChildren().add(line);
    }
}
