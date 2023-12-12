package gui.controller;

import gui.model.CircleGrid;
import gui.model.JFGameMoves;

public interface JFSearchAlgorithmMethod {

    JFGameMoves resolve(CircleGrid grid);
}
