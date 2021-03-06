package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_TipoUsuario;
import modelo.VO.VOTipoUsuario;
import vista.frmTipoUsuario;

public class controlador_TipoUsuario extends mensaje implements ActionListener,IControlador,MouseListener{
    frmTipoUsuario view;
    DAO_TipoUsuario AccesoDatosDelObjetoTipoUsuario;
    VOTipoUsuario valoresDelObjetoTipoUsuario;
     
    @Override
    public final void iniciar(){
        view.setTitle("Tipo Usuario");
        view.setLocale(null);
    }
    
    public controlador_TipoUsuario(frmTipoUsuario inicio){
        this.view = inicio;
        
        if(AccesoDatosDelObjetoTipoUsuario == null) AccesoDatosDelObjetoTipoUsuario = new DAO_TipoUsuario();
        cargarDatosATabla();
        iniciar();
        this.view.btnAgregar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnModificar.addActionListener(this);
        this.view.btnLimpiar.addActionListener(this);
        this.view.tablaTipoUsuario.addMouseListener(this);
        
        view.lblClave.setVisible(false);
        view.txtClave.setVisible(false);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(!"".equals(view.txtNombre.getText()) ){
                insertar();
            }
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnModificar){
            if(validar()){
                actualizar();
            }
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnEliminar){
            if(validar()){
                eliminar();
            }
            else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() ==  view.btnLimpiar){
            limpiar();
        }
    }
    @Override
    public boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty()) return false;
        return true;
    }
    @Override
    public void cargarDatosATabla(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaTipoUsuario.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoTipoUsuario.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            modeloDeTabla.addRow(new Object[]{id, nombre});
        }
        view.tablaTipoUsuario.setModel(modeloDeTabla);
    }
    
    @Override
    public void insertar(){
        valoresDelObjetoTipoUsuario = VOTipoUsuario.Make(view.txtNombre.getText()).Build();
        if(AccesoDatosDelObjetoTipoUsuario.insertar(valoresDelObjetoTipoUsuario)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }
    
    @Override
    public void actualizar(){
         valoresDelObjetoTipoUsuario = VOTipoUsuario.Make(view.txtNombre.getText()).setId(Integer.parseInt(view.txtClave.getText()))
                 .setNombre(view.txtNombre.getText()).Build();
        if(AccesoDatosDelObjetoTipoUsuario.actualizar(valoresDelObjetoTipoUsuario)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    
    @Override
    public void eliminar(){
         valoresDelObjetoTipoUsuario = VOTipoUsuario.Make(view.txtNombre.getText()).setId(Integer.parseInt(view.txtClave.getText()))
                 .Build();
        if(AccesoDatosDelObjetoTipoUsuario.eliminar(valoresDelObjetoTipoUsuario)){
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
    
    private void moverDatosAJtextBox(){
        int n = view.tablaTipoUsuario.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaTipoUsuario.getModel();
            view.txtClave.setText(modelo.getValueAt(n, 0).toString()); 
            view.txtNombre.setText(modelo.getValueAt(n, 1).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.tablaTipoUsuario){
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
