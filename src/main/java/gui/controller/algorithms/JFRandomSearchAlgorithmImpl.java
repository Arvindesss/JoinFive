package gui.controller.algorithms;

import gui.controller.GameEventHandler;
import gui.controller.JFSearchAlgorithmMethod;
import gui.controller.util.GameOptions;
import gui.controller.util.PossibleJFLineCalculator;
import gui.model.CircleGrid;
import gui.model.JFLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JFRandomSearchAlgorithmImpl implements JFSearchAlgorithmMethod {

    @Override
    public List<JFLine> resolve(CircleGrid grid) {
        List<JFLine> moves = new ArrayList<>();

        Random r = new Random();

        List<JFLine> lines = PossibleJFLineCalculator.calculatePossibleLines(grid, GameOptions.GAME_TYPE);

        while (!lines.isEmpty()) {
            int i = r.nextInt(lines.size());
            JFLine line = lines.get(i);
            moves.add(line);
            GameEventHandler.addLineEvent(line.getAlignedCircles().get(0), grid);
            lines = PossibleJFLineCalculator.calculatePossibleLines(grid, GameOptions.GAME_TYPE);
        }

        return lines;
    }
}
