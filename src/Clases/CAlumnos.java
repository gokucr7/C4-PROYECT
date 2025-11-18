package Clases;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CAlumnos {

    private static final String SELECT_BASE =
            "SELECT a.id, a.nombres, a.apellidos, a.promedio, "
            + "CONCAT(a.carrera, ' - ', COALESCE(p.nombre, '')) AS programa "
            + "FROM alumnos a LEFT JOIN programa p ON a.carrera = p.codigo";
    private static final String INSERT_ALUMNO =
            "INSERT INTO alumnos (nombres, apellidos, promedio, carrera) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ALUMNO =
            "UPDATE alumnos SET nombres = ?, apellidos = ?, promedio = ?, carrera = ? WHERE id = ?";
    private static final String DELETE_ALUMNO = "DELETE FROM alumnos WHERE id = ?";

    private int codigo;
    private String nombreAlumnos;
    private String apellidosAlumnos;
    private double promedio;
    private String carrera;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreALumnos() {
        return nombreAlumnos;
    }

    public void setNombreALumnos(String nombreALumnos) {
        this.nombreAlumnos = nombreALumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void cargarProgramas(JComboBox<String> combo) {
        cargarProgramas(combo, false);
    }

    public void cargarProgramas(JComboBox<String> combo, boolean incluirTodos) {
        new CProgramas().llenarComboProgramas(combo, incluirTodos);
    }

    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos,
            JSpinner paramPromedio, JComboBox<String> paramCarrera) {
        setNombreALumnos(paramNombres.getText().trim());
        setApellidosAlumnos(paramApellidos.getText().trim());
        setPromedio(((Number) paramPromedio.getValue()).doubleValue());
        setCarrera(extraerCodigoPrograma(paramCarrera.getSelectedItem()));

        if (getNombreALumnos().isEmpty() || getApellidosAlumnos().isEmpty() || getCarrera().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa todos los campos obligatorios");
            return;
        }

        if (getPromedio() < 0.0 || getPromedio() > 5.0) {
            JOptionPane.showMessageDialog(null, "El promedio debe estar entre 0.00 y 5.00");
            return;
        }

        ejecutarActualizacion(INSERT_ALUMNO,
                ps -> {
                    ps.setString(1, getNombreALumnos());
                    ps.setString(2, getApellidosAlumnos());
                    ps.setDouble(3, getPromedio());
                    ps.setString(4, getCarrera());
                },
                "Se insertó correctamente el alumno",
                "No se insertó correctamente el alumno");
    }

    public void MostrarAlumnos(JTable paramTablaTotalAlumnos) {
        filtrarAlumnos(paramTablaTotalAlumnos, null, null, null, null);
    }

    public void filtrarAlumnos(JTable tabla, String terminoBusqueda, String codigoCarrera,
            Double minimoPromedio, Double maximoPromedio) {
        DefaultTableModel modelo = crearModeloTablaAlumnos();
        tabla.setModel(modelo);
        tabla.setRowSorter(new TableRowSorter<>(modelo));

        StringBuilder sql = new StringBuilder(SELECT_BASE).append(" WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (terminoBusqueda != null && !terminoBusqueda.isBlank()) {
            sql.append(" AND (LOWER(a.nombres) LIKE ? OR LOWER(a.apellidos) LIKE ?)");
            String valor = "%" + terminoBusqueda.toLowerCase() + "%";
            parametros.add(valor);
            parametros.add(valor);
        }

        if (codigoCarrera != null && !codigoCarrera.isBlank() && !"Todos".equalsIgnoreCase(codigoCarrera)) {
            sql.append(" AND a.carrera = ?");
            parametros.add(codigoCarrera);
        }

        if (minimoPromedio != null) {
            sql.append(" AND a.promedio >= ?");
            parametros.add(minimoPromedio);
        }

        if (maximoPromedio != null) {
            sql.append(" AND a.promedio <= ?");
            parametros.add(maximoPromedio);
        }

        sql.append(" ORDER BY a.id DESC");

        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql.toString()) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5)
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al filtrar alumnos: " + ex.getMessage());
        }
    }

    private DefaultTableModel crearModeloTablaAlumnos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Promedio");
        modelo.addColumn("Carrera");
        return modelo;
    }

    public void MostrarAlumnosPorCarrera(JTable paramTabla, String codigoCarrera) {
        filtrarAlumnos(paramTabla, null, codigoCarrera, null, null);
    }

    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres,
            JTextField paramApellidos, JSpinner paramPromedio, JComboBox<String> paramCarrera) {
        try {
            int filaVista = paramTablaAlumnos.getSelectedRow();
            if (filaVista < 0) {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                return;
            }

            int filaModelo = paramTablaAlumnos.convertRowIndexToModel(filaVista);
            TableModel modelo = paramTablaAlumnos.getModel();

            paramId.setText(modelo.getValueAt(filaModelo, 0).toString());
            paramNombres.setText(modelo.getValueAt(filaModelo, 1).toString());
            paramApellidos.setText(modelo.getValueAt(filaModelo, 2).toString());

            double promedioSeleccionado = Double.parseDouble(modelo.getValueAt(filaModelo, 3).toString());
            paramPromedio.setValue(promedioSeleccionado);

            String carreraSeleccionada = modelo.getValueAt(filaModelo, 4).toString();
            seleccionarProgramaEnCombo(paramCarrera, extraerCodigoPrograma(carreraSeleccionada));
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.getMessage());
        }
    }

    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos,
            JSpinner paramPromedio, JComboBox<String> paramCarrera) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreALumnos(paramNombres.getText().trim());
        setApellidosAlumnos(paramApellidos.getText().trim());
        setPromedio(((Number) paramPromedio.getValue()).doubleValue());
        setCarrera(extraerCodigoPrograma(paramCarrera.getSelectedItem()));

        if (getNombreALumnos().isEmpty() || getApellidosAlumnos().isEmpty() || getCarrera().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Completa todos los campos obligatorios");
            return;
        }

        if (getPromedio() < 0.0 || getPromedio() > 5.0) {
            JOptionPane.showMessageDialog(null, "El promedio debe estar entre 0.00 y 5.00");
            return;
        }

        ejecutarActualizacion(UPDATE_ALUMNO,
                ps -> {
                    ps.setString(1, getNombreALumnos());
                    ps.setString(2, getApellidosAlumnos());
                    ps.setDouble(3, getPromedio());
                    ps.setString(4, getCarrera());
                    ps.setInt(5, getCodigo());
                },
                "Modificación exitosa",
                "No se pudo modificar");
    }

    public void EliminarAlumnos(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));

        ejecutarActualizacion(DELETE_ALUMNO,
                ps -> ps.setInt(1, getCodigo()),
                "Se eliminó correctamente el alumno",
                "No se pudo eliminar el alumno");
    }

    private void ejecutarActualizacion(String sql, StatementConfigurer configurer,
            String mensajeExito, String mensajeError) {
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            configurer.accept(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, mensajeExito);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, mensajeError + ": " + ex.getMessage());
        }
    }

    private String extraerCodigoPrograma(Object seleccionado) {
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

    private void seleccionarProgramaEnCombo(JComboBox<String> combo, String codigo) {
        if (combo == null || codigo == null || codigo.isEmpty()) {
            return;
        }
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && item.startsWith(codigo + " ")) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    public List<Double> getPromediosPorCarrera(String codigoCarrera) {
        List<Double> promedios = new ArrayList<>();
        String sql = "SELECT promedio FROM alumnos WHERE carrera = ?";

        try (Connection conexion = Cconexion.estableceConexion();
             PreparedStatement statement = conexion != null ? conexion.prepareStatement(sql) : null) {
            if (statement == null) {
                return promedios;
            }
            statement.setString(1, codigoCarrera);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    promedios.add(resultSet.getDouble("promedio"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener promedios por carrera: " + e.getMessage());
        }

        return promedios;
    }

    @FunctionalInterface
    private interface StatementConfigurer {
        void accept(PreparedStatement ps) throws SQLException;
    }
}
