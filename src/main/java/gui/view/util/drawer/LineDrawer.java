package gui.view.util.drawer;

import gui.model.JFCircle;
import gui.model.JFLine;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LineDrawer {
    /**
     * Draws a Line on the gui with a specified color from a model JFLine
     *
     * @param circlesLine the model line containing traversed circles
     * @param color       the desired color for the line
     * @return a ui Line component to render on the gui
     */
    public static Line drawLine(JFLine circlesLine, Color color) {
        //we need the coordinates of start and end point
        JFCircle firstCircle = circlesLine.getAlignedCircles().get(0);
        JFCircle lastCircle = null;

        for (JFCircle c : circlesLine.getAlignedCircles()) {
            c.setPlayed(true);
            lastCircle = c;
        }

        assert lastCircle != null;
        Line line = new Line(firstCircle.getCircle().getCenterX(), firstCircle.getCircle().getCenterY(), lastCircle.getCircle().getCenterX(), lastCircle.getCircle().getCenterY());
        line.setStroke(color);
        line.setStrokeWidth(4);
        return line;
    }
}
