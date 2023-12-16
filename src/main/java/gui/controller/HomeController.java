package gui.controller;

import gui.model.Player;
import gui.model.jdbc.DatabaseConnection;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.Home;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class HomeController {
    public static void launchHomeView() {
        Application.launch(Home.class, (String) null);
    }

    public static void openHomeView() {
        Stage stage = new Stage();
        new Home().start(stage);
    }

    public static void closeHomeView(Stage homeStage) {
        homeStage.close();
    }

    /**
     * Finds players from input (username and password)
     *
     * @param inputDTO
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static Optional<Player> readPlayer(ConnectionInputDTO inputDTO) throws IOException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        return PlayerDAO.readPlayer(inputDTO.getUsername(), connection);
    }

    /**
     * Check if password is valid and matches player password, by using BCrypt method with a salt
     *
     * @param password password to validate
     * @param salt     the salt given in order to verify password validity
     * @return the result of the validation
     */
    public static boolean validatePassword(String password, String salt) {
        return new BCryptPasswordEncoder().matches(password, salt);
    }
}
