package gui.model;

import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JFLineTest {

    @Test
    void testEquals() {
        LinkedList<JFCircle> firstList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            firstList.add(new JFCircle(new Circle(5), Coordinates.of(0, i)));
        }
        LinkedList<JFCircle> reversedList = reverseLinkedList(firstList);
        JFLine line = new JFLine(firstList);
        JFLine reversedLine = new JFLine(reversedList);
        assertEquals(line, reversedLine);
    }

    private static LinkedList<JFCircle> reverseLinkedList(LinkedList<JFCircle> originalList) {
        LinkedList<JFCircle> reversedList = new LinkedList<>();
        for (JFCircle element : originalList) {
            reversedList.addFirst(element);
        }
        return reversedList;
    }
}