package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VOPlan;

public class DAO_Plan{
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    

    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> resgistros = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [plan_idPlan] as CLAVE,[plan_nombre] as NOMBRE,[plan_descripcion] AS DESCRIPCION ,[plan_precio] AS PRECIO FROM [dbo].[tblPlan]");
            resultado = declaracionPreparada.executeQuery();
            
            while (resultado.next()) {
                int clave = resultado.getInt("CLAVE");
                String nombre = resultado.getString("NOMBRE");
                String descripcion = resultado.getString("DESCRIPCION");
                double precio = resultado.getFloat("PRECIO");
                informacion = new Object[]{clave,nombre,descripcion,precio};
                resgistros.add(informacion);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Plan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }

    public boolean insertar(VOPlan plan){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO tblPlan([plan_nombre],[plan_descripcion],[plan_precio])  VALUES (?,?,?)");
            declaracionPreparada.setString(1, plan.getNombre());
            declaracionPreparada.setString(2, plan.getDescripcion());
            declaracionPreparada.setString(3, String.valueOf(plan.getPrecio()));
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Plan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean actualizar(VOPlan plan){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblPlan] SET [plan_nombre] = ?, [plan_descripcion] =? ,[plan_precio] = ? WHERE plan_idPlan= ?");
            declaracionPreparada.setString(1, plan.getNombre());
            declaracionPreparada.setString(2, plan.getDescripcion());
            declaracionPreparada.setDouble(3, plan.getPrecio());
            declaracionPreparada.setInt(4,plan.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Plan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }   
 
    public boolean eliminar(VOPlan plan){
        PreparedStatement declaracionPreparada;
        
        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("DELETE FROM [dbo].[tblPlan]  WHERE [plan_idPlan] = ?");
            declaracionPreparada.setInt(1,plan.getId());
            if(!declaracionPreparada.execute()){
                bandera= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Plan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
