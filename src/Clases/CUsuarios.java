package Clases;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CUsuarios {
    private int codigo;
    private String usuario;
    private String contrasena;
    private String tipoDeUsuario;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public void cargarRoles(JComboBox<String> combo) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        String sql = "SELECT codigo, nombre FROM rol ORDER BY nombre";

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                establecerRolesPredeterminados(modelo);
            } else {
                try (PreparedStatement ps = conn.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String codigo = rs.getString("codigo");
                        String nombre = rs.getString("nombre");
                        modelo.addElement(codigo + " - " + nombre);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los roles: " + e.getMessage());
            establecerRolesPredeterminados(modelo);
        }

        if (modelo.getSize() == 0) {
            establecerRolesPredeterminados(modelo);
        }

        combo.setModel(modelo);
        if (modelo.getSize() > 0) {
            combo.setSelectedIndex(0);
        }
    }

    public void InsertarUsuarios(JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        setUsuario(paramUsuarios.getText().trim());
        setContrasena(paramContrasena.getText());
        setTipoDeUsuario(extraerCodigoRol(paramTipoDeUsuario.getSelectedItem()));

        if (getUsuario().isBlank() || getContrasena().isBlank()) {
            JOptionPane.showMessageDialog(null, "El usuario y la contraseña son obligatorios");
            return;
        }

        if (getTipoDeUsuario().isBlank()) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario válido");
            return;
        }

        String consulta = "INSERT INTO usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario) VALUES (?, ?, ?)";

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(consulta)) {
                ps.setString(1, getUsuario());
                ps.setString(2, BCrypt.hashpw(getContrasena(), BCrypt.gensalt()));
                ps.setString(3, getTipoDeUsuario());
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.getMessage());
        }
    }

    public void MostrarUsuarios(JTable paramTablaTotalUsuarios) {
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaTotalUsuarios.setRowSorter(ordenarTabla);

        modelo.addColumn("Id");
        modelo.addColumn("Usuario");
        modelo.addColumn("Rol");
        modelo.addColumn("Descripción");

        paramTablaTotalUsuarios.setModel(modelo);

        String sql = "SELECT u.id, u.ingresoUsuario, u.tipo_de_usuario, COALESCE(r.nombre, '') "
                   + "FROM usuarios u LEFT JOIN rol r ON u.tipo_de_usuario = r.codigo";

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                    });
                }
            }

            paramTablaTotalUsuarios.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.getMessage());
        }
    }

    public void SeleccionarUsuario(JTable paramTablaUsuarios, JTextField paramId, JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        try {
            int fila = paramTablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                paramId.setText(paramTablaUsuarios.getValueAt(fila, 0).toString());
                paramUsuarios.setText(paramTablaUsuarios.getValueAt(fila, 1).toString());
                paramContrasena.setText("");

                String tipo = paramTablaUsuarios.getValueAt(fila, 2).toString();
                seleccionarRolEnCombo(paramTipoDeUsuario, tipo);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.getMessage());
        }
    }

    public void ModificarUsuarios(JTextField paramCodigo, JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setUsuario(paramUsuarios.getText().trim());
        setContrasena(paramContrasena.getText());
        setTipoDeUsuario(extraerCodigoRol(paramTipoDeUsuario.getSelectedItem()));

        if (getUsuario().isBlank()) {
            JOptionPane.showMessageDialog(null, "El usuario es obligatorio");
            return;
        }

        if (getTipoDeUsuario().isBlank()) {
            JOptionPane.showMessageDialog(null, "Seleccione un tipo de usuario válido");
            return;
        }

        boolean actualizarContrasena = getContrasena() != null && !getContrasena().isBlank();
        String sqlConPassword = "UPDATE usuarios SET ingresoUsuario = ?, ingresoContrasenia = ?, tipo_de_usuario = ? WHERE id = ?";
        String sqlSinPassword = "UPDATE usuarios SET ingresoUsuario = ?, tipo_de_usuario = ? WHERE id = ?";

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            if (actualizarContrasena) {
                try (PreparedStatement ps = conn.prepareStatement(sqlConPassword)) {
                    ps.setString(1, getUsuario());
                    ps.setString(2, BCrypt.hashpw(getContrasena(), BCrypt.gensalt()));
                    ps.setString(3, getTipoDeUsuario());
                    ps.setInt(4, getCodigo());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(sqlSinPassword)) {
                    ps.setString(1, getUsuario());
                    ps.setString(2, getTipoDeUsuario());
                    ps.setInt(3, getCodigo());
                    ps.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.getMessage());
        }
    }

    public void EliminarUsuarios(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));

        String consulta = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = Cconexion.estableceConexion()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            try (PreparedStatement ps = conn.prepareStatement(consulta)) {
                ps.setInt(1, getCodigo());
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.getMessage());
        }
    }

    private void establecerRolesPredeterminados(DefaultComboBoxModel<String> modelo) {
        if (modelo == null) {
            return;
        }
        if (modelo.getSize() == 0) {
            modelo.addElement("ADMIN - Administrador");
            modelo.addElement("DOCENTE - Docente");
            modelo.addElement("ESTUDIANTE - Estudiante");
        }
    }

    private String extraerCodigoRol(Object seleccionado) {
        if (seleccionado == null) {
            return "";
        }
        String valor = seleccionado.toString();
        int indice = valor.indexOf('-');
        if (indice == -1) {
            return valor.trim();
        }
        return valor.substring(0, indice).trim();
    }

    private void seleccionarRolEnCombo(JComboBox<String> combo, String codigo) {
        if (combo == null || codigo == null || codigo.isBlank()) {
            return;
        }
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && extraerCodigoRol(item).equalsIgnoreCase(codigo.trim())) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}
