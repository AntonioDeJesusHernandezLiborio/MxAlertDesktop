package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.IServicioIniciarSesion;
import modelo.VO.VOInicioSesion;
import org.apache.commons.codec.digest.DigestUtils;

public class DAO_IniciarSesion implements IServicioIniciarSesion{
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    VOInicioSesion sesion;
    
    @Override
    public boolean consultar(VOInicioSesion DatosSesion){
        boolean bandera = false;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("Select CONCAT(cuent_nombre,' ',cuent_ap,' ',cuent_am) as Nombre, cuent_IdCuente as Id, cuent_FK_tipUsu_idUsuario as TipoUsuario, cuent_password as pw From tblCuenta Where cuent_nomUsuario=? and cuent_Status=1");
            declaracionPreparada.setString(1, DatosSesion.getUsuario());
            resultado = declaracionPreparada.executeQuery();
            if(resultado.next()) {
                sesion = VOInicioSesion.Make(resultado.getString("Nombre"))
                    .setId(resultado.getString("Id"))
                    .setTipoUsuario(resultado.getString("TipoUsuario"))
                    .setContraseñaEncriptada(resultado.getString("pw"))
                    .Build();
                bandera = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    
    @Override
    public boolean verificarContraseña(String contraseña){
        String textoEncriptadoConSHA=DigestUtils.sha1Hex(contraseña); 
        return textoEncriptadoConSHA.equals(sesion.getContraseñaEncriptada());
    }
    
    public VOInicioSesion sesion(){
        return sesion;
    }
}
