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
     * @return a JFLine composed of circles (if it exists)
     */
    public static Optional<JFLine> addLineEvent(JFCircle clickedCircle, JFGrid JFGrid) {
        List<JFCircle> neighbors = JFGrid.getNeighbors(clickedCircle.getCoordinates());
        for (JFCircle neighbor : neighbors) {
            if (neighbor.isPlayed()) {
                Optional<JFLine> line = makeLine(JFGrid, clickedCircle, neighbor);
                if (line.isPresent()) {
                    return line;
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Finds a line to draw on a JFGrid (if it exists), starting from a chosen circle and a neighbor
     *
     * @param circleClicked a circle chosen in order to play a move
     * @param neighbor      a neighbor of circleClicked
     * @param JFGrid        the state of a game grid
     * @return a JFLine composed of circles (if it exists)
     */
    public static Optional<JFLine> makeLine(JFGrid JFGrid, JFCircle circleClicked, JFCircle neighbor) {
        if (!neighbor.isPlayed()) {
            return Optional.empty();
        }
        JFLine line = new JFLine();
        line.addCircle(circleClicked);
        line.addCircle(neighbor);
        Direction d = Direction.getDirectionFromCoordinates(circleClicked.getCoordinates(), neighbor.getCoordinates());

        //Si il y a exactement 5 points non joué auparavant, OK

        //si il y a un 6 eme, ne pas créer de ligne

        findLine(line, JFGrid, d, neighbor);
        if (!line.isCrossedOut()) {
            goToDirection(line, JFGrid, circleClicked, d.getInvertedDirection());
        }
        if (!line.isCrossedOut()) {
            return Optional.empty();
        }
        return Optional.of(line);
    }

    /**
     * Tries to continue to find circles for an existing but none-completed Line on a given direction
     *
     * @param line          the line to continue
     * @param JFGrid        the state of the game grid
     * @param circleClicked the chosen circle
     * @param d             a given Direction
     */
    private static void goToDirection(JFLine line, JFGrid JFGrid, JFCircle circleClicked, Direction d) {
        Coordinates adj = Coordinates.of(circleClicked.getCoordinates().getX() + d.getDx(), circleClicked.getCoordinates().getY() + d.getDy());
        if (CoordinatesValidator.validate(adj)) {
            JFCircle invertedAdjCircle = JFGrid.getCircleFromCoordinates(adj);
            if (invertedAdjCircle.isPlayed()) {
                line.swapFirstAndLastElement();
                line.addCircle(invertedAdjCircle);
                findLine2(line, JFGrid, d, invertedAdjCircle);
            }
        }
    }

    /**
     * Adds a circle to existing line
     *
     * @param line      the line to continue
     * @param JFGrid    the state of the game grid
     * @param d         a given Direction
     * @param adjCircle a Circle to add to the line
     */
    private static void findLine(JFLine line, JFGrid JFGrid, Direction d, JFCircle adjCircle) {
        System.out.println("Taille findLine1 " + line.getAlignedCircles().size());
        for (int i = 1; i <= 3 + 1; i++) {
            if (CoordinatesValidator.validate(adjCircle.getCoordinates().getX() + i * d.getDx(), adjCircle.getCoordinates().getY() + i * d.getDy())) {
                final JFCircle circleFromCoordinates = JFGrid.getCircleFromCoordinates(Coordinates.of(adjCircle.getCoordinates().getX() + i * d.getDx(), adjCircle.getCoordinates().getY() + i * d.getDy()));
                if (circleFromCoordinates.isPlayed()) {
                    line.addCircle(circleFromCoordinates);
                } else break;
            }
        }
    }

    private static void findLine2(JFLine line, JFGrid JFGrid, Direction d, JFCircle adjCircle) {
        System.out.println("Taille findLine2 " + line.getAlignedCircles().size());
        for (int i = 1; i <= 3 + 1; i++) {
            if (CoordinatesValidator.validate(adjCircle.getCoordinates().getX() + i * d.getDx(), adjCircle.getCoordinates().getY() + i * d.getDy())) {
                final JFCircle circleFromCoordinates = JFGrid.getCircleFromCoordinates(Coordinates.of(adjCircle.getCoordinates().getX() + i * d.getDx(), adjCircle.getCoordinates().getY() + i * d.getDy()));
                if (circleFromCoordinates.isPlayed()) {
                    line.addCircle(circleFromCoordinates);
                } else break;
            }
        }
    }

}


