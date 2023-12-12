package gui.controller;

import gui.view.screens.CreateAccountScreen;
import javafx.stage.Stage;

public class CreateAccountScreenController {

    public static void displayAccountCreationScreen() {
        Stage stage = new Stage();
        CreateAccountScreen gridView = new CreateAccountScreen();
        gridView.start(stage);
    }

    public static void closeView(Stage homeStage) {
        homeStage.close();
    }
}
