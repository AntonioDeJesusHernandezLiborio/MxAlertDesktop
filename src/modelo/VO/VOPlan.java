package modelo.VO;

public class VOPlan {
    
   
    private String nombre;
    
    private int id; 
    private String descripcion;
    private double precio;

    
    private VOPlan(String nombre){
        this.nombre = nombre;   
    }
    
    public static VOPlan Make(String nombre){
        return new VOPlan(nombre);
    }
    
    public VOPlan Build(){
        return this;
    }
    
    public int getId() {
        return id;
    }

    public VOPlan setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public VOPlan setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public VOPlan setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public double getPrecio() {
        return precio;
    }

    public VOPlan setPrecio(double precio) {
        this.precio = precio;
        return this;
    }
}
