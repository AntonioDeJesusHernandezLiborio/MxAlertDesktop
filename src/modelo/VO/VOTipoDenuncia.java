package modelo.VO;

public class VOTipoDenuncia {
    private int id;
    private String nombre;
    
    private VOTipoDenuncia(String nombre){
        this.nombre = nombre;   
    }
    
    public static VOTipoDenuncia Make(String nombre){
        return new VOTipoDenuncia(nombre);
    }
    
    public VOTipoDenuncia Build(){
        return this;
    }
    
    
    public int getId() {
        return id;
    }

    public VOTipoDenuncia setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public VOTipoDenuncia setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
