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

    private static final String SELECT_PROGRAMAS =
            "SELECT id, codigo, nombre FROM programa ORDER BY nombre";

    public void cargarTablaProgramas(JTable tabla) {
        DefaultTableModel modelo = crearModeloTabla();
        try (Connection conn = Cconexion.estableceConexion();
             Statement st = conn != null ? conn.createStatement() : null;
             ResultSet rs = st != null ? st.executeQuery(SELECT_PROGRAMAS) : null) {
            if (rs == null) {
                return;
            }
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getLong("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre")
                });
            }
            tabla.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar programas: " + ex.getMessage());
        }
    }

    public void insertarPrograma(JTextField codigo, JTextField nombre) {
        ejecutarActualizacion(
                "INSERT INTO programa(codigo, nombre) VALUES (?, ?)",
                ps -> {
                    ps.setString(1, codigo.getText().trim());
                    ps.setString(2, nombre.getText().trim());
                },
                "Programa registrado correctamente",
                "Error al registrar programa"
        );
    }

    public void actualizarPrograma(JTextField id, JTextField codigo, JTextField nombre) {
        ejecutarActualizacion(
                "UPDATE programa SET codigo = ?, nombre = ? WHERE id = ?",
                ps -> {
                    ps.setString(1, codigo.getText().trim());
                    ps.setString(2, nombre.getText().trim());
                    ps.setLong(3, Long.parseLong(id.getText()));
                },
                "Programa actualizado",
                "Error al actualizar programa"
        );
    }

    public void eliminarPrograma(JTextField id) {
        ejecutarActualizacion(
                "DELETE FROM programa WHERE id = ?",
                ps -> ps.setLong(1, Long.parseLong(id.getText())),
                "Programa eliminado",
                "Error al eliminar programa"
        );
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
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(SELECT_PROGRAMAS) : null;
             ResultSet rs = ps != null ? ps.executeQuery() : null) {
            if (rs != null) {
                while (rs.next()) {
                    modelo.addElement(formatearPrograma(rs.getString("codigo"), rs.getString("nombre")));
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

    private void ejecutarActualizacion(String sql, StatementConfigurer configurer,
            String mensajeExito, String mensajeError) {
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión");
                return;
            }
            configurer.accept(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, mensajeExito);
            CatalogoRefresher.notificarCambios();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, mensajeError + ": " + ex.getMessage());
        }
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        return modelo;
    }

    private String formatearPrograma(String codigo, String nombre) {
        return codigo + " - " + nombre;
    }

    @FunctionalInterface
    private interface StatementConfigurer {
        void accept(PreparedStatement ps) throws SQLException;
    }
}
