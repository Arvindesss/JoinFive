package gui.view.util;

import gui.view.util.exception.InvalidInputException;

/**
 * An Object designed to contain raw input from a player
 */
public class ConnectionInputDTO {

    private final String username;

    private final String password;

    private ConnectionInputDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private static void validate(String username, String password) {
        if ("".equals(username) || username == null) {
            throw new InvalidInputException("Le nom d'utilisateur est vide");
        }
        if ("".equals(password) || password == null) {
            throw new InvalidInputException("Le mot de passe est vide");
        }
    }

    public static ConnectionInputDTO of(String username, String password) {
        validate(username, password);
        return new ConnectionInputDTO(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
