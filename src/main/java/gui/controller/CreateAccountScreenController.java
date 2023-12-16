package gui.controller;

import gui.model.jdbc.DatabaseConnection;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.CreateAccountScreen;
import gui.view.util.ConnectionEncryptedInputDTO;
import gui.view.util.ConnectionInputDTO;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateAccountScreenController {

    public static void displayAccountCreationScreen() {
        Stage stage = new Stage();
        CreateAccountScreen gridView = new CreateAccountScreen();
        gridView.start(stage);
    }

    public static void closeView(Stage homeStage) {
        homeStage.close();
    }

    /**
     * Saves new player to database
     *
     * @param inputDTO Player data (user and with encrypted password) to save
     * @throws IOException
     * @throws SQLException
     */
    public static void addPlayer(ConnectionEncryptedInputDTO inputDTO) throws IOException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PlayerDAO.insertNewPlayer(inputDTO, connection);
    }

    /**
     * Handles account creation from raw player input
     *
     * @param connectionInputDTO dto containing raw player connection input
     */

    public static void handleAccountCreation(ConnectionInputDTO connectionInputDTO) throws SQLException, IOException {
        String encryptedPassword = new BCryptPasswordEncoder().encode(connectionInputDTO.getPassword());
        CreateAccountScreenController.addPlayer(ConnectionEncryptedInputDTO.of(connectionInputDTO.getUsername(), encryptedPassword));
    }
}
