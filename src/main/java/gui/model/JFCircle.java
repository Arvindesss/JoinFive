package gui.model;

import javafx.scene.shape.Circle;

public class JFCircle {

    private Circle circle;

    private Coordinates coordinates;

    public JFCircle(Circle circle, Coordinates coordinates) {
        this.circle = circle;
        this.coordinates = coordinates;
    }

    public boolean isVisible() {
        return circle.getOpacity() == 1;
    }

    public Circle getCircle() {
        return circle;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

}
