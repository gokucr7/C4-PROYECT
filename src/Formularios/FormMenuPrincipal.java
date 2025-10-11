
package Formularios;


public class FormMenuPrincipal extends javax.swing.JFrame {

   
    public FormMenuPrincipal() {
       
        initComponents();
        this.setLocationRelativeTo(null);


    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();
        jButtonIngresar = new javax.swing.JButton();
        jButtonMostrar = new javax.swing.JButton();
        jButtonEstadistica = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabelNombrep = new javax.swing.JLabel();
        jLabelIcono = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonCerrar.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCerrar.setText("CERRAR SESION");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 140, 40));

        jButtonIngresar.setBackground(new java.awt.Color(0, 0, 0));
        jButtonIngresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonIngresar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonIngresar.setText("ALUMNOS");
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 130, 30));

        jButtonMostrar.setBackground(new java.awt.Color(0, 0, 0));
        jButtonMostrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonMostrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMostrar.setText("USUARIOS");
        jButtonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 130, 30));

        jButtonEstadistica.setBackground(new java.awt.Color(0, 0, 0));
        jButtonEstadistica.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEstadistica.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEstadistica.setText("ESTADISTICA");
        jButtonEstadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEstadisticaActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 130, 30));

        jButtonSalir.setBackground(new java.awt.Color(0, 0, 0));
        jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalir.setText("SALIR");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, 40));

        jLabelNombrep.setFont(new java.awt.Font("Lucida Sans", 1, 48)); // NOI18N
        jLabelNombrep.setText("C4 PROJECT");
        getContentPane().add(jLabelNombrep, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        jLabelIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logo  (1) (1).png"))); // NOI18N
        getContentPane().add(jLabelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, -50, 230, 240));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Fondo 2.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 230, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonEstadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEstadisticaActionPerformed
        FormEstadistica a = new FormEstadistica();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonEstadisticaActionPerformed

    private void jButtonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarActionPerformed
        FormUsuarios a = new FormUsuarios();
        a.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButtonMostrarActionPerformed

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        FormAlumno a = new FormAlumno();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        FormLogin login = new FormLogin();
        login.setVisible(true);
        this.dispose(); // Cierra el formulario actual
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenuPrincipal().setVisible(true);
            }
        });
    }
    
                
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonEstadistica;
    private javax.swing.JButton jButtonIngresar;
    public static javax.swing.JButton jButtonMostrar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelIcono;
    private javax.swing.JLabel jLabelNombrep;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
 
}
