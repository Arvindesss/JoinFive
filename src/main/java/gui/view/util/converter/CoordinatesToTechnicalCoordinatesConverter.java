package gui.view.util.converter;

import gui.controller.util.GameOptions;
import gui.model.Coordinates;
import gui.view.util.TechnicalCoordinates;

public class CoordinatesToTechnicalCoordinatesConverter {

    public static TechnicalCoordinates convert(final Coordinates coordinates) {
        //Impléménter la correspondance coordonées lisible vs coordonnées de grille de liste
        int y = Math.abs(coordinates.getY() - GameOptions.NB_ROWS);
        return TechnicalCoordinates.of(y, coordinates.getX());
    }
}
