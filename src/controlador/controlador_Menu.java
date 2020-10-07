
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.VO.VOInicioSesion;
import vista.*;


public class controlador_Menu implements ActionListener{
    frmInicio inicio;
    frmTipoUsuario formularioTipoUsuario;
    frmMetodoDePago formularioMetodoDePago;
    frmPlanDeUsuario formularioPlan;
    frmUsuarios formularioUsuarios;
    frmTipoDenuncia formularioTipoDenuncia;
    frmProtocolos formularioProtocolos;
    frmRecuperarContraseña formularioContraseña;
    frmDenuncia formularioDenuncia;
    frmRespaldo formularioRespaldo;

    //PRUEBA DE CONTRO DE GIT Y GITHUB
    
    
    IControlador controlador;
    
    VOInicioSesion sesion;

    public controlador_Menu(frmInicio inicio, VOInicioSesion sesion){
        this.inicio = inicio;
        this.sesion = sesion;
        this.inicio.btnTipoUsuario.addActionListener(this);
        this.inicio.btnMetodoDePago.addActionListener(this);
        this.inicio.btnPlanUsuario.addActionListener(this);
        this.inicio.btnUsuarios.addActionListener(this);
        this.inicio.btnTipoDenuncia.addActionListener(this);
        this.inicio.btnProtocolo.addActionListener(this);
        this.inicio.btnRestablecerPassword.addActionListener(this);
        this.inicio.btnDenuncias.addActionListener(this);
        this.inicio.btnRespaldo.addActionListener(this);
        
        inicio.escritorio.setBorder(new ImagenFondo());
        inicio.setExtendedState(inicio.MAXIMIZED_BOTH);
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
        if(e.getSource() == inicio.btnTipoDenuncia){
            abrirTipoDenuncia();
        }
        if(e.getSource() == inicio.btnProtocolo){
            abrirProtocolo();
        }
        if(e.getSource() == inicio.btnRestablecerPassword){
            abrirRecuperarContraseña();
        }
        if(e.getSource() == inicio.btnDenuncias){
            abrirRecuperarDenuncia();
        }
        if(e.getSource() == inicio.btnRespaldo){
            abrirRespaldo();
        }
    }
    
    public void iniciar(){
        inicio.setTitle("Menu");
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
    private void abrirTipoDenuncia(){
        if(formularioTipoDenuncia==null) formularioTipoDenuncia = new frmTipoDenuncia();
        inicio.escritorio.add(formularioTipoDenuncia);
        controlador = new controlador_TipoDenuncia(formularioTipoDenuncia);
        formularioTipoDenuncia.toFront();
        formularioTipoDenuncia.show();
    }
    
    private void abrirProtocolo(){
        if(formularioProtocolos ==  null) formularioProtocolos = new frmProtocolos();
        inicio.escritorio.add(formularioProtocolos);
        controlador = new controlador_Protocolo(formularioProtocolos);
        formularioProtocolos.toFront();
        formularioProtocolos.show();
    }
    private void abrirRecuperarContraseña(){
        if(formularioContraseña ==  null) formularioContraseña = new frmRecuperarContraseña();
        inicio.escritorio.add(formularioContraseña);
        controlador = new controlador_recuperarContraseña(formularioContraseña);
        formularioContraseña.toFront();
        formularioContraseña.show();
    }
    
    private void abrirRecuperarDenuncia(){
        if(formularioDenuncia ==  null) formularioDenuncia = new frmDenuncia();
        inicio.escritorio.add(formularioDenuncia);
        controlador = new controlador_Denuncias(formularioDenuncia, sesion);
        formularioDenuncia.toFront();
        formularioDenuncia.show();
    }
    
    private void abrirRespaldo(){
        if(formularioRespaldo ==  null) formularioRespaldo = new frmRespaldo();
        inicio.escritorio.add(formularioRespaldo);
        controlador = new controlador_Respaldo(formularioRespaldo, sesion);
        formularioRespaldo.toFront();
        formularioRespaldo.show();
    }
}
