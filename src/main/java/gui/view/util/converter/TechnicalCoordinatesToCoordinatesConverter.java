package gui.view.util.converter;

import gui.controller.util.GameOptions;
import gui.model.Coordinates;
import gui.view.util.TechnicalCoordinates;

public class TechnicalCoordinatesToCoordinatesConverter {

    public static Coordinates convert(final TechnicalCoordinates technicalCoordinates) {
        //Impléménter la correspondance coordonées lisible vs coordonnées de grille de liste
        int y = Math.abs(technicalCoordinates.getY() - GameOptions.NB_ROWS) % (GameOptions.NB_ROWS + 1);
        return Coordinates.of(technicalCoordinates.getX(), y);
    }
}
