package Vista;

import Controlador.EmpleadosDAO;
import Modelo.Empleados;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class jpEmpleados extends javax.swing.JPanel {

    Empleados em = new Empleados();
    EmpleadosDAO emd = new EmpleadosDAO();
    DefaultTableModel modeloEmpleados = new DefaultTableModel();

    public jpEmpleados() {
        initComponents();
        txtNom.requestFocus();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); //
        
        jtbEmpleados.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
            "Codigo", "Nombre", "Apellido", "Edad", "DNI", "Fecha Nacimiento", "Celular", "Correo", "Direccion"}));
        listarEmpleados();
    }

    private void limpiarCampos() {
        txtID.setText("");
        txtNom.setText("");
        txtApe.setText("");
        txtEdad.setText("");
        txtDni.setText("");
        txtCel.setText("");
        txtCorr.setText("");
        txtDir.setText("");
        jdcFech.setDate(null);
    }

    private void listarEmpleados() {
        List<Empleados> lista = emd.Listar();
        modeloEmpleados = (DefaultTableModel) jtbEmpleados.getModel();
        modeloEmpleados.setRowCount(0);

        Object[] ob = new Object[9];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getCodEmp();
            ob[1] = lista.get(i).getNomEmp();
            ob[2] = lista.get(i).getApeEmp();
            ob[3] = lista.get(i).getEdadEmp();
            ob[4] = lista.get(i).getDniEmp();
            ob[5] = lista.get(i).getNacEmp();
            ob[6] = lista.get(i).getCelEmp();
            ob[7] = lista.get(i).getCorrEmp();
            ob[8] = lista.get(i).getDirEmp();
            modeloEmpleados.addRow(ob);
        }
        jtbEmpleados.setModel(modeloEmpleados);

        // 1. Define el formato que quieres (ej. AAAA-MM-DD)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 3. Crea el renderizador personalizado
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof java.util.Date) {
                    // Formatea la fecha antes de mostrarla en la celda
                    value = sdf.format(value);
                }
                super.setValue(value);
            }
        };
        // 4. Aplica el renderizador a la columna 5 (índice 5 == "Fecha Nacimiento")
        jtbEmpleados.getColumnModel().getColumn(5).setCellRenderer(customRenderer);
    }

    public void registrarEmpleados() {
        String nomUsu = txtNom.getText().trim();
        String apeUsu = txtApe.getText().trim();
        String edadUsu = txtEdad.getText().trim();
        String dnisu = txtDni.getText().trim();
        String celUsu = txtCel.getText().trim();
        String corrUsu = txtCorr.getText().trim();
        String dirUsu = txtDir.getText().trim();

        if (txtNom.getText().isBlank() || txtApe.getText().isBlank() || txtEdad.getText().isBlank() || txtDni.getText().isBlank()
                || jdcFech.getDate() == null || txtCel.getText().isBlank() || txtCorr.getText().isBlank() || txtDir.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
            return;
        }
        try {
            em.setNomEmp(txtNom.getText());
            em.setApeEmp(txtApe.getText());
            em.setEdadEmp(Integer.parseInt(txtEdad.getText()));
            em.setDniEmp(txtDni.getText());
            em.setCelEmp(txtCel.getText());
            em.setCorrEmp(txtCorr.getText());
            em.setDirEmp(txtDir.getText());
            //fecha
            java.util.Date fecha = jdcFech.getDate();
            em.setNacEmp(new java.sql.Date(fecha.getTime()));
            if (emd.RegistrarEmpleado(em)) {
                JOptionPane.showMessageDialog(null, "Empleado registrado");
                listarEmpleados();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar empleado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La EDAD debe ser un numero");
        }

    }

    public void actualizarEmpleados() {
        if (txtID.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
            return;
        }
        try {
            em.setCodEmp(Integer.parseInt(txtID.getText()));
            em.setNomEmp(txtNom.getText());
            em.setApeEmp(txtApe.getText());
            em.setEdadEmp(Integer.parseInt(txtEdad.getText()));
            em.setDniEmp(txtDni.getText());
            em.setCelEmp(txtCel.getText());
            em.setCorrEmp(txtCorr.getText());
            em.setDirEmp(txtDir.getText());
            //fecha
            java.util.Date fecha = jdcFech.getDate();
            em.setNacEmp(new java.sql.Date(fecha.getTime()));

            if (emd.ActualizarEmpleado(em)) {
                JOptionPane.showMessageDialog(null, "Empleado actualizado");
                listarEmpleados();
                limpiarCampos();

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Edad invalida");
        }

    }

    public void eliminarEmpleados() {
        if (txtID.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
            return;
        }
        int fila = jtbEmpleados.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
            return;
        }
        int pregunta = JOptionPane.showConfirmDialog(null, "¿Seguro que desea ELIMINAR este Empleado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (pregunta == JOptionPane.YES_OPTION) {
            txtID.setText(jtbEmpleados.getValueAt(fila, 0).toString());
            em.setCodEmp(Integer.parseInt(txtID.getText()));
            if (emd.eliminarEmpleado(em)) {
                JOptionPane.showMessageDialog(null, "Empleado Eliminado");
                listarEmpleados();
                limpiarCampos();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbEmpleados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtNom = new javax.swing.JTextField();
        txtApe = new javax.swing.JTextField();
        txtCel = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtCorr = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        jdcFech = new com.toedter.calendar.JDateChooser();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnGuardar3 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jtbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jtbEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbEmpleados);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        txtCorr.setToolTipText("");

        btnActualizar.setBackground(new java.awt.Color(102, 153, 255));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jLabel3.setText("Edad");

        jLabel4.setText("Fecha de Nacimiento");

        jLabel5.setText("DNI");

        jLabel6.setText("Celular");

        jLabel7.setText("Correo");

        jLabel8.setText("Direccion");

        jdcFech.setDateFormatString("yyyy/MM/dd");

        btnEliminar.setBackground(new java.awt.Color(102, 153, 255));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(102, 153, 255));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(null);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnGuardar3.setBackground(new java.awt.Color(102, 153, 255));
        btnGuardar3.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar3.setText("Guardar");
        btnGuardar3.setBorder(null);
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });

        txtID.setEnabled(false);

        jLabel9.setText("ID");

        btnLimpiar.setBackground(new java.awt.Color(102, 153, 255));
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(null);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcFech, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(txtCel))
                                    .addComponent(jLabel2)
                                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(113, 113, 113)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(txtEdad)
                                    .addComponent(txtCorr, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(104, 104, 104)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5)
                                    .addComponent(txtDni)
                                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(7, 7, 7)
                        .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcFech, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarEmpleados();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarEmpleados();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        frmBuscarEmpleados bs = new frmBuscarEmpleados();
        bs.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        registrarEmpleados();
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void jtbEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbEmpleadosMouseClicked
        int fila = jtbEmpleados.getSelectedRow();
        if (fila == -1) {
            return;
        }
        txtID.setText(jtbEmpleados.getValueAt(fila, 0).toString());
        txtNom.setText(jtbEmpleados.getValueAt(fila, 1).toString()); // Nombre
        txtApe.setText(jtbEmpleados.getValueAt(fila, 2).toString());// Apellido
        txtEdad.setText(jtbEmpleados.getValueAt(fila, 3).toString()); // Edad
        txtDni.setText(jtbEmpleados.getValueAt(fila, 4).toString()); // Dni
        Object fechaValue = jtbEmpleados.getModel().getValueAt(fila, 5);
        if (fechaValue instanceof java.util.Date) {
            jdcFech.setDate((java.util.Date) fechaValue);
        }
        // jdcFech.setDate(java.sql.Date.valueOf(jtbEmpleados.getValueAt(fila, 5).toString())); // Fecha
        txtCel.setText(jtbEmpleados.getValueAt(fila, 6).toString()); // Celular
        txtCorr.setText(jtbEmpleados.getValueAt(fila, 7).toString()); //Correo
        txtDir.setText(jtbEmpleados.getValueAt(fila, 8).toString()); // Direccion
    }//GEN-LAST:event_jtbEmpleadosMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar3;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFech;
    private javax.swing.JTable jtbEmpleados;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtCorr;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
