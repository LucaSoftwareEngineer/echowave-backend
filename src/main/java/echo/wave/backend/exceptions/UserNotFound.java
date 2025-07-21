package echo.wave.backend.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(String warning) {
        super(warning);
    }
}
