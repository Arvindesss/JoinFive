package gui.model.jdbc;

import gui.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDAO {

    /**
     * Inserts a new row in database, containing data on a played game
     *
     * @param player     player with data to save
     * @param connection object to access database
     * @throws SQLException
     */
    public static void insertNewGame(Player player, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO GAME (player_id, obtained_score) VALUES (?,?);");
        insertStatement.setInt(1, player.getId());
        insertStatement.setDouble(2, player.getScore());
        insertStatement.executeUpdate();
    }
}
