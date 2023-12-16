package gui.view.util;

import gui.view.util.exception.InvalidInputException;

public class ConnectionEncryptedInputDTO {

    private final String username;

    private final String encryptedPassword;

    private ConnectionEncryptedInputDTO(String username, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    private static void validate(String username, String password){
        if("".equals(username) || username == null){
            throw new InvalidInputException("Le nom d'utilisateur est vide");
        }
        if("".equals(password) || password == null){
            throw new InvalidInputException("Le mot de passe est vide");
        }
    }

    public static ConnectionEncryptedInputDTO of(String username, String password) {
        validate(username, password);
        return new ConnectionEncryptedInputDTO(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
