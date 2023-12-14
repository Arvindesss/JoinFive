package gui.controller;

import gui.model.Player;
import gui.view.screens.JoinFiveGridView;
import javafx.stage.Stage;

public class GameController {

    public static void displayGameView() {
        Stage stage = new Stage();
        //todo: Récupérer le nom depuis la home View
        String name = " Broly Sosa";
        JoinFiveGridView gridView = new JoinFiveGridView(new Player(name));
        gridView.start(stage);
    }

    public static void closeGameView(Stage homeStage) {
        homeStage.close();
    }

}
