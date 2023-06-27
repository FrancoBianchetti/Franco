public class Admin extends Usuario {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "4321";
    private static final String DEFAULT_EMAIL = "admin@ejemplo.com";

    public Admin() {
        super(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_EMAIL);
    }

    public static boolean autenticar(String username, String password, String email) {
        return DEFAULT_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password) && DEFAULT_EMAIL.equals(email);
    }
}