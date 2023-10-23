package gui.view;

import gui.controller.GameEventHandler;
import gui.model.CircleGrid;
import gui.model.Coordinates;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JoinFiveGridView extends Application {

    private static int NB_ROWS = 17;
    private static int NB_COLUMNS = 17;
    private static int TILE_SPACE = 50;
    private CircleGrid circleGrid = new CircleGrid();
    private TilePane pane = new TilePane(TILE_SPACE, TILE_SPACE);
    private Group group = new Group(pane);

    /*public JoinFiveGridView() {
    }
*/
   /* public JoinFiveGridView(CircleGrid circleGrid) {
        this.circleGrid = circleGrid;
    }*/

    @Override
    public void start(Stage stage) {
        pane.setPrefColumns(NB_COLUMNS);

        for (int row = 0; row <= NB_ROWS; row++) {
            final double startY = (row * (pane.vgapProperty().doubleValue() + pane.getTileHeight()));

            Line rowLine = new Line(0, startY, NB_COLUMNS * (TILE_SPACE + pane.getTileWidth()), startY);
            rowLine.setStroke(Color.GRAY);
            group.getChildren().add(rowLine);

            List<Circle> circles = new ArrayList<>();
            for (int col = 0; col <= NB_COLUMNS; col++) {

                final double startX = (col * (pane.hgapProperty().doubleValue() + pane.getTileWidth()));
                Line colLine = new Line(startX, 0, startX, NB_ROWS * (TILE_SPACE + pane.getTileHeight()));
                colLine.setStroke(Color.GRAY);
                group.getChildren().add(colLine);

                //Point creation
                Circle point = new Circle(5);
                point.setFill(Color.BLACK);

                int finalCol = col;
                int finalRow = row;
                point.setOnMouseClicked((x) -> {
                    GameEventHandler.addCircleEvent(x, Coordinates.of(finalCol, finalRow), circleGrid, group);
                });
                point.setOpacity(0);
                circles.add(point);

                // x = ((tilewidth + hgap) * col)
                // y = ((tileheight + vgap) * row)
                point.centerXProperty().bind(
                        pane.tileWidthProperty().add(pane.hgapProperty())
                                .multiply(col));
                point.centerYProperty().bind(
                        pane.tileHeightProperty().add(pane.vgapProperty())
                                .multiply(row));

                group.getChildren().add(point);
            }
            this.circleGrid.getCircleGrid().add(circles);
        }

        JoinFiveGridDrawer.drawCrossOnEmptyGrid(this.circleGrid);

        stage.setScene(new Scene(group));
        stage.setTitle("Grid");
        stage.show();
    }
}
