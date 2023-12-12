package gui.controller;

import gui.model.Player;
import gui.model.jdbc.DemoApplication;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.Home;
import gui.view.screens.RankingInterface;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class RankingInterfaceController {
    public static void displayRankingInterface() throws IOException, SQLException {
        Stage stage = new Stage();
        Properties properties = new Properties();
        properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        List<Player> players = PlayerDAO.getTopTenPlayers(connection);

        RankingInterface rankingInterface = new RankingInterface(players);
        rankingInterface.start(stage);
    }

    public static void launchView() {
        Application.launch(RankingInterface.class, (String) null);
    }

    public static void closeView(Stage homeStage) {
        homeStage.close();
    }
}
