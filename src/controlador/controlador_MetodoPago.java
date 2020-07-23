package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_MetodoPago;
import modelo.VO.VOMetodoDePago;
import vista.frmMetodoDePago;

public class controlador_MetodoPago extends mensaje implements ActionListener,IControlador,MouseListener{

    frmMetodoDePago view;
    DAO_MetodoPago AccesoDatosDelObjetoMetodoDePago;
    VOMetodoDePago valoresDelObjetoMetodoDePago;
    
    
    @Override
    public void iniciar(){
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
        this.view.btnLimpiar.addActionListener(this);
        
        this.view.tablaMetodoDePago.addMouseListener(this);
        
        view.lblClave.setVisible(false);
        view.txtClave.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(!"".equals(view.txtNombre.getText()) )insertar();
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
        if(e.getSource() == view.btnLimpiar){
            limpiar();
        }
    }
    
    @Override
    public void cargarDatosATabla(){
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
    @Override
    public void insertar(){
        valoresDelObjetoMetodoDePago = VOMetodoDePago.Make(view.txtNombre.getText())
                .Build();
        
        if(AccesoDatosDelObjetoMetodoDePago.insertar(valoresDelObjetoMetodoDePago)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    } 
    @Override
    public void actualizar(){
        valoresDelObjetoMetodoDePago = VOMetodoDePago.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoMetodoDePago.actualizar(valoresDelObjetoMetodoDePago)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    @Override
    public void eliminar(){
        valoresDelObjetoMetodoDePago = VOMetodoDePago.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoMetodoDePago.eliminar(valoresDelObjetoMetodoDePago)){
            mandaMensajeDeTexto("Eliminado","Eliminado");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Eliminado");
        }
    }
    
    @Override
    public void limpiar(){
        view.txtClave.setText(null);
        view.txtNombre.setText(null);
    }
    @Override
    public boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty()) return false;
        return true;
    }
    private void moverDatosAJtextBox(){
        int n = view.tablaMetodoDePago.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaMetodoDePago.getModel();
            view.txtClave.setText(modelo.getValueAt(n, 0).toString()); 
            view.txtNombre.setText(modelo.getValueAt(n, 1).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.tablaMetodoDePago)
        {
            moverDatosAJtextBox();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
