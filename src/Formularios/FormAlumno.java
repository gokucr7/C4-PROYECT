
package Formularios;

import Clases.CAlumnos;
import Clases.CLogin;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;


public class FormAlumno extends javax.swing.JFrame {

    

 
    public FormAlumno() {
        initComponents();
        txtId.setEnabled(false);
        this.setLocationRelativeTo(null);
        CAlumnos objetoAlumnos = new CAlumnos();
        objetoAlumnos.MostrarAlumnos(tbTotalAlumnos);
        
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        paramid = new javax.swing.JLabel();
        paramNombres = new javax.swing.JLabel();
        paramApellidos = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtApellidos = new javax.swing.JTextField();
        spnPromedio = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        cboCarrera = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonInicio = new javax.swing.JButton();
        btnInforme = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTotalAlumnos = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(112, 145, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Alumnos "));

        paramid.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramid.setForeground(new java.awt.Color(255, 255, 255));
        paramid.setText("ID:");

        paramNombres.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramNombres.setForeground(new java.awt.Color(255, 255, 255));
        paramNombres.setText("Nombre:");

        paramApellidos.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramApellidos.setForeground(new java.awt.Color(255, 255, 255));
        paramApellidos.setText("Apellido: ");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(81, 84, 255));
        btnGuardar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(81, 84, 255));
        btnModificar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(81, 84, 255));
        btnEliminar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        spnPromedio.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 5.0d, 0.1d));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Promedio:");

        cboCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieria de sistemas", "Ingenieria Electronica", "Administracion de empresas" }));
        cboCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCarreraActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Carrera:");

        jButtonInicio.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonInicio.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicio.setText("INICIO");
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        btnInforme.setBackground(new java.awt.Color(81, 84, 255));
        btnInforme.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnInforme.setForeground(new java.awt.Color(255, 255, 255));
        btnInforme.setText("Informe");
        btnInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paramNombres)
                            .addComponent(paramApellidos)
                            .addComponent(jLabel1)
                            .addComponent(paramid)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(spnPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE))
                            .addComponent(txtId)
                            .addComponent(txtNombres)
                            .addComponent(txtApellidos)
                            .addComponent(cboCarrera, 0, 1, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInforme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonInicio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paramid))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paramNombres))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramApellidos)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnInforme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(112, 145, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Alumnos "));

        tbTotalAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTotalAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTotalAlumnosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTotalAlumnos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        CAlumnos objetoAlumno = new CAlumnos();
        objetoAlumno.InsertarAlumno(txtNombres, txtApellidos, spnPromedio, cboCarrera);
        objetoAlumno.MostrarAlumnos(tbTotalAlumnos);
        

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        CAlumnos objetoAlumno = new CAlumnos ();
        objetoAlumno.ModificarAlumnos(txtId, txtNombres, txtApellidos,spnPromedio,cboCarrera);
        objetoAlumno.MostrarAlumnos(tbTotalAlumnos);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cboCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCarreraActionPerformed

    }//GEN-LAST:event_cboCarreraActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Obtén el ID del alumno a eliminar desde el campo de texto txtId
        String idAlumnoAEliminar = txtId.getText();

        // Verifica si se ingresó un ID válido
        if (idAlumnoAEliminar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un alumno de la tabla para eliminar.");
        } else {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar a este alumno?", "Confirmación de Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {

        // Llama al método para eliminar el alumno en la clase CAlumnos
        CAlumnos objetoAlumno = new CAlumnos();
        objetoAlumno.EliminarAlumnos(txtId);

        // Limpia los campos después de eliminar
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        spnPromedio.setValue(0.0);
        cboCarrera.setSelectedIndex(0);

        // Actualiza la tabla de alumnos
        objetoAlumno.MostrarAlumnos(tbTotalAlumnos);
        }
        
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        
    }//GEN-LAST:event_txtIdActionPerformed

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
        // Determine el tipo de usuario accediendo al usuario que ha iniciado sesión en CLogin
        String TipoDeUsuario = CLogin.getTipoUsuarioAdentro(); // Usa metodo

        // Crear el formulario de menú adecuado en función del tipo de usuario
        if ("administrador".equals(TipoDeUsuario)) {
            FormMenuPrincipal menuPrincipal = new FormMenuPrincipal();
            menuPrincipal.setVisible(true);
        } else if ("maestro".equals(TipoDeUsuario)) {
            FormMenuPrincipal2 menuPrincipal2 = new FormMenuPrincipal2();
            menuPrincipal2.setVisible(true);
        }

        // Cierra el formulario actual
        this.dispose();
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void btnInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeActionPerformed
        generarInformeExcel();
    }//GEN-LAST:event_btnInformeActionPerformed

    private void tbTotalAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTotalAlumnosMouseClicked
        CAlumnos objetoAlumno = new CAlumnos ();
        objetoAlumno.SeleccionarAlumno(tbTotalAlumnos, txtId, txtNombres, txtApellidos, spnPromedio, cboCarrera);
    }//GEN-LAST:event_tbTotalAlumnosMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAlumno().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInforme;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboCarrera;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel paramApellidos;
    private javax.swing.JLabel paramNombres;
    private javax.swing.JLabel paramid;
    private javax.swing.JSpinner spnPromedio;
    private javax.swing.JTable tbTotalAlumnos;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
    
   private void generarInformeExcel() {
    try {
        // Crea un nuevo libro de Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // Crea una hoja en el libro
        XSSFSheet sheet = workbook.createSheet("Informe Alumnos");
        
        // Define las cabeceras de las columnas
        String[] headers = {"Id","Nombres", "Apellidos", "Promedio", "Carrera"};
        
        // Crea la primera fila con las cabeceras
        XSSFRow headerRow = sheet.createRow(0);
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        
        // Obtiene los datos de la tabla y los agrega al archivo de Excel
        int rowCount = 1; // Comenzamos desde la segunda fila
        
        for (int row = 0; row < tbTotalAlumnos.getRowCount(); row++) {
            XSSFRow excelRow = sheet.createRow(rowCount);
            
            for (int col = 0; col < tbTotalAlumnos.getColumnCount(); col++) {
                Object value = tbTotalAlumnos.getValueAt(row, col);
                Cell cell = excelRow.createCell(col);
                
                // Convierte el valor a String antes de agregarlo al archivo de Excel
                cell.setCellValue(value.toString());
            }
            
            rowCount++;
        }
        
        // Guarda el archivo en disco
        String informeFilePath = "InformeAlumnos.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(informeFilePath)) {
            workbook.write(fileOut);
        }
        
        // Abre el archivo con la aplicación predeterminada para archivos Excel
        File informeFile = new File(informeFilePath);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            Desktop.getDesktop().open(informeFile);
        } else {
            JOptionPane.showMessageDialog(null, "Informe generado con éxito, pero no se pudo abrir automáticamente.");
        }
        
        JOptionPane.showMessageDialog(null, "Informe generado y abierto con éxito.");
        
    } catch (HeadlessException | IOException e) {
        JOptionPane.showMessageDialog(null, "Error al generar el informe: " + e.getMessage());
    }
}
}