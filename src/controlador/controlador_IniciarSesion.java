package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.IServicioIniciarSesion;
import modelo.VO.VOInicioSesion;
import vista.frmInicio;
import vista.frmInicioSesion;

public class controlador_IniciarSesion  implements ActionListener{
    
    frmInicioSesion view;
    IServicioIniciarSesion AccesoDatosDelObjetoInicioSesion;
    VOInicioSesion valoresDelObjetoInicioSesion;
   
        
    public void iniciar(){
        view.setTitle("Inicio Sesion");
        view.setLocale(null);
    }
    public controlador_IniciarSesion(frmInicioSesion view, IServicioIniciarSesion model){
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
}
