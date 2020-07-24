package modelo;

import controlador.mensaje;
import modelo.DAO.DAO_IniciarSesion;
import modelo.VO.VOInicioSesion;

public class ProxyServicioIniciarSesion extends mensaje implements IServicioIniciarSesion {

    private IServicioIniciarSesion servicio;

    @Override
    public boolean consultar(VOInicioSesion DatosSesion) {
        if (!"".equals(DatosSesion.getUsuario()) && !"".equals(DatosSesion.getContraseña())) {
            if (ObtenerServicio().consultar(DatosSesion)) {
                return true;
            } else {
                mandaMensajeDeTexto("Usuario no existe/no disponible", "Advertencia");
                return false;
            }
        } else {
            mandaMensajeDeTexto("Llene los campos", "Advertencia");
            return false;
        }
    }

    @Override
    public boolean verificarContraseña(String contraseña) {
        if (!"".equals(contraseña)) {
            if (ObtenerServicio().verificarContraseña(contraseña)) {
                return true;
            } else {
                mandaMensajeDeTexto("Contraseña incorrecta", ":(");
                return false;
            }
        } else {
            return false;
        }
    }

    private IServicioIniciarSesion ObtenerServicio() {
        if (servicio == null) {
            servicio = new DAO_IniciarSesion();
        }
        return servicio;
    }

    @Override
    public VOInicioSesion sesion() {
      return ObtenerServicio().sesion();
    }
    
    

}
