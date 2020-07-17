package controlador;


public interface IControlador {
    void iniciar();
    boolean validar();
    void cargarDatosATabla();
    void insertar();
    void actualizar();
    void eliminar();
    void limpiar();
}
