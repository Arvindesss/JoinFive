package gui.controller.util;

import gui.model.*;

import java.util.List;
import java.util.Optional;

public class AddLineEventHandler {
    /**
     * Finds a line to draw on a JFGrid (if it exists), starting from a chosen circle
     *
     * @param clickedCircle a circle chosen in order to play a move
     * @param JFGrid        the state of a game grid
     * @param gameType      game mode
     * @return a JFLine composed of circles (if it exists)
     */
    public static Optional<JFLine> addLineEvent(JFCircle clickedCircle, JFGrid JFGrid, JFGameType gameType) {
        if (clickedCircle.isPlayed() || clickedCircle.isVisible()) {
            return Optional.empty();
        }
        List<JFCircle> neighbors = JFGrid.getNeighbors(clickedCircle.getCoordinates());
        for (JFCircle neighbor : neighbors) {
            if (gameType.equals(JFGameType.FIVE_D)) {
                Optional<JFLine> line = makeLine5D(JFGrid, clickedCircle, neighbor);
                if (line.isPresent()) {
                    return line;
                }
            } else if (gameType.equals(JFGameType.FIVE_T)) {
                Optional<JFLine> line = makeLine5T(JFGrid, clickedCircle, neighbor);
                if (line.isPresent()) {
                    return line;
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a line to draw on a JFGrid (if it exists for 5T), starting from a chosen circle and a neighbor
     *
     * @param circleClicked a circle chosen in order to play a move
     * @param neighbor      a neighbor of circleClicked
     * @param JFGrid        the state of a game grid
     * @return a JFLine composed of circles (if it exists)
     */
    public static Optional<JFLine> makeLine5T(JFGrid JFGrid, JFCircle circleClicked, JFCircle neighbor) {
        if (!neighbor.isVisible()) {
            return Optional.empty();
        }
        Direction d = Direction.getDirectionFromCoordinates(circleClicked.getCoordinates(), neighbor.getCoordinates());
        if (neighbor.isPlayed()) {
            Optional<JFLine> touchingLine = findNewLine(JFGrid, neighbor, d, 5);
            if (touchingLine.isPresent()) {
                circleClicked.getCircle().setOpacity(1);
                Optional<JFLine> newLine = findNewLine(JFGrid, neighbor, d.getInvertedDirection(), 4);
                circleClicked.getCircle().setOpacity(0);
                if (newLine.isPresent()) {
                    if (newLine.get().isCrossedOut()) {
                        return newLine;
                    }
                }
            }
        }
        return getJfLine(JFGrid, circleClicked, neighbor, d);
    }

    /**
     * Finds a line to draw on a JFGrid (if it exists for 5D), starting from a chosen circle and a neighbor
     *
     * @param circleClicked a circle chosen in order to play a move
     * @param neighbor      a neighbor of circleClicked
     * @param JFGrid        the state of a game grid
     * @return a JFLine composed of circles (if it exists)
     */
    public static Optional<JFLine> makeLine5D(JFGrid JFGrid, JFCircle circleClicked, JFCircle neighbor) {
        if (!neighbor.isVisible()) {
            return Optional.empty();
        }
        Direction d = Direction.getDirectionFromCoordinates(circleClicked.getCoordinates(), neighbor.getCoordinates());
        return getJfLine(JFGrid, circleClicked, neighbor, d);
    }

    /**
     * Finds a JF line if it exists
     *
     * @param JFGrid        the state of the game grid
     * @param circleClicked a circle chosen in order to play a move
     * @param neighbor      a neighbor of circleClicked
     * @param d             a given direction
     * @return
     */
    private static Optional<JFLine> getJfLine(JFGrid JFGrid, JFCircle circleClicked, JFCircle neighbor, Direction d) {
        JFLine line = new JFLine();
        line.addCircle(circleClicked);
        line.addCircle(neighbor);
        findFromLine(line, JFGrid, d, neighbor, 4);
        if (!line.isCrossedOut()) {
            //Handles the case where th clickedCircle is in the middle
            line.swapFirstAndLastElement();
            findFromLine(line, JFGrid, d.getInvertedDirection(), circleClicked, 4);
        }
        return line.isCrossedOut() ? Optional.of(line) : Optional.empty();
    }

    /**
     * Tries to continue to find circles for an existing but none-completed Line on a given direction
     *
     * @param line         the line to continue
     * @param JFGrid       the state of the game grid
     * @param chosenCircle the chosen circle
     * @param d            a given Direction
     */
    private static void findFromLine(JFLine line, JFGrid JFGrid, Direction d, JFCircle chosenCircle, int maxIterations) {
        for (int i = 1; i <= maxIterations; i++) {
            if (CoordinatesValidator.validate(chosenCircle.getCoordinates().getX() + i * d.getDx(), chosenCircle.getCoordinates().getY() + i * d.getDy())) {
                final JFCircle circleFromCoordinates = JFGrid.getCircleFromCoordinates(Coordinates.of(chosenCircle.getCoordinates().getX() + i * d.getDx(), chosenCircle.getCoordinates().getY() + i * d.getDy()));
                if (circleFromCoordinates.isVisible()) {
                    line.addCircle(circleFromCoordinates);
                } else break;
            }
        }
    }

    /**
     * Tries to find a new line from chosen circle
     *
     * @param JFGrid        the state of the game grid
     * @param chosenCircle  the chosen circle
     * @param d             a given direction
     * @param maxIterations the number of circles to check
     * @return a line if it is valid
     */
    private static Optional<JFLine> findNewLine(JFGrid JFGrid, JFCircle chosenCircle, Direction d, int maxIterations) {
        JFLine line = new JFLine();
        line.addCircle(chosenCircle);
        int startX = line.getAlignedCircles().getFirst().getCoordinates().getX();
        int startY = line.getAlignedCircles().getFirst().getCoordinates().getY();
        for (int i = 1; i <= maxIterations; i++) {
            if (CoordinatesValidator.validate(startX + i * d.getDx(), startY + i * d.getDy())) {
                final JFCircle circleFromCoordinates = JFGrid.getCircleFromCoordinates(Coordinates.of(startX + i * d.getDx(), startY + i * d.getDy()));
                if (circleFromCoordinates.isVisible()) {
                    line.addCircle(circleFromCoordinates);
                } else break;
            }
        }
        return line.isCrossedOut() ? Optional.of(line) : Optional.empty();
    }
}


