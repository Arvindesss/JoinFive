package gui.controller;

import gui.controller.util.CoordinatesValidator;
import gui.model.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

public class GameEventHandler {

    public static Optional<JFLine> addLineEvent(JFCircle clickedCircle, CircleGrid circleGrid) {

        List<JFCircle> neighbors = circleGrid.getNeighbors(clickedCircle.getCoordinates());

        for (JFCircle neighbor : neighbors) {
            if (neighbor.isPlayed()) {
                Optional<JFLine> line = makeLine(circleGrid, clickedCircle, neighbor);

                if (line.isPresent()) {
                    return line;
                }
            }
        }

        return Optional.empty();
    }

    public static Optional<JFLine> makeLine(CircleGrid circleGrid, JFCircle circleClicked, JFCircle neighbor) {
        if (!neighbor.isPlayed()) {
            return Optional.empty();

        }
        JFLine line = new JFLine();
        line.addCircle(circleClicked);
        line.addCircle(neighbor);

        Direction d = Direction.getDirectionFromCoordinates(circleClicked.getCoordinates(), neighbor.getCoordinates());

        findLine(line, circleGrid, d, neighbor);

        goBack(line, circleGrid, circleClicked, d.getInvertedDirection());

        if (!line.isCrossedOut()) {
            return Optional.empty();
        }
        return Optional.of(line);

    }

    private static void goBack(JFLine line, CircleGrid circleGrid, JFCircle circleClicked, Direction d) {
        Coordinates adj = Coordinates.of(circleClicked.getCoordinates().getX() + d.getDx(), circleClicked.getCoordinates().getY() + d.getDy());

        if (CoordinatesValidator.validate(adj)) {
            JFCircle invertedAdjCircle = circleGrid.getCircleFromCoordinates(adj);

            if (invertedAdjCircle.getCircle().getOpacity() == 1 && invertedAdjCircle.getCircle().getFill().equals(Color.BLACK)) {
                swapFirstAndLastElement(line);
                line.addCircle(invertedAdjCircle);
                findLine(line, circleGrid, d, invertedAdjCircle);
            }
        }

    }

    private static void findLine(JFLine line, CircleGrid circleGrid, Direction d, JFCircle invertedAdjCircle) {
        for (int i = 1; i <= 4; i++) {
            if (CoordinatesValidator.validate(invertedAdjCircle.getCoordinates().getX() + i * d.getDx(), invertedAdjCircle.getCoordinates().getY() + i * d.getDy())) {
                final JFCircle circleFromCoordinates = circleGrid.getCircleFromCoordinates(Coordinates.of(invertedAdjCircle.getCoordinates().getX() + i * d.getDx(), invertedAdjCircle.getCoordinates().getY() + i * d.getDy()));
                if (circleFromCoordinates.isPlayed()) {
                    line.addCircle(circleFromCoordinates);
                } else {
                    break;
                }
            }
        }
    }

    private static void swapFirstAndLastElement(JFLine line) {
        JFCircle tmp = line.getAlignedCircles().get(0);
        line.getAlignedCircles().set(0, line.getAlignedCircles().getLast());
        line.getAlignedCircles().removeLast();
        line.addCircle(tmp);
    }

}


