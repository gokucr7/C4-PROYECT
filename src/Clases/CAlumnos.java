
package Clases;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


public class CAlumnos {
    int codigo;
    String nombreALumnos;
    String apellidosAlumnos;
    double promedio;
    String carrera;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreALumnos() {
        return nombreALumnos;
    }

    public void setNombreALumnos(String nombreALumnos) {
        this.nombreALumnos = nombreALumnos;
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
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        String sql = "SELECT codigo, nombre FROM programa ORDER BY nombre";
        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null;
             ResultSet rs = ps != null ? ps.executeQuery() : null) {
            if (rs != null) {
                while (rs.next()) {
                    String codigo = rs.getString("codigo");
                    String nombre = rs.getString("nombre");
                    modelo.addElement(codigo + " - " + nombre);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar programas: " + e.getMessage());
        }
        if (modelo.getSize() == 0) {
            modelo.addElement("SIS - Ingeniería de Sistemas");
        }
        combo.setModel(modelo);
        if (modelo.getSize() > 0) {
            combo.setSelectedIndex(0);
        }
    }

    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos, JSpinner paramPromedio, JComboBox<String> paramCarrera) {
        setNombreALumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        setPromedio(((Number) paramPromedio.getValue()).doubleValue());
        setCarrera(extraerCodigoPrograma(paramCarrera.getSelectedItem()));

        String consulta = "INSERT INTO alumnos (nombres, apellidos, promedio, carrera) VALUES (?, ?, ?, ?)";

        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }
            cs.setString(1, getNombreALumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setDouble(3, getPromedio());
            cs.setString(4, getCarrera());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error: " + e.getMessage());
        }
    }


    public void MostrarAlumnos (JTable paramTablaTotalAlumnos) {
    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);

    paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);

    // Defino las columnas de la tabla
    modelo.addColumn("Id");
    modelo.addColumn("Nombres");
    modelo.addColumn("Apellidos");
    modelo.addColumn("Promedio");
    modelo.addColumn("Carrera");

    paramTablaTotalAlumnos.setModel(modelo);

    String sql = "SELECT a.id, a.nombres, a.apellidos, a.promedio, CONCAT(a.carrera, ' - ', COALESCE(p.nombre, '')) AS programa FROM alumnos a LEFT JOIN programa p ON a.carrera = p.codigo";
    String[] datos = new String[5];

    Statement st;
    try {
        st = Cconexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);

            modelo.addRow(datos);
        }

        paramTablaTotalAlumnos.setModel(modelo);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
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
        DefaultTableModel modelo = crearModeloTablaAlumnos();
        paramTabla.setModel(modelo);

        String sql = "SELECT a.id, a.nombres, a.apellidos, a.promedio, CONCAT(a.carrera, ' - ', COALESCE(p.nombre, '')) AS programa FROM alumnos a LEFT JOIN programa p ON a.carrera = p.codigo WHERE a.carrera = ?";

        try (Connection conn = Cconexion.estableceConexion();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {
            if (ps == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            ps.setString(1, codigoCarrera);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getString(1);
                    fila[1] = rs.getString(2);
                    fila[2] = rs.getString(3);
                    fila[3] = rs.getString(4);
                    fila[4] = rs.getString(5);
                    modelo.addRow(fila);
                }
            }

            paramTabla.setModel(modelo);
            TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
            paramTabla.setRowSorter(ordenarTabla);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los registros de la carrera seleccionada: " + e.getMessage());
        }
    }

    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JSpinner paramPromedio, JComboBox<String> paramCarrera) {
    try {
        int fila = paramTablaAlumnos.getSelectedRow();
        if (fila >= 0) {
            paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
            paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
            paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());


            String promedioStr = paramTablaAlumnos.getValueAt(fila, 3).toString();
            double promedio = Double.parseDouble(promedioStr);
            paramPromedio.setValue(promedio);

            // Para establecer el valor seleccionado en el JComboBox
            String carrera = paramTablaAlumnos.getValueAt(fila, 4).toString();
            seleccionarProgramaEnCombo(paramCarrera, extraerCodigoPrograma(carrera));
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (HeadlessException | NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
    }
}


    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos, JSpinner paramPromedio, JComboBox<String> paramCarrera) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreALumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        setPromedio(((Number) paramPromedio.getValue()).doubleValue());
        setCarrera(extraerCodigoPrograma(paramCarrera.getSelectedItem()));

        String consulta = "UPDATE alumnos SET nombres = ?, apellidos = ?, promedio = ?, carrera = ? WHERE id = ?";

        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            cs.setString(1, getNombreALumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setDouble(3, getPromedio());
            cs.setString(4, getCarrera());
            cs.setInt(5, getCodigo());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.getMessage());
        }
    }


    public void EliminarAlumnos(JTextField paramCodigo) {
        setCodigo(Integer.parseInt(paramCodigo.getText()));

        String consulta = "DELETE FROM alumnos WHERE id = ?";
        try (Connection conn = Cconexion.estableceConexion();
             CallableStatement cs = conn != null ? conn.prepareCall(consulta) : null) {
            if (cs == null) {
                JOptionPane.showMessageDialog(null, "No se pudo establecer conexión con la base de datos");
                return;
            }

            cs.setInt(1, getCodigo());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el alumno");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el alumno, error: " + e.getMessage());
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
        if (codigo == null || codigo.isEmpty()) {
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
        Connection conexion = null;

        try {
            conexion = Cconexion.estableceConexion(); // Establece la conexión usando la clase Cconexion.

            String consulta = "SELECT promedio FROM alumnos WHERE carrera = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, codigoCarrera);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        double promedio = resultSet.getDouble("promedio");
                        promedios.add(promedio);
                    }
                }
            }
        } catch (SQLException e) {
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                }
            }
        }

        return promedios;
    }


    public static double calcularMedia(List<Double> datos) {
        return datos.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public static double calcularModa(List<Double> datos) {
    if (datos.isEmpty()) {
        return 0.0; // Si no hay datos, la moda es 0.
    }

    // Crear una lista para almacenar los valores modales.
    List<Double> modas = new ArrayList<>();

    // Crear un mapa para contar la frecuencia de cada valor.
    Map<Double, Integer> frecuencias = new HashMap<>();

    // Encontrar la frecuencia máxima.
    int frecuenciaMaxima = 1;
    for (double dato : datos) {
        int frecuencia = frecuencias.getOrDefault(dato, 0) + 1;
        frecuencias.put(dato, frecuencia);
        frecuenciaMaxima = Math.max(frecuenciaMaxima, frecuencia);
    }

    // Encontrar los valores con frecuencia máxima.
    for (Map.Entry<Double, Integer> entry : frecuencias.entrySet()) {
        if (entry.getValue() == frecuenciaMaxima) {
            modas.add(entry.getKey());
        }
    }

    if (modas.size() == 1) {
        return modas.get(0); // Si solo hay una moda, retornarla.
    } else {
        return 0.0; // Si hay múltiples modas, retornar 0 o manejarlo según tus necesidades.
    }
}


    public static double calcularMediana(List<Double> datos) {
    if (datos == null || datos.isEmpty()) throw new IllegalArgumentException("Lista vacía");
    List<Double> copia = new ArrayList<>(datos);
    Collections.sort(copia);
    int n = copia.size();
    if (n % 2 == 0) {
        return (copia.get(n/2 - 1) + copia.get(n/2)) / 2.0;
    } else {
        return copia.get(n/2);
    }
}


    public static double calcularCuartil1(List<Double> datos) {
    if (datos == null || datos.isEmpty()) {
        throw new IllegalArgumentException("Lista vacía");
    }
    List<Double> copia = new ArrayList<>(datos);
    Collections.sort(copia);
    int n = copia.size();


    double p = 0.25;
    double pos = p * (n - 1);
    int lower = (int) Math.floor(pos);
    int upper = (int) Math.ceil(pos);
    double frac = pos - lower;

    if (lower == upper) return copia.get(lower);
    return copia.get(lower) + (copia.get(upper) - copia.get(lower)) * frac;
}

    public static double calcularCuartil3(List<Double> datos) {
    if (datos == null || datos.isEmpty()) {
        throw new IllegalArgumentException("Lista vacía");
    }
    List<Double> copia = new ArrayList<>(datos);
    Collections.sort(copia);
    int n = copia.size();


    double p = 0.75;
    double pos = p * (n - 1);
    int lower = (int) Math.floor(pos);
    int upper = (int) Math.ceil(pos);
    double frac = pos - lower;

    if (lower == upper) return copia.get(lower);
    return copia.get(lower) + (copia.get(upper) - copia.get(lower)) * frac;
}

    public static double calcularDesviacionMedia(List<Double> datos) {
        double media = calcularMedia(datos);
        double sum = 0.0;
        for (double dato : datos) {
            sum += Math.abs(dato - media);
        }
        return sum / datos.size();
    }

    public static double calcularVarianza(List<Double> datos) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double dato : datos) {
            stats.addValue(dato);
        }
        return stats.getVariance();
    }

    public static double calcularDesviacionTipica(List<Double> datos) {
        return Math.sqrt(calcularVarianza(datos));
    }

    public static double calcularCoeficienteVariacion(List<Double> datos) {
        double media = calcularMedia(datos);
        double desviacionTipica = calcularDesviacionTipica(datos);
        return (desviacionTipica / media) * 100.0;
    }

    public static double calcularAsimetria(List<Double> datos) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double dato : datos) {
            stats.addValue(dato);
        }
        return stats.getSkewness();
    }

    public static double calcularCurtosis(List<Double> datos) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double dato : datos) {
            stats.addValue(dato);
        }
        return stats.getKurtosis();
    }



}
