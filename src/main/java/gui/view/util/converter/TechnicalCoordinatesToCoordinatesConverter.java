package gui.view.util.converter;

import gui.controller.GameOptions;
import gui.model.Coordinates;
import gui.view.util.TechnicalCoordinates;

public class TechnicalCoordinatesToCoordinatesConverter {

    public static Coordinates convert(final TechnicalCoordinates technicalCoordinates) {
        int y = Math.abs(technicalCoordinates.getY() - GameOptions.getNbRows()) % (GameOptions.getNbRows() + 1);
        return Coordinates.of(technicalCoordinates.getX(), y);
    }
}
