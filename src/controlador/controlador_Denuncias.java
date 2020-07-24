package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultaCombo;
import modelo.DAO.DAO_Denuncia;
import modelo.Imagen;
import modelo.ResultSetComboBoxModel;
import modelo.ResultSetComboBoxModelObject;
import modelo.VO.VODenuncia;
import modelo.VO.VOInicioSesion;
import vista.frmDenuncia;

public class controlador_Denuncias extends mensaje implements ActionListener, IControlador, MouseListener {

    frmDenuncia view;
    DAO_Denuncia AccesoDatosDelObjetoDenuncia;
    VODenuncia ValoresObjetoDenuncia;
    VOInicioSesion sesion;

    String destino = System.getProperty("user.dir") + "\\imgDenuncias\\";
    String ruta = "";
    String nombreArchivo = "";

    public controlador_Denuncias(frmDenuncia view, VOInicioSesion sesion) {
        this.view = view;
        this.sesion = sesion;

        if (AccesoDatosDelObjetoDenuncia == null) {
            AccesoDatosDelObjetoDenuncia = new DAO_Denuncia();
        }

        view.btnSeleccionarImagen.addActionListener(this);
        view.btnAgregar.addActionListener(this);
        view.btnModificar.addActionListener(this);
        view.tablaDenuncias.addMouseListener(this);
        view.btnLimpiar.addActionListener(this);
        view.btnDesactivar.addActionListener(this);

        cargarDatosATabla();
        cargarComboTipoDenuncia();
        ocultarColumnas();

        this.view.txtId.setVisible(false);
        this.view.lblId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnSeleccionarImagen) {
            seleccionaImagen();
        }
        if (e.getSource() == view.btnAgregar) {
            insertar();
        }
        if (e.getSource() == view.btnModificar) {
            actualizar();
        }
        if (e.getSource() == view.btnLimpiar) {
            limpiar();
        }
        if(e.getSource() == view.btnDesactivar){
            eliminar();
        }
    }

    private void seleccionaImagen() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG,PNG", "jpg", "png");
        fc.setFileFilter(filtro);

        int seleccion = fc.showOpenDialog(view);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            ruta = fichero.getAbsolutePath();
            nombreArchivo = fichero.getName();
            mostraImagen(ruta);
        }
    }

    private void mostraImagen(String ruta) {
        view.JpanelImagen.removeAll();
        int x = view.JpanelImagen.getWidth();
        int y = view.JpanelImagen.getHeight();
        Imagen img = new Imagen(x, y, ruta);
        view.JpanelImagen.add(img);
        view.JpanelImagen.repaint();

    }

    public boolean copiarArchivos(String origen, String destino) {
        File folder = new File(destino);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        boolean bandera = false;
        try {
            Path de = Paths.get(origen);
            Path para = Paths.get(destino);

            CopyOption[] opciones = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(de, para, opciones);
            bandera = true;
        } catch (IOException ex) {
            Logger.getLogger(controlador_Denuncias.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }

    @Override
    public void iniciar() {
        view.setTitle("Denuncia");
        view.setLocale(null);
    }

    @Override
    public boolean validar() {
        return true;
    }

    @Override
    public void cargarDatosATabla() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) view.tablaDenuncias.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = AccesoDatosDelObjetoDenuncia.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;

            int clave = Integer.parseInt(contenedorDeRegistro[0].toString());
            String Descripcion = contenedorDeRegistro[1].toString();
            String Fecha = contenedorDeRegistro[2].toString();
            String Hora = contenedorDeRegistro[3].toString();
            String Foto = contenedorDeRegistro[4].toString();
            String Estado = contenedorDeRegistro[5].toString();
            String Municipio = contenedorDeRegistro[6].toString();
            String Colonia = contenedorDeRegistro[7].toString();
            String Calle = contenedorDeRegistro[8].toString();
            boolean status = Boolean.parseBoolean(contenedorDeRegistro[9].toString());
            String Desactivo = contenedorDeRegistro[10].toString();
            String Creacion = contenedorDeRegistro[11].toString();
            int idTipoDenuncia = Integer.parseInt(contenedorDeRegistro[12].toString());
            modeloDeTabla.addRow(new Object[]{clave, Descripcion, Fecha, Hora, Foto, Estado, Municipio, Colonia, Calle, status, Desactivo, Creacion, idTipoDenuncia});
        }
        view.tablaDenuncias.setModel(modeloDeTabla);
    }

    public void cargarComboTipoDenuncia() {
        try {
            ConsultaCombo combo = new ConsultaCombo();
            view.cmbTipoDenuncia.setModel(new ResultSetComboBoxModel(combo.consultarTipoDenuncia(), "Id", "Nombre"));
            view.cmbTipoDenuncia.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(controlador_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insertar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbTipoDenuncia.getModel().getSelectedItem();
        ValoresObjetoDenuncia = VODenuncia.Make(view.txtDescripcion.getText())
                .setFoto(nombreArchivo)
                .setEstado(view.txtEstado.getText())
                .setMunicipio(view.txtMunicipio.getText())
                .setColonia(view.txtColonia.getText())
                .setCalle(view.txtCalle.getText())
                .setCuentaCracion((Integer.parseInt(sesion.getId())))
                .setTipoDenuncia(model.getCodigo())
                .Build();
        if (AccesoDatosDelObjetoDenuncia.insertar(ValoresObjetoDenuncia)) {
            mandaMensajeDeTexto("Insertado con éxito", "Insertar");
            copiarArchivos(ruta, destino + "" + nombreArchivo);
            limpiar();
            cargarDatosATabla();
        } else {
            mandaMensajeDeTexto("Ocurrio un error al insertar", "Insertar");
        }
    }

    @Override
    public void actualizar() {
        ResultSetComboBoxModelObject model = (ResultSetComboBoxModelObject) view.cmbTipoDenuncia.getModel().getSelectedItem();
        ValoresObjetoDenuncia = VODenuncia.Make(view.txtDescripcion.getText())
                .setFoto(nombreArchivo)
                .setEstado(view.txtEstado.getText())
                .setMunicipio(view.txtMunicipio.getText())
                .setColonia(view.txtColonia.getText())
                .setCalle(view.txtCalle.getText())
                .setTipoDenuncia(model.getCodigo())
                .setId(Integer.parseInt(view.txtId.getText()))
                .Build();
        if (AccesoDatosDelObjetoDenuncia.actualizar(ValoresObjetoDenuncia)) {
            mandaMensajeDeTexto("Actualizado con éxito", "Actualizar");
            copiarArchivos(ruta, destino + "" + nombreArchivo);
            limpiar();
            cargarDatosATabla();
        } else {
            mandaMensajeDeTexto("Ocurrio un error al actualizar", "Actualizar");
        }
    }

    private void moverDatosAJtextBox() {

        int n = view.tablaDenuncias.getSelectedRow();
        if (n >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) view.tablaDenuncias.getModel();
            view.txtId.setText(modelo.getValueAt(n, 0).toString());
            view.txtDescripcion.setText(modelo.getValueAt(n, 1).toString());
            view.txtFecha.setText(modelo.getValueAt(n, 2).toString());
            view.txtHora.setText(modelo.getValueAt(n, 3).toString());
            nombreArchivo = modelo.getValueAt(n, 4).toString();
            mostraImagen(destino + "" + nombreArchivo);
            view.txtEstado.setText(modelo.getValueAt(n, 5).toString());
            view.txtMunicipio.setText(modelo.getValueAt(n, 6).toString());
            view.txtColonia.setText(modelo.getValueAt(n, 7).toString());
            view.txtCalle.setText(modelo.getValueAt(n, 8).toString());
            view.rdbActivo.setSelected((boolean) modelo.getValueAt(n, 9));
            view.txtDesactivo.setText(modelo.getValueAt(n, 10).toString());
            view.txtAutor.setText(modelo.getValueAt(n, 11).toString());
            view.cmbTipoDenuncia.setSelectedItem(((ResultSetComboBoxModel) view.cmbTipoDenuncia.getModel()).searchSelectedItem(Integer.parseInt(modelo.getValueAt(n, 12).toString())));

        } else {
            mandaMensajeDeTexto("Selecione un registro para visualizar", "Advertencia");
        }
    }

    @Override
    public void eliminar() {
        ValoresObjetoDenuncia = VODenuncia.Make(view.txtDescripcion.getText())
                .setId(Integer.parseInt(view.txtId.getText()))
                .setCuentaDesactivo(Integer.parseInt(sesion.getId()))
                .Build();
        if(AccesoDatosDelObjetoDenuncia.eliminar(ValoresObjetoDenuncia)){
            mandaMensajeDeTexto("Desactivado","Desactivacion");
            limpiar();
            cargarDatosATabla();
        }else{
             mandaMensajeDeTexto("Ocurrio un error al desactivar","Desactivacion");
        }
    }

    @Override
    public void limpiar() {
        view.txtId.setText(null);
        view.txtDescripcion.setText(null);
        view.txtFecha.setText(null);
        view.txtHora.setText(null);
        nombreArchivo = "";
        ruta = "";
        view.txtEstado.setText(null);
        view.txtMunicipio.setText(null);
        view.txtColonia.setText(null);
        view.txtCalle.setText(null);
        view.rdbActivo.setSelected(false);
        view.txtDesactivo.setText(null);
        view.txtAutor.setText(null);
        view.JpanelImagen.removeAll();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.tablaDenuncias) {
            moverDatosAJtextBox();
        }
    }
    
    public void ocultarColumnas(){
        
        view.tablaDenuncias.getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaDenuncias.getColumnModel().getColumn(0).setMinWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        view.tablaDenuncias.getColumnModel().getColumn(3).setMaxWidth(0);
        view.tablaDenuncias.getColumnModel().getColumn(3).setMinWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        
        view.tablaDenuncias.getColumnModel().getColumn(4).setMaxWidth(0);
        view.tablaDenuncias.getColumnModel().getColumn(4).setMinWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        
        view.tablaDenuncias.getColumnModel().getColumn(8).setMaxWidth(0);
        view.tablaDenuncias.getColumnModel().getColumn(8).setMinWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        
        view.tablaDenuncias.getColumnModel().getColumn(12).setMaxWidth(0);
        view.tablaDenuncias.getColumnModel().getColumn(12).setMinWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        view.tablaDenuncias.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);
        
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
