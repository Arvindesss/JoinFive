package gui.controller;

import gui.model.Player;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.Home;
import gui.view.util.ConnectionEncryptedInputDTO;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

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

    public static void addPlayer(ConnectionEncryptedInputDTO inputDTO) throws IOException, SQLException {

        Properties properties = new Properties();
        properties.load(HomeController.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        PlayerDAO.insertNewPlayer(inputDTO, connection);
    }

    public static Optional<Player> readPlayer(ConnectionInputDTO inputDTO) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(HomeController.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        return PlayerDAO.readPlayer(inputDTO.getUsername(), connection);
    }

    public static boolean validatePassword(String password, Player player) {
        return new BCryptPasswordEncoder().matches(password, player.getSaltedPassword());
    }
}
