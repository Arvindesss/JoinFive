package gui.controller;

import gui.model.Player;
import gui.view.screens.JoinFiveGridView;
import gui.view.screens.RankingInterface;
import javafx.stage.Stage;

import java.util.List;

public class GameController {

    public static void displayGameView() {
        Stage stage = new Stage();
        //todo: Récupérer le nom depuis la home View
        String name = " Broly Sosa";
        JoinFiveGridView gridView = new JoinFiveGridView(new Player(name));
        gridView.start(stage);
    }

    public static void displayRankingInterface() {
        Stage stage = new Stage();
        //todo: Requete à la BD
        List<Player> players = RankingInterface.parsePlayerList();

        RankingInterface rankingInterface = new RankingInterface(players);
        rankingInterface.start(stage);
    }
}
