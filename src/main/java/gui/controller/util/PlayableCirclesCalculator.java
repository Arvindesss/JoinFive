package gui.controller.util;

import gui.model.JFCircle;
import gui.model.JFGameType;
import gui.model.JFGrid;
import gui.model.JFLine;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PlayableCirclesCalculator {

    /**
     * Calculates every possible moves on a game grid
     *
     * @param JFGrid   the state of the game grid
     * @param gameType game Mode (5D or 5T)
     * @return A list of playable circles
     */

    public static List<JFCircle> calculateCirclesFromPossibleLines(final JFGrid JFGrid, JFGameType gameType) {
        List<JFLine> possibleLines = PossibleJFLineCalculator.calculatePossibleLines(JFGrid, gameType);
        Set<JFCircle> playableCircles = new LinkedHashSet<>();
        for (JFLine line : possibleLines) {
            if (!line.getAlignedCircles().get(0).isPlayed() && !line.getAlignedCircles().get(0).isVisible()) {
                playableCircles.add(line.getAlignedCircles().get(0));
            } else if (!line.getAlignedCircles().get(2).isPlayed() && !line.getAlignedCircles().get(2).isVisible()) {
                //Handles the case where a circle is not at the start of the line (between two visible circles)
                playableCircles.add(line.getAlignedCircles().get(2));
            }
        }
        return new ArrayList<>(playableCircles);
    }

}
