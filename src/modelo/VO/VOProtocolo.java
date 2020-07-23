
package modelo.VO;

public class VOProtocolo {
    
    private int id;
    private String nombre;
    private int idTipoDEnuncia;
    
    private VOProtocolo(String nombre){
        this.nombre = nombre;   
    }
    
    public static VOProtocolo Make(String nombre){
        return new VOProtocolo(nombre);
    }
    
    public VOProtocolo Build(){
        return this;
    }
    public int getIdTipoDEnuncia() {
        return idTipoDEnuncia;
    }

    public VOProtocolo setIdTipoDEnuncia(int idTipoDEnuncia) {
        this.idTipoDEnuncia = idTipoDEnuncia;
        return this;
    }
    
    public int getId() {
        return id;
    }

    public VOProtocolo setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public VOProtocolo setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
