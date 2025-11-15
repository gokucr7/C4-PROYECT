package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;

public class Cconexion {

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/login";
    private static final String DEFAULT_USER = "angel1204";
    private static final String DEFAULT_PASSWORD = "1234";

    private static final String URL = valueFromEnv("DB_URL", DEFAULT_URL);
    private static final String USER = valueFromEnv("DB_USER", DEFAULT_USER);
    private static final String PASSWORD = valueFromEnv("DB_PASSWORD", DEFAULT_PASSWORD);

    private static final String INSERT_USUARIO =
            "INSERT INTO usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario) VALUES (?, ?, ?)";
    private static final String SELECT_EXISTE_USUARIO =
            "SELECT 1 FROM usuarios WHERE ingresoUsuario = ?";

    public Cconexion() {
    }

    public static Connection estableceConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            return null;
        }
    }

    public void guardarUsuario(String user, String password) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(password, "password");

        try (Connection conexion = estableceConexion()) {
            if (conexion == null) {
                JOptionPane.showMessageDialog(null, "Error al establecer la conexión");
                return;
            }

            if (usuarioExiste(user, conexion)) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado");
                return;
            }

            if (!validarContrasenia(password)) {
                JOptionPane.showMessageDialog(null,
                        "La contraseña no es suficientemente segura.\n"
                                + "Debe tener al menos 8 caracteres y contener un símbolo especial.");
                return;
            }

            try (PreparedStatement pstmt = conexion.prepareStatement(INSERT_USUARIO)) {
                pstmt.setString(1, user.trim());
                pstmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
                pstmt.setString(3, "DOCENTE");

                int resultado = pstmt.executeUpdate();
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario registrado como 'DOCENTE' correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el usuario");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    private boolean usuarioExiste(String user, Connection conexion) throws SQLException {
        try (PreparedStatement pstmt = conexion.prepareStatement(SELECT_EXISTE_USUARIO)) {
            pstmt.setString(1, user.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean validarContrasenia(String password) {
        return password.length() >= 8
                && password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*");
    }

    private static String valueFromEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
