package gui.controller.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordValidator {

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
