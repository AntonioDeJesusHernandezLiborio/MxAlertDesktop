package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.IServicioIniciarSesion;
import modelo.VO.VOInicioSesion;
import modelo.recuperar_contraseña;
import vista.frmInicio;
import vista.frmInicioSesion;
import vista.frmRestablecerContraseña;

public class controlador_IniciarSesion extends mensaje implements ActionListener{
    
    frmInicioSesion view;
    IServicioIniciarSesion AccesoDatosDelObjetoInicioSesion;
    VOInicioSesion valoresDelObjetoInicioSesion;
   
    frmRestablecerContraseña viewRestablecer;
    recuperar_contraseña recuperar;
    String nuevaContraseña;
        
        
    public void iniciar(){
        view.setTitle("Inicio Sesion");
        view.setLocationRelativeTo(null);
    }
    public controlador_IniciarSesion(frmInicioSesion view, IServicioIniciarSesion model){
        this.view = view;
        if(recuperar == null) recuperar = new recuperar_contraseña();
        if(viewRestablecer ==  null) viewRestablecer = new frmRestablecerContraseña();
        this.AccesoDatosDelObjetoInicioSesion = model;
        iniciar();
        this.view.btnIniciarSesion.addActionListener(this);  
        this.view.btnRecuperarContraseña.addActionListener(this);
        
        this.viewRestablecer.btnRestablecer.addActionListener(this);
        this.viewRestablecer.btnCancelar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnIniciarSesion){
            procesoDeVerificacion();
        }
        if(e.getSource() == view.btnRecuperarContraseña){
            abrirVentanaRestablecer();
        }
        if(e.getSource() == viewRestablecer.btnRestablecer){
             if(validar()){
                if(validarCorreo()){
                   if(enviarCorreo()) viewRestablecer.lblRespuesta.setText("Se ha enviado un correo electronico a su cuenta");
                   else viewRestablecer.lblRespuesta.setText("Ocurrio un error, compruebe su conexion a internet");
                }else viewRestablecer.lblRespuesta.setText("Correo electronico invalido");
            }else viewRestablecer.lblRespuesta.setText("Ingrese usuario");
        }
        if(e.getSource() == viewRestablecer.btnCancelar){
            viewRestablecer.setVisible(false);
            viewRestablecer.dispose();
            view.setTitle("Restablecer contraseña");
        }
    }
    
    private void procesoDeVerificacion(){
        if(consultaDatosInicioSesion()){
            if(verificarContraseña()){
               abrirMenu();
            }
        }
    }
    
    private boolean consultaDatosInicioSesion(){
        valoresDelObjetoInicioSesion = VOInicioSesion.Make(view.txtUsuario.getText()).setContraseña(view.txtContraseña.getText()).Build();
        return AccesoDatosDelObjetoInicioSesion.consultar(valoresDelObjetoInicioSesion);
    }
    
    private boolean verificarContraseña(){
       return AccesoDatosDelObjetoInicioSesion.verificarContraseña(view.txtContraseña.getText());
    } 
    
    private void abrirMenu(){
        VOInicioSesion sesionActual = AccesoDatosDelObjetoInicioSesion.sesion();
        frmInicio viewMenu = new frmInicio();
        controlador_Menu controller = new controlador_Menu(viewMenu, sesionActual);
        controller.iniciar();
        viewMenu.setVisible(true);
        view.dispose();
    }
    
    private void abrirVentanaRestablecer(){
        viewRestablecer.show();
        viewRestablecer.setLocationRelativeTo(null);
    }
    
    public boolean enviarCorreo(){
        this.nuevaContraseña = generarTextoAleatorio();
        if(recuperar.reseteoContraseña(view.txtUsuario.getText(), nuevaContraseña)){
            String destinatario =  viewRestablecer.txtCorreo.getText();
            String asunto = "Restablecer contraseña";
            String cuerpo = "Su nueva contraseña de su cuenta de RegionalSoft es: " + this.nuevaContraseña;
            return recuperar.enviarConGMail(destinatario, asunto, cuerpo);
        }else return false;
    }
    
    public boolean validar() {
        return !"".equals(viewRestablecer.txtCorreo.getText());
    }
    
    public boolean validarCorreo(){
        return validarCorreo(viewRestablecer.txtCorreo.getText());
    }
    
}
