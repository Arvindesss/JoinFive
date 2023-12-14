package gui.controller.util;

import gui.model.CircleGrid;
import gui.model.JFCircle;
import gui.model.JFGameType;
import gui.model.JFLine;

import java.util.ArrayList;
import java.util.List;

public class PlayableCirclesCalculator {

    public static List<JFCircle> calculateCirclesFromPossibleLines(final CircleGrid circleGrid, JFGameType gameType) {
        List<JFLine> possibleLines = PossibleJFLineCalculator.calculatePossibleLines(circleGrid, gameType);
        List<JFCircle> playableCircles = new ArrayList<>();
        for (JFLine line : possibleLines) {
            if (!line.getAlignedCircles().get(0).isPlayed()) {
                playableCircles.add(line.getAlignedCircles().get(0));
            } else if (!line.getAlignedCircles().get(2).isPlayed()) {
                playableCircles.add(line.getAlignedCircles().get(2));
            }
        }
        return new ArrayList<>(playableCircles);
    }

}
