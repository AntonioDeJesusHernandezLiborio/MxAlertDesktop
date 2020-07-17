package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOMetodoDePago;

public class DAO_MetodoPago {
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> resgistros = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [metPag_idMetodoPago] as CLAVE ,[metPag_nombre] as NOMBRE FROM [dbo].[tblMetodoPago]");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int clave = resultado.getInt("CLAVE");
                String nombre = resultado.getString("NOMBRE");
                informacion = new Object[]{clave,nombre};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_MetodoPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }
    public boolean insertar(VOMetodoDePago metodoPago){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO [dbo].[tblMetodoPago]([metPag_nombre])VALUES (?)");
            declaracionPreparada.setString(1, metodoPago.getNombre());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_MetodoPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    public boolean actualizar(VOMetodoDePago metodoPago){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblMetodoPago]  SET [metPag_nombre] = ? WHERE metPag_idMetodoPago=?");
            declaracionPreparada.setString(2, String.valueOf(metodoPago.getId()));
            declaracionPreparada.setString(1, metodoPago.getNombre());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_MetodoPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
    public boolean eliminar(VOMetodoDePago metodoPago){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM [dbo].[tblMetodoPago] WHERE metPag_idMetodoPago = ?");
            declaracionPreparada.setString(1, String.valueOf(metodoPago.getId()));
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_MetodoPago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
