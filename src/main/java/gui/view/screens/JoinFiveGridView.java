package gui.view.screens;

import gui.controller.GameEventHandler;
import gui.controller.algorithms.JFRandomSearchAlgorithmImpl;
import gui.controller.util.GameOptions;
import gui.controller.util.PlayableCirclesCalculator;
import gui.controller.util.PossibleJFLineCalculator;
import gui.model.*;
import gui.view.util.JoinFiveGridDrawer;
import gui.view.util.LineDrawer;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JoinFiveGridView extends Application {

    private CircleGrid circleGrid = new CircleGrid();

    private Player player;

    private TilePane pane = new TilePane(GameOptions.TILE_SPACE, GameOptions.TILE_SPACE);
    private Group group = new Group();

    private List<Line> possibleLines = new ArrayList<>();

    private List<Line> drawedLines = new ArrayList<>();

    private List<Text> displayedPlayingOrder = new ArrayList<>();

    private List<Circle> displayedCirclesState = new ArrayList<>();

    private JFGameMoves jfGameMoves = new JFGameMoves();

    private int nbMoves = 0;

    public JoinFiveGridView(Player player) {
        this.player = player;
    }

    @Override
    public void start(Stage stage) {
        HBox menuHBox = showMenu(this.group, this.pane, stage, player, jfGameMoves);

        Group pane = showGridView(this.player, this.pane, this.group);
        pane.setTranslateY(100);

        this.group = new Group(menuHBox, pane);
        stage.setScene(new Scene(group));
        stage.setTitle("Grid");
        stage.show();
    }

    private Group showGridView(Player player, TilePane pane, Group group) {
        pane.setPrefColumns(GameOptions.NB_COLUMNS);

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
                    point.setFill(Color.BLACK);
                    System.out.println(TechnicalCoordinates.of(finalCol, finalRow).getX() + " " + TechnicalCoordinates.of(finalCol, finalRow).getY());
                    Coordinates c2 = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(finalCol, finalRow));
                    JFCircle clickedCircle = circleGrid.getCircleFromCoordinates(c2);
                    Optional<JFLine> line = GameEventHandler.addLineEvent(clickedCircle, circleGrid);

                    if (line.isPresent()) {
                        Line l = LineDrawer.drawLine(line.get(), Color.RED);
                        group.getChildren().add(l);
                        drawedLines.add(l);
                        jfGameMoves.addGameMove(new JFMove(clickedCircle, line.get()));
                        player.addScore();
                        clickedCircle.getCircle().setOpacity(1);
                        clickedCircle.setPlayed(true);

                        Text orderText = new Text(String.valueOf(nbMoves++));
                        orderText.setX(clickedCircle.getCircle().getCenterX() + 10);
                        orderText.setY(clickedCircle.getCircle().getCenterY() + 10);
                        orderText.setFill(Color.PURPLE);
                        clickedCircle.getCircle().toBack();

                        displayedPlayingOrder.add(orderText);

                        group.getChildren().add(orderText);
                    }

                    /* Fin de partie
                    if (PossibleJFLineCalculator.calculatePossibleLines(circleGrid, JFGameType.FIVE_D).isEmpty()) {
                        GameController.displayRankingInterface();
                    }*/
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

        return group;
    }

    private HBox showMenu(Group group, TilePane pane, Stage stage, Player player, JFGameMoves jfGameMoves) {
        Button restart = new Button("Restart");

        // bouton restart
        restart.setOnAction(event -> {
            this.circleGrid = new CircleGrid();
            player.resetScore();
            group.getChildren().clear();
            showGridView(player, pane, group);
            nbMoves = 0;
        });
        restart.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        restart.setPrefWidth(100);
        restart.setPrefHeight(50);

        Button undo = new Button("Undo");

        // bouton undo
        undo.setOnAction(event -> {
            if (!jfGameMoves.getGameMoves().isEmpty()) {
                JFMove jfMove = jfGameMoves.getGameMoves().get(jfGameMoves.getGameMoves().size() - 1);
                jfMove.getClickedCircle().getCircle().setOpacity(0);
                jfMove.getClickedCircle().setPlayed(false);
                group.getChildren().remove(drawedLines.get(drawedLines.size() - 1));
                group.getChildren().remove(displayedPlayingOrder.get(displayedPlayingOrder.size() - 1));
                drawedLines.remove(drawedLines.size() - 1);
                displayedPlayingOrder.remove(displayedPlayingOrder.size() - 1);
                player.decreaseScore();
                nbMoves--;
                jfGameMoves.getGameMoves().remove(jfGameMoves.getGameMoves().size() - 1);
            }
        });

        undo.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        undo.setPrefWidth(100);
        undo.setPrefHeight(50);

        // bouton hint
        Button btnHint = new Button("Hint");
        btnHint.setOnAction(event -> {
            if (!displayedCirclesState.isEmpty()) {
                group.getChildren().removeAll(displayedCirclesState);
                displayedCirclesState.clear();
            } else {
                for (JFCircle circle : PlayableCirclesCalculator.calculateCirclesFromPossibleLines(circleGrid, JFGameType.FIVE_D)) {
                    Circle circleCopy = new Circle(circle.getCircle().getCenterX(), circle.getCircle().getCenterY(), circle.getCircle().getRadius());
                    displayedCirclesState.add(circleCopy);
                    circleCopy.setOpacity(1);

                    circleCopy.setFill(Color.BLUE);
                    group.getChildren().add(circleCopy);
                }
            }
        });

        // bouton possible Lines
        Button btnPossibleLines = new Button("possibles Lines (debug)");
        btnPossibleLines.setOnAction(event -> {
            if (!possibleLines.isEmpty()) {
                group.getChildren().removeAll(possibleLines);
                possibleLines.clear();
            } else {
                for (JFLine line : PossibleJFLineCalculator.calculatePossibleLines(circleGrid, JFGameType.FIVE_D)) {
                    Line l = LineDrawer.drawLine(line, Color.CORAL);
                    possibleLines.add(l);
                    group.getChildren().add(l);
                }
            }
        });

        btnHint.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        btnHint.setPrefWidth(100);
        btnHint.setPrefHeight(50);

        // bouton random
        Button randomSolve = new Button("Random");
        randomSolve.setOnAction(event ->
        {
            JFGameMoves moves = new JFRandomSearchAlgorithmImpl().resolve(circleGrid);
            for (JFMove move : moves.getGameMoves()) {
                Line l = LineDrawer.drawLine(move.getDrawedLine(), Color.GREEN);
                group.getChildren().add(l);
                drawedLines.add(l);
                jfGameMoves.addGameMove(move);
                player.addScore();
                move.getClickedCircle().getCircle().setOpacity(1);
                move.getClickedCircle().setPlayed(true);
                Text orderText = new Text(String.valueOf(nbMoves++));
                orderText.setX(move.getClickedCircle().getCircle().getCenterX() + 10);
                orderText.setY(move.getClickedCircle().getCircle().getCenterY() + 10);
                orderText.setFill(Color.PURPLE);
                move.getClickedCircle().getCircle().toBack();
                displayedPlayingOrder.add(orderText);
                group.getChildren().add(orderText);
            }
        });
        randomSolve.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
        randomSolve.setPrefWidth(100);
        randomSolve.setPrefHeight(50);

        // label score

        Label score = new Label("Score : " + player.scoreProperty());
        player.scoreProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> score.setText("Score : " + player.scoreProperty())));

        // btn 5D
        Button btnFiveD = new Button("5D");
        btnFiveD.setOnAction(event -> {
            restart.fire();

        });

        // btn 5T
        Button btnFiveT = new Button("5T");
        btnFiveT.setOnAction(event -> {
            restart.fire();

        });

        HBox hbox = new HBox(restart, undo, btnHint, randomSolve, btnPossibleLines, btnFiveD, btnFiveT, score);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(50);
        return hbox;

    }

}
