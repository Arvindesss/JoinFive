package gui.controller.util;

import gui.model.CircleGrid;
import gui.model.JFCircle;
import gui.model.JFGameType;
import gui.model.JFLine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayableCirclesCalculator {

    public static Set<JFCircle> calculateCirclesFromPossibleLines(final CircleGrid circleGrid, JFGameType gameType) {
        List<JFLine> possibleLines = PossibleJFLineCalculator.calculatePossibleLines(circleGrid, gameType);
        Set<JFCircle> playableCircles = new HashSet<>();
        for (JFLine line : possibleLines) {
            if (!line.getAlignedCircles().get(0).isPlayed()) {
                playableCircles.add(line.getAlignedCircles().get(0));
            } else if (!line.getAlignedCircles().get(2).isPlayed()) {
                playableCircles.add(line.getAlignedCircles().get(2));
            }
        }
        return playableCircles;
    }

}
