package gui.model;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class CircleGrid {
    private List<List<Circle>> circleGrid;

    public CircleGrid() {
        this.circleGrid = new ArrayList<>();
    }

    public List<List<Circle>> getCircleGrid() {
        return circleGrid;
    }

    public Circle getCircleFromCoordonates(Coordinates c) {
        return this.getCircleGrid().get(c.getY()).get(c.getX());
    }
}
