package modelo;

import modelo.VO.VOInicioSesion;

public interface IServicioIniciarSesion {
    boolean consultar(VOInicioSesion DatosSesion);
    boolean verificarContraseña(String contraseña);
    VOInicioSesion sesion();
}
