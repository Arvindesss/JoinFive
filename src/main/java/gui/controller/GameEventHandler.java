package gui.controller;

import gui.controller.util.CoordinatesValidator;
import gui.model.*;
import gui.view.screens.JoinFiveGridView;
import gui.view.util.LineDrawer;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Optional;

public class GameEventHandler {

    private static final JoinFiveGridView joinFiveGridView = new JoinFiveGridView(/*new CircleGrid()*/);

    public static void addCircleEvent(MouseEvent mouseEvent, Coordinates pos, CircleGrid circleGrid, Group group, Player player) {
        Circle c = (Circle) mouseEvent.getSource();
        checkNeighbours(circleGrid, pos, group, player);
    }

    public static void addCircleEvent2(MouseEvent mouseEvent, Coordinates pos, CircleGrid circleGrid, Group group, Player player) {
        Circle c = (Circle) mouseEvent.getSource();

        JFCircle clickedCircle = circleGrid.getCircleFromCoordinates(pos)

        List<JFCircle> neighbors = circleGrid.getNeighbors(pos);

        for (JFCircle neighbor : neighbors) {
            if (neighbor.getCircle().isVisible()) {
                JFLine line = makeLine(circleGrid,clickedCircle , neighbor);

               /* if (line.isCrossedOut()) {
                    LineDrawer.drawLine(line, group);
                    player.addScore(1);
                    circleClicked.getCircle().setOpacity(1);
                    circleClicked.getCircle().toBack();
                }*/

            }

        }

        checkNeighbours(circleGrid, pos, group, player);
    }

    private static JFLine makeLine(CircleGrid circleGrid, JFCircle circleClicked, JFCircle neighbor) {
        JFLine line = new JFLine();
        line.addCircle(circleClicked);
        line.addCircle(neighbor);
        Direction d = Direction.getDirectionFromCoordinates(circleClicked.getCoordinates(), neighbor.getCoordinates());

        for (int i = 1; i <= 4; i++) {
            final JFCircle circleFromCoordonates = circleGrid.getCircleFromCoordinates(Coordinates.of(neighbor.getCoordinates().getX() + i * d.getDx(), neighbor.getCoordinates().getY() + i * d.getDy()));
            if (circleFromCoordonates.getCircle().getOpacity() == 1) {
                line.addCircle(circleFromCoordonates);
            } else {
                break;
            }
        }

        return Optional.empty();

    }

    private void goBack(JFCircle circleClicked) {

    }

    private static void checkNeighbours(CircleGrid circleGrid, Coordinates clickedCirclePos, Group group, Player player) {

        for (Direction d : Direction.values()) {

            // pointer vers le cercle technique
            final JFCircle circleClicked = circleGrid.getCircleFromCoordinates(Coordinates.of(clickedCirclePos.getX(), clickedCirclePos.getY()));
            final int adjX = clickedCirclePos.getX() + d.getDx();
            final int adjY = clickedCirclePos.getY() + d.getDy();

            if (CoordinatesValidator.validate(adjX, adjY)) {
                JFCircle adjCircle = circleGrid.getCircleFromCoordinates(Coordinates.of(adjX, adjY));

                if (adjCircle.getCircle().getOpacity() == 1) {
                    JFLine line = new JFLine();

                    line.addCircle(circleClicked);
                    line.addCircle(adjCircle);

                    for (int i = 1; i <= 4; i++) {
                        if (CoordinatesValidator.validate(adjX + i * d.getDx(), adjY + i * d.getDy())) {
                            final JFCircle circleFromCoordonates = circleGrid.getCircleFromCoordinates(Coordinates.of(adjX + i * d.getDx(), adjY + i * d.getDy()));
                            if (circleFromCoordonates.getCircle().getOpacity() == 1) {
                                line.addCircle(circleFromCoordonates);
                            } else {
                                break;
                            }
                        }
                    }

                    //si c'est pas fini, verifier dans l'autre sens de la ligne
                    if (!line.isCrossedOut()) {
                        Direction invertedDir = d.getInvertedDirection();
                        final int invertedAdjX = clickedCirclePos.getX() + invertedDir.getDx();
                        final int invertedAdjY = clickedCirclePos.getY() + invertedDir.getDy();
                        if (CoordinatesValidator.validate(invertedAdjX, invertedAdjY)) {
                            JFCircle invertedAdjCircle = circleGrid.getCircleFromCoordinates(Coordinates.of(invertedAdjX, invertedAdjY));

                            if (invertedAdjCircle.getCircle().getOpacity() == 1) {
                                //echange à faire
                                JFCircle tmp = line.getAlignedCircles().get(0);
                                line.getAlignedCircles().set(0, line.getAlignedCircles().getLast());
                                line.getAlignedCircles().removeLast();
                                line.addCircle(tmp);

                                line.addCircle(invertedAdjCircle);
                                for (int i = 1; i <= 4; i++) {
                                    if (CoordinatesValidator.validate(invertedAdjX + i * invertedDir.getDx(), invertedAdjY + i * invertedDir.getDy())) {
                                        final JFCircle circleFromCoordonates = circleGrid.getCircleFromCoordinates(Coordinates.of(invertedAdjX + i * invertedDir.getDx(), invertedAdjY + i * invertedDir.getDy()));
                                        if (circleFromCoordonates.getCircle().getOpacity() == 1) {
                                            line.addCircle(circleFromCoordonates);
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (line.isCrossedOut()) {
                        LineDrawer.drawLine(line, group);
                        player.addScore(1);
                        circleClicked.getCircle().setOpacity(1);
                        circleClicked.getCircle().toBack();
                        return;
                    }
                }

            }
        }
    }

}


