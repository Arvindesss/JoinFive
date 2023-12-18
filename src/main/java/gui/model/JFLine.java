package gui.model;

import java.util.LinkedList;
import java.util.Objects;

public class JFLine {

    private static int MAX_LINE_SIZE = 5;

    private LinkedList<JFCircle> alignedCircles;

    public JFLine() {
        this.alignedCircles = new LinkedList<>();
    }

    public JFLine(LinkedList<JFCircle> alignedCircles) {
        if (alignedCircles.size() > MAX_LINE_SIZE) {
            throw new RuntimeException("Line is too long: " + MAX_LINE_SIZE + " circles are allowed at maximum");
        }
        this.alignedCircles = alignedCircles;
    }

    public LinkedList<JFCircle> getAlignedCircles() {
        return alignedCircles;
    }

    public void addCircle(JFCircle c) {
        this.alignedCircles.add(c);
    }

    public boolean isCrossedOut() {
        return alignedCircles.size() == MAX_LINE_SIZE;
    }

    public void swapFirstAndLastElement() {
        JFCircle tmp = alignedCircles.get(0);
        alignedCircles.set(0, alignedCircles.getLast());
        alignedCircles.removeLast();
        addCircle(tmp);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JFLine line = (JFLine) obj;
        // Compares with reversedList and original LinkedList
        LinkedList<JFCircle> reversedList = reverseLinkedList(line.alignedCircles);
        return this.alignedCircles.equals(reversedList) || this.alignedCircles.equals(line.alignedCircles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alignedCircles);
    }

    private static LinkedList<JFCircle> reverseLinkedList(LinkedList<JFCircle> originalList) {
        LinkedList<JFCircle> reversedList = new LinkedList<>();
        for (JFCircle element : originalList) {
            reversedList.addFirst(element);
        }
        return reversedList;
    }
}
