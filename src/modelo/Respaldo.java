package modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Respaldo {
    
    ConexionBaseDatos conexion = ConexionBaseDatos.conexion();
    
    public boolean backup() {
        boolean bandera = false;
        try {
            Runtime app = Runtime.getRuntime();
            app.exec("cmd /c " + System.getProperty("user.dir") + "\\BAT\\BackupCompleto.bat");
            bandera = true;
        } catch (IOException ex) {
            Logger.getLogger(Respaldo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    public boolean restore() {
        boolean bandera = false;
        try {
            Runtime app = Runtime.getRuntime();
            app.exec("cmd /c " + System.getProperty("user.dir") + "\\BAT\\RestoreCompleto.bat");
            bandera = true;
        } catch (IOException ex) {
            Logger.getLogger(Respaldo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
