package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOUsuarios;

public class DAO_Usuarios {
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    
    public ArrayList<Object[]> consultar(){
        ArrayList<Object[]> resgistros = new ArrayList<>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [cuent_IdCuente] as Id\n" +
            "      ,[cuent_nombre] as Nombre\n" +
            "      ,[cuent_ap] as AP\n" +
            "      ,[cuent_am] as AM\n" +
            "      ,[cuent_direccion] as Direccion\n" +
            "      ,[cuent_Telefono] as Telefono\n" +
            "      ,[cuent_nomUsuario] as Usuario\n" +
            "      ,[cuent_Status] as Estado\n" +
            "      ,[cuent_FK_tipUsu_idUsuario] as NivelUsuario\n" +
            "      ,[cuent_correoElectronico] as Correo\n" +
            "  FROM [dbo].[tblCuenta]");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int id = resultado.getInt("Id");
                String nombre = resultado.getString("Nombre");
                String AP = resultado.getString("AP");
                String AM = resultado.getString("AM");
                String DR = resultado.getString("Direccion");
                String telefono = resultado.getString("Telefono");
                String usuario = resultado.getString("Usuario");
                boolean status = resultado.getBoolean("Estado");
                int nivelUsuario = resultado.getInt("NivelUsuario");
                String correo = resultado.getString("Correo");
                informacion = new Object[]{id, nombre, AP, AM, DR, telefono, usuario, status, nivelUsuario, correo};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }
    
    public boolean insertar(VOUsuarios usuarios){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO [dbo].[tblCuenta] VALUES (?,?,?,?,?,?,?,(select Convert(DATE, GETDATE())),?,'AWDHuadu12',?,?)");
            declaracionPreparada.setString(1, usuarios.getNombre());
            declaracionPreparada.setString(2, usuarios.getApellidoPaterno());
            declaracionPreparada.setString(3, usuarios.getApellidoMaterno());
            declaracionPreparada.setString(4, usuarios.getDireccion());
            declaracionPreparada.setString(5, usuarios.getTelefono());
            declaracionPreparada.setString(6, usuarios.getUsuario());
            declaracionPreparada.setString(7, usuarios.getContraseña());
            declaracionPreparada.setBoolean(8, usuarios.getEstado());
            declaracionPreparada.setInt(9, usuarios.getNivelUsuario());
            declaracionPreparada.setString(10, usuarios.getCorreoElectonico());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    
    public boolean actualizar(VOUsuarios usuarios){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblCuenta]\n" +
            "   SET [cuent_nombre] = ?\n" +
            "      ,[cuent_ap] = ?\n" +
            "      ,[cuent_am] = ?\n" +
            "      ,[cuent_direccion] = ?\n" +
            "      ,[cuent_Telefono] = ?\n" +
            "      ,[cuent_nomUsuario] = ?\n" +
            "      ,[cuent_Status] = ?\n" +
            "      ,[cuent_FK_tipUsu_idUsuario] = ?\n" +
            "      ,[cuent_correoElectronico] = ?\n" +
            " WHERE cuent_IdCuente = ?");
            declaracionPreparada.setString(1,usuarios.getNombre());
            declaracionPreparada.setString(2,usuarios.getApellidoPaterno());
            declaracionPreparada.setString(3,usuarios.getApellidoMaterno());
            declaracionPreparada.setString(4,usuarios.getDireccion());
            declaracionPreparada.setString(5,usuarios.getTelefono());
            declaracionPreparada.setString(6,usuarios.getUsuario());
            declaracionPreparada.setBoolean(7,usuarios.getEstado());
            declaracionPreparada.setInt(8,usuarios.getNivelUsuario());
            declaracionPreparada.setString(9,usuarios.getCorreoElectonico());
            declaracionPreparada.setString(10,usuarios.getId());
            
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
    
    public boolean eliminar(VOUsuarios usuario){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM [dbo].[tblCuenta]  WHERE cuent_IdCuente = ?");
            declaracionPreparada.setString(1, String.valueOf(usuario.getId()));
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
