package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultaCombo;
import modelo.DAO.DAO_Protocolo;
import modelo.ResultSetComboBoxModel;
import modelo.ResultSetComboBoxModelObject;
import modelo.VO.VOProtocolo;
import vista.frmProtocolos;


public class controlador_Protocolo extends mensaje implements ActionListener,IControlador,MouseListener{
    
    frmProtocolos view;
    DAO_Protocolo AccesoDatosDelObjetoProtocolo;
    VOProtocolo valoresDelObjetoProtocolo;
    
    public controlador_Protocolo(frmProtocolos view){
        this.view = view;
        if(AccesoDatosDelObjetoProtocolo == null) AccesoDatosDelObjetoProtocolo = new DAO_Protocolo();
        view.btnAgregar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.tablaTipoDenuncia.addMouseListener(this);
        view.btnLimpiar.addActionListener(this);
        
        ocultarColumnas();
        cargarDatosATabla();
        cargarComboNivelUsuario();
        iniciar();
    }
    public void cargarComboNivelUsuario(){
        try {
            ConsultaCombo combo = new ConsultaCombo();
            view.cmbDenuncia.setModel(new ResultSetComboBoxModel(combo.consultarTipoDenuncia(), "Id", "Nombre"));
            view.cmbDenuncia.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(controlador_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(validarInsertar()){
                insertar();
            }else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnModificar){
            if(validar()){
                actualizar();
            }else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnEliminar){
            if(validar()){
                eliminar();
            }else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() ==  view.btnLimpiar){
            limpiar();
        }
    }

    @Override
    public void iniciar() {
      view.setTitle("Protocolo");
      view.setLocale(null);
    }

    @Override
    public boolean validar() {
        return !"".equals(view.txtClave.getText()) || !"".equals(view.txtNombre.getText());
    }
    public boolean validarInsertar() {
        return !"".equals(view.txtNombre.getText());
    }
    @Override
    public void cargarDatosATabla() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaTipoDenuncia.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoProtocolo.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            int status = Integer.parseInt(contenedorDeRegistro[2].toString());
            modeloDeTabla.addRow(new Object[]{id, nombre, status});
        }
        view.tablaTipoDenuncia.setModel(modeloDeTabla);
    }

    @Override
    public void insertar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbDenuncia.getModel().getSelectedItem();
        valoresDelObjetoProtocolo = VOProtocolo.Make(view.txtNombre.getText())
                .setIdTipoDEnuncia(model.getCodigo())
                .Build();
        if(AccesoDatosDelObjetoProtocolo.insertar(valoresDelObjetoProtocolo)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }

    @Override
    public void actualizar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbDenuncia.getModel().getSelectedItem();
        valoresDelObjetoProtocolo = VOProtocolo.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .setIdTipoDEnuncia(model.getCodigo())
                .Build();
        if(AccesoDatosDelObjetoProtocolo.actualizar(valoresDelObjetoProtocolo)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }

    @Override
    public void eliminar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbDenuncia.getModel().getSelectedItem();
        valoresDelObjetoProtocolo = VOProtocolo.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoProtocolo.eliminar(valoresDelObjetoProtocolo)){
            mandaMensajeDeTexto("Eliminado con éxito","Eliminar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Eliminar");
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
            view.cmbDenuncia.setSelectedItem(((ResultSetComboBoxModel)view.cmbDenuncia.getModel()).searchSelectedItem(Integer.parseInt(modelo.getValueAt(n, 2).toString())));
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.tablaTipoDenuncia) {
            moverDatosAJtextBox();
        } 
    }

    public void ocultarColumnas(){
        
        view.tablaTipoDenuncia.getColumnModel().getColumn(2).setMaxWidth(0);
        view.tablaTipoDenuncia.getColumnModel().getColumn(2).setMinWidth(0);
        view.tablaTipoDenuncia.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        view.tablaTipoDenuncia.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        
        view.lblClave.setVisible(false);
        view.txtClave.setVisible(false);
        
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
