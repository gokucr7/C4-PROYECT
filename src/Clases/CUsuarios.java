package Clases;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
    private boolean activo;
    private String ultimoAcceso;

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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(String ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public void cargarRoles(JComboBox<String> combo) {
        new CRoles().llenarComboRoles(combo);
    }

    public void InsertarUsuarios(JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        setUsuario(paramUsuarios.getText());
        setContrasena(paramContrasena.getText());
        setTipoDeUsuario(obtenerCodigoRol(paramTipoDeUsuario.getSelectedItem()));

        if (getUsuario().isBlank() || getContrasena().isBlank()) {
            JOptionPane.showMessageDialog(null, "El usuario y la contraseña son obligatorios");
            return;
        }

        String consulta = "INSERT INTO usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario) VALUES (?, ?, ?);";

        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            cs.setString(1, getUsuario());
            cs.setString(2, BCrypt.hashpw(getContrasena(), BCrypt.gensalt()));
            cs.setString(3, getTipoDeUsuario());

            cs.execute();

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
        modelo.addColumn("Código rol");
        modelo.addColumn("Descripción");
        modelo.addColumn("Activo");
        modelo.addColumn("Último acceso");

        paramTablaTotalUsuarios.setModel(modelo);

        String sql = "SELECT u.id, u.ingresoUsuario, u.tipo_de_usuario, r.nombre, u.activo, u.ultimo_acceso "
                   + "FROM usuarios u LEFT JOIN rol r ON u.tipo_de_usuario = r.codigo";

        try (Connection conn = Cconexion.estableceConexion();
             Statement st = conn != null ? conn.createStatement() : null;
             ResultSet rs = st != null ? st.executeQuery(sql) : null) {
            if (rs == null) {
                return;
            }
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getInt(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getInt(5) == 1 ? "Sí" : "No";
                fila[5] = rs.getString(6) != null ? rs.getString(6) : "-";
                modelo.addRow(fila);
            }
            paramTablaTotalUsuarios.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.getMessage());
        }
    }

    public void SeleccionarUsuario(JTable paramTablaUsuarios, JTextField paramId, JTextField paramUsuarios,
            JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario, JCheckBox chkActivo, JLabel lblUltimoAcceso) {
        try {
            int fila = paramTablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                paramId.setText(paramTablaUsuarios.getValueAt(fila, 0).toString());
                paramUsuarios.setText(paramTablaUsuarios.getValueAt(fila, 1).toString());
                paramContrasena.setText("");

                String tipo = paramTablaUsuarios.getValueAt(fila, 2).toString();
                seleccionarRolEnCombo(paramTipoDeUsuario, tipo);

                String activoTabla = paramTablaUsuarios.getValueAt(fila, 4).toString();
                chkActivo.setSelected("Sí".equalsIgnoreCase(activoTabla));

                String ultimo = paramTablaUsuarios.getValueAt(fila, 5).toString();
                lblUltimoAcceso.setText(ultimo);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.getMessage());
        }
    }

    public void ModificarUsuarios(JTextField paramCodigo, JTextField paramUsuarios, JTextField paramContrasena,
            JComboBox<String> paramTipoDeUsuario, JCheckBox chkActivo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setUsuario(paramUsuarios.getText());
        setContrasena(paramContrasena.getText());
        setTipoDeUsuario(obtenerCodigoRol(paramTipoDeUsuario.getSelectedItem()));
        setActivo(chkActivo.isSelected());

        boolean actualizarContrasena = getContrasena() != null && !getContrasena().isBlank();
        String consulta;
        if (actualizarContrasena) {
            consulta = "UPDATE usuarios SET ingresoUsuario = ?, ingresoContrasenia = ?, tipo_de_usuario = ?, activo = ? WHERE id = ?";
        } else {
            consulta = "UPDATE usuarios SET ingresoUsuario = ?, tipo_de_usuario = ?, activo = ? WHERE id = ?";
        }

        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            cs.setString(1, getUsuario());
            if (actualizarContrasena) {
                cs.setString(2, BCrypt.hashpw(getContrasena(), BCrypt.gensalt()));
                cs.setString(3, getTipoDeUsuario());
                cs.setBoolean(4, isActivo());
                cs.setInt(5, getCodigo());
            } else {
                cs.setString(2, getTipoDeUsuario());
                cs.setBoolean(3, isActivo());
                cs.setInt(4, getCodigo());
            }

            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.getMessage());
        }
    }

    public void EliminarUsuarios(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));

        String consulta = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            cs.setInt(1, getCodigo());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.getMessage());
        }
    }

    public void actualizarEstado(JTextField paramCodigo, boolean activo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        String consulta = "UPDATE usuarios SET activo = ? WHERE id = ?";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(consulta) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            ps.setBoolean(1, activo);
            ps.setInt(2, getCodigo());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, activo ? "Usuario activado" : "Usuario desactivado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado, error: " + e.getMessage());
        }
    }

    private String obtenerCodigoRol(Object seleccionado) {
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
        if (combo == null || codigo == null) {
            return;
        }
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && item.startsWith(codigo)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}
