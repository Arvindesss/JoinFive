package gui.model;

import javafx.scene.shape.Circle;

import java.util.LinkedList;

public class JFLine {

    private static int MAX_LINE_SIZE = 5;

    private LinkedList<Circle> alignedCircles;

    private boolean crossedOut;

    public JFLine() {
        this.alignedCircles = new LinkedList<>();
        this.crossedOut = false;
    }

    public JFLine(LinkedList<Circle> alignedCircles) {
        if (alignedCircles.size() > MAX_LINE_SIZE) {
            throw new RuntimeException("Line is too long: " + MAX_LINE_SIZE + " circles are allowed at maximum");
        }
        this.alignedCircles = alignedCircles;
        this.crossedOut = alignedCircles.size() == MAX_LINE_SIZE;
    }

    public LinkedList<Circle> getAlignedCircles() {
        return alignedCircles;
    }

    public void addCircle(Circle c) {
        if (this.alignedCircles.size() == MAX_LINE_SIZE) {
            throw new RuntimeException("Line is full");
        }
        this.alignedCircles.add(c);
        if (alignedCircles.size() == MAX_LINE_SIZE) {
            this.crossedOut = true;
        }
    }

    public boolean isCrossedOut() {
        return crossedOut;
    }
}
