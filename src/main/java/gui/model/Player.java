package gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    private StringProperty name;
    private DoubleProperty score;

    public Player(String name, double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
    }

    public Player(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(0);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty scoreProperty() {
        return score;
    }

    public void addScore(double score) {
        this.score.set(this.score.doubleValue() + 1);
    }

    public double getScore() {
        return score.get();
    }
}
