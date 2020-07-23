package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOTipoDenuncia;

public class DAO_TipoDenuncia {
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    

    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> resgistros = new ArrayList<>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [tipDen_idTipoDenuncia]  as Clave,[tipDen_nombre] as Nombre FROM [dbo].[tblTipoDenuncia]");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int clave = resultado.getInt("Clave");
                String nombre = resultado.getString("Nombre");
                informacion = new Object[]{clave,nombre};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoDenuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }

    public boolean insertar(VOTipoDenuncia tipoDenuncia){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO [dbo].[tblTipoDenuncia] ([tipDen_nombre])VALUES (?)");
            declaracionPreparada.setString(1, tipoDenuncia.getNombre());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoDenuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean actualizar(VOTipoDenuncia tipoDenuncia){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblTipoDenuncia] SET [tipDen_nombre] = ? WHERE tipDen_idTipoDenuncia = ?");
            declaracionPreparada.setString(1, tipoDenuncia.getNombre());
            declaracionPreparada.setInt(2,tipoDenuncia.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoDenuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
 
    public boolean eliminar(VOTipoDenuncia tipoDenuncia){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM [dbo].[tblTipoDenuncia] WHERE tipDen_idTipoDenuncia = ?");
            declaracionPreparada.setInt(1,tipoDenuncia.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_TipoDenuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
