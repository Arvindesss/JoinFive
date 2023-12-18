package gui.model.jdbc;

import gui.model.ConnectionEncryptedInputDTO;
import gui.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    /**
     * Saves new player to database by executing an SQL query
     *
     * @param inputs     Player data (user and with encrypted password) to save
     * @param connection object to access database
     * @throws SQLException
     */
    public static void insertNewPlayer(ConnectionEncryptedInputDTO inputs, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO PLAYER (username, password) VALUES (?,?);");
        insertStatement.setString(1, inputs.getUsername());
        insertStatement.setString(2, inputs.getEncryptedPassword());
        insertStatement.executeUpdate();
    }

    /**
     * Reads new player (if exists) from database by executing an SQL query
     *
     * @param playerName player name to search (it is unique)
     * @param connection object to access database
     * @return the searched player (if he exists)
     * @throws SQLException
     */
    public static Optional<Player> readPlayer(String playerName, Connection connection) throws SQLException {
        LOGGER.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER WHERE username = ?;");
        readStatement.setString(1, playerName);
        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            LOGGER.info("There is no data in the database!");
            return Optional.empty();
        }
        LOGGER.info("Data is extracted !");
        Player p = Player.builder()
                .setId(resultSet.getInt("id"))
                .setName(resultSet.getString("username"))
                .setSaltedPassword(resultSet.getString("password"))
                .build();
        return Optional.of(p);
    }

    /**
     * Get A list of the top 10 player games, sorted by score, from the database by executing an SQL query
     *
     * @param connection object to access database
     * @return the list of the top 10 players
     * @throws SQLException
     */
    public static List<Player> getTopTenPlayers(Connection connection) throws SQLException {
        List<Player> topPlayers = new ArrayList<>();
        LOGGER.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER p INNER JOIN GAME g on p.id = g.player_id ORDER BY g.obtained_score");
        ResultSet resultSet = readStatement.executeQuery();
        while (resultSet.next()) {
            Player p = Player.builder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("username"))
                    .setScore(resultSet.getInt("obtained_score"))
                    .build();
            topPlayers.add(p);
        }
        LOGGER.info("Data is extracted !");
        return topPlayers;
    }
}
