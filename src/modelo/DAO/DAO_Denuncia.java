package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ConexionBaseDatos;
import modelo.VO.VODenuncia;

public class DAO_Denuncia {

    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();

    public ArrayList<Object[]> consultar() {

        ArrayList<Object[]> resgistros = new ArrayList<Object[]>();
        Object[] informacion;

        try {
            PreparedStatement declaracionPreparada;
            ResultSet resultado = null;
            declaracionPreparada = conexion.conectar().prepareStatement("SELECT "
                    + "       [den_idDenuncia] as Clave\n"
                    + "      ,[den_descripcion] as Descripcion\n"
                    + "      ,[den_fecha] as Fecha\n"
                    + "      ,[den_hora] as Hora\n"
                    + "      ,[den_foto] as Foto\n"
                    + "      ,[den_estado] as Estado\n"
                    + "      ,[den_municipio] as Municipio\n"
                    + "      ,[den_colonia] as Colonia\n"
                    + "      ,[den_calle] as Calle\n"
                    + "      ,[den_status] as status\n"
                    + "      ,ISNULL((Select cuent_nomUsuario from tblCuenta where [den_cuenDesactivado] = cuent_IdCuente), 'A/P') Desactivo"
                    + "      ,(Select cuent_nomUsuario from tblCuenta where den_FK_cuent_IdCuente = cuent_IdCuente) as Creacion  \n"
                    + "      ,[den_FK_tipDen_idTipoDenuncia] as idTipoDenuncia\n"
                    + "  FROM [dbo].[tblDenuncia]");
            resultado = declaracionPreparada.executeQuery();

            while (resultado.next()) {
                int clave = resultado.getInt("Clave");
                String Descripcion = resultado.getString("Descripcion");
                String Fecha = resultado.getString("Fecha");
                String Hora = resultado.getString("Hora");
                String Foto = resultado.getString("Foto");
                String Estado = resultado.getString("Estado");
                String Municipio = resultado.getString("Municipio");
                String Colonia = resultado.getString("Colonia");
                String Calle = resultado.getString("Calle");
                boolean status = resultado.getBoolean("status");
                String Desactivo = resultado.getString("Desactivo");
                String Creacion = resultado.getString("Creacion");
                int idTipoDenuncia = resultado.getInt("idTipoDenuncia");
                informacion = new Object[]{clave, Descripcion, Fecha, Hora, Foto, Estado, Municipio, Colonia, Calle, status, Desactivo, Creacion, idTipoDenuncia};
                resgistros.add(informacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Denuncia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resgistros;
    }

    public boolean insertar(VODenuncia denuncia) {
        PreparedStatement declaracionPreparada;

        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("INSERT INTO [dbo].[tblDenuncia]\n"
                    + "           ([den_descripcion]\n"
                    + "           ,[den_fecha]\n"
                    + "           ,[den_hora]\n"
                    + "           ,[den_foto]\n"
                    + "           ,[den_estado]\n"
                    + "           ,[den_municipio]\n"
                    + "           ,[den_colonia]\n"
                    + "           ,[den_calle]\n"
                    + "           ,[den_status]\n"
                    + "           ,[den_FK_cuent_IdCuente]\n"
                    + "           ,[den_FK_tipDen_idTipoDenuncia])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,(select Convert(DATE, GETDATE()))\n"
                    + "           ,(select convert(char(8), getdate(), 108) as [hh:mm:ss])\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,1\n"
                    + "           ,?\n"
                    + "           ,?)");
            declaracionPreparada.setString(1, denuncia.getDescripcion());
            declaracionPreparada.setString(2, denuncia.getFoto());
            declaracionPreparada.setString(3, denuncia.getEstado());
            declaracionPreparada.setString(4, denuncia.getMunicipio());
            declaracionPreparada.setString(5, denuncia.getColonia());
            declaracionPreparada.setString(6, denuncia.getCalle());
            declaracionPreparada.setInt(7, denuncia.getCuentaCracion());
            declaracionPreparada.setInt(8, denuncia.getTipoDenuncia());
            if (!declaracionPreparada.execute()) {
                bandera = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean actualizar(VODenuncia denuncia) {
        PreparedStatement declaracionPreparada;

        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblDenuncia]\n"
                    + "   SET [den_descripcion] = ?\n"
                    + "      ,[den_foto] = ?\n"
                    + "      ,[den_estado] = ?\n"
                    + "      ,[den_municipio] = ?\n"
                    + "      ,[den_colonia] = ?\n"
                    + "      ,[den_calle] =?\n"
                    + "      ,[den_FK_tipDen_idTipoDenuncia] = ?\n"
                    + " WHERE [den_idDenuncia] = ?");
            declaracionPreparada.setString(1, String.valueOf(denuncia.getDescripcion()));
            declaracionPreparada.setString(2, String.valueOf(denuncia.getFoto()));
            declaracionPreparada.setString(3, String.valueOf(denuncia.getEstado()));
            declaracionPreparada.setString(4, String.valueOf(denuncia.getMunicipio()));
            declaracionPreparada.setString(5, String.valueOf(denuncia.getColonia()));
            declaracionPreparada.setString(6, String.valueOf(denuncia.getCalle()));
            declaracionPreparada.setString(7, String.valueOf(denuncia.getTipoDenuncia()));
            declaracionPreparada.setString(8, String.valueOf(denuncia.getId()));

            if (!declaracionPreparada.execute()) {
                bandera = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean eliminar(VODenuncia denuncia) {
        PreparedStatement declaracionPreparada;

        boolean bandera = false;
        try {
            declaracionPreparada = conexion.conectar().prepareStatement("UPDATE [dbo].[tblDenuncia] SET [den_status] = 0, [den_cuenDesactivado] = ? WHERE [den_idDenuncia] = ?");
            declaracionPreparada.setString(1, String.valueOf(denuncia.getCuentaDesactivo()));
            declaracionPreparada.setString(2, String.valueOf(denuncia.getId()));
            if (!declaracionPreparada.execute()) {
                bandera = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
