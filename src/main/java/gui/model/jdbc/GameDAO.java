package gui.model.jdbc;

import gui.model.Player;
import gui.view.util.ConnectionInputDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameDAO {

    public static void insertNewGame(Player player, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO GAME (player_id, obtained_score) VALUES (?,?);");
        insertStatement.setInt(1, player.getId());
        insertStatement.setDouble(2, player.getScore());
        insertStatement.executeUpdate();
    }
}
