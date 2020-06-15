/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_Parkir;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Paulus
 */
public class Park_1 extends javax.swing.JFrame {
    
    static ArrayList<Integer> slotsID = new ArrayList<Integer>();
    static ArrayList<String> slots = new ArrayList<String>();
    static ArrayList<String> slotsName = new ArrayList<String>();
    
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    static final String driver = "com.mysql.jdbc.Driver";
    static final String db = "jdbc:mysql://localhost/database_1";
    static final String user = "root";
    static final String pass = "12345";
    
    /**
     * Creates new form Park_1
     */
    public Park_1() {
        
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
        check();
    }
    
    public void park(){
       String _plate = jPlate.getText();
       String _slot = jParkSlot.getText();
       int id = 1 ;
       String id_num;
       int stat = 1;
       try{
            Class.forName(driver);
            System.out.println(stat);
            con = DriverManager.getConnection(db, user, pass);
            stmt = con.createStatement();
            String sql = "SELECT * FROM transaction";
            rs = stmt.executeQuery(sql);
            
            if(jPlate.getText() != null && jParkSlot.getText() != null){
                while(rs.next()){
                    id++;
                }
                if(slotsName.contains(_slot)){
                    int index = slotsName.indexOf(_slot);
                    if(slotsID.get(index)!=0){
                        System.out.println(id);
                        id_num = Integer.toString(id);
                        String query = "INSERT INTO transaction (`Plate`, `Slot`, `Slip_No`) VALUES ('" + _plate + "', '" + _slot + "', '" + id_num + "')";
                        stmt.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Park Success\nPlate = " + _plate + "\nSlot = " + _slot + "\nSlip No. = " + id_num);
                        int updateindex = slotsID.get(index);
                        slotsID.set(index, 0);
                        slots.set(index, "Not Available");
                        
                        
                    }
                    else if(slotsID.get(index)==0){
                        JOptionPane.showMessageDialog(null,"Slot Not Available");
                    }
                }
                   
                               
                jPlate.setText("");
                jParkSlot.setText("");
                            }
            
            stmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void update(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(db, user, pass);
            stmt = con.createStatement();
            String sql = "SELECT * FROM slot_parkir";
            rs = stmt.executeQuery(sql);
            int i = 0;
            int slotStatusID;
            String SlotStatus, SlotNo;
            for(i=0;i<24;i++){
                slotStatusID = slotsID.get(i);
                SlotStatus = slots.get(i);
                SlotNo = slotsName.get(i);
                String updates = "UPDATE slot_parkir SET Status_ID = '" + slotStatusID +"' , Status = '"+ SlotStatus +"' WHERE Slot = '"+ SlotNo +"'";
                stmt.execute(updates);
            }
            
            stmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }    
    }
    
    public static void check(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(db, user, pass);
            stmt = con.createStatement();
            String sql = "SELECT * FROM slot_parkir";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                slotsID.add(rs.getInt("Status_ID"));
                slots.add(rs.getString("Status"));
                slotsName.add(rs.getString("Slot"));
            }
            for(int i =0; i< slots.size(); i++){
                System.out.println(i+1);
                System.out.println(slotsName.get(i));
                System.out.println(slots.get(i));
                System.out.println(slotsID.get(i));
                System.out.println("");
            }
            
            stmt.close();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
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
        jPlate = new javax.swing.JTextField();
        jParkButton = new javax.swing.JButton();
        jParkSlot = new javax.swing.JTextField();
        jParkButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Plate             :");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Parking Slot :");

        jPlate.setName("PlateNum"); // NOI18N
        jPlate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPlateActionPerformed(evt);
            }
        });

        jParkButton.setBackground(new java.awt.Color(255, 255, 204));
        jParkButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jParkButton.setForeground(new java.awt.Color(51, 153, 0));
        jParkButton.setText("Park");
        jParkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jParkButtonActionPerformed(evt);
            }
        });

        jParkSlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jParkSlotActionPerformed(evt);
            }
        });

        jParkButton1.setBackground(new java.awt.Color(255, 255, 204));
        jParkButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jParkButton1.setForeground(new java.awt.Color(255, 0, 0));
        jParkButton1.setText("Back");
        jParkButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jParkButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Parking Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jParkSlot, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(19, 19, 19)
                                .addComponent(jPlate, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jParkButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jParkButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel3)))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPlate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jParkSlot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jParkButton1)
                    .addComponent(jParkButton))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPlateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPlateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPlateActionPerformed

    private void jParkSlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jParkSlotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jParkSlotActionPerformed

    private void jParkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jParkButtonActionPerformed
        // TODO add your handling code here:
        park();
        update();
        this.setVisible(false);
        Menu _menu = new Menu();
        _menu.setVisible(true);
    }//GEN-LAST:event_jParkButtonActionPerformed

    private void jParkButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jParkButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu _menu = new Menu();
        _menu.setVisible(true);
    }//GEN-LAST:event_jParkButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Park_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Park_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Park_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Park_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Park_1().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jParkButton;
    private javax.swing.JButton jParkButton1;
    private javax.swing.JTextField jParkSlot;
    private javax.swing.JTextField jPlate;
    // End of variables declaration//GEN-END:variables
}
