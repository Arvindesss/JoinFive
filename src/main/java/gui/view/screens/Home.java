package gui.view.screens;

import gui.controller.GameController;
import gui.controller.HomeController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Home extends Application {
    public static boolean mode;

    @Override
    public void start(Stage primaryStage) {
        // Crée un StackPane
        StackPane stackPane = new StackPane();

        Button topButton = new Button("Morpion Solitaire");
        topButton.setStyle("-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px;");
        topButton.setPrefWidth(400);
        topButton.setPrefHeight(50);

        Button centerButton = new Button("Jouer");

        centerButton.setOnAction(event ->
        {
            GameController.displayGameView();
            HomeController.closeHomeView(primaryStage);
        });

        centerButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        centerButton.setPrefWidth(300);
        centerButton.setPrefHeight(100);

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

        // Crée un HBox pour les boutons du bas
        HBox bottomButtonsBox = new HBox(leftButton, middleButton, rightButton);
        bottomButtonsBox.setSpacing(40); // Optional: Set spacing between buttons
        bottomButtonsBox.setAlignment(Pos.CENTER); // Align buttons to the center

        // Crée un VBox pour les boutons centraux et ceux du bas
        VBox allButtonsBox = new VBox(topButton, centerButton, bottomButtonsBox);
        allButtonsBox.setAlignment(Pos.CENTER); // Align VBox content to the center
        allButtonsBox.setSpacing(60); // Optional: Set spacing between buttons and bottomButtonsBox

        // Ajoute le VBox au StackPane
        stackPane.getChildren().add(allButtonsBox);

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
