package gui.controller.algorithms;

import gui.controller.JFSearchAlgorithmMethod;
import gui.controller.util.AddLineEventHandler;
import gui.controller.util.PlayableCirclesCalculator;
import gui.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class JFRandomSearchAlgorithmImpl implements JFSearchAlgorithmMethod {

    /**
     * Implementation of a random search algorithm to play Join Five
     *
     * @param grid the state of the Join Five grid
     * @return A list of played moves
     */

    @Override
    public List<JFMove> resolve(JFGrid grid, JFGameType jfGameType) {
        List<JFMove> gameMoves = new ArrayList<>();

        Random r = new Random();

        List<JFCircle> lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, jfGameType);
        while (!lines.isEmpty()) {
            int i = r.nextInt(lines.size());
            JFCircle point = lines.get(i);
            Optional<JFLine> line = AddLineEventHandler.addLineEvent(point, grid, jfGameType);
            if (line.isPresent()) {
                gameMoves.add(new JFMove(point, line.get()));
                for (JFCircle jfCircle : line.get().getAlignedCircles()) {
                    jfCircle.setPlayed(true);
                    jfCircle.getCircle().setOpacity(1);
                }
            }
            lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, jfGameType);
        }
        return gameMoves;
    }
}
