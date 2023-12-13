package gui.controller;

import gui.model.JFCircle;
import gui.model.JFLine;

import java.util.List;

public class JFAlgorithmResult {

    private List<JFCircle> playedCircles;

    private List<JFLine> linesToDraw;

    private JFAlgorithmResult(List<JFCircle> playedCircles, List<JFLine> linesToDraw) {
        this.playedCircles = playedCircles;
        this.linesToDraw = linesToDraw;
    }

    public static JFAlgorithmResult of(List<JFCircle> playedCircles, List<JFLine> linesToDraw) {
        return new JFAlgorithmResult(playedCircles, linesToDraw);
    }

    public List<JFCircle> getPlayedCircles() {
        return playedCircles;
    }

    public List<JFLine> getLinesToDraw() {
        return linesToDraw;
    }
}
