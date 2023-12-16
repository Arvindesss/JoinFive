package gui.controller.util;

import gui.controller.GameOptions;
import gui.model.*;
import gui.view.util.JoinFiveGridDrawer;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PossibleJFLineCalculatorTest {

    @Test
    void calculatePossibleLines() {
        //Given a new JFGrid with a drawn cross
        JFGrid jfGrid = new JFGrid();
        fillGrid(jfGrid);
        JoinFiveGridDrawer.drawCrossOnEmptyGrid(jfGrid);
        //When we calculate all the possibles lines
        List<JFLine> lines = PossibleJFLineCalculator.calculatePossibleLines(jfGrid, JFGameType.FIVE_D);
        //Then we expect this result
        assertEquals(28, lines.size());
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