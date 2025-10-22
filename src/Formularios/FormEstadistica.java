
package Formularios;

import Clases.CAlumnos;
import Clases.CLogin;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;





public class FormEstadistica extends javax.swing.JFrame {
    private final CAlumnos alumnos = new CAlumnos();

    public FormEstadistica() {
        initComponents();

    }







    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jButtonInicioSistemas = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableSistemas = new javax.swing.JTable();
        jbtnCalcularSistemas = new javax.swing.JButton();
        jScrollPaneSistemas = new javax.swing.JScrollPane();
        jbtnFiltrarSistemas = new javax.swing.JButton();
        jbtnGraficaSistemas = new javax.swing.JButton();
        jbtnGraficaSistemas2 = new javax.swing.JButton();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jButtonInicioElectronica = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableElectronica = new javax.swing.JTable();
        jbtnCalcularElectronica = new javax.swing.JButton();
        jScrollPaneElectronica = new javax.swing.JScrollPane();
        jbtnFiltrarElectronica = new javax.swing.JButton();
        jbtnGraficaElectronica = new javax.swing.JButton();
        jbtnGraficaElectronica2 = new javax.swing.JButton();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        jButtonInicioAdmin = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableAdmin = new javax.swing.JTable();
        jbtnCalcularAdmin = new javax.swing.JButton();
        jScrollPaneAdmin = new javax.swing.JScrollPane();
        jbtnFiltrarAdmin = new javax.swing.JButton();
        jbtnGraficarAdmin = new javax.swing.JButton();
        jbtnGraficarAdmin1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setBackground(new java.awt.Color(112, 145, 255));
        jInternalFrame1.setVisible(true);

        jPanel5.setBackground(new java.awt.Color(112, 145, 255));

