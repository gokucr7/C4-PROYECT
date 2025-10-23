package Clases;

import Formularios.FormLogin;
import Formularios.FormMenuPrincipal;
import Formularios.FormMenuPrincipal2;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CLogin {
    private static final String DEFAULT_ADMIN_PASSWORD = "admin123";
    private static final String[] DEFAULT_ADMIN_HASHES = {
        "$2y$10$E9nPo.Eys2SbkDXOiv1sEuLT4Va2eG5n0UZANUeAFX3K8t1Bz88.i",
        "$2y$10$E9nPo.Eys2SbkDXOiv1sEuDJyUXHHQj5YVJmF7UZ4P.Z0/hqtd7x2"
    };

    private static String TipoUsuarioAdentro;

    public boolean validaUsuario(FormLogin origen, JTextField usuario, JPasswordField contrasenia) {
        String username = usuario.getText();
        if (username == null || username.isBlank()) {
            JOptionPane.showMessageDialog(null, "Ingrese el usuario");
            contrasenia.setText("");
            return false;
        }

        char[] input = contrasenia.getPassword();
        String inputPassword = String.valueOf(input);
        Arrays.fill(input, '\0');

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos");
                return false;
            }

            String sql = "SELECT ingresoContrasenia, tipo_de_usuario, activo FROM usuarios WHERE ingresoUsuario = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "El usuario es incorrecto, vuelva a intentar");
                        return false;
                    }

                    if (rs.getInt("activo") != 1) {
                        JOptionPane.showMessageDialog(null, "El usuario se encuentra inactivo");
                        return false;
                    }

                    String storedHash = rs.getString("ingresoContrasenia");
                    String roleCode = rs.getString("tipo_de_usuario");

                    if (!validarPassword(conn, username, inputPassword, storedHash)) {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta, vuelva a intentar");
                        return false;
                    }

                    TipoUsuarioAdentro = roleCode;
                    abrirMenuPorRol(origen, roleCode);
                    return true;
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            contrasenia.setText("");
        }
        return false;
    }

    public static String getTipoUsuarioAdentro() {
        return TipoUsuarioAdentro;
    }

    private boolean validarPassword(Connection conn, String username, String inputPassword, String storedHash) throws SQLException {
        if (storedHash == null) {
            return false;
        }

        if (esHashBcrypt(storedHash)) {
            if (BCrypt.checkpw(inputPassword, storedHash)) {
                return true;
            }

            if (esHashAdminPorDefecto(storedHash) && DEFAULT_ADMIN_PASSWORD.equals(inputPassword)) {
                actualizarPassword(conn, username, inputPassword);
                return true;
            }

            return false;
        }

        if (storedHash.equals(inputPassword)) {
            actualizarPassword(conn, username, inputPassword);
            return true;
        }

        return false;
    }

    private void actualizarPassword(Connection conn, String usuario, String nuevaContrasenia) throws SQLException {
        String update = "UPDATE usuarios SET ingresoContrasenia = ? WHERE ingresoUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, BCrypt.hashpw(nuevaContrasenia, BCrypt.gensalt()));
            ps.setString(2, usuario);
            ps.executeUpdate();
        }
    }

    private boolean esHashAdminPorDefecto(String hash) {
        for (String defaultHash : DEFAULT_ADMIN_HASHES) {
            if (defaultHash.equals(hash)) {
                return true;
            }
        }
        return false;
    }

    private boolean esHashBcrypt(String hash) {
        return hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$");
    }

    private void abrirMenuPorRol(FormLogin origen, String tipoUsuario) {
        if (origen != null) {
            origen.dispose();
        }
        switch (tipoUsuario) {
            case "ADMIN" -> {
                FormMenuPrincipal objetoMenu = new FormMenuPrincipal();
                objetoMenu.setVisible(true);
            }
            case "DOCENTE" -> {
                FormMenuPrincipal2 objetoMenu2 = new FormMenuPrincipal2();
                objetoMenu2.setVisible(true);
            }
            case "ESTUDIANTE" -> JOptionPane.showMessageDialog(null, "El usuario ESTUDIANTE no tiene acceso a esta aplicación");
            default -> JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido");
        }
    }
}
