package gui.controller.algorithms;

import gui.controller.GameEventHandler;
import gui.controller.JFSearchAlgorithmMethod;
import gui.controller.util.GameOptions;
import gui.controller.util.PlayableCirclesCalculator;
import gui.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class JFRandomSearchAlgorithmImpl implements JFSearchAlgorithmMethod {

    @Override
    public JFGameMoves resolve(CircleGrid grid) {
        JFGameMoves gameMoves = new JFGameMoves();
        Random r = new Random();

        List<JFCircle> lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, GameOptions.GAME_TYPE);

        while (!lines.isEmpty()) {
            int i = r.nextInt(lines.size());
            JFCircle point = lines.get(i);
            Optional<JFLine> line = GameEventHandler.addLineEvent(point, grid);
            if (line.isPresent()) {
                gameMoves.addGameMove(new JFMove(point, line.get()));
                point.setPlayed(true);
            }
            lines = PlayableCirclesCalculator.calculateCirclesFromPossibleLines(grid, GameOptions.GAME_TYPE);
            System.out.println("On continue");
        }

        System.out.println("On arrete");
        return gameMoves;
    }
}
