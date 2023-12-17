package gui.model;

import gui.controller.util.CoordinatesValidator;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.CoordinatesToTechnicalCoordinatesConverter;

import java.util.ArrayList;
import java.util.List;

public class JFGrid {
    private List<List<JFCircle>> circleGrid;

    public JFGrid() {
        this.circleGrid = new ArrayList<>();
    }

    public List<List<JFCircle>> getCircleGrid() {
        return circleGrid;
    }

    public JFCircle getCircleFromCoordinates(Coordinates c) {
        TechnicalCoordinates tc = CoordinatesToTechnicalCoordinatesConverter.convert(c);
        return this.getCircleGrid().get(tc.getX()).get(tc.getY());
    }

    public List<JFCircle> getNeighbors(final Coordinates coordinates) {
        List<JFCircle> neighbors = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Coordinates neighbor = Coordinates.of(coordinates.getX() + d.getDx(), coordinates.getY() + d.getDy());
            if (CoordinatesValidator.validate(neighbor)) {
                neighbors.add(getCircleFromCoordinates(neighbor));
            }
        }
        return neighbors;
    }
}
