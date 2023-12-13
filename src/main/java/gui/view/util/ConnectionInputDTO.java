package gui.view.util;

public class ConnectionInputDTO {

    private final String username;

    private final String password;

    private ConnectionInputDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static ConnectionInputDTO of(String username, String password) {
        return new ConnectionInputDTO(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
