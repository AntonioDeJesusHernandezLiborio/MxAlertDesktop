package controlador;

import static controlador.mensaje.mandaMensajeDeTexto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.recuperar_contraseña;
import vista.frmRecuperarContraseña;

public class controlador_recuperarContraseña  extends mensaje implements ActionListener,IControlador,MouseListener{
    frmRecuperarContraseña view;
    recuperar_contraseña recuperar;
    
    String aP, aM, Nombre, nuevaContraseña;
    
    public controlador_recuperarContraseña(frmRecuperarContraseña inicio){
        this.view = inicio;
        if(recuperar == null) recuperar = new recuperar_contraseña();
        
        view.btnEnviarCorreo.addActionListener(this);
        view.tablaCuentas.addMouseListener(this);
        view.btnLimpiar.addActionListener(this);
        ocultarColumnas();
        cargarDatosATabla();
        iniciar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnEnviarCorreo){
            if(validar()){
                if(enviarCorreo()) mandaMensajeDeTexto("Contraseña restablecidad con exito","Correo enviado");
                else mandaMensajeDeTexto("Ocurrio un error, compruebe su conexion a internet","Correo no enviado");
            }else mandaMensajeDeTexto("Llene los campos","Advertencia");
        }
        if(e.getSource() == view.btnLimpiar){
            limpiar();
        }
    }

    @Override
    public void iniciar() {
        view.setTitle("Recuperar contraseña");
        view.setLocale(null);
    }
    
    public boolean enviarCorreo(){
        this.nuevaContraseña = generarTextoAleatorio();
        if(recuperar.reseteoContraseña(view.txtUsuario.getText(), nuevaContraseña)){
            String destinatario =  view.txtCorreo.getText();
            String asunto = "Restablecer contraseña";
            String cuerpo = "Hola"+" "+ this.Nombre + " "+this.aP+" "+this.aM+" la nueva contraseña de su cuenta de RegionalSoft es: " + this.nuevaContraseña;
            return recuperar.enviarConGMail(destinatario, asunto, cuerpo);
        }else return false;
    }
    
    
    @Override
    public boolean validar() {
        return !"".equals(view.txtUsuario.getText()) || !"".equals(view.txtCorreo.getText());
    }

    @Override
    public void cargarDatosATabla() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaCuentas.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = recuperar.cargarDatosATabla();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String nombre = contenedorDeRegistro[0].toString();
            String AP = contenedorDeRegistro[1].toString();
            String AM = contenedorDeRegistro[2].toString();
            String usuario = contenedorDeRegistro[3].toString();
            String correo =  contenedorDeRegistro[4].toString();
            modeloDeTabla.addRow(new Object[]{nombre, AP, AM, usuario,correo});
        }
        view.tablaCuentas.setModel(modeloDeTabla);
    }
    
    @Override
    public void limpiar() {
        this.Nombre = null;
        this.aM = null;
        view.txtCorreo.setText(null);
        view.txtUsuario.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.tablaCuentas) {
            moverDatosAJtextBox();
        }
    }
    public void ocultarColumnas(){
        view.tablaCuentas.getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaCuentas.getColumnModel().getColumn(0).setMinWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        view.tablaCuentas.getColumnModel().getColumn(1).setMaxWidth(0);
        view.tablaCuentas.getColumnModel().getColumn(1).setMinWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
        
        view.tablaCuentas.getColumnModel().getColumn(2).setMaxWidth(0);
        view.tablaCuentas.getColumnModel().getColumn(2).setMinWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        view.tablaCuentas.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
    }
    private void moverDatosAJtextBox(){
        int n = view.tablaCuentas.getSelectedRow();
        if(n >= 0){
            DefaultTableModel modelo = (DefaultTableModel) view.tablaCuentas.getModel();
            this.Nombre = modelo.getValueAt(n, 0).toString();
            this.aP= modelo.getValueAt(n, 1).toString();
            this.aM= modelo.getValueAt(n, 2).toString();
            view.txtUsuario.setText(modelo.getValueAt(n, 3).toString());
            view.txtCorreo.setText(modelo.getValueAt(n, 4).toString());
        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar","Advertencia");
        }
    }
    
   
    
    @Override
    public void insertar() {}
    @Override
    public void actualizar() {}
    @Override
    public void eliminar() {}
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
