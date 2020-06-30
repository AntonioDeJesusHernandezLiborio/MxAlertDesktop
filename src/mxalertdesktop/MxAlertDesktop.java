package mxalertdesktop;

import controlador.controlador_Menu;
import vista.frmInicio;

public class MxAlertDesktop {

    public static void main(String[] args) {
        frmInicio view = new frmInicio();
        controlador_Menu controller = new controlador_Menu(view);
        controller.iniciar();
        view.setVisible(true);
    }
    
}
