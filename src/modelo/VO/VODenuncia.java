package modelo.VO;

public class VODenuncia {

    int id;
    String descripcion;
    String fecha;
    String hora;
    String foto;
    String estado;
    String municipio;
    String colonia;
    String calle;
    boolean status;
    int cuentaDesactivo;
    int cuentaCracion;
    int tipoDenuncia;

    private VODenuncia(String descripcion) {
        this.descripcion = descripcion;
    }

    public static VODenuncia Make(String descripcion) {
        return new VODenuncia(descripcion);
    }

    public VODenuncia Build() {
        return this;
    }

    public VODenuncia setId(int id) {
        this.id = id;
        return this;
    }

    public VODenuncia setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public VODenuncia setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }

    public VODenuncia setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public VODenuncia setFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public VODenuncia setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public VODenuncia setMunicipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public VODenuncia setColonia(String colonia) {
        this.colonia = colonia;
        return this;
    }

    public VODenuncia setCalle(String calle) {
        this.calle = calle;
        return this;
    }

    public VODenuncia setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public VODenuncia setCuentaDesactivo(int cuentaDesactivo) {
        this.cuentaDesactivo = cuentaDesactivo;
        return this;
    }

    public VODenuncia setCuentaCracion(int cuentaCracion) {
        this.cuentaCracion = cuentaCracion;
        return this;
    }

    public VODenuncia setTipoDenuncia(int tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getFoto() {
        return foto;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCalle() {
        return calle;
    }

    public boolean isStatus() {
        return status;
    }

    public int getCuentaDesactivo() {
        return cuentaDesactivo;
    }

    public int getCuentaCracion() {
        return cuentaCracion;
    }

    public int getTipoDenuncia() {
        return tipoDenuncia;
    }
    
}
