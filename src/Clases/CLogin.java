package Clases;

import Formularios.FormLogin;
import Formularios.FormMenuPrincipal;
import Formularios.FormMenuPrincipal2;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public String validaUsuario(JTextField usuario, JPasswordField contrasenia) {
        String username = usuario.getText();
        if (username == null || username.isBlank()) {
            JOptionPane.showMessageDialog(null, "Ingrese el usuario");
            return null;
        }

        String inputPassword = String.valueOf(contrasenia.getPassword());

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos");
                return null;
            }

            String sql = "SELECT ingresoContrasenia, tipo_de_usuario, activo FROM usuarios WHERE ingresoUsuario = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "El usuario es incorrecto, vuelva a intentar");
                        return null;
                    }

                    if (rs.getInt("activo") != 1) {
                        JOptionPane.showMessageDialog(null, "El usuario se encuentra inactivo");
                        return null;
                    }

                    String storedHash = rs.getString("ingresoContrasenia");
                    String roleCode = rs.getString("tipo_de_usuario");

                    if (!validarPassword(conn, username, inputPassword, storedHash)) {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta, vuelva a intentar");
                        return null;
                    }

                    TipoUsuarioAdentro = roleCode;
                    registrarUltimoAcceso(conn, username.trim());
                    abrirMenuPorRol(roleCode);
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }
        return null;
    }

    public static String getTipoUsuarioAdentro() {
        return TipoUsuarioAdentro;
    }

    private boolean validarPassword(Connection conn, String username, String inputPassword, String storedHash) throws SQLException {
        if (storedHash == null) {
            return false;
        }

        if (BCrypt.checkpw(inputPassword, storedHash)) {
            return true;
        }

        if (esHashAdminPorDefecto(storedHash) && DEFAULT_ADMIN_PASSWORD.equals(inputPassword)) {
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

    private void abrirMenuPorRol(String tipoUsuario) {
        switch (tipoUsuario) {
            case "ADMIN" -> {
                FormLogin form = new FormLogin();
                form.dispose();
                FormMenuPrincipal objetoMenu = new FormMenuPrincipal();
                objetoMenu.setVisible(true);
            }
            case "DOCENTE" -> {
                FormLogin form = new FormLogin();
                form.dispose();
                FormMenuPrincipal2 objetoMenu2 = new FormMenuPrincipal2();
                objetoMenu2.setVisible(true);
            }
            case "ESTUDIANTE" -> JOptionPane.showMessageDialog(null, "El usuario ESTUDIANTE no tiene acceso a esta aplicación");
            default -> JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido");
        }
    }

    private void registrarUltimoAcceso(Connection conn, String usuario) {
        String update = "UPDATE usuarios SET ultimo_acceso = CURRENT_TIMESTAMP WHERE ingresoUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setString(1, usuario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("No se pudo actualizar el último acceso: " + ex.getMessage());
        }
    }
}
