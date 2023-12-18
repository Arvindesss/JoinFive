package gui.view.util;

/**
 * A technical coordinate is a coordinates that is readable for the javafx application
 * (it reads from top to bottom for the y-axis instead of a conventional x and y-axis )
 */
public class TechnicalCoordinates {

    private final int x;

    private final int y;

    private TechnicalCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static TechnicalCoordinates of(int x, int y) {
        return new TechnicalCoordinates(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
