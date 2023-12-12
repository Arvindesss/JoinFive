package gui.controller.util;

import gui.model.Coordinates;

public class CoordinatesValidator {

    public static boolean validate(int x, int y) {
        return x >= 0 && y >= 0 && x <= GameOptions.NB_COLUMNS && y <= GameOptions.NB_ROWS;
    }

    public static boolean validate(Coordinates c) {
        return c.getX() >= 0 && c.getY() >= 0 && c.getX() <= GameOptions.NB_COLUMNS && c.getY() <= GameOptions.NB_ROWS;
    }
}
