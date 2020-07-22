package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOTipoUsuario;




public class DAO_TipoUsuario {
   
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
   
    public ArrayList<Object[]> consultar(){
        ArrayList<Object[]> resgistros = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT tipUsu_idUsuario as 'CLAVE' ,tipUsu_nombre as 'NOMBRE' FROM tblTipoUsuario");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int clave = resultado.getInt("CLAVE");
                String nombre = resultado.getString("NOMBRE");
                informacion = new Object[]{clave,nombre};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }
    
    public boolean insertar(VOTipoUsuario tipoUsuario){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO tblTipoUsuario  (tipUsu_nombre)  VALUES  (?)");
            declaracionPreparada.setString(1, tipoUsuario.getNombre());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    public boolean actualizar(VOTipoUsuario tipoUsuario){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblTipoUsuario] SET [tipUsu_nombre] = ? WHERE tipUsu_idUsuario = ?");
            declaracionPreparada.setString(2, String.valueOf(tipoUsuario.getId()));
            declaracionPreparada.setString(1, tipoUsuario.getNombre());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
    public boolean eliminar(VOTipoUsuario tipoUsuario){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM tblTipoUsuario WHERE tipUsu_idUsuario = ?");
            declaracionPreparada.setString(1, String.valueOf(tipoUsuario.getId()));
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
