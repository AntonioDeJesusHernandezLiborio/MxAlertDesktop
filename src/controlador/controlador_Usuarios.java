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
import modelo.DAO.DAO_Usuarios;
import modelo.ResultSetComboBoxModel;
import modelo.ResultSetComboBoxModelObject;
import modelo.VO.VOUsuarios;
import vista.frmUsuarios;


public class controlador_Usuarios extends mensaje implements ActionListener,IControlador,MouseListener{
    frmUsuarios view;
    DAO_Usuarios AccesoDatosDelObjetoUsuario;
    VOUsuarios valoresDelObjetoUsuario;
    
    public controlador_Usuarios(frmUsuarios inicio){
        this.view = inicio;
        if(AccesoDatosDelObjetoUsuario == null) AccesoDatosDelObjetoUsuario = new DAO_Usuarios();
        
        view.btnAgregar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.btnEliminar.addActionListener(this);
        view.tablaTipoUsuario.addMouseListener(this);
        view.btnLimpiar.addActionListener(this);
        
        ocultarColumnas();
        cargarDatosATabla();
        cargarComboNivelUsuario();
        iniciar();
        OcultarTextos();
    }
    
    private void OcultarTextos(){
        view.txtId.setVisible(false);
        view.lblId.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnAgregar){
            if(validarInsertar()){
                insertar();
            }else  mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnModificar){
            if(validar()){
                actualizar();
            }else  mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnEliminar){
            if(validar()){
                eliminar();
            }else  mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() ==  view.btnLimpiar){
            limpiar();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.tablaTipoUsuario) {
            moverDatosAJtextBox();
        } 
    }
    
    @Override
    public void iniciar() {
        view.setTitle("Usuario");
        view.setLocale(null);
    }

    @Override
    public boolean validar() {
        return !"".equals(view.txtAM.getText()) || !"".equals(view.txtDireccion.getText())
                || !"".equals(view.txtId.getText())  || !"".equals(view.txtNombre.getText())
                || !"".equals(view.txtAP.getText()) || !"".equals(view.txtTelefono.getText())
                || !"".equals(view.txtUsuario.getText()) || !"".equals(view.txtContraseña.getText())
                || !"".equals(view.txtEmail.getText());
    }
    
    public boolean validarInsertar() {
        return !"".equals(view.txtAM.getText()) || !"".equals(view.txtDireccion.getText())
                || !"".equals(view.txtNombre.getText()) || !"".equals(view.txtAP.getText()) 
                || !"".equals(view.txtTelefono.getText()) || !"".equals(view.txtUsuario.getText()) 
                || !"".equals(view.txtContraseña.getText()) || !"".equals(view.txtEmail.getText());
    }

    @Override
    public void cargarDatosATabla() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaTipoUsuario.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoUsuario.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            String AP = contenedorDeRegistro[2].toString();
            String AM = contenedorDeRegistro[3].toString();
            String DR = contenedorDeRegistro[4].toString();
            String telefono = contenedorDeRegistro[5].toString();
            String usuario = contenedorDeRegistro[6].toString();
            boolean status = (boolean) contenedorDeRegistro[7];
            int nivelUsuario = Integer.parseInt(contenedorDeRegistro[8].toString());
            String correo =  contenedorDeRegistro[9].toString();
            modeloDeTabla.addRow(new Object[]{id, nombre, AP, AM, DR, telefono, usuario, status, nivelUsuario,correo});
        }
        view.tablaTipoUsuario.setModel(modeloDeTabla);
    
    }

    @Override
    public void insertar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbNivelUsuario.getModel().getSelectedItem();
        valoresDelObjetoUsuario = VOUsuarios.Make(view.txtNombre.getText())
               .setApellidoPaterno(view.txtAP.getText())
               .setApellidoMaterno(view.txtAM.getText())
               .setDireccion(view.txtDireccion.getText())
               .setTelefono(view.txtTelefono.getText())
               .setUsuario(view.txtUsuario.getText())
               .setContraseña(view.txtContraseña.getText())
               .setNivelUsuario(model.getCodigo())
               .setEstado(view.rdbActivo.isSelected())
               .setCorreoElectonico(view.txtEmail.getText())
               .Build();
        if(AccesoDatosDelObjetoUsuario.insertar(valoresDelObjetoUsuario)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }

    @Override
    public void actualizar() {
       ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbNivelUsuario.getModel().getSelectedItem();
       valoresDelObjetoUsuario = VOUsuarios.Make(view.txtNombre.getText())
               .setApellidoPaterno(view.txtAP.getText())
               .setApellidoMaterno(view.txtAM.getText())
               .setDireccion(view.txtDireccion.getText())
               .setTelefono(view.txtTelefono.getText())
               .setUsuario(view.txtUsuario.getText())
               .setNivelUsuario(model.getCodigo())
               .setEstado(view.rdbActivo.isSelected())
               .setId(view.txtId.getText())
               .setCorreoElectonico(view.txtEmail.getText())
               .Build();
        if(AccesoDatosDelObjetoUsuario.actualizar(valoresDelObjetoUsuario)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    
    private void moverDatosAJtextBox(){
        int n = view.tablaTipoUsuario.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaTipoUsuario.getModel();
            view.txtId.setText(modelo.getValueAt(n, 0).toString()); 
            view.txtNombre.setText(modelo.getValueAt(n, 1).toString());
            view.txtAP.setText(modelo.getValueAt(n, 2).toString());
            view.txtAM.setText(modelo.getValueAt(n, 3).toString());
            view.txtDireccion.setText(modelo.getValueAt(n, 4).toString());
            view.txtTelefono.setText(modelo.getValueAt(n, 5).toString());
            view.txtUsuario.setText(modelo.getValueAt(n, 6).toString());
            view.rdbActivo.setSelected((boolean) modelo.getValueAt(n, 7));
            view.cmbNivelUsuario.setSelectedItem(((ResultSetComboBoxModel)view.cmbNivelUsuario.getModel()).searchSelectedItem(Integer.parseInt(modelo.getValueAt(n, 8).toString())));
            view.txtEmail.setText(modelo.getValueAt(n, 9).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }

    @Override
    public void eliminar() {
        valoresDelObjetoUsuario = VOUsuarios.Make(view.txtNombre.getText()).setId(view.txtId.getText())
               .Build();
        if(AccesoDatosDelObjetoUsuario.eliminar(valoresDelObjetoUsuario)){
            mandaMensajeDeTexto("Eliminado","Elimnar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al eliminar","Actualizar");
        }
     }

    @Override
    public void limpiar() {
        view.txtAM.setText(null);
        view.txtDireccion.setText(null);
        view.txtId.setText(null);
        view.txtNombre.setText(null);
        view.txtAP.setText(null);
        view.txtTelefono.setText(null);
        view.txtUsuario.setText(null);
        view.txtContraseña.setText(null);
        view.txtEmail.setText(null);
    }
    
    public void ocultarColumnas(){
        
        view.tablaTipoUsuario.getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaTipoUsuario.getColumnModel().getColumn(0).setMinWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        view.tablaTipoUsuario.getColumnModel().getColumn(8).setMaxWidth(0);
        view.tablaTipoUsuario.getColumnModel().getColumn(8).setMinWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        
        view.tablaTipoUsuario.getColumnModel().getColumn(4).setMaxWidth(0);
        view.tablaTipoUsuario.getColumnModel().getColumn(4).setMinWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        
        view.tablaTipoUsuario.getColumnModel().getColumn(5).setMaxWidth(0);
        view.tablaTipoUsuario.getColumnModel().getColumn(5).setMinWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        
        view.tablaTipoUsuario.getColumnModel().getColumn(7).setMaxWidth(0);
        view.tablaTipoUsuario.getColumnModel().getColumn(7).setMinWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        view.tablaTipoUsuario.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
    }
    
    public void cargarComboNivelUsuario(){
        try {
            ConsultaCombo combo = new ConsultaCombo();
            view.cmbNivelUsuario.setModel(new ResultSetComboBoxModel(combo.consultarNivelUsuario(), "Id", "Nombre"));
            view.cmbNivelUsuario.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(controlador_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
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
