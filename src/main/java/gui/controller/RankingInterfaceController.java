package gui.controller;

import gui.model.Player;
import gui.model.jdbc.DatabaseConnection;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.RankingInterface;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RankingInterfaceController {
    public static void displayRankingInterface() throws IOException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Player> players = PlayerDAO.getTopTenPlayers(connection);
        RankingInterface rankingInterface = new RankingInterface(players);
        rankingInterface.start(new Stage());
    }

    public static void closeView(Stage homeStage) {
        homeStage.close();
    }
}
