package gui.view.util;

import gui.model.Coordinates;
import gui.model.JFGrid;

import java.util.ArrayList;
import java.util.List;

public class JoinFiveGridDrawer {

    /**
     * Draws a cross on an empty game grid
     *
     * @param pg an empty game grid
     * @return the list of desired circles to draw a cross
     */
    public static void drawCrossOnEmptyGrid(JFGrid pg) {
        List<Coordinates> circlesToDisplay = getCrossCoordinates();
        for (Coordinates c : circlesToDisplay) {
            pg.getCircleFromCoordinates(c).getCircle().setOpacity(1);
            pg.getCircleFromCoordinates(c).getCircle().toFront();
            pg.getCircleFromCoordinates(c).setPlayed(true);
        }
    }

    /**
     * Contains boilerplate code to get all coordinates of the circles we want to display in order to render
     * a cross on game grid
     *
     * @return the list of desired circles to draw a cross
     */
    private static List<Coordinates> getCrossCoordinates() {

        List<Coordinates> circlesToDisplay = new ArrayList<>();

        //vertical cross lines
        for (int i = 7; i < 11; i++) {
            circlesToDisplay.add(Coordinates.of(4, i));
            circlesToDisplay.add(Coordinates.of(13, i));

        }

        for (int i = 4; i < 8; i++) {
            circlesToDisplay.add(Coordinates.of(7, i));
            circlesToDisplay.add(Coordinates.of(7, i + 6));

            circlesToDisplay.add(Coordinates.of(10, i));
            circlesToDisplay.add(Coordinates.of(10, i + 6));

        }

        //horizontal cross lines
        for (int i = 8; i < 10; i++) {
            circlesToDisplay.add(Coordinates.of(i, 4));
            circlesToDisplay.add(Coordinates.of(i, 13));
        }

        for (int i = 5; i < 7; i++) {
            circlesToDisplay.add(Coordinates.of(i, 7));
            circlesToDisplay.add(Coordinates.of(i + 6, 7));

            circlesToDisplay.add(Coordinates.of(i, 10));
            circlesToDisplay.add(Coordinates.of(i + 6, 10));
        }
        return circlesToDisplay;
    }
}

