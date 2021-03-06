package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.DAO_Plan;
import modelo.ResultSetComboBoxModelObject;
import modelo.VO.VOPlan;
import vista.frmPlanDeUsuario;

public class controlador_Plan extends mensaje implements ActionListener,IControlador,MouseListener {
    frmPlanDeUsuario view;
    DAO_Plan AccesoDatosDelObjetoPlan;
    VOPlan valoresDelObjetoPlan;
    
    @Override
    public void iniciar(){
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
        this.view.btnLimpiar.addActionListener(this);
        
        this.view.tablaTipoUsuario.addMouseListener(this);
        
        view.lblClave.setVisible(false);
        view.txtClave.setVisible(false);
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
    public void cargarDatosATabla(){
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
    @Override
    public void insertar(){
        valoresDelObjetoPlan = VOPlan.Make(view.txtNombre.getText())
                .setDescripcion(view.txtDescripcion.getText())
                .setPrecio(Double.parseDouble(view.txtPrecio.getText()))
                .Build();
        if(AccesoDatosDelObjetoPlan.insertar(valoresDelObjetoPlan)){
            mandaMensajeDeTexto("Insertado con éxito","Insertar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al insertar","Insertar");
        }
    }   
    @Override
    public void actualizar(){
        valoresDelObjetoPlan = VOPlan.Make(view.txtNombre.getText())
                .setDescripcion(view.txtDescripcion.getText())
                .setPrecio(Double.parseDouble(view.txtPrecio.getText()))
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoPlan.actualizar(valoresDelObjetoPlan)){
            mandaMensajeDeTexto("Actualizado con éxito","Actualizar");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al actualizar","Actualizar");
        }
    }
    @Override
    public void eliminar(){
        valoresDelObjetoPlan = VOPlan.Make(view.txtNombre.getText())
                .setId(Integer.parseInt(view.txtClave.getText()))
                .Build();
        if(AccesoDatosDelObjetoPlan.eliminar(valoresDelObjetoPlan)){
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
        view.txtDescripcion.setText(null);
        view.txtPrecio.setText(null);
    }
    private void moverDatosAJtextBox(){
        int n = view.tablaTipoUsuario.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaTipoUsuario.getModel();
            view.txtClave.setText(modelo.getValueAt(n, 0).toString()); 
            view.txtNombre.setText(modelo.getValueAt(n, 1).toString());
            view.txtDescripcion.setText(modelo.getValueAt(n,2).toString());
            view.txtPrecio.setText(modelo.getValueAt(n, 3).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }
    @Override
    public boolean validar(){
        if(view.txtClave.getText().isEmpty() || view.txtNombre.getText().isEmpty() || view.txtDescripcion.getText().isEmpty() 
                || view.txtPrecio.getText().isEmpty()) return false;
        return true;
    }
    
    private boolean validarInsertar(){
        if(view.txtNombre.getText().isEmpty() || view.txtDescripcion.getText().isEmpty() 
                || view.txtPrecio.getText().isEmpty()) return false;
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.tablaTipoUsuario){
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