        jButtonInicioSistemas.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicioSistemas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonInicioSistemas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicioSistemas.setText("INICIO");
        jButtonInicioSistemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioSistemasActionPerformed(evt);
            }
        });

        jTableSistemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTableSistemas);

        jbtnCalcularSistemas.setBackground(new java.awt.Color(81, 84, 255));
        jbtnCalcularSistemas.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnCalcularSistemas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCalcularSistemas.setText("CALCULAR");
        jbtnCalcularSistemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcularSistemasActionPerformed(evt);
            }
        });

        jbtnFiltrarSistemas.setBackground(new java.awt.Color(81, 84, 255));
        jbtnFiltrarSistemas.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnFiltrarSistemas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnFiltrarSistemas.setText("FILTRAR");
        jbtnFiltrarSistemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFiltrarSistemasActionPerformed(evt);
            }
        });

        jbtnGraficaSistemas.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficaSistemas.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficaSistemas.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficaSistemas.setText("GRAFICO CIRCULAR");
        jbtnGraficaSistemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficaSistemasActionPerformed(evt);
            }
        });

        jbtnGraficaSistemas2.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficaSistemas2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficaSistemas2.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficaSistemas2.setText("GRAFICO HISTOGRAMA");
        jbtnGraficaSistemas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficaSistemas2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jbtnFiltrarSistemas))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jbtnCalcularSistemas)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnGraficaSistemas2)
                            .addComponent(jbtnGraficaSistemas))))
                .addGap(150, 150, 150))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButtonInicioSistemas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneSistemas)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCalcularSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFiltrarSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGraficaSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGraficaSistemas2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jButtonInicioSistemas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Ingenieria de sistemas", jInternalFrame1);

        jInternalFrame2.setBackground(new java.awt.Color(112, 145, 255));
        jInternalFrame2.setVisible(true);

        jPanel6.setBackground(new java.awt.Color(112, 145, 255));

        jButtonInicioElectronica.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicioElectronica.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonInicioElectronica.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicioElectronica.setText("INICIO");
        jButtonInicioElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioElectronicaActionPerformed(evt);
            }
        });

        jTableElectronica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombres", "Apellidos", "Promedio", "Carrera"
            }
        ));
        jScrollPane9.setViewportView(jTableElectronica);

        jbtnCalcularElectronica.setBackground(new java.awt.Color(81, 84, 255));
        jbtnCalcularElectronica.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnCalcularElectronica.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCalcularElectronica.setText("CALCULAR");
        jbtnCalcularElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcularElectronicaActionPerformed(evt);
            }
        });

        jbtnFiltrarElectronica.setBackground(new java.awt.Color(81, 84, 255));
        jbtnFiltrarElectronica.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnFiltrarElectronica.setForeground(new java.awt.Color(255, 255, 255));
        jbtnFiltrarElectronica.setText("FILTRAR");
        jbtnFiltrarElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFiltrarElectronicaActionPerformed(evt);
            }
        });

        jbtnGraficaElectronica.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficaElectronica.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficaElectronica.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficaElectronica.setText("GRAFICO CIRCULAR");
        jbtnGraficaElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficaElectronicaActionPerformed(evt);
            }
        });

        jbtnGraficaElectronica2.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficaElectronica2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficaElectronica2.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficaElectronica2.setText("GRAFICO HISTOGRAMA");
        jbtnGraficaElectronica2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficaElectronica2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jbtnFiltrarElectronica))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jbtnCalcularElectronica)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnGraficaElectronica))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jbtnGraficaElectronica2)))
                .addGap(141, 141, 141))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButtonInicioElectronica)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneElectronica)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCalcularElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFiltrarElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGraficaElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGraficaElectronica2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jButtonInicioElectronica, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Ingenieria electronica", jInternalFrame2);

        jInternalFrame3.setBackground(new java.awt.Color(112, 145, 255));
        jInternalFrame3.setVisible(true);

        jPanel4.setBackground(new java.awt.Color(112, 145, 255));

        jButtonInicioAdmin.setBackground(new java.awt.Color(0, 0, 0));
        jButtonInicioAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonInicioAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicioAdmin.setText("INICIO");
        jButtonInicioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioAdminActionPerformed(evt);
            }
        });

        jTableAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombres", "Apellidos", "Promedio", "Carrera"
            }
        ));
        jScrollPane5.setViewportView(jTableAdmin);

        jbtnCalcularAdmin.setBackground(new java.awt.Color(81, 84, 255));
        jbtnCalcularAdmin.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnCalcularAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCalcularAdmin.setText("CALCULAR");
        jbtnCalcularAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcularAdminActionPerformed(evt);
            }
        });

        jbtnFiltrarAdmin.setBackground(new java.awt.Color(81, 84, 255));
        jbtnFiltrarAdmin.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnFiltrarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnFiltrarAdmin.setText("FILTRAR");
        jbtnFiltrarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFiltrarAdminActionPerformed(evt);
            }
        });

        jbtnGraficarAdmin.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficarAdmin.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficarAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficarAdmin.setText("GRAFICO CIRCULAR");
        jbtnGraficarAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficarAdminActionPerformed(evt);
            }
        });

        jbtnGraficarAdmin1.setBackground(new java.awt.Color(81, 84, 255));
        jbtnGraficarAdmin1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jbtnGraficarAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        jbtnGraficarAdmin1.setText("GRAFICO HISTOGRAMA");
        jbtnGraficarAdmin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGraficarAdmin1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jbtnFiltrarAdmin))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnCalcularAdmin)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnGraficarAdmin))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jbtnGraficarAdmin1)))
                .addGap(141, 141, 141))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButtonInicioAdmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneAdmin)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCalcularAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnFiltrarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnGraficarAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnGraficarAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jButtonInicioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Administracion de empresas", jInternalFrame3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInicioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioAdminActionPerformed
    // Determina el tipo de usuario accediendo al usuario que ha iniciado sesión en CLogin
    String TipoDeUsuario = CLogin.getTipoUsuarioAdentro(); // Usa metodo

    // Crear el formulario de menú adecuado en función del tipo de usuario
    boolean cerrado = false;
    if ("ADMIN".equals(TipoDeUsuario)) {
        FormMenuPrincipal menuPrincipal = new FormMenuPrincipal();
        menuPrincipal.setVisible(true);
        cerrado = true;
    } else if ("DOCENTE".equals(TipoDeUsuario)) {
        FormMenuPrincipal2 menuPrincipal2 = new FormMenuPrincipal2();
        menuPrincipal2.setVisible(true);
        cerrado = true;
    } else {
        JOptionPane.showMessageDialog(this, "El usuario no tiene permisos para acceder a esta sección");
    }

    if (cerrado) {
        // Cierra el formulario actual
        this.dispose();
    }
    }//GEN-LAST:event_jButtonInicioAdminActionPerformed

    private void jbtnCalcularAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcularAdminActionPerformed
        
    // Obtener los datos de los alumnos de Administracion (puedes usar un método en CAlumnos para esto).
    List<Double> datosAdmin = alumnos.getPromediosAdministracionEmpresas();

    // Verificar si hay datos disponibles para el cálculo.
    if (datosAdmin.isEmpty()) {
        // Manejar la falta de datos de alguna manera (mostrar un mensaje, etc.).
        JOptionPane.showMessageDialog(this, "No hay datos para calcular estadísticas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        // Calcular estadísticas
        double media = CAlumnos.calcularMedia(datosAdmin);
        double moda = CAlumnos.calcularModa(datosAdmin);
        double mediana = CAlumnos.calcularMediana(datosAdmin);
        double cuartil1 = CAlumnos.calcularCuartil1(datosAdmin);
        double cuartil3 = CAlumnos.calcularCuartil3(datosAdmin);
        double desviacionMedia = CAlumnos.calcularDesviacionMedia(datosAdmin);
        double varianza = CAlumnos.calcularVarianza(datosAdmin);
        double desviacionTipica = CAlumnos.calcularDesviacionTipica(datosAdmin);
        double coeficienteVariacion = CAlumnos.calcularCoeficienteVariacion(datosAdmin);
        double asimetria = CAlumnos.calcularAsimetria(datosAdmin);
        double curtosis = CAlumnos.calcularCurtosis(datosAdmin);

        // Mostrar los resultados en jScrollPaneAdmin
        JTextArea resultados = new JTextArea();
        resultados.setText("Número de datos: " + datosAdmin.size() + "\n");
        resultados.append("Media: " + media + "\n");
        resultados.append("Moda: " + moda + "\n");
        resultados.append("Mediana: " + mediana + "\n");
        resultados.append("Cuartil 1: " + cuartil1 + "\n");
        resultados.append("Cuartil 3: " + cuartil3 + "\n");
        resultados.append("Desviación Media: " + desviacionMedia + "\n");
        resultados.append("Varianza: " + varianza + "\n");
        resultados.append("Desviación Típica: " + desviacionTipica + "\n");
        resultados.append("Coeficiente de Variación: " + coeficienteVariacion + "\n");
        resultados.append("Asimetría: " + asimetria + "\n");
        resultados.append("Curtosis: " + curtosis + "\n");

        jScrollPaneAdmin.setViewportView(resultados);
    }
    }//GEN-LAST:event_jbtnCalcularAdminActionPerformed

    private void jbtnFiltrarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFiltrarAdminActionPerformed
        alumnos.MostrarAlumnosAdministracionEmpresas(jTableAdmin);
    }//GEN-LAST:event_jbtnFiltrarAdminActionPerformed

    private void jButtonInicioSistemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioSistemasActionPerformed
    // Determine el tipo de usuario accediendo al usuario que ha iniciado sesión en CLogin
    String TipoDeUsuario = CLogin.getTipoUsuarioAdentro(); // Usa metodo

    // Crear el formulario de menú adecuado en función del tipo de usuario
    boolean cerrado = false;
    if ("ADMIN".equals(TipoDeUsuario)) {
        FormMenuPrincipal menuPrincipal = new FormMenuPrincipal();
        menuPrincipal.setVisible(true);
        cerrado = true;
    } else if ("DOCENTE".equals(TipoDeUsuario)) {
        FormMenuPrincipal2 menuPrincipal2 = new FormMenuPrincipal2();
        menuPrincipal2.setVisible(true);
        cerrado = true;
    } else {
        JOptionPane.showMessageDialog(this, "El usuario no tiene permisos para acceder a esta sección");
    }

    if (cerrado) {
        // Cierra el formulario actual
        this.dispose();
    }
        
    }//GEN-LAST:event_jButtonInicioSistemasActionPerformed
    
    private void jbtnCalcularSistemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcularSistemasActionPerformed
                                                    
    // Obtener los datos de los alumnos de Ingeniería de sistemas (usar un método en CAlumnos para esto).
    List<Double> datosSistemas = alumnos.getPromediosIngenieriaSistemas();

    // Verificar si hay datos disponibles para el cálculo.
    if (datosSistemas.isEmpty()) {
        // Manejar la falta de datos de alguna manera (mostrar un mensaje, etc.).
        JOptionPane.showMessageDialog(this, "No hay datos para calcular estadísticas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        // Calcular estadísticas
        double media = CAlumnos.calcularMedia(datosSistemas);
        double moda = CAlumnos.calcularModa(datosSistemas);
        double mediana = CAlumnos.calcularMediana(datosSistemas);
        double cuartil1 = CAlumnos.calcularCuartil1(datosSistemas);
        double cuartil3 = CAlumnos.calcularCuartil3(datosSistemas);
        double desviacionMedia = CAlumnos.calcularDesviacionMedia(datosSistemas);
        double varianza = CAlumnos.calcularVarianza(datosSistemas);
        double desviacionTipica = CAlumnos.calcularDesviacionTipica(datosSistemas);
        double coeficienteVariacion = CAlumnos.calcularCoeficienteVariacion(datosSistemas);
        double asimetria = CAlumnos.calcularAsimetria(datosSistemas);
        double curtosis = CAlumnos.calcularCurtosis(datosSistemas);

        // Mostrar los resultados en jScrollPaneSistemas
        JTextArea resultados = new JTextArea();
        resultados.setText("Número de datos: " + datosSistemas.size() + "\n");
        resultados.append("Media: " + media + "\n");
        resultados.append("Moda: " + moda + "\n");
        resultados.append("Mediana: " + mediana + "\n");
        resultados.append("Cuartil 1: " + cuartil1 + "\n");
        resultados.append("Cuartil 3: " + cuartil3 + "\n");
        resultados.append("Desviación Media: " + desviacionMedia + "\n");
        resultados.append("Varianza: " + varianza + "\n");
        resultados.append("Desviación Típica: " + desviacionTipica + "\n");
        resultados.append("Coeficiente de Variación: " + coeficienteVariacion + "\n");
        resultados.append("Asimetría: " + asimetria + "\n");
        resultados.append("Curtosis: " + curtosis + "\n");

        jScrollPaneSistemas.setViewportView(resultados);
    }



    }//GEN-LAST:event_jbtnCalcularSistemasActionPerformed
   

    private void jbtnFiltrarSistemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFiltrarSistemasActionPerformed
        alumnos.MostrarAlumnosIngenieriaSistemas(jTableSistemas);
    }//GEN-LAST:event_jbtnFiltrarSistemasActionPerformed
    
    private void jButtonInicioElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioElectronicaActionPerformed
    // Determine el tipo de usuario accediendo al usuario que ha iniciado sesión en CLogin
    String TipoDeUsuario = CLogin.getTipoUsuarioAdentro(); // Usa metodo

    // Crear el formulario de menú adecuado en función del tipo de usuario
    boolean cerrado = false;
    if ("ADMIN".equals(TipoDeUsuario)) {
        FormMenuPrincipal menuPrincipal = new FormMenuPrincipal();
        menuPrincipal.setVisible(true);
        cerrado = true;
    } else if ("DOCENTE".equals(TipoDeUsuario)) {
        FormMenuPrincipal2 menuPrincipal2 = new FormMenuPrincipal2();
        menuPrincipal2.setVisible(true);
        cerrado = true;
    } else {
        JOptionPane.showMessageDialog(this, "El usuario no tiene permisos para acceder a esta sección");
    }

    if (cerrado) {
        // Cierra el formulario actual
        this.dispose();
    }
    }//GEN-LAST:event_jButtonInicioElectronicaActionPerformed

    private void jbtnCalcularElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcularElectronicaActionPerformed
    
    // Obtener los datos de los alumnos de Ingeniería de electronica (puedes usar un método en CAlumnos para esto).
    List<Double> datosElectronica = alumnos.getPromediosIngenieriaElectronica();

    // Verificar si hay datos disponibles para el cálculo.
    if (datosElectronica.isEmpty()) {
        // Manejar la falta de datos de alguna manera (mostrar un mensaje, etc.).
        JOptionPane.showMessageDialog(this, "No hay datos para calcular estadísticas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    } else {
        // Calcular estadísticas
        double media = CAlumnos.calcularMedia(datosElectronica);
        double moda = CAlumnos.calcularModa(datosElectronica);
        double mediana = CAlumnos.calcularMediana(datosElectronica);
        double cuartil1 = CAlumnos.calcularCuartil1(datosElectronica);
        double cuartil3 = CAlumnos.calcularCuartil3(datosElectronica);
        double desviacionMedia = CAlumnos.calcularDesviacionMedia(datosElectronica);
        double varianza = CAlumnos.calcularVarianza(datosElectronica);
        double desviacionTipica = CAlumnos.calcularDesviacionTipica(datosElectronica);
        double coeficienteVariacion = CAlumnos.calcularCoeficienteVariacion(datosElectronica);
        double asimetria = CAlumnos.calcularAsimetria(datosElectronica);
        double curtosis = CAlumnos.calcularCurtosis(datosElectronica);

        // Mostrar los resultados en jScrollPaneElectronica
        JTextArea resultados = new JTextArea();
        resultados.setText("Número de datos: " + datosElectronica.size() + "\n");
        resultados.append("Media: " + media + "\n");
        resultados.append("Moda: " + moda + "\n");
        resultados.append("Mediana: " + mediana + "\n");
        resultados.append("Cuartil 1: " + cuartil1 + "\n");
        resultados.append("Cuartil 3: " + cuartil3 + "\n");
        resultados.append("Desviación Media: " + desviacionMedia + "\n");
        resultados.append("Varianza: " + varianza + "\n");
        resultados.append("Desviación Típica: " + desviacionTipica + "\n");
        resultados.append("Coeficiente de Variación: " + coeficienteVariacion + "\n");
        resultados.append("Asimetría: " + asimetria + "\n");
        resultados.append("Curtosis: " + curtosis + "\n");

        jScrollPaneElectronica.setViewportView(resultados);
    }

    }//GEN-LAST:event_jbtnCalcularElectronicaActionPerformed

    private void jbtnFiltrarElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFiltrarElectronicaActionPerformed
        alumnos.MostrarAlumnosIngenieriaElectronica(jTableElectronica);
    }//GEN-LAST:event_jbtnFiltrarElectronicaActionPerformed

    private void jbtnGraficaSistemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficaSistemasActionPerformed
        List<Double> datosSistemas = alumnos.getPromediosIngenieriaSistemas();
        generarGraficoCircular(datosSistemas, "Ingeniería de Sistemas");
    }//GEN-LAST:event_jbtnGraficaSistemasActionPerformed

    private void jbtnGraficaSistemas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficaSistemas2ActionPerformed
        List<Double> datosSistemas = alumnos.getPromediosIngenieriaSistemas();
        generarHistograma(datosSistemas, "Ingeniería de Sistemas");
    }//GEN-LAST:event_jbtnGraficaSistemas2ActionPerformed

    private void jbtnGraficaElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficaElectronicaActionPerformed
        List<Double> datosElectronica = alumnos.getPromediosIngenieriaElectronica();
        generarGraficoCircular(datosElectronica, "Ingeniería Electrónica");
    }//GEN-LAST:event_jbtnGraficaElectronicaActionPerformed

    private void jbtnGraficarAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficarAdminActionPerformed
        List<Double> datosAdministracion = alumnos.getPromediosAdministracionEmpresas();
        generarGraficoCircular(datosAdministracion, "Administración de Empresas");
    }//GEN-LAST:event_jbtnGraficarAdminActionPerformed

    private void jbtnGraficaElectronica2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficaElectronica2ActionPerformed
        List<Double> datosElectronica = alumnos.getPromediosIngenieriaElectronica();
        generarHistograma(datosElectronica, "Ingeniería Electrónica");
    }//GEN-LAST:event_jbtnGraficaElectronica2ActionPerformed

    private void jbtnGraficarAdmin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGraficarAdmin1ActionPerformed
        List<Double> datosAdministracion = alumnos.getPromediosAdministracionEmpresas();
        generarHistograma(datosAdministracion, "Administración de Empresas");
    }//GEN-LAST:event_jbtnGraficarAdmin1ActionPerformed

 
    public static void main(String args[]) {
  
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEstadistica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEstadistica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInicioAdmin;
    private javax.swing.JButton jButtonInicioElectronica;
    private javax.swing.JButton jButtonInicioSistemas;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneAdmin;
    private javax.swing.JScrollPane jScrollPaneElectronica;
    private javax.swing.JScrollPane jScrollPaneSistemas;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAdmin;
    private javax.swing.JTable jTableElectronica;
    private javax.swing.JTable jTableSistemas;
    private javax.swing.JButton jbtnCalcularAdmin;
    private javax.swing.JButton jbtnCalcularElectronica;
    private javax.swing.JButton jbtnCalcularSistemas;
    private javax.swing.JButton jbtnFiltrarAdmin;
    private javax.swing.JButton jbtnFiltrarElectronica;
    private javax.swing.JButton jbtnFiltrarSistemas;
    private javax.swing.JButton jbtnGraficaElectronica;
    private javax.swing.JButton jbtnGraficaElectronica2;
    private javax.swing.JButton jbtnGraficaSistemas;
    private javax.swing.JButton jbtnGraficaSistemas2;
    private javax.swing.JButton jbtnGraficarAdmin;
    private javax.swing.JButton jbtnGraficarAdmin1;
    // End of variables declaration//GEN-END:variables

private void generarGraficoCircular(List<Double> datos, String carrera) {
    DefaultPieDataset dataset = new DefaultPieDataset();

    int aprobados = 0;
    int noAprobados = 0;

    for (Double promedio : datos) {
        if (promedio >= 3.5) {
            aprobados++;
        } else {
            noAprobados++;
        }
    }

    dataset.setValue("Aprobados (" + aprobados + ")", aprobados);
    dataset.setValue("No Aprobados (" + noAprobados + ")", noAprobados);

    JFreeChart chart = ChartFactory.createPieChart(
        "Porcentaje de Aprobados vs. No Aprobados en " + carrera,
        dataset,
        true,
        true,
        false
    );

    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));

    JFrame frame = new JFrame("Gráfico Circular - " + carrera);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(chartPanel);
    frame.pack();
    frame.setVisible(true);
} 

private void generarHistograma(List<Double> datos, String carrera) {    HistogramDataset dataset = new HistogramDataset();
    double[] values = datos.stream().mapToDouble(Double::doubleValue).toArray();
    dataset.addSeries("Histograma de Promedios en " + carrera, values, 10); // El número 10 representa el número de bins en el histograma, puedes ajustarlo según tus necesidades.

    JFreeChart chart = ChartFactory.createHistogram(
        "Histograma de Promedios en " + carrera,
        "Promedio",
        "Frecuencia",
        dataset,
        PlotOrientation.VERTICAL,
        false,
        true,
        false
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));

    JFrame frame = new JFrame("Histograma - " + carrera);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(chartPanel);
    frame.pack();
    frame.setVisible(true);
}

}
