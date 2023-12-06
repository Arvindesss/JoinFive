package gui.controller;

import gui.model.CircleGrid;
import gui.model.JFLine;

import java.util.List;

public interface JFSearchAlgorithmMethod {

    List<JFLine> resolve(CircleGrid grid);
}
