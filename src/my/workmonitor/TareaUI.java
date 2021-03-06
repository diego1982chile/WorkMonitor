/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.workmonitor;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import my.dao.TareaDao;
import my.dao.TipoTareaDao;
import my.entity.Persona;
import my.entity.Tarea;
import my.entity.TipoTarea;

/**
 *
 * @author Diego
 */
public class TareaUI extends javax.swing.JDialog {

    boolean nuevo=true;
    Tarea tarea;
    /**
     * Creates new form TareaUI
     */
    private static TipoTareaDao tipoTareaDao = new TipoTareaDao();
    private static TareaDao tareaDao= new TareaDao();
    private static TipoTarea tipoTarea;
    
    public TareaUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public TareaUI(java.awt.Frame parent, boolean modal, Integer idTipoTarea) {        
        super(parent, modal);
        initComponents();
        tipoTarea=tipoTareaDao.get(TipoTarea.class ,(Integer)idTipoTarea); 
        jTextField2.setText(tipoTarea.getNombre().toString());
    }
    
    public TareaUI(java.awt.Frame parent, boolean modal, Tarea tarea) {
        super(parent, modal);
        initComponents();
        jLabel1.setText(" Tarea Actual");
        jButton1.setText("Modificar");
        jTextField2.setText(tarea.getTipoTarea().getNombre().trim().toUpperCase());
        jTextField1.setText(tarea.getNombre().trim().toUpperCase());
        jTextArea1.setText(tarea.getComentario().trim().toUpperCase());
        this.tarea=tarea;
        nuevo=false;
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(300, 400));
        setMinimumSize(new java.awt.Dimension(300, 400));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(300, 400));

        jLabel1.setBackground(new java.awt.Color(123, 153, 172));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" Nueva Tarea");
        jLabel1.setOpaque(true);

        jLabel2.setText("Nombre");
        jLabel2.setAlignmentX(0.5F);

        jLabel3.setText("Descripción");
        jLabel3.setAlignmentX(0.5F);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 11)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Crear");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo");
        jLabel4.setAlignmentX(0.5F);

        jTextField2.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jTextField2)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jScrollPane1)
                        .addComponent(jTextField1)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nombre=jTextField1.getText().toString();
        String descripcion=jTextArea1.getText().toString();

        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para este tipo");
            return;
        }
        if(descripcion.equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción para este tipo");
            return;
        }
        
        if(!nuevo){
            //tarea.setTipoTarea(this.tarea.getTipoTarea());
            //tarea.setIdTipoTarea(this.tarea.getTipoTarea().getId());
            this.tarea.setNombre(nombre.trim().toUpperCase());
            this.tarea.setComentario(descripcion.trim().toUpperCase());  
            try {
                tareaDao.update(this.tarea);
            } catch (Exception ex) {
                Logger.getLogger(TareaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "La tarea se ha modificado correctamente");
            WorkMonitorUI.refreshTable();
            this.dispose();
        }
        else{
            Tarea tarea=new Tarea();
            tarea.setNombre(nombre.trim().toUpperCase());
            tarea.setComentario(descripcion.trim().toUpperCase());
            tarea.setTipoTarea(tipoTarea);
            tarea.setIdTipoTarea(tipoTarea.getId());
            List<Tarea> tareas=tareaDao.getByNombre(nombre);

            if(!tareas.isEmpty()){
                JOptionPane.showMessageDialog(null, "Ya existe una tarea con este nombre");
                return;
            }

            try {
                if(tareaDao.save(tarea)==(Serializable)0){
                    JOptionPane.showMessageDialog(null, "Error al ingresar tarea. No se ha podido ingresar");
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(TareaUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "La tarea se ha ingresado correctamente");
            this.dispose();
        }    
        WorkMonitorUI.jList3.setModel(new javax.swing.AbstractListModel() {
            List<Tarea> tareas=tareaDao.getByTipoTarea(tipoTarea.getNombre());
            public int getSize() { return tareas.size(); }
            public Object getElementAt(int i) { return tareas.get(i); }
        });        
        return;
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TareaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TareaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TareaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TareaUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TareaUI dialog = new TareaUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
