package gui.view.screens;

import com.sun.javafx.css.StyleManager;
import gui.controller.GameController;
import gui.controller.GameEventHandler;
import gui.controller.RankingInterfaceController;
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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
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
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/css/global.css");
        Font.loadFont(getClass().getResourceAsStream("/css/global.css"), 12);

        StackPane stackPane = new StackPane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);

        HBox menuHBox = showMenu();
        menuHBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(menuHBox);

        // label score
        Label score = new Label("Score : " + player.scoreProperty().doubleValue());
        score.getStyleClass().add("white");
        score.setAlignment(Pos.CENTER);
        player.scoreProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> score.setText("Score : " + player.scoreProperty())));
        vBox.getChildren().add(score);

        Group group = showGridView();

        vBox.getChildren().add(group);

        // bouton possible Lines (debug)
        Button btnPossibleLines = new Button("possibles Lines (debug)");
        btnPossibleLines.setAlignment(Pos.CENTER);
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

        // bouton possible Lines (debug)
        Button btnEndGame = new Button("Fin de partie");
        btnEndGame.setAlignment(Pos.CENTER);
        btnEndGame.setOnAction(event -> {
            try {
                RankingInterfaceController.displayRankingInterface();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            GameController.closeGameView(stage);
        });
        vBox.getChildren().add(btnEndGame);

        // Charge l'image de fond
        Image backgroundImage = new Image("/img/HD-wallpaper-black.jpg");

        // Créer une ImageView pour afficher l'image
        ImageView imageView = new ImageView(backgroundImage);

        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(vBox);

        Scene scene = new Scene(stackPane, 1000, 1000);
        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.setTitle("Grid");
        stage.show();
    }

    private Group showGridView() {
        pane.setPrefColumns(GameOptions.NB_COLUMNS);

        for (int row = 0; row <= GameOptions.NB_ROWS; row++) {
            final double startY = (row * (pane.vgapProperty().doubleValue() + pane.getTileHeight()));

            Line rowLine = new Line(0, startY, GameOptions.NB_COLUMNS * (GameOptions.TILE_SPACE + pane.getTileWidth()), startY);
            rowLine.setStroke(Color.WHITE);
            group.getChildren().add(rowLine);

            List<JFCircle> circles = new ArrayList<>();
            for (int col = 0; col <= GameOptions.NB_COLUMNS; col++) {

                final double startX = (col * (pane.hgapProperty().doubleValue() + pane.getTileWidth()));
                Line colLine = new Line(startX, 0, startX, GameOptions.NB_ROWS * (GameOptions.TILE_SPACE + pane.getTileHeight()));
                colLine.setStroke(Color.WHITE);
                group.getChildren().add(colLine);

                //Point creation
                int finalCol = col;
                int finalRow = row;
                Circle point = new Circle(5);
                Coordinates c = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(finalCol, finalRow));
                circles.add(new JFCircle(point, c));

                point.setFill(Color.valueOf("#FFEC5C"));

                point.setOnMouseClicked((x) -> {
                    point.setFill(Color.valueOf("#FFEC5C"));
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

    private HBox showMenu() {
        // bouton restart
        Button restart = new Button("Restart");
        restart.setOnAction(event -> {
            this.circleGrid = new CircleGrid();
            player.resetScore();
            group.getChildren().clear();
            showGridView();
            nbMoves = 0;
        });
        restart.getStyleClass().addAll("bg-blue", "white");
        restart.setPrefWidth(100);
        restart.setPrefHeight(50);

        Button btnUndo = new Button("Undo");

        // bouton undo
        btnUndo.setOnAction(event -> {
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

        btnUndo.getStyleClass().addAll("bg-blue", "white");
        btnUndo.setPrefWidth(100);
        btnUndo.setPrefHeight(50);

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

        btnHint.getStyleClass().addAll("bg-blue", "white");
        btnHint.setPrefWidth(100);
        btnHint.setPrefHeight(50);

        // bouton random
        Button btnRandomSolve = new Button("Random");
        btnRandomSolve.setOnAction(event ->
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
                orderText.setFill(Color.valueOf("#FFEC5C"));
                move.getClickedCircle().getCircle().toBack();
                displayedPlayingOrder.add(orderText);
                group.getChildren().add(orderText);
            }
        });
        btnRandomSolve.getStyleClass().addAll("bg-blue", "white");
        btnRandomSolve.setPrefWidth(100);
        btnRandomSolve.setPrefHeight(50);

        // btn 5D
        Button btnFiveD = new Button("5D");
        btnFiveD.getStyleClass().addAll("bg-blue", "white");
        btnRandomSolve.setPrefWidth(100);
        btnRandomSolve.setPrefHeight(50);
        btnFiveD.setOnAction(event -> {
            restart.fire();
        });

        // btn 5T
        Button btnFiveT = new Button("5T");
        btnFiveT.getStyleClass().addAll("bg-blue", "white");
        btnRandomSolve.setPrefWidth(100);
        btnRandomSolve.setPrefHeight(50);
        btnFiveT.setOnAction(event -> {
            restart.fire();
        });

        HBox hbox = new HBox(restart, btnUndo, btnHint, btnRandomSolve, btnFiveD, btnFiveT);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(50);
        return hbox;
    }
}
