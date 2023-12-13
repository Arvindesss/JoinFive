package gui.model;

public class JFMove {

    private JFCircle clickedCircle;

    private JFLine drawedLine;

    public JFMove(JFCircle clickedCircle, JFLine drawedLine) {
        this.clickedCircle = clickedCircle;
        this.drawedLine = drawedLine;
    }

    public JFCircle getClickedCircle() {
        return clickedCircle;
    }

    public JFLine getDrawedLine() {
        return drawedLine;
    }
}
