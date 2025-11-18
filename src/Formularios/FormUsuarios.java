
package Formularios;

import Clases.CLogin;
import Clases.CUsuarios;
import Clases.CatalogoRefresher;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class FormUsuarios extends javax.swing.JFrame {

    private final Runnable catalogoListener = this::recargarRoles;

 
    public FormUsuarios() {
        initComponents();
        txtId.setEnabled(false);
        this.setLocationRelativeTo(null);
        CUsuarios objetoUsuarios = new CUsuarios();
        objetoUsuarios.cargarRoles(cboTipoDeUsuario);
        objetoUsuarios.MostrarUsuarios(tbTotalUsuarios);
        lblUltimoAccesoValor.setText("-");

        CatalogoRefresher.registrar(catalogoListener);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                CatalogoRefresher.remover(catalogoListener);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                CatalogoRefresher.remover(catalogoListener);
            }
        });
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
        txtUsuarios = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtContrasena = new javax.swing.JTextField();
        cboTipoDeUsuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButtonInicio = new javax.swing.JButton();
        chkActivo = new javax.swing.JCheckBox();
        btnToggleActivo = new javax.swing.JButton();
        lblUltimoAcceso = new javax.swing.JLabel();
        lblUltimoAccesoValor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTotalUsuarios = new javax.swing.JTable();

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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Usuarios "));

        paramid.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramid.setForeground(new java.awt.Color(255, 255, 255));
        paramid.setText("ID:");

        paramNombres.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramNombres.setForeground(new java.awt.Color(255, 255, 255));
        paramNombres.setText("Usuario:");

        paramApellidos.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        paramApellidos.setForeground(new java.awt.Color(255, 255, 255));
        paramApellidos.setText("Contraseña:");

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

        cboTipoDeUsuario.setModel(new javax.swing.DefaultComboBoxModel<>());
        cboTipoDeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoDeUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo:");

        jButtonInicio.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonInicio.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicio.setText("INICIO");
        jButtonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioActionPerformed(evt);
            }
        });

        chkActivo.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        chkActivo.setForeground(new java.awt.Color(255, 255, 255));
        chkActivo.setText("Activo");
        chkActivo.setOpaque(false);
        chkActivo.setSelected(true);

        btnToggleActivo.setBackground(new java.awt.Color(81, 84, 255));
        btnToggleActivo.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        btnToggleActivo.setForeground(new java.awt.Color(255, 255, 255));
        btnToggleActivo.setText("Cambiar estado");
        btnToggleActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActivoActionPerformed(evt);
            }
        });

        lblUltimoAcceso.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        lblUltimoAcceso.setForeground(new java.awt.Color(255, 255, 255));
        lblUltimoAcceso.setText("Último acceso:");

        lblUltimoAccesoValor.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        lblUltimoAccesoValor.setForeground(new java.awt.Color(255, 255, 255));
        lblUltimoAccesoValor.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chkActivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnToggleActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paramid)
                            .addComponent(paramNombres)
                            .addComponent(paramApellidos)
                            .addComponent(jLabel2)
                            .addComponent(lblUltimoAcceso)
                            .addComponent(jButtonInicio))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtId)
                            .addComponent(txtUsuarios)
                            .addComponent(txtContrasena)
                            .addComponent(cboTipoDeUsuario, 0, 154, Short.MAX_VALUE)
                            .addComponent(lblUltimoAccesoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
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
                    .addComponent(txtUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paramNombres))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramApellidos)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTipoDeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkActivo)
                    .addComponent(btnToggleActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUltimoAcceso)
                    .addComponent(lblUltimoAccesoValor))
                .addGap(30, 30, 30)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jButtonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(112, 145, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Usuarios"));

        tbTotalUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTotalUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTotalUsuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTotalUsuarios);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
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

        jPanel1.getAccessibleContext().setAccessibleName("Datos Usuarios");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       CUsuarios objetoUsuario = new CUsuarios();
       objetoUsuario.InsertarUsuarios(txtUsuarios, txtContrasena, cboTipoDeUsuario);
       objetoUsuario.MostrarUsuarios(tbTotalUsuarios);
       txtContrasena.setText("");
       chkActivo.setSelected(true);
       lblUltimoAccesoValor.setText("-");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       CUsuarios objetoUsuario = new CUsuarios ();
       objetoUsuario.ModificarUsuarios(txtId, txtUsuarios, txtContrasena, cboTipoDeUsuario, chkActivo);
       objetoUsuario.MostrarUsuarios(tbTotalUsuarios);
       txtContrasena.setText("");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tbTotalUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTotalUsuariosMouseClicked
        
       CUsuarios objetoUsuario = new CUsuarios ();
       objetoUsuario.SeleccionarUsuario(tbTotalUsuarios, txtId, txtUsuarios, txtContrasena, cboTipoDeUsuario, chkActivo, lblUltimoAccesoValor);
    }//GEN-LAST:event_tbTotalUsuariosMouseClicked

    private void cboTipoDeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoDeUsuarioActionPerformed

    }//GEN-LAST:event_cboTipoDeUsuarioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Obtén el ID del usuario a eliminar desde el campo de texto txtId
        String idUsuarioAEliminar = txtId.getText();

        // Verifica si se ingresó un ID válido
        if (idUsuarioAEliminar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona un usuario de la tabla para eliminar.");
        } else {
        Object[] opciones = {"Sí", "No"};
        int confirmacion = JOptionPane.showOptionDialog(null, "¿Estás seguro de que deseas eliminar a este usuario?", "Confirmación de Eliminación",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (confirmacion == JOptionPane.YES_OPTION) {

        // Llama al método para eliminar el usuario en la clase CUsuarios
        CUsuarios objetoUsuario = new CUsuarios();
        objetoUsuario.EliminarUsuarios(txtId);

        // Limpia los campos después de eliminar
        txtId.setText("");
        txtUsuarios.setText("");
        txtContrasena.setText("");
        cboTipoDeUsuario.setSelectedIndex(0);
        chkActivo.setSelected(true);
        lblUltimoAccesoValor.setText("-");

        // Actualiza la tabla de usuarios
        objetoUsuario.MostrarUsuarios(tbTotalUsuarios);
        }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnToggleActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleActivoActionPerformed
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario para cambiar su estado");
            return;
        }
        boolean nuevoEstado = !chkActivo.isSelected();
        CUsuarios dao = new CUsuarios();
        dao.actualizarEstado(txtId, nuevoEstado);
        chkActivo.setSelected(nuevoEstado);
        dao.MostrarUsuarios(tbTotalUsuarios);
        for (int i = 0; i < tbTotalUsuarios.getRowCount(); i++) {
            if (tbTotalUsuarios.getValueAt(i, 0).toString().equals(txtId.getText())) {
                tbTotalUsuarios.setRowSelectionInterval(i, i);
                dao.SeleccionarUsuario(tbTotalUsuarios, txtId, txtUsuarios, txtContrasena, cboTipoDeUsuario, chkActivo, lblUltimoAccesoValor);
                break;
            }
        }
    }//GEN-LAST:event_btnToggleActivoActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        
    }//GEN-LAST:event_txtIdActionPerformed

    private void jButtonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioActionPerformed
    // Determine el tipo de usuario accediendo al usuario que ha iniciado sesión en CLogin
    String TipoDeUsuario = CLogin.getTipoUsuarioAdentro(); // Usa metodo

    // Crear el formulario de menú adecuado en función del tipo de usuario
    if ("ADMIN".equals(TipoDeUsuario)) {
        FormMenuPrincipal menuPrincipal = new FormMenuPrincipal();
        menuPrincipal.setVisible(true);
    } else if ("DOCENTE".equals(TipoDeUsuario)) {
        FormMenuPrincipal2 menuPrincipal2 = new FormMenuPrincipal2();
        menuPrincipal2.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(null, "Este usuario no tiene acceso al menú de administración");
        return;
    }

    // Cierra el formulario actual
    this.dispose();
    }//GEN-LAST:event_jButtonInicioActionPerformed

    private void recargarRoles() {
        String seleccionado = (String) cboTipoDeUsuario.getSelectedItem();
        String codigoSeleccionado = null;
        if (seleccionado != null && !seleccionado.isBlank()) {
            int idx = seleccionado.indexOf('-');
            codigoSeleccionado = idx >= 0 ? seleccionado.substring(0, idx).trim() : seleccionado.trim();
        }

        CUsuarios dao = new CUsuarios();
        dao.cargarRoles(cboTipoDeUsuario);
        if (codigoSeleccionado != null) {
            for (int i = 0; i < cboTipoDeUsuario.getItemCount(); i++) {
                String item = cboTipoDeUsuario.getItemAt(i);
                if (item != null && item.startsWith(codigoSeleccionado)) {
                    cboTipoDeUsuario.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnToggleActivo;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JComboBox<String> cboTipoDeUsuario;
    private javax.swing.JButton jButtonInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblUltimoAcceso;
    private javax.swing.JLabel lblUltimoAccesoValor;
    private javax.swing.JLabel paramApellidos;
    private javax.swing.JLabel paramNombres;
    private javax.swing.JLabel paramid;
    private javax.swing.JTable tbTotalUsuarios;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtUsuarios;
    // End of variables declaration//GEN-END:variables
}
