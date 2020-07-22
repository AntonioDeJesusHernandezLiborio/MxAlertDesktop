package modelo.VO;


public class VOUsuarios {
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    private String usuario;
    private String contraseña;
    private boolean estado;
    private int nivelUsuario;
    private String correoElectonico;

    public String getCorreoElectonico() {
        return correoElectonico;
    }

    public VOUsuarios setCorreoElectonico(String correoElectonico) {
        this.correoElectonico = correoElectonico;
        return this;
    }
    
    private VOUsuarios(String nombre){
        this.setNombre(nombre);
    }
    
    public static VOUsuarios Make(String nombre){
        return new VOUsuarios(nombre);
    }
    
    public VOUsuarios Build(){
        return this;
    }
    
    public String getId() {
        return id;
    }

    public VOUsuarios setId(String id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public VOUsuarios setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public VOUsuarios setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        return this;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public VOUsuarios setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public VOUsuarios setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public VOUsuarios setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public VOUsuarios setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getContraseña() {
        return contraseña;
    }

    public VOUsuarios setContraseña(String contraseña) {
        this.contraseña = contraseña;
        return this;
    }

    public boolean getEstado() {
        return estado;
    }

    public VOUsuarios setEstado(boolean estado) {
        this.estado = estado;
        return this;
    }

    public int getNivelUsuario() {
        return nivelUsuario;
    }

    public VOUsuarios setNivelUsuario(int nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
        return this;
    }
    
}
