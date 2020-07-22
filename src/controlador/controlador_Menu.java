
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;


public class controlador_Menu implements ActionListener{
    frmInicio inicio;
    frmTipoUsuario formularioTipoUsuario;
    frmMetodoDePago formularioMetodoDePago;
    frmPlanDeUsuario formularioPlan;
    frmUsuarios formularioUsuarios;

    IControlador controlador;
    

    public controlador_Menu(frmInicio inicio){
        this.inicio = inicio;
        this.inicio.btnTipoUsuario.addActionListener(this);
        this.inicio.btnMetodoDePago.addActionListener(this);
        this.inicio.btnPlanUsuario.addActionListener(this);
        this.inicio.btnUsuarios.addActionListener(this);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inicio.btnTipoUsuario){
            abrirTipoUsuario();
        }
        if(e.getSource() == inicio.btnMetodoDePago){
           abrirMetodoDePago();
        }
        if(e.getSource()== inicio.btnPlanUsuario){
           abrirPlaUsuario();
        }
        if(e.getSource() == inicio.btnUsuarios){
            abrirUsuarios();
        }
    }
    
    public void iniciar(){
        inicio.setTitle("Tipo Usuario");
        inicio.setLocationRelativeTo(null);
    }
    
    private void abrirTipoUsuario(){
        if(formularioTipoUsuario==null) formularioTipoUsuario = new frmTipoUsuario();
        inicio.escritorio.add(formularioTipoUsuario);
        controlador = new controlador_TipoUsuario(formularioTipoUsuario);
        formularioTipoUsuario.toFront();
        formularioTipoUsuario.show();
    }
    
    private void abrirMetodoDePago(){
        if(formularioMetodoDePago ==  null) formularioMetodoDePago = new frmMetodoDePago();
        inicio.escritorio.add(formularioMetodoDePago);
        controlador = new controlador_MetodoPago(formularioMetodoDePago);
        formularioMetodoDePago.toFront();
        formularioMetodoDePago.show();
    }
    
    private void abrirPlaUsuario(){
        if(formularioPlan ==  null) formularioPlan = new frmPlanDeUsuario();
        inicio.escritorio.add(formularioPlan);
        controlador = new controlador_Plan(formularioPlan);
        formularioPlan.toFront();
        formularioPlan.show();
    }
    
    private void abrirUsuarios(){
        if(formularioUsuarios == null) formularioUsuarios = new frmUsuarios();
        inicio.escritorio.add(formularioUsuarios);
        controlador = new controlador_Usuarios(formularioUsuarios);
        formularioUsuarios.toFront();
        formularioUsuarios.show();
        
    }
}
