package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;


public class ConsultaCombo {
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    
    public ResultSet consultarNivelUsuario(){
        ResultSet result = null;
        try {
            PreparedStatement declaracionPreparada;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [tipUsu_idUsuario] as Id,[tipUsu_nombre] as Nombre FROM [dbo].[tblTipoUsuario]");
            result = declaracionPreparada.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  result;
    }
    
    public ResultSet consultarTipoDenuncia(){
        ResultSet result = null;
        try {
            PreparedStatement declaracionPreparada;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT [tipDen_idTipoDenuncia] as Id,[tipDen_nombre] as Nombre FROM [dbo].[tblTipoDenuncia]");
            result = declaracionPreparada.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  result;
    }
}


// nuevoModelo = new ResultSetComboBoxModel(result, "Id", "Nombre");
