package gui.controller;

import gui.model.jdbc.DemoApplication;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.Home;
import gui.view.util.ConnectionInputDTO;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    public static void addPlayer(ConnectionInputDTO inputDTO) throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        PlayerDAO.insertNewPlayer(inputDTO, connection);
    }
}
