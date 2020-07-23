package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOProtocolo;


public class DAO_Protocolo {
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    

    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> resgistros = new ArrayList<>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [protoc_idProtocolo] as Id,[protoc_protocolo] as Protocolo ,[protoc_FK_tipDen_idTipoDenuncia] as IdDenuncia FROM [dbo].[tblProtocolo]");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int clave = resultado.getInt("Id");
                String nombre = resultado.getString("Protocolo");
                int idDenuncia = resultado.getInt("IdDenuncia");
                informacion = new Object[]{clave,nombre,idDenuncia};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }

    public boolean insertar(VOProtocolo protocolo){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO [dbo].[tblProtocolo] VALUES (?,?)");
            declaracionPreparada.setString(1, protocolo.getNombre());
            declaracionPreparada.setInt(2,protocolo.getIdTipoDEnuncia());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean actualizar(VOProtocolo protocolo){
        PreparedStatement declaracionPreparada;
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblProtocolo] SET [protoc_protocolo] = ? ,[protoc_FK_tipDen_idTipoDenuncia] = ? WHERE protoc_idProtocolo = ?");
            declaracionPreparada.setString(1, protocolo.getNombre());
            declaracionPreparada.setInt(2, protocolo.getIdTipoDEnuncia());
            declaracionPreparada.setInt(3,protocolo.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
 
    public boolean eliminar(VOProtocolo protocolo){
        PreparedStatement declaracionPreparada;
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM [dbo].[tblProtocolo]  WHERE protoc_idProtocolo = ?");
            declaracionPreparada.setInt(1,protocolo.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Protocolo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
