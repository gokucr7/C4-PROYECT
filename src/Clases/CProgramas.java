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

public class CProgramas {

    public void cargarTablaProgramas(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        tabla.setModel(modelo);

        String sql = "SELECT id, codigo, nombre FROM programa ORDER BY nombre";
        try (Connection conn = Cconexion.estableceConexion();
             Statement st = conn != null ? conn.createStatement() : null;
             ResultSet rs = st != null ? st.executeQuery(sql) : null) {
            if (rs == null) {
                return;
            }
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getLong("id");
                fila[1] = rs.getString("codigo");
                fila[2] = rs.getString("nombre");
                modelo.addRow(fila);
            }
            tabla.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar programas: " + ex.getMessage());
        }
    }

    public void insertarPrograma(JTextField codigo, JTextField nombre) {
        String sql = "INSERT INTO programa(codigo, nombre) VALUES (?, ?)";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión");
                return;
            }
            ps.setString(1, codigo.getText().trim());
            ps.setString(2, nombre.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Programa registrado correctamente");
            CatalogoRefresher.notificarCambios();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar programa: " + ex.getMessage());
        }
    }

    public void actualizarPrograma(JTextField id, JTextField codigo, JTextField nombre) {
        String sql = "UPDATE programa SET codigo = ?, nombre = ? WHERE id = ?";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión");
                return;
            }
            ps.setString(1, codigo.getText().trim());
            ps.setString(2, nombre.getText().trim());
            ps.setLong(3, Long.parseLong(id.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Programa actualizado");
            CatalogoRefresher.notificarCambios();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar programa: " + ex.getMessage());
        }
    }

    public void eliminarPrograma(JTextField id) {
        String sql = "DELETE FROM programa WHERE id = ?";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión");
                return;
            }
            ps.setLong(1, Long.parseLong(id.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Programa eliminado");
            CatalogoRefresher.notificarCambios();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar programa: " + ex.getMessage());
        }
    }

    public void seleccionarPrograma(JTable tabla, JTextField id, JTextField codigo, JTextField nombre) {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            id.setText(tabla.getValueAt(fila, 0).toString());
            codigo.setText(tabla.getValueAt(fila, 1).toString());
            nombre.setText(tabla.getValueAt(fila, 2).toString());
        }
    }

    public void llenarComboProgramas(JComboBox<String> combo, boolean incluirTodos) {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        if (incluirTodos) {
            modelo.addElement("Todos");
        }
        String sql = "SELECT codigo, nombre FROM programa ORDER BY nombre";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null;
             ResultSet rs = ps != null ? ps.executeQuery() : null) {
            if (rs != null) {
                while (rs.next()) {
                    modelo.addElement(rs.getString("codigo") + " - " + rs.getString("nombre"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar programas: " + ex.getMessage());
        }
        combo.setModel(modelo);
        if (modelo.getSize() > 0) {
            combo.setSelectedIndex(0);
        }
    }
}
