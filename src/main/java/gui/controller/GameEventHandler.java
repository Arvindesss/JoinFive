package gui.controller;

import gui.model.CircleGrid;
import gui.model.Coordinates;
import gui.model.Direction;
import gui.model.JFLine;
import gui.view.JoinFiveGridView;
import gui.view.LineDrawer;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class GameEventHandler {

    //private CircleGrid circleGrid = new CircleGrid();

    private static final JoinFiveGridView joinFiveGridView = new JoinFiveGridView(/*new CircleGrid()*/);

    public static void addCircleEvent(MouseEvent mouseEvent, Coordinates pos, CircleGrid circleGrid, Group group) {

        Circle c = (Circle) mouseEvent.getSource();

        //verifier si le point cliqué est dans une ligne de 5 (verticale, horizontale, diagonale)
        //checker dans les 8 directions si on a une ligne de 5
        //if()...

        checkNeighbours(circleGrid, pos, group);

        if (isInFive(circleGrid, c)) {
            //afficher le point
            c.setOpacity(1);
            c.toFront();

            //dessiner la ligne

          /*  LinkedList<Circle> jfLine = new LinkedList<Circle>();

            //point de départ
            jfLine.add(circleGrid.getCircleFromCoordonates(
                    Coordinates.of(pos.getX(), pos.getY())));

            //point de de fin
            jfLine.add(circleGrid.getCircleFromCoordonates(
                    Coordinates.of(5, 11)));

            LineDrawer.drawLine(new JFLine(jfLine), group);*/

        }
        //augmenter le score du joueur

        //else: on fait rien / on affiche que le point n'est pas cliquable sur le second menu
    }

    private static boolean isInFive(CircleGrid circleGrid, Circle circle) {
        return true;
    }

    private static void checkNeighbours(CircleGrid circleGrid, Coordinates clickedCirclePos, Group group) {
        for (Direction d : Direction.values()) {
            final int adjX = clickedCirclePos.getX() + d.getDx();
            final int adjY = clickedCirclePos.getY() + d.getDy();
            Circle adjCircle = circleGrid.getCircleFromCoordonates(Coordinates.of(adjX, adjY));
            if (adjCircle.getOpacity() == 1) {
                JFLine line = new JFLine();
                line.addCircle(circleGrid.getCircleFromCoordonates(Coordinates.of(clickedCirclePos.getX(), clickedCirclePos.getY())));
                line.addCircle(adjCircle);

                for (int i = 1; i <= 3; i++) {
                    final Circle circleFromCoordonates = circleGrid.getCircleFromCoordonates(Coordinates.of(adjX + i * d.getDx(), adjY + i * d.getDy()));
                    if (circleFromCoordonates.getOpacity() == 1) {
                        line.addCircle(circleFromCoordonates);
                    }
                }

                if (line.isCrossedOut()) {
                    LineDrawer.drawLine(line, group);
                    return;
                }

            }
            /*System.out.println("x " + adjCircle.getCenterX() / (50));
            System.out.println("y " + adjCircle.getCenterY() / (50));*/
           /* adjCircle.setOpacity(1);
            adjCircle.setFill(Color.GREEN);*/
            //stocker la nouvelle ligne crée

        }

        //return true;
    }
}
