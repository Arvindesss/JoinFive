package gui.view.screens;

import com.sun.javafx.css.StyleManager;
import gui.controller.CreateAccountScreenController;
import gui.controller.HomeController;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateAccountScreen extends Application {

    /**
     * Displays a screen allowing the player to create a new player account
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
        
        StackPane stackPane = new StackPane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);

        Label signInLabel = new Label("Inscription");
        signInLabel.getStyleClass().addAll("title", "white");
        signInLabel.setAlignment(Pos.BASELINE_CENTER);
        signInLabel.setPrefHeight(30);
        signInLabel.setPrefWidth(400);

        vBox.getChildren().add(signInLabel);

        Label lblErrorResponse = new Label();
        lblErrorResponse.getStyleClass().add("orange");

        Label lblUsernameField = new Label("Identifiant de joueur");
        lblUsernameField.getStyleClass().add("white");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        usernameField.setPromptText("Identifiant du joueur");

        Label lblPasswordField = new Label("Mot de passe");
        lblPasswordField.getStyleClass().add("white");
        TextField passwordField = new TextField();
        passwordField.setMaxWidth(300);
        passwordField.setPromptText("Mot de passe");

        VBox fieldsVBox = new VBox(lblErrorResponse, lblUsernameField, usernameField, lblPasswordField, passwordField);
        fieldsVBox.setSpacing(15);
        fieldsVBox.setPadding(new Insets(40, 40, 40, 40));
        vBox.getChildren().add(fieldsVBox);

        Button btnReturn = new Button("Retour");
        btnReturn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnReturn.setPrefWidth(175);
        btnReturn.setPrefHeight(30);
        btnReturn.setOnAction((event -> {
            HomeController.openHomeView();
            CreateAccountScreenController.closeView(primaryStage);
        }));

        Button btnValidate = new Button("Valider");
        btnValidate.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnValidate.setPrefWidth(175);
        btnValidate.setPrefHeight(30);
        btnValidate.setOnAction(event -> {
            try {
                CreateAccountScreenController.handleAccountCreation(ConnectionInputDTO.of(usernameField.getText(), passwordField.getText()));
                HomeController.openHomeView();
                CreateAccountScreenController.closeView(primaryStage);
            } catch (Exception e) {
                lblErrorResponse.setText(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        });

        HBox btnLine = new HBox(btnReturn, btnValidate);
        btnLine.setSpacing(100);
        vBox.getChildren().add(btnLine);

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
