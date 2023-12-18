package gui.view.util.converter;

import gui.controller.GameOptions;
import gui.model.Coordinates;
import gui.view.util.TechnicalCoordinates;

public class CoordinatesToTechnicalCoordinatesConverter {

    public static TechnicalCoordinates convert(final Coordinates coordinates) {
        int y = Math.abs(coordinates.getY() - GameOptions.getNbRows());
        return TechnicalCoordinates.of(y, coordinates.getX());
    }
}
