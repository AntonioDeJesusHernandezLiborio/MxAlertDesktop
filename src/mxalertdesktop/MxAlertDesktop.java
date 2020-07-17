package mxalertdesktop;


import controlador.controlador_IniciarSesion;
import modelo.DAO.DAO_IniciarSesion;
import vista.frmInicioSesion;

public class MxAlertDesktop {

    public static void main(String[] args) {
       /*
        frmInicio view = new frmInicio();
        controlador_Menu controller = new controlador_Menu(view);
        controller.iniciar();
        view.setVisible(true);
        */
       DAO_IniciarSesion model = new DAO_IniciarSesion();
       frmInicioSesion view = new frmInicioSesion();
       controlador_IniciarSesion controller = new controlador_IniciarSesion(view,model);
        controller.iniciar();
        view.setVisible(true);
    }
    
}
