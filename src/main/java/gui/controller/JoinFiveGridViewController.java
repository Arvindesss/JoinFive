package gui.controller;

import gui.model.ConnectionInputDTO;
import gui.model.Player;
import gui.model.jdbc.DatabaseConnection;
import gui.model.jdbc.GameDAO;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.JoinFiveGridView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class JoinFiveGridViewController {

    public static void displayGameView(ConnectionInputDTO inputDTO) throws IOException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Optional<Player> player = PlayerDAO.readPlayer(inputDTO.getUsername(), connection);
        JoinFiveGridView gridView = new JoinFiveGridView(player.get());
        gridView.start(new Stage());
    }

    public static void closeGameView(Stage homeStage) {
        homeStage.close();
    }

    /**
     * Saves a finished game
     *
     * @param player player having data to save
     * @throws IOException
     * @throws SQLException
     */
    public static void addGame(Player player) throws IOException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        GameDAO.insertNewGame(player, connection);
    }
}
