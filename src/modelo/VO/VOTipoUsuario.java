package modelo.VO;

public class VOTipoUsuario {
    private int id;
    private String nombre;
    
    private VOTipoUsuario(String nombre){
        this.nombre = nombre;   
    }
    
    public static VOTipoUsuario Make(String nombre){
        return new VOTipoUsuario(nombre);
    }
    
    public VOTipoUsuario Build(){
        return this;
    }
    
    
    public int getId() {
        return id;
    }

    public VOTipoUsuario setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public VOTipoUsuario setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    
}
