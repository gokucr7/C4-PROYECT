package Formularios;

import Clases.CAlumnos;
import Clases.CLogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

public class FormEstadistica extends javax.swing.JFrame {
    private static final Color COLOR_FONDO = new Color(112, 145, 255);
    private static final Font FUENTE_BOTON = new Font("Lucida Sans", Font.BOLD, 14);

    private final CAlumnos alumnos = new CAlumnos();

    public FormEstadistica() {
        initComponents();
        configurarTabsCarreras();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(COLOR_FONDO);
        jTabbedPane1.setBackground(COLOR_FONDO);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
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

    private void configurarTabsCarreras() {
        jTabbedPane1.removeAll();
        List<CarreraInfo> carreras = Arrays.asList(
            new CarreraInfo("SIS", "Ingeniería de Sistemas"),
            new CarreraInfo("ELE", "Ingeniería Electrónica"),
            new CarreraInfo("ADM", "Administración de Empresas"),
            new CarreraInfo("AGR", "Agronomía"),
            new CarreraInfo("ZOO", "Zootecnia"),
            new CarreraInfo("CON", "Contaduría Pública"),
            new CarreraInfo("SOC", "Licenciatura en Sociales"),
            new CarreraInfo("IND", "Ingeniería Industrial"),
            new CarreraInfo("MEC", "Ingeniería Mecatrónica")
        );

        for (CarreraInfo info : carreras) {
            JPanel tab = crearTabCarrera(info);
            jTabbedPane1.addTab(info.nombre, tab);
        }
    }

    private JPanel crearTabCarrera(CarreraInfo info) {
        JTable tabla = new JTable();
        tabla.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Nombres", "Apellidos", "Promedio", "Carrera"}));
        tabla.setFillsViewportHeight(true);

        JScrollPane tablaScroll = new JScrollPane(tabla);
        tablaScroll.setPreferredSize(new Dimension(520, 420));

        JTextArea resultadosArea = new JTextArea();
        resultadosArea.setEditable(false);
        resultadosArea.setLineWrap(true);
        resultadosArea.setWrapStyleWord(true);

        JScrollPane resultadosScroll = new JScrollPane(resultadosArea);
        resultadosScroll.setPreferredSize(new Dimension(320, 420));

        JButton btnFiltrar = crearBoton("FILTRAR");
        btnFiltrar.addActionListener(e -> alumnos.MostrarAlumnosPorCarrera(tabla, info.codigo));

        JButton btnCalcular = crearBoton("CALCULAR");
        btnCalcular.addActionListener(e -> calcularEstadisticas(info, resultadosArea));

        JButton btnGrafCircular = crearBoton("GRÁFICO CIRCULAR");
        btnGrafCircular.addActionListener(e -> mostrarGraficoCircular(info));

        JButton btnGrafHist = crearBoton("GRÁFICO HISTOGRAMA");
        btnGrafHist.addActionListener(e -> mostrarHistograma(info));

        JButton btnInicio = new JButton("INICIO");
        btnInicio.setBackground(Color.BLACK);
        btnInicio.setForeground(Color.WHITE);
        btnInicio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnInicio.addActionListener(e -> irAlInicio());

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonesPanel.setBackground(COLOR_FONDO);
        botonesPanel.add(btnFiltrar);
        botonesPanel.add(btnCalcular);
        botonesPanel.add(btnGrafCircular);
        botonesPanel.add(btnGrafHist);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(COLOR_FONDO);
        topPanel.add(btnInicio);

        JPanel centerPanel = new JPanel(new BorderLayout(16, 16));
        centerPanel.setBackground(COLOR_FONDO);
        centerPanel.add(tablaScroll, BorderLayout.CENTER);
        centerPanel.add(resultadosScroll, BorderLayout.EAST);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(COLOR_FONDO);
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        container.add(botonesPanel, BorderLayout.SOUTH);

        return container;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(81, 84, 255));
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE_BOTON);
        return boton;
    }

    private void calcularEstadisticas(CarreraInfo info, JTextArea resultadosArea) {
        List<Double> datos = alumnos.getPromediosPorCarrera(info.codigo);
        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos para calcular estadísticas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            resultadosArea.setText("");
            return;
        }

        double media = CAlumnos.calcularMedia(datos);
        double moda = CAlumnos.calcularModa(datos);
        double mediana = CAlumnos.calcularMediana(datos);
        double cuartil1 = CAlumnos.calcularCuartil1(datos);
        double cuartil3 = CAlumnos.calcularCuartil3(datos);
        double desviacionMedia = CAlumnos.calcularDesviacionMedia(datos);
        double varianza = CAlumnos.calcularVarianza(datos);
        double desviacionTipica = CAlumnos.calcularDesviacionTipica(datos);
        double coeficienteVariacion = CAlumnos.calcularCoeficienteVariacion(datos);
        double asimetria = CAlumnos.calcularAsimetria(datos);
        double curtosis = CAlumnos.calcularCurtosis(datos);

        StringBuilder resultados = new StringBuilder();
        resultados.append("Número de datos: ").append(datos.size()).append('\n');
        resultados.append("Media: ").append(media).append('\n');
        resultados.append("Moda: ").append(moda).append('\n');
        resultados.append("Mediana: ").append(mediana).append('\n');
        resultados.append("Cuartil 1: ").append(cuartil1).append('\n');
        resultados.append("Cuartil 3: ").append(cuartil3).append('\n');
        resultados.append("Desviación Media: ").append(desviacionMedia).append('\n');
        resultados.append("Varianza: ").append(varianza).append('\n');
        resultados.append("Desviación Típica: ").append(desviacionTipica).append('\n');
        resultados.append("Coeficiente de Variación: ").append(coeficienteVariacion).append('\n');
        resultados.append("Asimetría: ").append(asimetria).append('\n');
        resultados.append("Curtosis: ").append(curtosis).append('\n');

        resultadosArea.setText(resultados.toString());
        resultadosArea.setCaretPosition(0);
    }

    private void mostrarGraficoCircular(CarreraInfo info) {
        List<Double> datos = alumnos.getPromediosPorCarrera(info.codigo);
        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos suficientes para generar el gráfico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        generarGraficoCircular(datos, info.nombre);
    }

    private void mostrarHistograma(CarreraInfo info) {
        List<Double> datos = alumnos.getPromediosPorCarrera(info.codigo);
        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay datos suficientes para generar el histograma.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        generarHistograma(datos, info.nombre);
    }

    private void irAlInicio() {
        String TipoDeUsuario = CLogin.getTipoUsuarioAdentro();
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
            this.dispose();
        }
    }

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
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

    private void generarHistograma(List<Double> datos, String carrera) {
        HistogramDataset dataset = new HistogramDataset();
        double[] values = datos.stream().mapToDouble(Double::doubleValue).toArray();
        dataset.addSeries("Histograma de Promedios en " + carrera, values, 10);

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

    private static class CarreraInfo {
        final String codigo;
        final String nombre;

        CarreraInfo(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }
    }

}
