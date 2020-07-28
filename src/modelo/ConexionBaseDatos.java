package modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBaseDatos {

    private static ConexionBaseDatos claseConexion;
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static String nombreBaseDeDatos = "dbMXAlert";
    private static String nombreUsuario = "practica";
    private static String claveBaseDeDatos = "123";

    private static String direccion = "jdbc:sqlserver://DESKTOP-P0QI2TJ\\SQLEXPRESS:1433;"
            + "databaseName=" + nombreBaseDeDatos + ";"
            + "user=" + nombreUsuario + ";"
            + "password=" + claveBaseDeDatos + ";";

    private static Connection conexion;

    private ConexionBaseDatos() {
        try {
            Class.forName(driver);
            try {
                conexion = DriverManager.getConnection(direccion);
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized static ConexionBaseDatos conexion() {
        if (claseConexion == null) {
            claseConexion = new ConexionBaseDatos();
        }
        return claseConexion;
    }

    public Connection conectar() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
