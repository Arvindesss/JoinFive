package gui.view;

import gui.model.Coordinates;
import gui.model.JFLine;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JoinFiveGridView extends Application {

    private static int NB_ROWS = 17;
    private static int NB_COLUMNS = 17;
    private static int TILE_SPACE = 50;

    TilePane pane = new TilePane(TILE_SPACE, TILE_SPACE);

    Group group = new Group(pane);

    private JoinFiveGridDrawer joinFiveGridDrawer = new JoinFiveGridDrawer();

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
                System.out.println(startX);
                colLine.setStroke(Color.GRAY);
                group.getChildren().add(colLine);

                //Point creation
                Circle point = new Circle(5);
                point.setFill(Color.BLACK);

                point.setOnMouseClicked(this::activateCircleEvent);
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
            this.joinFiveGridDrawer.getCircleGrid().getCircleGrid().add(circles);
        }

        JoinFiveGridDrawer.drawCrossOnEmptyGrid(this.joinFiveGridDrawer.getCircleGrid());

        stage.setScene(new Scene(group));
        stage.setTitle("Grid");
        stage.show();
    }

    public void activateCircleEvent(MouseEvent mouseEvent) {
        Circle c = (Circle) mouseEvent.getSource();

        //verifier si le point cliqué est dans une ligne de 5 (verticale, horizontale, diagonale)

        //avoir la position du point cliqué
        //checker dans les 8 directions si on a une ligne de 5
        //if()...

        //afficher le point
        c.setOpacity(1);
        c.toFront();

        //dessiner la ligne

        LinkedList<Circle> jfLine = new LinkedList<Circle>();

        jfLine.add(joinFiveGridDrawer
                .getCircleGrid()
                .getCircleFromCoordonates(
                        Coordinates.of(5, 10)));
        jfLine.add(joinFiveGridDrawer
                .getCircleGrid()
                .getCircleFromCoordonates(
                        Coordinates.of(5, 11)));

        LineDrawer.drawLine(new JFLine(jfLine), group);

        //augmenter le score du joueur

        //else: on fait rien / on affiche que le point n'est pas cliquable

    }
}
