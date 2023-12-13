package gui.model.jdbc;

import gui.model.Player;
import gui.view.util.ConnectionInputDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PlayerDAO {

    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DemoApplication.class.getName());
    }

    public static void insertNewPlayer(ConnectionInputDTO inputs, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO PLAYER (username, password) VALUES (?,?);");
        insertStatement.setString(1, inputs.getUsername());
        insertStatement.setString(2, inputs.getPassword());
        insertStatement.executeUpdate();
    }

    public static Optional<Player> readPlayer(String playerName, Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER WHERE username = ?;");
        readStatement.setString(1, playerName);

        ResultSet resultSet = readStatement.executeQuery();
        if (!resultSet.next()) {
            log.info("There is no data in the database!");
            return Optional.empty();
        }
        log.info("Data is extracted !");
        return Optional.of(new Player(resultSet.getString("username"), resultSet.getString("password")));
    }

    public static List<Player> getTopTenPlayers(Connection connection) throws SQLException {
        List<Player> topPlayers = new ArrayList<>();
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM PLAYER p INNER JOIN GAME g on p.id = g.player_id ORDER BY g.obtained_score   ");

        ResultSet resultSet = readStatement.executeQuery();

        while (resultSet.next()) {
            topPlayers.add(new Player(resultSet.getInt("id"),resultSet.getString("username"), resultSet.getDouble("obtained_score")));
        }
        log.info("Data is extracted !");
        return topPlayers;
    }
}
