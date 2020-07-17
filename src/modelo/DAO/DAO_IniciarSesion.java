package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOInicioSesion;
import org.apache.commons.codec.digest.DigestUtils;

public class DAO_IniciarSesion {
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    VOInicioSesion sesion;
    
    public void consultar(VOInicioSesion DatosSesion){
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    public boolean verificarContraseña(String contraseña){
        String textoEncriptadoConSHA=DigestUtils.sha1Hex(contraseña); 
        if(textoEncriptadoConSHA.equals(sesion.getContraseñaEncriptada()))return true;
        else return false;
    }
    
    public boolean existeUsuario(){
        if(sesion.getUsuario().equals("")) return false;
        else return true;
    }
    
}
