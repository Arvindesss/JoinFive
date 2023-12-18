package gui.controller;

import gui.model.ConnectionEncryptedInputDTO;
import gui.model.ConnectionInputDTO;
import gui.model.jdbc.DatabaseConnection;
import gui.model.jdbc.PlayerDAO;
import gui.view.screens.CreateAccountScreen;
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
     * Handles account creation from raw player input
     *
     * @param connectionInputDTO dto containing raw player connection input
     */

    public static void handleAccountCreation(ConnectionInputDTO connectionInputDTO) throws SQLException, IOException {
        Connection connection = DatabaseConnection.getConnection();
        String encryptedPassword = new BCryptPasswordEncoder().encode(connectionInputDTO.getPassword());
        PlayerDAO.insertNewPlayer(ConnectionEncryptedInputDTO.of(connectionInputDTO.getUsername(), encryptedPassword), connection);
    }
}
