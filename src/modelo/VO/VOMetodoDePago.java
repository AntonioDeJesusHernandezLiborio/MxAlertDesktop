package modelo.VO;

public class VOMetodoDePago {
    private int id;
    private String nombre;
    
    
    private VOMetodoDePago(String nombre){
        this.nombre = nombre;   
    }
    
    public static VOMetodoDePago Make(String nombre){
        return new VOMetodoDePago(nombre);
    }
    
    public VOMetodoDePago Build(){
        return this;
    }

    public int getId() {
        return id;
    }

    public VOMetodoDePago setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }
}
