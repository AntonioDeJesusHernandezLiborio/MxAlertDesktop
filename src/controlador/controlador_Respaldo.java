package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Respaldo;
import modelo.VO.VOInicioSesion;
import vista.frmRespaldo;

public class controlador_Respaldo extends mensaje implements ActionListener,IControlador{

    frmRespaldo view;
    Respaldo respaldar;
    
    
    VOInicioSesion sesion;
    public controlador_Respaldo(frmRespaldo respaldo,VOInicioSesion sesion) {
        this.view = respaldo;
        this.sesion = sesion;
        if(respaldar == null) respaldar = new Respaldo();
        
        this.view.btnBackup.addActionListener(this);
        this.view.btnRestore.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.btnBackup){
            if("2".equals(sesion.getTipoUsuario())){
                System.out.print(System.getProperty("user.dir"));
                backup();
            }else mandaMensajeDeTexto("Usted no tiene permisos","Error");
           
        }
        if(e.getSource() == view.btnRestore){
            if("2".equals(sesion.getTipoUsuario())){
                restore();
            }else mandaMensajeDeTexto("Usted no tiene permisos","Error");
           
        }
    }
    public void iniciar() {
        view.setTitle("Respaldo");
        view.setLocation(null);
    }
    
    private boolean backup(){
        return respaldar.backup();
    }
    
    private boolean restore(){
        return respaldar.restore();
    }

    @Override
    public boolean validar() {return true;}

    @Override
    public void cargarDatosATabla() {}

    @Override
    public void insertar() {}

    @Override
    public void actualizar() {}

    @Override
    public void eliminar() {}

    @Override
    public void limpiar() {}

}
