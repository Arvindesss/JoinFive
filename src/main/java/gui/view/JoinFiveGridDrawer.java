package gui.view;

import gui.model.CircleGrid;
import gui.model.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class JoinFiveGridDrawer {

    public static void drawCrossOnEmptyGrid(CircleGrid pg) {
        List<Coordinates> circlesToDisplay = getCrossCoordonates();
        for (Coordinates c : circlesToDisplay) {
            pg.getCircleFromCoordonates(c).setOpacity(1);
            pg.getCircleFromCoordonates(c).toFront();
        }
    }

    private static List<Coordinates> getCrossCoordonates() {

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

