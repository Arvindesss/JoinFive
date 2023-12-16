package gui.controller.util;

import gui.controller.GameOptions;
import gui.model.Coordinates;
import gui.model.JFCircle;
import gui.model.JFGrid;
import gui.model.JFLine;
import gui.view.util.JoinFiveGridDrawer;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddLineEventHandlerTest {

    @Test
    void addHorizontalLineEvent() {
        JFCircle jfCircle = new JFCircle(new Circle(5), Coordinates.of(6, 4));
        //Given a new JFGrid with a drawn cross
        JFGrid jfGrid = new JFGrid();
        fillGrid(jfGrid);
        JoinFiveGridDrawer.drawCrossOnEmptyGrid(jfGrid);
        //When we try to add a circle to get a Line
        Optional<JFLine> line = AddLineEventHandler.addLineEvent(jfCircle, jfGrid);
        //Then we expect to get a Line that we could draw horizontally, from [6,4] to [10,4]
        assertTrue(line.isPresent());
        assertTrue(line.get().isCrossedOut());
        int startX = jfCircle.getCoordinates().getX();
        for (int i = 0; i < 5; i++) {
            assertEquals(startX, line.get().getAlignedCircles().get(i).getCoordinates().getX());
            assertEquals(4, line.get().getAlignedCircles().get(i).getCoordinates().getY());
            startX++;
        }
    }

    @Test
    void addVerticalLineEvent() {
        JFCircle jfCircle = new JFCircle(new Circle(5), Coordinates.of(7, 3));
        //Given a new JFGrid with a drawn cross
        JFGrid jfGrid = new JFGrid();
        fillGrid(jfGrid);
        JoinFiveGridDrawer.drawCrossOnEmptyGrid(jfGrid);
        //When we try to add a circle to get a Line
        Optional<JFLine> line = AddLineEventHandler.addLineEvent(jfCircle, jfGrid);
        //Then we expect to get a Line that we could draw vertically on the app, from [7,3] to [7,7]
        assertTrue(line.isPresent());
        assertTrue(line.get().isCrossedOut());
        int startY = jfCircle.getCoordinates().getY();
        for (int i = 0; i < 5; i++) {
            assertEquals(7, line.get().getAlignedCircles().get(i).getCoordinates().getX());
            assertEquals(startY, line.get().getAlignedCircles().get(i).getCoordinates().getY());
            startY++;
        }
    }

    @Test
    void addDiagonalLineEvent() {
        JFCircle jfCircle = new JFCircle(new Circle(5), Coordinates.of(6, 6));
        //Given a new JFGrid with a drawn cross
        JFGrid jfGrid = new JFGrid();
        fillGrid(jfGrid);
        JoinFiveGridDrawer.drawCrossOnEmptyGrid(jfGrid);
        //When we try to add a circle to get a Line
        Optional<JFLine> line = AddLineEventHandler.addLineEvent(jfCircle, jfGrid);
        //Then we expect to get a Line that we could draw vertically on the app, from [7,3] to [7,7]
        assertTrue(line.isPresent());
        assertTrue(line.get().isCrossedOut());
        int startX = 4;
        int startY = 8;
        for (int i = 0; i < 5; i++) {
            assertEquals(startX, line.get().getAlignedCircles().get(i).getCoordinates().getX());
            assertEquals(startY, line.get().getAlignedCircles().get(i).getCoordinates().getY());
            startX++;
            startY--;
        }
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