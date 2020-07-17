package controlador;

import javax.swing.JOptionPane;

public class mensaje {
    public static void mandaMensajeDeTexto(String informacionDeMensaje, String titulo){
       JOptionPane.showMessageDialog(null, informacionDeMensaje,titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
