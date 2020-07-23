package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_TipoDenuncia;
import modelo.VO.VOTipoDenuncia;
import vista.frmTipoDenuncia;

public class controlador_TipoDenuncia extends mensaje implements ActionListener,IControlador,MouseListener{
    frmTipoDenuncia view;
    DAO_TipoDenuncia AccesoDatosDelObjetoTipoDenuncia;
    VOTipoDenuncia valoresDelObjetoTipoDenuncia;
    
    public controlador_TipoDenuncia(frmTipoDenuncia inicio){
        this.view = inicio;
        if(AccesoDatosDelObjetoTipoDenuncia == null) AccesoDatosDelObjetoTipoDenuncia = new DAO_TipoDenuncia();
        
        view.btnAgregar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.tablaTipoDenuncia.addMouseListener(this);
        view.btnLimpiar.addActionListener(this);
        
        view.lblClave.setVisible(false);
        view.txtClave.setVisible(false);
        
        cargarDatosATabla();
        iniciar();
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
        if(e.getSource() == view.btnLimpiar){
            limpiar();
        }
    }

    @Override
    public void iniciar() {
        view.setTitle("Tipo Denuncia");
        view.setLocale(null);
    }

    @Override
    public boolean validar() {
        return !"".equals(view.txtClave.getText()) || !"".equals(view.txtNombre.getText());
    }
    
    public boolean validarInsertar(){
         return !"".equals(view.txtNombre.getText());
    }

    @Override
    public void cargarDatosATabla() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaTipoDenuncia.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoTipoDenuncia.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            modeloDeTabla.addRow(new Object[]{id, nombre});
        }
        view.tablaTipoDenuncia.setModel(modeloDeTabla);
    }

    @Override
    public void insertar() {
         valoresDelObjetoTipoDenuncia = VOTipoDenuncia.Make(view.txtNombre.getText()).Build();
        if(AccesoDatosDelObjetoTipoDenuncia.insertar(valoresDelObjetoTipoDenuncia)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }

    @Override
    public void actualizar() {
        valoresDelObjetoTipoDenuncia = VOTipoDenuncia.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoTipoDenuncia.actualizar(valoresDelObjetoTipoDenuncia)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }

    @Override
    public void eliminar() {
        valoresDelObjetoTipoDenuncia = VOTipoDenuncia.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoTipoDenuncia.eliminar(valoresDelObjetoTipoDenuncia)){
            mandaMensajeDeTexto("Eliminado","Eliminado");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Eliminado");
        }
    }

    @Override
    public void limpiar() {
        view.txtClave.setText(null);
        view.txtNombre.setText(null);
    }
    private void moverDatosAJtextBox(){
        int n = view.tablaTipoDenuncia.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaTipoDenuncia.getModel();
            view.txtClave.setText(modelo.getValueAt(n, 0).toString()); 
            view.txtNombre.setText(modelo.getValueAt(n, 1).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.tablaTipoDenuncia){
            moverDatosAJtextBox();
        }   
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
