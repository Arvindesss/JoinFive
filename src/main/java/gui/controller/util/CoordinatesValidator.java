package gui.controller.util;

import gui.controller.GameOptions;
import gui.model.Coordinates;

public class CoordinatesValidator {

    public static boolean validate(int x, int y) {
        return x >= 0 && y >= 0 && x <= GameOptions.getNbColumns() && y <= GameOptions.getNbRows();
    }

    public static boolean validate(Coordinates c) {
        return c.getX() >= 0 && c.getY() >= 0 && c.getX() <= GameOptions.getNbColumns() && c.getY() <= GameOptions.getNbRows();
    }
}
