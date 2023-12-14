package gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
    private int id;

    private StringProperty name;
    private DoubleProperty score;

    private final String saltedPassword;

    public Player(String name, double score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(score);
        this.saltedPassword = "";
    }

    public Player(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(0);
        this.saltedPassword = "";
    }

    public Player(String name, String saltedPassword) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleDoubleProperty(0);
        this.saltedPassword = saltedPassword;
    }

    public Player(int id, String username, double obtained_score) {
        this.name = new SimpleStringProperty(username);
        this.score = new SimpleDoubleProperty(obtained_score);
        this.id = id;
        this.saltedPassword = "";
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty scoreProperty() {
        return score;
    }

    public void addScore() {
        this.score.set(this.score.doubleValue() + 1);
    }

    public void decreaseScore() {
        this.score.set(this.score.doubleValue() - 1);
    }

    public double getScore() {
        return score.get();
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public void resetScore() {
        this.score.set(0);
    }

    public int getId() {
        return id;
    }
}
