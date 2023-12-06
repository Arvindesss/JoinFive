package gui.model;

import javafx.scene.paint.Color;
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

    public boolean isPlayed() {
        return circle.getOpacity() == 1 && circle.getFill().equals(Color.BLACK);
    }

    public Circle getCircle() {
        return circle;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return this.getCoordinates().toString();
    }
}
