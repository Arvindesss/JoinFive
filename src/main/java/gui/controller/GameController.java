package gui.controller;

import gui.model.Player;
import gui.model.jdbc.GameDAO;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.JoinFiveGridView;
import gui.view.util.ConnectionInputDTO;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class GameController {

    public static void displayGameView(ConnectionInputDTO inputDTO) throws IOException, SQLException {
        Stage stage = new Stage();
        Properties properties = new Properties();
        properties.load(GameController.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        Optional<Player> player = PlayerDAO.readPlayer(inputDTO.getUsername(), connection);
        JoinFiveGridView gridView = new JoinFiveGridView(player.get());
        gridView.start(stage);
    }

    public static void closeGameView(Stage homeStage) {
        homeStage.close();
    }

    public static void addGame(Player player) throws IOException, SQLException {

        Properties properties = new Properties();
        properties.load(GameController.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        GameDAO.insertNewGame(player, connection);
    }

}
