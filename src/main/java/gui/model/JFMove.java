package gui.model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class JFMove {

    private Circle clickedCircle;

    private Line drawedLine;

    public JFMove(Circle clickedCircle, Line drawedLine) {
        this.clickedCircle = clickedCircle;
        this.drawedLine = drawedLine;
    }

    public Circle getClickedCircle() {
        return clickedCircle;
    }

    public Line getDrawedLine() {
        return drawedLine;
    }
}
