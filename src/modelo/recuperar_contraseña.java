package modelo;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.DAO.DAO_Usuarios;
import org.apache.commons.codec.digest.DigestUtils;

public class recuperar_contraseña {
    
    DAO_Usuarios AccesoDatosDelObjetoUsuario;

    public recuperar_contraseña() {
        this.AccesoDatosDelObjetoUsuario = new DAO_Usuarios();
    }
    
    
    public ArrayList<Object[]> cargarDatosATabla() {
        ArrayList<Object[]> resgistros = new ArrayList<>();
        Object[] informacion;
        ArrayList<Object[]> consulta = AccesoDatosDelObjetoUsuario.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : consulta) {
            contenedorDeRegistro = registro;
            String nombre = contenedorDeRegistro[1].toString();
            String AP = contenedorDeRegistro[2].toString();
            String AM = contenedorDeRegistro[3].toString();
            String usuario = contenedorDeRegistro[6].toString();
            String correo =  contenedorDeRegistro[9].toString();
            informacion = new Object[]{nombre, AP, AM,usuario, correo};
            resgistros.add(informacion); 
        }   
        return resgistros;
    }
    
    
    
    public static boolean enviarConGMail(String destinatario, String asunto, String cuerpo) {
        boolean bandera= false;
        try {
           
            String remitente = "soporte.regionalsoft@gmail.com"; 
            
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");  
            props.put("mail.smtp.user", remitente);
            props.put("mail.smtp.clave", "tormenta:11");    
            props.put("mail.smtp.auth", "true");    
            props.put("mail.smtp.starttls.enable", "true"); 
            props.put("mail.smtp.port", "587"); 
            
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "tormenta:11");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            bandera= true;
        } catch (MessagingException ex) {
            Logger.getLogger(recuperar_contraseña.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    
    public boolean reseteoContraseña(String usuario,String contraseña){
        String encriptadoContraseña=DigestUtils.sha1Hex(contraseña);
        return AccesoDatosDelObjetoUsuario.restablecerContraseña(usuario, encriptadoContraseña);
    }
    
    
}
