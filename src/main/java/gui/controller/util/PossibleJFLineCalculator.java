package gui.controller.util;

import gui.model.JFCircle;
import gui.model.JFGameType;
import gui.model.JFGrid;
import gui.model.JFLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PossibleJFLineCalculator {

    /**
     * Calculates every drawable lines on the game grid according to game type
     *
     * @param JFGrid     the state of the game grid
     * @param jfGameType game mode (5D,5T)
     * @return a list of drawable Lines on the game grid
     */
    public static List<JFLine> calculatePossibleLines(JFGrid JFGrid, JFGameType jfGameType) {
        List<JFLine> lines = new ArrayList<>();
        if (jfGameType.equals(JFGameType.FIVE_D)) {
            lines = calculatePossibleLines5D(JFGrid);
        } else if (jfGameType.equals(JFGameType.FIVE_T)) {
            lines = calculatePossibleLines5T(JFGrid);
        }
        return lines;
    }

    /**
     * Calculates every drawable lines on the game grid for 5D
     *
     * @param JFGrid the state of the game grid
     * @return a list of drawable Lines on the game grid
     */
    private static List<JFLine> calculatePossibleLines5D(JFGrid JFGrid) {
        List<JFLine> lines = new ArrayList<>();
        for (List<JFCircle> circlesList : JFGrid.getCircleGrid()) {
            for (JFCircle c : circlesList) {
                List<JFCircle> neighbors = JFGrid.getNeighbors(c.getCoordinates());
                for (JFCircle neighbor : neighbors) {
                    Optional<JFLine> line = AddLineEventHandler.makeLine5D(JFGrid, c, neighbor);
                    if (line.isPresent()) {
                        if (!lines.contains(line.get())) {
                            lines.add(line.get());
                        }
                    }
                }
            }
        }
        return lines;
    }

    /**
     * Calculates every drawable lines on the game grid for 5T
     *
     * @param JFGrid the state of the game grid
     * @return a list of drawable Lines on the game grid
     */
    private static List<JFLine> calculatePossibleLines5T(final JFGrid JFGrid) {
        List<JFLine> lines = new ArrayList<>();
        for (List<JFCircle> circlesList : JFGrid.getCircleGrid()) {
            for (JFCircle c : circlesList) {
                List<JFCircle> neighbors = JFGrid.getNeighbors(c.getCoordinates());
                for (JFCircle neighbor : neighbors) {
                    Optional<JFLine> line = AddLineEventHandler.makeLine5T(JFGrid, c, neighbor);
                    if (line.isPresent()) {
                        if (!lines.contains(line.get())) {
                            lines.add(line.get());
                        }
                    }
                }
            }
        }
        return lines;
    }
}
