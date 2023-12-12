package gui.view.screens;

import gui.controller.CreateAccountScreenController;
import gui.controller.HomeController;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Crée un StackPane
        VBox stackPane = new VBox();

        Label topLabel = new Label("Inscription");
        topLabel.setStyle("-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-padding: 10px 20px;");
        topLabel.setPrefWidth(400);
        topLabel.setPrefHeight(50);

        TextField usernameField = new TextField();
        usernameField.setStyle("-fx-text-fill: #FFB4B4;");
        usernameField.setPromptText("Identifiant du joueur");

        TextField passwordField = new TextField();
        passwordField.setStyle("-fx-text-fill: #FFB4B4;");
        passwordField.setPromptText("Mot de passe");

        Button btnValidate = new Button("Validate");
        btnValidate.setAlignment(Pos.BOTTOM_RIGHT);

        btnValidate.setOnAction(event ->
        {
            try {
                String encryptedPassword = new BCryptPasswordEncoder().encode(passwordField.getText());
                HomeController.addPlayer(ConnectionInputDTO.of(usernameField.getText(), encryptedPassword));
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
            HomeController.openHomeView();
            CreateAccountScreenController.closeView(primaryStage);
        });

        btnValidate.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnValidate.setPrefWidth(50);
        btnValidate.setPrefHeight(50);

        // Charge l'image de fond
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("img/fond.jpg"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Crée un objet Background avec l'image de fond
        Background background = new Background(backgroundImage);

        // Applique le fond au StackPane
        stackPane.setBackground(background);
        stackPane.getChildren().add(topLabel);
        stackPane.getChildren().add(usernameField);
        stackPane.getChildren().add(passwordField);
        stackPane.getChildren().add(btnValidate);

        // Crée une scène avec le StackPane
        Scene scene = new Scene(stackPane, 400, 400);

        // Configure la scène et affiche la fenêtre
        primaryStage.setTitle("Morpion solitaire");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
