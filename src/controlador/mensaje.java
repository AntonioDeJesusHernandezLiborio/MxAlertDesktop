package controlador;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class mensaje {

    public static void mandaMensajeDeTexto(String informacionDeMensaje, String titulo) {
        JOptionPane.showMessageDialog(null, informacionDeMensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String generarTextoAleatorio() {
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(40, random).toString(32);
        return text;
    }

    public boolean validarCorreo(String correo) {
        boolean bandera= false;
       String correoPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                    "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
       
        Pattern pattern = Pattern.compile(correoPattern);
        String email = correo;
        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                bandera=  true;
            } 
        }
        return bandera;
    }
    
    public boolean validarContraseña(String contraseña){
        boolean bandera= false;
        String contraseñaPatter = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(contraseñaPatter);
        if (contraseña != null) {
            Matcher matcher = pattern.matcher(contraseña);
            if (matcher.matches()) {
                bandera=  true;
            } 
        }
        return bandera;
    }
}
