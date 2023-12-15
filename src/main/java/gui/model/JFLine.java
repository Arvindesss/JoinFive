package gui.model;

import java.util.LinkedList;

public class JFLine {

    private static int MAX_LINE_SIZE = 5;

    private LinkedList<JFCircle> alignedCircles;

    private boolean crossedOut;

    public JFLine() {
        this.alignedCircles = new LinkedList<>();
        this.crossedOut = false;
    }

    public JFLine(LinkedList<JFCircle> alignedCircles) {
        if (alignedCircles.size() > MAX_LINE_SIZE) {
            throw new RuntimeException("Line is too long: " + MAX_LINE_SIZE + " circles are allowed at maximum");
        }
        this.alignedCircles = alignedCircles;
        this.crossedOut = alignedCircles.size() == MAX_LINE_SIZE;
    }

    public LinkedList<JFCircle> getAlignedCircles() {
        return alignedCircles;
    }

    public void addCircle(JFCircle c) {
        if (this.alignedCircles.size() == MAX_LINE_SIZE) {
            throw new RuntimeException("Line is full " + this.toString());
        }
        this.alignedCircles.add(c);
        if (alignedCircles.size() == MAX_LINE_SIZE) {
            this.crossedOut = true;
        }
    }

    public boolean isCrossedOut() {
        return crossedOut;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (JFCircle circle : this.alignedCircles) {
            stringBuilder.append("[")
                    .append(circle.getCoordinates().getX())
                    .append(" ")
                    .append(circle.getCoordinates().getY())
                    .append("] ");

        }
        return stringBuilder.toString();
    }
}
