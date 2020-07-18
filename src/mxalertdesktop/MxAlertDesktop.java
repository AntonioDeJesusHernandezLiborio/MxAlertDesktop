package mxalertdesktop;


import controlador.controlador_IniciarSesion;
import modelo.DAO.DAO_IniciarSesion;
import modelo.IServicioIniciarSesion;
import modelo.ProxyServicioIniciarSesion;
import vista.frmInicioSesion;

public class MxAlertDesktop {

    public static void main(String[] args) {
        IServicioIniciarSesion model = new ProxyServicioIniciarSesion();
        frmInicioSesion view = new frmInicioSesion();
        controlador_IniciarSesion controller = new controlador_IniciarSesion(view,model);
        controller.iniciar();
        view.setVisible(true);
    }
    
}
