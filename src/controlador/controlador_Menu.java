
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.*;


public class controlador_Menu implements ActionListener{
    frmInicio inicio;
    frmTipoUsuario formularioTipoUsuario;
    frmMetodoDePago formularioMetodoDePago;
    frmPlanDeUsuario formularioPlan;

    IControlador controlador;
    

    public controlador_Menu(frmInicio inicio){
        this.inicio = inicio;
        this.inicio.btnTipoUsuario.addActionListener(this);
        this.inicio.btnMetodoDePago.addActionListener(this);
        this.inicio.btnPlanUsuario.addActionListener(this);
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
        formularioMetodoDePago.toBack();
        formularioMetodoDePago.show();
    }
    
    private void abrirPlaUsuario(){
        if(formularioPlan ==  null) formularioPlan = new frmPlanDeUsuario();
        inicio.escritorio.add(formularioPlan);
        controlador = new controlador_Plan(formularioPlan);
        formularioPlan.toBack();
        formularioPlan.show();
    }
}
