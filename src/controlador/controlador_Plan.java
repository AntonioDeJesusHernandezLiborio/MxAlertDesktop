package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_Plan;
import modelo.VO.VOPlan;
import vista.frmPlanDeUsuario;

public class controlador_Plan implements ActionListener {
    frmPlanDeUsuario view;
    DAO_Plan AccesoDatosDelObjetoPlan;
    VOPlan valoresDelObjetoPlan;
    
    private void iniciar(){
        view.setTitle("Plan");
        view.setLocale(null);
    }
    
    public controlador_Plan(frmPlanDeUsuario inicio){
        this.view = inicio;
        if(AccesoDatosDelObjetoPlan == null) AccesoDatosDelObjetoPlan = new DAO_Plan();
        cargarDatosATabla();
        iniciar();
        this.view.btnAgregar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(validarInsertar())insertar();
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnModificar){
            if(validar())actualizar();
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnEliminar){
            if(validar())eliminar();
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
    }
    
    
    private void cargarDatosATabla(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaTipoUsuario.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoPlan.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            String descripcion = contenedorDeRegistro[2].toString();
            double precio = Double.parseDouble(contenedorDeRegistro[3].toString());
            modeloDeTabla.addRow(new Object[]{id, nombre,descripcion,precio});
        }
        view.tablaTipoUsuario.setModel(modeloDeTabla);
    }
    private void insertar(){
        valoresDelObjetoPlan = new VOPlan();
        valoresDelObjetoPlan.setNombre(view.txtNombre.getText());
        valoresDelObjetoPlan.setDescripcion(view.txtDescripcion.getText());
        valoresDelObjetoPlan.setPrecio(Double.parseDouble(view.txtPrecio.getText()));
        if(AccesoDatosDelObjetoPlan.insertar(valoresDelObjetoPlan)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }   
    private void actualizar(){
        valoresDelObjetoPlan = new VOPlan();
        valoresDelObjetoPlan.setId(Integer.parseInt(view.txtClave.getText()));
        valoresDelObjetoPlan.setNombre(view.txtNombre.getText());
        valoresDelObjetoPlan.setDescripcion(view.txtDescripcion.getText());
        valoresDelObjetoPlan.setPrecio(Double.parseDouble(view.txtPrecio.getText()));
        if(AccesoDatosDelObjetoPlan.actualizar(valoresDelObjetoPlan)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    private void eliminar(){
        valoresDelObjetoPlan = new VOPlan();
        valoresDelObjetoPlan.setId(Integer.parseInt(view.txtClave.getText()));
        if(AccesoDatosDelObjetoPlan.eliminar(valoresDelObjetoPlan)){
            mandaMensajeDeTexto("Eliminado","Eliminado");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Eliminado");
        }
    }
    
    private static void mandaMensajeDeTexto(String infoMessage, String titleBar){
       JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void limpiar(){
        view.txtClave.setText(null);
        view.txtNombre.setText(null);
        view.txtDescripcion.setText(null);
        view.txtPrecio.setText(null);
    }
    
    private boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty() || view.txtDescripcion.getText().isEmpty() 
                || view.txtPrecio.getText().isEmpty()) return false;
        return true;
    }
    
    private boolean validarInsertar(){
        if(view.txtNombre.getText().isEmpty() || view.txtDescripcion.getText().isEmpty() 
                || view.txtPrecio.getText().isEmpty()) return false;
        return true;
    }
}
