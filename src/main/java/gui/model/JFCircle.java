package gui.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class JFCircle {

    private Circle circle;

    private Coordinates coordinates;

    private boolean played;

    public JFCircle(Circle circle, Coordinates coordinates) {
        this.circle = circle;
        this.coordinates = coordinates;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isVisible() {
        return this.circle.getOpacity() == 1 && this.circle.getFill() == Color.BLACK;
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
