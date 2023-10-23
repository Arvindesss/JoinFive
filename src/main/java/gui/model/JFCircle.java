package gui.model;

import javafx.scene.shape.Circle;

public class JFCircle {

    private Circle circle;

    private Coordinates coordinates;

    public JFCircle(Circle circle, Coordinates coordinates) {
        this.circle = circle;
        this.coordinates = coordinates;
    }

    public Circle getCircle() {
        return circle;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
