package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.DAO.DAO_IniciarSesion;
import modelo.VO.VOInicioSesion;
import vista.frmInicio;
import vista.frmInicioSesion;

public class controlador_IniciarSesion extends mensaje implements ActionListener{
    
    frmInicioSesion view;
    DAO_IniciarSesion AccesoDatosDelObjetoInicioSesion;
    VOInicioSesion valoresDelObjetoInicioSesion;
   
        
    public void iniciar(){
        view.setTitle("Inicio Sesion");
        view.setLocale(null);
    }
    public controlador_IniciarSesion(frmInicioSesion view, DAO_IniciarSesion model){
        this.view = view;
        this.AccesoDatosDelObjetoInicioSesion = model;
        iniciar();
        this.view.btnIniciarSesion.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnIniciarSesion){
            procesoDeVerificacion();
        }
    }
    
    private void consultaDatosInicioSesion(){
        valoresDelObjetoInicioSesion = VOInicioSesion.Make(view.txtUsuario.getText())
                .setContraseña(view.txtContraseña.getText()).Build();
        AccesoDatosDelObjetoInicioSesion.consultar(valoresDelObjetoInicioSesion);
    } 
    
    private boolean exiteUsuario(){
       if(AccesoDatosDelObjetoInicioSesion.existeUsuario()){
           return true;
       }else{
           return false;
       }
    } 
    private boolean verificarContraseña(){
       if(AccesoDatosDelObjetoInicioSesion.verificarContraseña(view.txtContraseña.getText())){
           return true;
       }else{
           return false;
       }
    } 
    private void procesoDeVerificacion(){
        if(validar()){
            consultaDatosInicioSesion();
            if(exiteUsuario()){
                if(verificarContraseña()){
                   abrirMenu();
                }else mandaMensajeDeTexto("Contraseña incorrecta",":(");   
            }else mandaMensajeDeTexto("Usuario no existe/no disponible","Advertencia");
        }else mandaMensajeDeTexto("Llene los campos","Advertencia");
    }
    
    private void abrirMenu(){
        frmInicio viewMenu = new frmInicio();
        controlador_Menu controller = new controlador_Menu(viewMenu);
        controller.iniciar();
        viewMenu.setVisible(true);
        view.dispose();
    }
    private boolean validar(){
        if(view.txtUsuario.getText().equals("") && view.txtContraseña.getText().equals("")){
            return false;
        }else{
            return true;
        }
    }
}
