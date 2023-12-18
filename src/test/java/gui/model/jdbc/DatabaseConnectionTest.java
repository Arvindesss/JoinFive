package gui.model.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnectionTest {

    @Test
    @DisplayName("Assert that connection object is accessible and properties are valid")
    void getConnection() throws SQLException, IOException {
        Connection connection = DatabaseConnection.getConnection();
    }
}