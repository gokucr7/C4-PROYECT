package Clases;


import Formularios.FormLogin;
import Formularios.FormMenuPrincipal;
import Formularios.FormMenuPrincipal2;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class CLogin {
    // 
    private static String TipoUsuarioAdentro;
    //metodo
     public String validaUsuario(JTextField usuario, JPasswordField contrasenia){
    
        
        try {
        ResultSet rs = null;
        PreparedStatement ps = null;

        String consulta = "select * from Usuarios where Usuarios.ingresoUsuario = ? and Usuarios.ingresoContrasenia = ?;";
        ps = Cconexion.estableceConexion().prepareStatement(consulta);

        String contra = String.valueOf(contrasenia.getPassword());

        ps.setString(1, usuario.getText());
        ps.setString(2, contra);

        rs = ps.executeQuery();

        if (rs.next()) {
            // 
            TipoUsuarioAdentro = rs.getString("tipo_de_usuario");

            String tipoUsuario = rs.getString("tipo_de_usuario");

            if ("administrador".equals(tipoUsuario)) {
                // 
                FormLogin form = new FormLogin();
                form.dispose();
                FormMenuPrincipal objetoMenu = new FormMenuPrincipal();
                objetoMenu.setVisible(true);
            } else if ("maestro".equals(tipoUsuario)) {
                // 
                FormLogin form = new FormLogin();
                form.dispose();
                FormMenuPrincipal2 objetoMenu2 = new FormMenuPrincipal2();
                objetoMenu2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de usuario no reconocido");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Usuario es INCORRECTO, VUELVA A INTENTAR");
        }
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());
    }
    return null;
    }
    // añadde metodo estatico
     
    public static String getTipoUsuarioAdentro() {
        return TipoUsuarioAdentro;
    }
}