package gui.view.screens;

import gui.controller.CreateAccountScreenController;
import gui.controller.GameController;
import gui.controller.HomeController;
import gui.controller.RankingInterfaceController;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Home extends Application {
    public static boolean mode;

    @Override
    public void start(Stage primaryStage) {
        // Crée un StackPane
        VBox stackPane = new VBox();

        Label gameTitle = new Label("Morpion Solitaire");
        gameTitle.setStyle("-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px;");
        gameTitle.setPrefWidth(400);
        gameTitle.setPrefHeight(50);

        TextField playerField = new TextField();
        playerField.setStyle("-fx-text-fill: #FFB4B4;");
        playerField.setPromptText("Identifiant du joueur");

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-text-fill: #FFB4B4;");
        passwordField.setPromptText("Mot de passe");

        Button playButton = new Button("Jouer");

        playButton.setOnAction(event ->
        {
            try {
                if (HomeController.validatePlayerConnectionData(ConnectionInputDTO.of(playerField.getText(), passwordField.getText()))) {
                    GameController.displayGameView();
                    HomeController.closeHomeView(primaryStage);
                } else {
                    System.out.println("je ne te connais pas ");
                }
            } catch (IOException | SQLException e) {

                throw new RuntimeException(e);
            }

        });

        playButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

        // Crée deux nouveaux boutons
        Button rightButton = new Button("5D");
        Button leftButton = new Button("5T");

        leftButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        leftButton.setPrefHeight(50);
        leftButton.setPrefWidth(100);

        leftButton.setOnAction(event -> {
            leftButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            rightButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            mode = false;

        });

        rightButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        rightButton.setPrefHeight(50);
        rightButton.setPrefWidth(100);

        rightButton.setOnAction(event -> {
            leftButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            rightButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            mode = true;

        });

        Button middleButton = new Button("Règles du jeu");
        middleButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        middleButton.setPrefHeight(50);
        middleButton.setPrefWidth(100);
        middleButton.setOnAction(event -> {
            getHostServices().showDocument("http://joinfive.com/index.php?a=a");
        });

        Button btnSignUp = new Button("S'inscrire");
        btnSignUp.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        btnSignUp.setPrefHeight(50);
        btnSignUp.setPrefWidth(100);
        btnSignUp.setOnAction(event -> {
            CreateAccountScreenController.displayAccountCreationScreen();
            HomeController.closeHomeView(primaryStage);
        });

        Button topScore = new Button("Top score");
        topScore.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        topScore.setPrefHeight(50);
        topScore.setPrefWidth(100);
        topScore.setOnAction(event -> {
            try {
                RankingInterfaceController.displayRankingInterface();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
            HomeController.closeHomeView(primaryStage);
        });
        stackPane.getChildren().add(gameTitle);
        stackPane.getChildren().add(playerField);
        stackPane.getChildren().add(passwordField);
        stackPane.getChildren().add(leftButton);
        stackPane.getChildren().add(middleButton);
        stackPane.getChildren().add(btnSignUp);
        stackPane.getChildren().add(rightButton);
        stackPane.getChildren().add(playButton);
        stackPane.getChildren().add(topScore);
        // Charge l'image de fond
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("img/fond.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Crée un objet Background avec l'image de fond
        Background background = new Background(backgroundImage);

        // Applique le fond au StackPane
        stackPane.setBackground(background);

        // Crée une scène avec le StackPane
        Scene scene = new Scene(stackPane, 400, 400);

        // Configure la scène et affiche la fenêtre
        primaryStage.setTitle("Morpion solitaire");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
