package Formularios;

import Clases.CRoles;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class FormRol extends JFrame {

    private final CRoles dao = new CRoles();
    private final JTextField txtId = new JTextField(10);
    private final JTextField txtCodigo = new JTextField(10);
    private final JTextField txtNombre = new JTextField(20);
    private final JTable tabla = new JTable();

    public FormRol() {
        setTitle("Mantenimiento de Roles");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        cargarTabla();
    }

    private void initComponents() {
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        txtId.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        btnGuardar.addActionListener(e -> {
            dao.insertarRol(txtCodigo, txtNombre);
            cargarTabla();
            limpiarCampos();
        });

        btnActualizar.addActionListener(e -> {
            if (txtId.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Selecciona un rol de la tabla");
                return;
            }
            dao.actualizarRol(txtId, txtCodigo, txtNombre);
            cargarTabla();
            limpiarCampos();
        });

        btnEliminar.addActionListener(e -> {
            if (txtId.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Selecciona un rol de la tabla");
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar el rol seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                dao.eliminarRol(txtId);
                cargarTabla();
                limpiarCampos();
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        tabla.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Código", "Nombre"}));
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(tabla.getValueAt(fila, 0).toString());
                    txtCodigo.setText(tabla.getValueAt(fila, 1).toString());
                    txtNombre.setText(tabla.getValueAt(fila, 2).toString());
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pack();
    }

    private void cargarTabla() {
        dao.cargarTablaRoles(tabla);
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombre.setText("");
        tabla.clearSelection();
    }
}
