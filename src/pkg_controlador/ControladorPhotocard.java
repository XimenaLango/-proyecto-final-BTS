package pkg_controlador;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pkg_excepciones.PrecioInvalidoException;
import pkg_modelo.ModeloPhotocard;
import pkg_modelo.Photocard;
import pkg_vista.PhotocardVista;

/**
 *
 * @author MELI
 */
public class ControladorPhotocard implements ActionListener {

    private final PhotocardVista vista;
    private final ModeloPhotocard modelo;

    public ControladorPhotocard(PhotocardVista vista, ModeloPhotocard modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        this.vista.tablaInventario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFilaTabla();
            }
        });

        listarEnTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardar();
        } else if (e.getSource() == vista.btnConsultar) {
            consultar();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizar();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminar();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void guardar() {
        try {
            String id = vista.txtId.getText();
            String rareza = vista.cmbRareza.getSelectedItem().toString();
            double precio = Double.parseDouble(vista.txtPrecioEstimado.getText());
            String detalles = vista.txtDetalles.getText();

            Photocard p = new Photocard(id, rareza, precio, detalles);

            if (modelo.insertar(p)) {
                JOptionPane.showMessageDialog(vista, "Guardado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar el registro");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido");
        } catch (PrecioInvalidoException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error: " + ex.getMessage());
        }
    }

    private void consultar() {
        try {
            String id = vista.txtId.getText();
            Photocard p = modelo.buscarPorId(id);
            
            if (p != null) {
                vista.cmbRareza.setSelectedItem(p.getRareza());
                vista.txtPrecioEstimado.setText(String.valueOf(p.getPrecioEstimado()));
                vista.txtDetalles.setText(p.getDetalles());
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró el ID ingresado");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al consultar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            String id = vista.txtId.getText();
            String rareza = vista.cmbRareza.getSelectedItem().toString();
            double precio = Double.parseDouble(vista.txtPrecio.getText());
            String detalles = vista.txtDetalles.getText();

            Photocard p = new Photocard(id, rareza, precio, detalles);

            if (modelo.actualizar(p)) {
                JOptionPane.showMessageDialog(vista, "Actualizado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar el registro");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido");
        } catch (PrecioInvalidoException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "ERROR " + ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            String id = vista.txtId.getText();
            if (modelo.eliminar(id)) {
                JOptionPane.showMessageDialog(vista, "Eliminado correctamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo eliminar el registro");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void listarEnTabla() {
        DefaultTableModel model = (DefaultTableModel) vista.tablaInventario.getModel();
        model.setRowCount(0); // Limpiar tabla

        List<Photocard> lista = modelo.obtenerTodos();
        for (Photocard p : lista) {
            model.addRow(new Object[]{
                p.getId(),
                p.getRareza(),
                p.getPrecioEstimado(),
                p.getDetalles()
            });
        }
    }

    private void seleccionarFilaTabla() {
        int fila = vista.tablaInventario.getSelectedRow();
        if (fila >= 0) {
            vista.txtId.setText(vista.tablaInventario.getValueAt(fila, 0).toString());
            vista.cmbRareza.setSelectedItem(vista.tablaInventario.getValueAt(fila, 1).toString());
            vista.txtPrecioEstimado.setText(vista.tablaInventario.getValueAt(fila, 2).toString());
            vista.txtDetalles.setText(vista.tablaInventario.getValueAt(fila, 3).toString());
        }
    }

    private boolean validarCampos() {
        if (vista.txtId.getText().trim().isEmpty() ||
            vista.cmbRareza.getSelectedIndex() <= 0 ||
            vista.txtPrecioEstimado.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(vista, "Por favor completa el ID, selecciona una Rareza y asigna un Precio.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.cmbRareza.setSelectedIndex(0);
        vista.txtPrecioEstimado.setText("");
        vista.txtDetalles.setText("");
        vista.tablaInventario.clearSelection();
    }
}
