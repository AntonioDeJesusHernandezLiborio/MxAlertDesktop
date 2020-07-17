package modelo.VO;

public class VOInicioSesion {
    private String id;
    private String usuario;
    private String contraseña;
    private String contraseñaEncriptada;
    private String tipoUsuario;

    public String getId() {
        return id;
    }

    public VOInicioSesion setId(String id) {
        this.id = id;
        return this;
    } 
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public VOInicioSesion setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }

    public String getContraseñaEncriptada() {
        return contraseñaEncriptada;
    }

    public VOInicioSesion setContraseñaEncriptada(String contraseñaEncriptada) {
        this.contraseñaEncriptada = contraseñaEncriptada;
        return this;
    }

    private VOInicioSesion(String usuario){
        this.usuario = usuario;
    }
    public static VOInicioSesion Make(String usuario){
        return new VOInicioSesion(usuario);
    }
    
    public VOInicioSesion Build(){
        return this;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public VOInicioSesion setContraseña(String contraseña) {
        this.contraseña = contraseña;
        return this;
    }
    
    
}
