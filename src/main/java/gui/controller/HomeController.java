package gui.controller;

import gui.view.screens.JoinFiveGridView;
import javafx.application.Application;
import javafx.stage.Stage;

public class HomeController {
    public static void openHomeView() {
        Application.launch(JoinFiveGridView.class, (String) null);
    }

    public static void closeHomeView(Stage homeStage) {
        homeStage.close();
    }

}
