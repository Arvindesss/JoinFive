package gui.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {

    private final int id;

    private final StringProperty name;

    private final IntegerProperty score;

    private final String saltedPassword;

    public Player(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.score = builder.score;
        this.saltedPassword = builder.saltedPassword;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void addScore() {
        this.score.set(this.score.intValue() + 1);
    }

    public void decreaseScore() {
        this.score.set(this.score.intValue() - 1);
    }

    public void resetScore() {
        this.score.set(0);
    }

    public double getScore() {
        return score.get();
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public int getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id = 0;

        private StringProperty name = new SimpleStringProperty("Unknown");

        private IntegerProperty score = new SimpleIntegerProperty(0);

        private String saltedPassword = "";

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name.set(name);
            return this;
        }

        public Builder setScore(int score) {
            this.score.set(score);
            return this;
        }

        public Builder setSaltedPassword(String saltedPassword) {
            this.saltedPassword = saltedPassword;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
