package gui.controller.util;

import gui.controller.GameEventHandler;
import gui.model.CircleGrid;
import gui.model.JFCircle;
import gui.model.JFGameType;
import gui.model.JFLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PossibleJFLineCalculator {

    public static List<JFLine> calculatePossibleLines(CircleGrid circleGrid, JFGameType jfGameType) {
        List<JFLine> lines = new ArrayList<>();
        if (jfGameType.equals(JFGameType.FIVE_D)) {
            lines = calculatePossibleLines5D(circleGrid);
        } else if (jfGameType.equals(JFGameType.FIVE_T)) {
            lines = calculatePossibleLines5T(circleGrid);
        }
        return lines;
    }

    private static List<JFLine> calculatePossibleLines5D(CircleGrid circleGrid) {
        List<JFLine> lines = new ArrayList<>();
        for (List<JFCircle> circlesList : circleGrid.getCircleGrid()) {
            for (JFCircle c : circlesList) {
                List<JFCircle> neighbors = circleGrid.getNeighbors(c.getCoordinates());
                for (JFCircle neighbor : neighbors) {
                    try {
                        Optional<JFLine> line = GameEventHandler.makeLine(circleGrid, c, neighbor);
                        if (line.isPresent()) {
                            lines.add(line.get());
                        }
                    } catch (RuntimeException re) {
                        System.out.println(re.getMessage());

                    }
                }
            }
        }
        return lines;
    }

    private static List<JFLine> calculatePossibleLines5T(final CircleGrid circleGrid) {
        List<JFLine> lines = new ArrayList<>();
        return lines;
    }
}
