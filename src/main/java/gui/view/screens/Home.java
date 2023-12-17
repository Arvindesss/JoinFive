package gui.view.screens;

import com.sun.javafx.css.StyleManager;
import gui.controller.CreateAccountScreenController;
import gui.controller.HomeController;
import gui.controller.JoinFiveGridViewController;
import gui.controller.RankingInterfaceController;
import gui.model.ConnectionInputDTO;
import gui.model.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Home extends Application {

    /**
     * Displays a screen showing the homepage of the application
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        StyleManager.getInstance().addUserAgentStylesheet("/css/global.css");
        Font.loadFont(getClass().getResourceAsStream("/css/global.css"), 12);

        // Crée un StackPane
        StackPane stackPane = new StackPane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);

        Label gameTitle = new Label("Morpion Solitaire");
        gameTitle.setPadding(new Insets(10, 10, 10, 10));
        gameTitle.getStyleClass().addAll("title-screen", "white");
        gameTitle.setAlignment(Pos.BASELINE_CENTER);
        gameTitle.setPrefHeight(30);

        vBox.getChildren().add(gameTitle);

        Label lblErrorResponse = new Label();
        lblErrorResponse.getStyleClass().add("orange");

        Label lblPlayerField = new Label("Identifiant du joueur");
        lblPlayerField.getStyleClass().add("white");
        TextField playerField = new TextField();
        playerField.setMaxWidth(300);
        playerField.setPromptText("Identifiant du joueur");

        Label lblPasswordField = new Label("Mot de passe");
        lblPasswordField.getStyleClass().add("white");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordField.setPromptText("Mot de passe");

        Button btnPlay = new Button("Jouer");
        btnPlay.setPrefHeight(30);
        btnPlay.setPrefWidth(175);

        btnPlay.setOnAction(event ->
        {
            try {
                ConnectionInputDTO connectionInputDTO = ConnectionInputDTO.of(playerField.getText(), passwordField.getText());
                Optional<Player> player = HomeController.readPlayer(connectionInputDTO);
                if (!player.isPresent()) {
                    lblErrorResponse.setText("Compte introuvable");
                    return;
                }
                if (!HomeController.validatePassword(connectionInputDTO.getPassword(), player.get().getSaltedPassword())) {
                    lblErrorResponse.setText("Mot de passe incorrect");
                } else {
                    JoinFiveGridViewController.displayGameView(connectionInputDTO);
                    HomeController.closeHomeView(primaryStage);
                }
            } catch (Exception e) {
                lblErrorResponse.setText(e.getMessage());
                throw new RuntimeException(e);
            }
        });

        btnPlay.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

        Button btnSignUp = new Button("S'inscrire");
        btnSignUp.getStyleClass().addAll("bg-green", "white");
        btnSignUp.setPrefHeight(30);
        btnSignUp.setPrefWidth(175);
        btnSignUp.setOnAction(event -> {
            CreateAccountScreenController.displayAccountCreationScreen();
            HomeController.closeHomeView(primaryStage);
        });

        Button btnGameRules = new Button("Règles");
        btnGameRules.getStyleClass().addAll("bg-green", "white");
        btnGameRules.setPrefHeight(30);
        btnGameRules.setPrefWidth(175);
        btnGameRules.setOnAction(event -> {
            getHostServices().showDocument("http://joinfive.com/index.php?a=a");
        });

        Button btnTopScore = new Button("Top score");
        btnTopScore.getStyleClass().addAll("bg-green", "white");
        btnTopScore.setPrefHeight(30);
        btnTopScore.setPrefWidth(175);
        btnTopScore.setOnAction(event -> {
            try {
                RankingInterfaceController.displayRankingInterface();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            HomeController.closeHomeView(primaryStage);
        });

        VBox fieldsVBox = new VBox(lblErrorResponse, lblPlayerField, playerField, lblPasswordField, passwordField);
        fieldsVBox.setSpacing(15);
        fieldsVBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.getChildren().add(fieldsVBox);

        HBox firstLine = new HBox(btnSignUp, btnPlay);
        firstLine.setSpacing(100);
        HBox secondLine = new HBox(btnGameRules, btnTopScore);
        secondLine.setSpacing(100);

        VBox btnVBox = new VBox(firstLine, secondLine);
        btnVBox.setSpacing(10);
        vBox.getChildren().add(btnVBox);

        Image backgroundImage = new Image("/img/HD-wallpaper-black.jpg");
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setViewport(new Rectangle2D(0, 0, 700, 500));

        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(vBox);

        Scene scene = new Scene(stackPane, 500, 500);

        imageView.fitWidthProperty().bind(scene.widthProperty());
        imageView.fitHeightProperty().bind(scene.heightProperty());

        primaryStage.setTitle("Morpion solitaire");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

}
