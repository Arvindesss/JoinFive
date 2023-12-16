package gui.controller.algorithms;

import gui.controller.GameOptions;
import gui.controller.JFSearchAlgorithmMethod;
import gui.controller.util.AddLineEventHandler;
import gui.controller.util.PlayableCirclesCalculator;
import gui.model.JFCircle;
import gui.model.JFGrid;
import gui.model.JFLine;
import gui.model.JFMove;

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
    public List<JFMove> resolve(JFGrid grid) {
        List<JFMove> gameMoves = new ArrayList<>();

        Random r = new Random();

        List<JFCircle> lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, GameOptions.getGameType());
        int repCounter = 0;
        while (!lines.isEmpty()) {
            int oldLineSize = lines.size();

            int i = r.nextInt(lines.size());
            JFCircle point = lines.get(i);

            System.out.println("Boucle");

            try {
                Optional<JFLine> line = AddLineEventHandler.addLineEvent(point, grid);
                if (line.isPresent()) {
                    gameMoves.add(new JFMove(point, line.get()));
                    point.setPlayed(true);
                    for (JFCircle jfCircle : line.get().getAlignedCircles()) {
                        jfCircle.setPlayed(true);
                    }
                }
                lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, GameOptions.getGameType());
                System.out.println("On continue : il en reste " + lines.size());

            } catch (Exception e) {
                lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, GameOptions.getGameType());
                if (oldLineSize == lines.size()) {
                    repCounter++;
                }
                if (repCounter == 5) {
                    break;
                }
                System.out.println("On continue : il en reste " + lines.size());
                System.out.println(e.getMessage());
            }
        }
        System.out.println("On arrete");
        return gameMoves;
    }
}
