package Vista;

import Controlador.ProductoDAO;
import Modelo.CargarComboBox;
import Modelo.Producto;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class jpRegistroProducto extends javax.swing.JPanel {

    Producto pr = new Producto();
    ProductoDAO prd = new ProductoDAO();
    DefaultTableModel modeloProducto = new DefaultTableModel();

    public jpRegistroProducto() {
        initComponents();
        prd.CargarCategoria(cmbCat);
        prd.CargarProveedor(cmbProv);
        jtbProducto.setModel(new DefaultTableModel(null, new String[]{
            "Codigo", "Producto", "Categoria", "Cantidad", "Descripcion", "Proveedor"
        }));
        listarProducto();
    }

    public void limpiarCampos() {
        txtID.setText("");
        txtNomProd.setText("");
        txtDescr.setText("");
        txtCant.setText("");
        cmbCat.setSelectedIndex(0);
        cmbProv.setSelectedIndex(0);
    }

    public void listarProducto() {
        List<Producto> lista = prd.Listar();
        modeloProducto = (DefaultTableModel) jtbProducto.getModel();
        modeloProducto.setRowCount(0);

        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getIdProd();
            ob[1] = lista.get(i).getNomProd();
            ob[2] = lista.get(i).getNomCat();
            ob[3] = lista.get(i).getCanProd();
            ob[4] = lista.get(i).getDesProd();
            ob[5] = lista.get(i).getNomProv();
            modeloProducto.addRow(ob);
        }
    }

    public void registrarProducto() {
        if (txtNomProd.getText().isBlank() || txtDescr.getText().isBlank()
                || txtCant.getText().isBlank() || cmbCat.getSelectedIndex() == 0 || cmbProv.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }
        try {
            pr.setNomProd(txtNomProd.getText());
            pr.setCanProd(Integer.parseInt(txtCant.getText()));
            pr.setDesProd(txtDescr.getText());
            CargarComboBox cat = (CargarComboBox) cmbCat.getSelectedItem();
            CargarComboBox prov = (CargarComboBox) cmbProv.getSelectedItem();
            pr.setIdCat(cat.getId());
            pr.setIdProv(prov.getId());
            if (prd.RegistroProducto(pr)) {
                JOptionPane.showMessageDialog(null, "Producto registrado");
                limpiarCampos();
                listarProducto();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar el producto");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un numero");
        }
    }

    public void actualizarProducto() {
        if (txtID.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Seleciones un Producto");
            return;
        }
        try {
            pr.setIdProd(Integer.parseInt(txtID.getText()));
            pr.setNomProd(txtNomProd.getText());
            pr.setIdCat(cmbCat.getSelectedIndex());
            pr.setCanProd(Integer.parseInt(txtCant.getText()));
            pr.setDesProd(txtDescr.getText());
            pr.setIdProv(cmbProv.getSelectedIndex());

            if (prd.ActualizarProducto(pr)) {
                JOptionPane.showMessageDialog(null, "Producto Actualizado");
                listarProducto();
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad de ser un numero");
        }
    }
    
    public void eliminarProducto(){
        if(txtID.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "Seleccione un Producto");
            return;
        }
        int fila = jtbProducto.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea ELIMINAR este producto?","Confirmal", JOptionPane.YES_NO_OPTION);
        if(pregunta == JOptionPane.YES_OPTION){
            txtID.setText(jtbProducto.getValueAt(fila, 0).toString());
            pr.setIdProd(Integer.parseInt(txtID.getText()));
            if(prd.EliminarProducto(pr)){
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
                limpiarCampos();
                listarProducto();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        txtNomProd = new javax.swing.JTextField();
        cmbCat = new javax.swing.JComboBox<>();
        txtCant = new javax.swing.JTextField();
        txtDescr = new javax.swing.JTextField();
        cmbProv = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProducto = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        txtID.setEnabled(false);

        cmbCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una opción" }));

        cmbProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una opción" }));

        jtbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbProducto);

        btnRegistrar.setBackground(new java.awt.Color(153, 153, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorder(null);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(153, 153, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(153, 153, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(null);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo");

        jLabel2.setText("Producto");

        jLabel3.setText("Categoria");

        jLabel4.setText("Cantidad");

        jLabel5.setText("Descripcion del Producto");

        jLabel6.setText("Proveedor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNomProd, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cmbCat, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(cmbProv, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(63, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrarProducto();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jtbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbProductoMouseClicked
        String proveedor, categoria;
        int fila = jtbProducto.getSelectedRow();
        if (fila == -1) {
            return;
        }
        txtID.setText(jtbProducto.getValueAt(fila, 0).toString());
        txtNomProd.setText(jtbProducto.getValueAt(fila, 1).toString());
        txtCant.setText(jtbProducto.getValueAt(fila, 3).toString());
        txtDescr.setText(jtbProducto.getValueAt(fila, 4).toString());

        proveedor = jtbProducto.getValueAt(fila, 5).toString();
        for (int i = 0; i < cmbProv.getItemCount(); i++) {
            Object item = cmbProv.getItemAt(i);
            if (item instanceof CargarComboBox) {
                CargarComboBox cbItem = (CargarComboBox) item;
                if (cbItem.toString().equals(proveedor)) { // Compara los nombres (strings)
                    cmbProv.setSelectedIndex(i);
                    break;
                }
            }
        }
        //obtener en el combo
        categoria = jtbProducto.getValueAt(fila, 2).toString();
        //buscar en el combo
        for (int i = 0; i < cmbCat.getItemCount(); i++) {
            Object item = cmbCat.getItemAt(i);
            if (item instanceof CargarComboBox) {
                CargarComboBox cbItem = (CargarComboBox) item;
                if (cbItem.toString().equals(categoria)) {// Compara los nombres (strings)
                    cmbCat.setSelectedIndex(i);
                    break;
                }
            }
        }

    }//GEN-LAST:event_jtbProductoMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarProducto();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbCat;
    private javax.swing.JComboBox<String> cmbProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProducto;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtDescr;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNomProd;
    // End of variables declaration//GEN-END:variables
}
