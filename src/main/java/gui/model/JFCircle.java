package gui.model;

import javafx.scene.shape.Circle;

public class JFCircle {

    private Circle circle;

    private Coordinates coordinates;

    private int number;

    private boolean played;

    public JFCircle(Circle circle, Coordinates coordinates) {
        this.circle = circle;
        this.coordinates = coordinates;
    }

    public JFCircle(Circle circle, Coordinates coordinates, int number) {
        this.circle = circle;
        this.coordinates = coordinates;
        this.number = number;
    }

    public boolean isPlayed() {
        //return circle.getOpacity() == 1 && circle.getFill().equals(Color.BLACK);
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
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

    public int getNumber() {
        return number;
    }
}
