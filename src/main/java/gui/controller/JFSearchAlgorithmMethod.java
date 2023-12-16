package gui.controller;

import gui.model.JFGrid;
import gui.model.JFMove;

import java.util.List;

public interface JFSearchAlgorithmMethod {

    /**
     * Finds a solution to resolve a game of JF
     *
     * @param grid
     * @return a list of ordered game moves
     */
    List<JFMove> resolve(JFGrid grid);
}
