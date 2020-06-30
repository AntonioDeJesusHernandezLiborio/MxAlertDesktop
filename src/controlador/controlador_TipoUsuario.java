package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_TipoUsuario;
import modelo.VO.VOTipoUsuario;
import vista.frmTipoUsuario;

public class controlador_TipoUsuario implements ActionListener{
    frmTipoUsuario view;
    DAO_TipoUsuario AccesoDatosDelObjetoTipoUsuario;
    VOTipoUsuario valoresDelObjetoTipoUsuario;
     
    private void iniciar(){
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
    
    private boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty()) return false;
        return true;
    }
    
    private void cargarDatosATabla(){
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
    private void insertar(){
        valoresDelObjetoTipoUsuario = new VOTipoUsuario();
        valoresDelObjetoTipoUsuario.setNombre(view.txtNombre.getText());
        if(AccesoDatosDelObjetoTipoUsuario.insertar(valoresDelObjetoTipoUsuario)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }   
    private void actualizar(){
        valoresDelObjetoTipoUsuario = new VOTipoUsuario();
        valoresDelObjetoTipoUsuario.setId(Integer.parseInt(view.txtClave.getText()));
        valoresDelObjetoTipoUsuario.setNombre(view.txtNombre.getText());
        if(AccesoDatosDelObjetoTipoUsuario.actualizar(valoresDelObjetoTipoUsuario)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    private void eliminar(){
        valoresDelObjetoTipoUsuario = new VOTipoUsuario();
        valoresDelObjetoTipoUsuario.setId(Integer.parseInt(view.txtClave.getText()));
        if(AccesoDatosDelObjetoTipoUsuario.eliminar(valoresDelObjetoTipoUsuario)){
            mandaMensajeDeTexto("Eliminado","Eliminado");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Eliminado");
        }
    }
    
    private static void mandaMensajeDeTexto(String infoMessage, String titleBar)
    {
       JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    private void limpiar(){
        view.txtClave.setText(null);
        view.txtNombre.setText(null);
    }
    
}
