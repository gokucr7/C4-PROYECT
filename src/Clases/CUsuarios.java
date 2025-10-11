
package Clases;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CUsuarios {
    int codigo;
    String usuario;
    String contrasena;
    String tipoDeUsuario;

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
    
    public void InsertarUsuarios (JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        
    setUsuario(paramUsuarios.getText());
    setContrasena(paramContrasena.getText());
    setTipoDeUsuario(paramTipoDeUsuario.getSelectedItem().toString());

    String consulta = "INSERT INTO Usuarios (ingresoUsuario, ingresoContrasenia, tipo_de_usuario) VALUES (?, ?, ?);";

    try {
        CallableStatement cs = Cconexion.estableceConexion().prepareCall(consulta);
        cs.setString(1, getUsuario());
        cs.setString(2, getContrasena());
        cs.setString(3, getTipoDeUsuario());

        cs.execute();

        JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
    }
        
    }
    
    
    public void MostrarUsuarios (JTable paramTablaTotalUsuarios) {
    DefaultTableModel modelo = new DefaultTableModel();
    TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
    paramTablaTotalUsuarios.setRowSorter(OrdenarTabla);

    // Define las columnas de la tabla
    modelo.addColumn("Id");
    modelo.addColumn("Usuario");
    modelo.addColumn("Contraseña");
    modelo.addColumn("Tipo de usuario"); 

    paramTablaTotalUsuarios.setModel(modelo);

    String sql = "select * from Usuarios;";
    String[] datos = new String[4]; //

    Statement st;
    try {
        st = Cconexion.estableceConexion().createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4); // tipo

            modelo.addRow(datos);
        }

        paramTablaTotalUsuarios.setModel(modelo);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: " + e.toString());
    }
    
    


}

    public void SeleccionarUsuario(JTable paramTablaUsuarios, JTextField paramId, JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
        try {
            int fila = paramTablaUsuarios.getSelectedRow();
            if (fila >= 0) {
                paramId.setText(paramTablaUsuarios.getValueAt(fila, 0).toString());
                paramUsuarios.setText(paramTablaUsuarios.getValueAt(fila, 1).toString());
                paramContrasena.setText(paramTablaUsuarios.getValueAt(fila, 2).toString());

                // Para establecer el valor seleccionado en el JComboBox
                String tipo = paramTablaUsuarios.getValueAt(fila, 3).toString();
                paramTipoDeUsuario.setSelectedItem(tipo);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
}

        
    public void ModificarUsuarios(JTextField paramCodigo, JTextField paramUsuarios, JTextField paramContrasena, JComboBox<String> paramTipoDeUsuario) {
    
    setCodigo(Integer.parseInt(paramCodigo.getText()));
    setUsuario(paramUsuarios.getText());
    setContrasena(paramContrasena.getText());
    setTipoDeUsuario(paramTipoDeUsuario.getSelectedItem().toString());

    String consulta = "UPDATE Usuarios SET ingresoUsuario = ?, ingresoContrasenia = ?, tipo_de_usuario = ? WHERE id = ?;";
    
    try {
        CallableStatement cs = Cconexion.estableceConexion().prepareCall(consulta);
        
        cs.setString(1, getUsuario());
        cs.setString(2, getContrasena());
        cs.setString(3, getTipoDeUsuario());
        cs.setInt(4, getCodigo());

        cs.execute();

        JOptionPane.showMessageDialog(null, "Modificación exitosa");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se pudo modificar, error: " + e.toString());
    }
}
        
        
        public void EliminarUsuarios(JTextField paramCodigo) {
            setCodigo(Integer.parseInt(paramCodigo.getText()));
            
            
            String consulta = "DELETE from Usuarios WHERE id = ?;";
            try {
                CallableStatement cs  = Cconexion.estableceConexion().prepareCall(consulta);
                
                cs.setInt(1, getCodigo());
                cs.execute();
                
                JOptionPane.showMessageDialog(null, "Modificacion exitosa ");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "   No se modifico, error:  ");
            }
        }

  
    

 
  
  

    





    
        }
        
        
        