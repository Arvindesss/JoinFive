package gui.controller;

import gui.model.JFGameType;
import gui.model.JFGrid;
import gui.model.JFMove;

import java.util.List;

public interface JFSearchAlgorithmMethod {

    /**
     * Finds a solution to resolve a game of JF
     *
     * @param grid
     * @param jfGameType JF game mode
     * @return a list of ordered game moves
     */
    List<JFMove> resolve(JFGrid grid, JFGameType jfGameType);
}
