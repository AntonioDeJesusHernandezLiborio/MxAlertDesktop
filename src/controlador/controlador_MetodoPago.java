package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_MetodoPago;
import modelo.VO.VOMetodoDePago;
import vista.frmMetodoDePago;

public class controlador_MetodoPago implements ActionListener{

    frmMetodoDePago view;
    DAO_MetodoPago AccesoDatosDelObjetoMetodoDePago;
    VOMetodoDePago valoresDelObjetoMetodoDePago;
    
    
    private void iniciar(){
        view.setTitle("Metodo de pago");
        view.setLocale(null);
    }
    
    public controlador_MetodoPago(frmMetodoDePago inicio){
        this.view = inicio;
        if(AccesoDatosDelObjetoMetodoDePago == null) AccesoDatosDelObjetoMetodoDePago = new DAO_MetodoPago();
        cargarDatosATabla();
        iniciar();
        this.view.btnAgregar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(view.txtNombre.getText() != "" )insertar();
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
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaMetodoDePago.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoMetodoDePago.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            modeloDeTabla.addRow(new Object[]{id, nombre});
        }
        view.tablaMetodoDePago.setModel(modeloDeTabla);
    }
    private void insertar(){
        valoresDelObjetoMetodoDePago = new VOMetodoDePago();
        valoresDelObjetoMetodoDePago.setNombre(view.txtNombre.getText());
        if(AccesoDatosDelObjetoMetodoDePago.insertar(valoresDelObjetoMetodoDePago)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    } 
    private void actualizar(){
        valoresDelObjetoMetodoDePago = new VOMetodoDePago();
        valoresDelObjetoMetodoDePago.setId(Integer.parseInt(view.txtClave.getText()));
        valoresDelObjetoMetodoDePago.setNombre(view.txtNombre.getText());
        if(AccesoDatosDelObjetoMetodoDePago.actualizar(valoresDelObjetoMetodoDePago)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    private void eliminar(){
        valoresDelObjetoMetodoDePago = new VOMetodoDePago();
        valoresDelObjetoMetodoDePago.setId(Integer.parseInt(view.txtClave.getText()));
        if(AccesoDatosDelObjetoMetodoDePago.eliminar(valoresDelObjetoMetodoDePago)){
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
    }
    private boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty()) return false;
        return true;
    }
}
