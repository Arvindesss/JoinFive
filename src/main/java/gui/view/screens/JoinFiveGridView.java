package gui.view.screens;

import gui.controller.GameEventHandler;
import gui.controller.util.GameOptions;
import gui.model.CircleGrid;
import gui.model.Coordinates;
import gui.model.JFCircle;
import gui.model.Player;
import gui.view.util.JoinFiveGridDrawer;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JoinFiveGridView extends Application {

    private CircleGrid circleGrid = new CircleGrid();
    private TilePane pane = new TilePane(GameOptions.TILE_SPACE, GameOptions.TILE_SPACE);
    private Group group = new Group(pane);



    /*public JoinFiveGridView() {
    }
*/
   /* public JoinFiveGridView(CircleGrid circleGrid) {
        this.circleGrid = circleGrid;
    }*/

    @Override
    public void start(Stage stage) {
        pane.setPrefColumns(GameOptions.NB_COLUMNS);
        Player player = new Player("Harry");

        Label score = new Label("Score : " + player.scoreProperty());

        player.scoreProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> score.setText("Score : " + player.scoreProperty())));
        group.getChildren().add(score);

        for (int row = 0; row <= GameOptions.NB_ROWS; row++) {
            final double startY = (row * (pane.vgapProperty().doubleValue() + pane.getTileHeight()));

            Line rowLine = new Line(0, startY, GameOptions.NB_COLUMNS * (GameOptions.TILE_SPACE + pane.getTileWidth()), startY);
            rowLine.setStroke(Color.GRAY);
            group.getChildren().add(rowLine);

            List<JFCircle> circles = new ArrayList<>();
            for (int col = 0; col <= GameOptions.NB_COLUMNS; col++) {

                final double startX = (col * (pane.hgapProperty().doubleValue() + pane.getTileWidth()));
                Line colLine = new Line(startX, 0, startX, GameOptions.NB_ROWS * (GameOptions.TILE_SPACE + pane.getTileHeight()));
                colLine.setStroke(Color.GRAY);
                group.getChildren().add(colLine);

                //Point creation
                int finalCol = col;
                int finalRow = row;
                Circle point = new Circle(5);
                Coordinates c = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(finalCol, finalRow));
                circles.add(new JFCircle(point, c));

                point.setFill(Color.BLACK);

                point.setOnMouseClicked((x) -> {
                    System.out.println(Coordinates.of(finalCol, finalRow).getX() + " " + Coordinates.of(finalCol, finalRow).getY());
                    Coordinates c2 = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(finalCol, finalRow));
                    GameEventHandler.addCircleEvent(x, c2, circleGrid, group, player);
                    point.setOpacity(1);
                });
                point.setOpacity(0);

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

        group.setTranslateY(50);
        group.setTranslateX(50);

        stage.setScene(new Scene(group));
        stage.setTitle("Grid");
        stage.show();
    }
}
