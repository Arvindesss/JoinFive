package gui.view.screens;

import com.sun.javafx.css.StyleManager;
import gui.controller.GameOptions;
import gui.controller.JoinFiveGridViewController;
import gui.controller.RankingInterfaceController;
import gui.controller.algorithms.JFRandomSearchAlgorithmImpl;
import gui.controller.util.AddLineEventHandler;
import gui.controller.util.PlayableCirclesCalculator;
import gui.model.*;
import gui.view.util.TechnicalCoordinates;
import gui.view.util.converter.TechnicalCoordinatesToCoordinatesConverter;
import gui.view.util.drawer.JoinFiveGridDrawer;
import gui.view.util.drawer.LineDrawer;
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

    private JFGrid JFGrid = new JFGrid();

    private Player player;

    private TilePane pane = new TilePane(GameOptions.getTileSpace(), GameOptions.getTileSpace());
    private Group group = new Group();

    private List<Line> possibleLines = new ArrayList<>();

    private List<Line> drawedLines = new ArrayList<>();

    private List<Text> displayedPlayingOrder = new ArrayList<>();

    private List<Circle> displayedCirclesState = new ArrayList<>();

    private List<JFMove> jfGameMoves = new ArrayList<>();

    private int nbMoves = 0;

    public JoinFiveGridView(Player player) {
        this.player = player;
    }

    /**
     * Displays a screen allowing the player to play a game of JF
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
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

        // label game Mode
        Label lblGameMode = new Label("Game Mode: " + GameOptions.getGameType());
        lblGameMode.getStyleClass().add("black");
        lblGameMode.setAlignment(Pos.CENTER);
        GameOptions.getGameTypeProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lblGameMode.setText("Game Mode: " + GameOptions.getGameType())));

        // label score
        Label lblScore = new Label("Score : " + player.scoreProperty().intValue());
        lblScore.getStyleClass().add("black");
        lblScore.setAlignment(Pos.CENTER);
        player.scoreProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() -> lblScore.setText("Score : " + player.scoreProperty().intValue())));

        HBox lblHBox = new HBox(lblScore, lblGameMode);
        lblHBox.setAlignment(Pos.CENTER);
        lblHBox.setSpacing(20);
        vBox.getChildren().add(lblHBox);

        Group group = showGridView();

        vBox.getChildren().add(group);

        // button possible Lines (only for debug purposes)
     /*   Button btnPossibleLines = new Button("possibles Lines (debug)");
        btnPossibleLines.setAlignment(Pos.CENTER);
        btnPossibleLines.setOnAction(event -> {
            if (!possibleLines.isEmpty()) {
                group.getChildren().removeAll(possibleLines);
                possibleLines.clear();
            } else {
                for (JFLine line : PossibleJFLineCalculator.calculatePossibleLines(JFGrid, GameOptions.getGameType())) {
                    Line l = LineDrawer.drawLine(line, Color.CORAL);
                    possibleLines.add(l);
                    group.getChildren().add(l);
                }
            }
        });
        vBox.getChildren().add(btnPossibleLines);*/

        // button endGame
        Button btnEndGame = new Button("Fin de partie");
        btnEndGame.getStyleClass().addAll("bg-blue", "white");
        btnEndGame.setAlignment(Pos.CENTER);
        btnEndGame.setOnAction(event -> {
            try {
                JoinFiveGridViewController.addGame(this.player);
                RankingInterfaceController.displayRankingInterface();
                JoinFiveGridViewController.closeGameView(stage);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        });
        vBox.getChildren().add(btnEndGame);

        // Charge l'image de fond
        Image backgroundImage = new Image("/img/green-light.png");

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

    /**
     * Displays a screen allowing the player to play a game of JF
     *
     * @return a JavaFx group containing the game grid
     */
    public Group showGridView() {
        pane.setPrefColumns(GameOptions.getNbColumns());

        for (int row = 0; row <= GameOptions.getNbRows(); row++) {
            final double startY = (row * (pane.vgapProperty().doubleValue() + pane.getTileHeight()));
            Line rowLine = new Line(0, startY, GameOptions.getNbColumns() * (GameOptions.getTileSpace() + pane.getTileWidth()), startY);
            rowLine.setStroke(Color.BLACK);
            group.getChildren().add(rowLine);

            List<JFCircle> circles = new ArrayList<>();
            for (int col = 0; col <= GameOptions.getNbColumns(); col++) {

                final double startX = (col * (pane.hgapProperty().doubleValue() + pane.getTileWidth()));
                Line colLine = new Line(startX, 0, startX, GameOptions.getNbRows() * (GameOptions.getTileSpace() + pane.getTileHeight()));
                colLine.setStroke(Color.BLACK);
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
                    Coordinates c2 = TechnicalCoordinatesToCoordinatesConverter.convert(TechnicalCoordinates.of(finalCol, finalRow));
                    JFCircle clickedCircle = JFGrid.getCircleFromCoordinates(c2);
                    Optional<JFLine> line = AddLineEventHandler.addLineEvent(clickedCircle, JFGrid, GameOptions.getGameType());

                    if (line.isPresent()) {
                        Line l = LineDrawer.drawLine(line.get(), Color.RED);
                        group.getChildren().add(l);
                        drawedLines.add(l);
                        jfGameMoves.add(new JFMove(clickedCircle, line.get()));
                        player.addScore();
                        clickedCircle.getCircle().setOpacity(1);
                        clickedCircle.setPlayed(true);

                        Text orderText = new Text(String.valueOf(nbMoves++));
                        orderText.setX(clickedCircle.getCircle().getCenterX() + 10);
                        orderText.setY(clickedCircle.getCircle().getCenterY() + 10);
                        orderText.setFill(Color.BLACK);
                        clickedCircle.getCircle().toBack();

                        displayedPlayingOrder.add(orderText);

                        group.getChildren().add(orderText);
                    }
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

            this.JFGrid.getCircleGrid().add(circles);
        }

        JoinFiveGridDrawer.drawCrossOnEmptyGrid(this.JFGrid);

        return group;
    }

    /**
     * Creates interactions buttons and sets their actions on click
     *
     * @return a JavaFx HBox containing all the buttons to interact with the application
     */
    private HBox showMenu() {
        // button restart
        Button restart = new Button("Restart");
        restart.setOnAction(event -> {
            this.JFGrid = new JFGrid();
            player.resetScore();
            group.getChildren().clear();
            displayedPlayingOrder.clear();
            drawedLines.clear();
            possibleLines.clear();
            displayedCirclesState.clear();
            jfGameMoves.clear();
            showGridView();
            nbMoves = 0;
        });
        restart.getStyleClass().addAll("bg-blue", "white");
        restart.setPrefWidth(100);
        restart.setPrefHeight(50);

        Button btnUndo = new Button("Undo");

        // button undo
        btnUndo.setOnAction(event -> {
            if (!jfGameMoves.isEmpty()) {
                JFMove lastMove = jfGameMoves.get(jfGameMoves.size() - 1);
                lastMove.getClickedCircle().getCircle().setOpacity(0);
                lastMove.getClickedCircle().setPlayed(false);
                group.getChildren().remove(drawedLines.get(drawedLines.size() - 1));
                group.getChildren().remove(displayedPlayingOrder.get(displayedPlayingOrder.size() - 1));
                drawedLines.remove(drawedLines.size() - 1);
                displayedPlayingOrder.remove(displayedPlayingOrder.size() - 1);
                player.decreaseScore();
                nbMoves--;
                jfGameMoves.remove(jfGameMoves.size() - 1);
            }
        });

        btnUndo.getStyleClass().addAll("bg-blue", "white");
        btnUndo.setPrefWidth(100);
        btnUndo.setPrefHeight(50);

        // button hint
        Button btnHint = new Button("Hint");
        btnHint.setOnAction(event -> {
            if (!displayedCirclesState.isEmpty()) {
                group.getChildren().removeAll(displayedCirclesState);
                displayedCirclesState.clear();
            } else {
                for (JFCircle circle : PlayableCirclesCalculator.calculateCirclesFromPossibleLines(JFGrid, GameOptions.getGameType())) {
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

        // button random
        Button btnRandomSolve = new Button("Random");
        btnRandomSolve.setOnAction(event ->
        {
            List<JFMove> moves = new JFRandomSearchAlgorithmImpl().resolve(JFGrid, GameOptions.getGameType());
            for (JFMove move : moves) {
                Line l = LineDrawer.drawLine(move.getDrawedLine(), Color.GREEN);
                group.getChildren().add(l);
                drawedLines.add(l);
                jfGameMoves.add(move);
                player.addScore();
                move.getClickedCircle().getCircle().setOpacity(1);
                move.getClickedCircle().setPlayed(true);
                Text orderText = new Text(String.valueOf(nbMoves++));
                orderText.setX(move.getClickedCircle().getCircle().getCenterX() + 10);
                orderText.setY(move.getClickedCircle().getCircle().getCenterY() + 10);
                orderText.setFill(Color.BLACK);
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
            if (GameOptions.getGameType() != JFGameType.FIVE_D) {
                GameOptions.setGameType(JFGameType.FIVE_D);
                restart.fire();
            }
        });

        // btn 5T
        Button btnFiveT = new Button("5T");
        btnFiveT.getStyleClass().addAll("bg-blue", "white");
        btnRandomSolve.setPrefWidth(100);
        btnRandomSolve.setPrefHeight(50);
        btnFiveT.setOnAction(event -> {
            if (GameOptions.getGameType() != JFGameType.FIVE_T) {
                GameOptions.setGameType(JFGameType.FIVE_T);
                restart.fire();
            }
        });

        HBox hbox = new HBox(restart, btnUndo, btnHint, btnRandomSolve, btnFiveD, btnFiveT);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(50);
        return hbox;
    }
}
