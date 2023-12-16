package gui.controller.algorithms;

import gui.controller.GameOptions;
import gui.controller.util.PlayableCirclesCalculator;
import gui.model.*;
import gui.view.util.JoinFiveGridDrawer;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JFRandomSearchAlgorithmImplTest {

    @Test
    @DisplayName("Check if random search finishes and produces a result")
    void testRandomAlgorithm() throws SQLException, IOException {
        JFGrid jfGrid = new JFGrid();
        fillGrid(jfGrid);
        JoinFiveGridDrawer.drawCrossOnEmptyGrid(jfGrid);
        List<JFMove> JFMoves = new JFRandomSearchAlgorithmImpl().resolve(jfGrid);
        assertNotEquals(0, JFMoves.size());
        assertEquals(0, PlayableCirclesCalculator.calculateCirclesFromPossibleLines(jfGrid, JFGameType.FIVE_D).size());
    }

    private void fillGrid(JFGrid jfGrid) {
        for (int row = 0; row <= GameOptions.getNbRows(); row++) {
            List<JFCircle> circles = new ArrayList<>();
            for (int col = 0; col <= GameOptions.getNbColumns(); col++) {
                //Point creation
                Circle point = new Circle(5);
                Coordinates c = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(col, row));
                circles.add(new JFCircle(point, c));
            }
            jfGrid.getCircleGrid().add(circles);
        }
    }

}