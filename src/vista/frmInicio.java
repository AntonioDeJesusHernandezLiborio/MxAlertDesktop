/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author ANTONIO-LIBORIO
 */
public class frmInicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public frmInicio() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        editMenu = new javax.swing.JMenu();
        btnTipoUsuario = new javax.swing.JMenuItem();
        btnMetodoDePago = new javax.swing.JMenuItem();
        btnPlanUsuario = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carpeta.png"))); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("Datos");

        btnTipoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        btnTipoUsuario.setMnemonic('t');
        btnTipoUsuario.setText("Tipo Usuario");
        editMenu.add(btnTipoUsuario);

        btnMetodoDePago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pagar.png"))); // NOI18N
        btnMetodoDePago.setMnemonic('y');
        btnMetodoDePago.setText("Metodo de Pago");
        editMenu.add(btnMetodoDePago);

        btnPlanUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/las-tendencias-del-mercado.png"))); // NOI18N
        btnPlanUsuario.setMnemonic('p');
        btnPlanUsuario.setText("Plan de Usuario");
        editMenu.add(btnPlanUsuario);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Proximamente");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Proximamente");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem aboutMenuItem;
    public javax.swing.JMenuItem btnMetodoDePago;
    public javax.swing.JMenuItem btnPlanUsuario;
    public javax.swing.JMenuItem btnTipoUsuario;
    public javax.swing.JMenuItem contentMenuItem;
    public javax.swing.JMenu editMenu;
    public javax.swing.JDesktopPane escritorio;
    public javax.swing.JMenuItem exitMenuItem;
    public javax.swing.JMenu fileMenu;
    public javax.swing.JMenu helpMenu;
    public javax.swing.JMenuBar menuBar;
    public javax.swing.JMenuItem openMenuItem;
    public javax.swing.JMenuItem saveAsMenuItem;
    public javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
