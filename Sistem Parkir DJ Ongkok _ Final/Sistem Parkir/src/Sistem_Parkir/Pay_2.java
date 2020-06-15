/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistem_Parkir;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Pay_2 extends javax.swing.JFrame {
    
    
    
    static ArrayList<Integer> slotsID = new ArrayList<Integer>();
    static ArrayList<String> slots = new ArrayList<String>();
    static ArrayList<String> slotsName = new ArrayList<String>();
    
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    String plate = "Plate : ";
    String slot = "Slot : "; 
    String price = "Price : ";
    int price_;
    int slips, _time;
    
    static final String driver = "com.mysql.jdbc.Driver";
    static final String db = "jdbc:mysql://localhost/database_1";
    static final String user = "root";
    static final String pass = "12345";

    /**
     * Creates new form Pay_2
     */
    public Pay_2() {
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
        checks();
    }
    
    public void check(){
        String slip = jSlipNo.getText();
        slips = Integer.valueOf(slip);
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(db, user, pass);
            stmt = con.createStatement();
            String sql = "SELECT * FROM transaction";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int status = rs.getInt("Payment_Status");
                if(rs.getInt("Slip_No")==slips && status == 0){
                    jPlate.setText(plate + rs.getString("Plate"));
                    jSlot.setText(slot + rs.getString("Slot"));
                    jLabelSlip.setText("Slip Valid");
                    break;
                }
                else if(rs.getInt("Slip_No")==slips && status == 1){
                    jPlate.setText(plate);
                    jSlot.setText(slot);
                    jLabelSlip.setText("Slip Invalid.");
                }
                else if (rs.getInt("Slip_No")!=slips){
                    jPlate.setText(plate);
                    jSlot.setText(slot);
                    jLabelSlip.setText("Slip Invalid");
                }
            }
        }catch(Exception e){
            
        }
    }
    
    public void count(){
        String time = jTime.getText();
        _time = Integer.valueOf(time);
        price_ = (_time-1) * 20000;
        if (_time<2){
            jPrice.setText(price + "Free");
        }
        else if (_time >1){
            jPrice.setText(price + "Rp." + price_);
        }
    }
    
    public void pay(){
        String slip = jSlipNo.getText();
        slips = Integer.valueOf(slip);
        String money = jMoney.getText();
        int _money = Integer.valueOf(money);
        if(_money < price_){
            jLabelMoney.setText("Lack of Money");
        }
        else if (_money >= price_){
            int change = _money - price_;
            try{
            Class.forName(driver);
            
            con = DriverManager.getConnection(db, user, pass);
            stmt = con.createStatement();
            String sql = "SELECT * FROM transaction";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                int tes = rs.getInt("Slip_No");
                if(tes==slips){
                    String _plate = rs.getString("Plate");
                    String _slot = rs.getString("Slot");
                    int slip_no = rs.getInt("Slip_No");
                    JOptionPane.showMessageDialog(null, "Payment Success\nPlate = " + _plate + "\nSlot = " + _slot + "\nSlip No. = " + slip_no + "\nPrice = Rp." + price_ + "\nChange = Rp." + change + "\nThank you.");
                    String update = "UPDATE transaction SET `Park_Time` = '" + _time+ "' , Payment_Status = '1', Price = '" + price_ + "' WHERE `Slip_No` = '"+ slip_no+ "'";
                    stmt.execute(update);
                    int index = slotsName.indexOf(_slot);
                    slotsID.set(index, 1);
                    slots.set(index, "Available");
                    break;
                }
                else {
                    
                }
            }
            
         stmt.close();
         con.close();   
        }catch(Exception e){
            
        }
        }
    }
    
    public static void checks(){
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
    
    public static void update_(){
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPlate = new javax.swing.JLabel();
        jSlot = new javax.swing.JLabel();
        jPrice = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMoney = new javax.swing.JTextField();
        jLabelMoney = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSlipNo = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTime = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabelSlip = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Slip No :");

        jPlate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPlate.setText("Plate :");

        jSlot.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jSlot.setText("Slot :");

        jPrice.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jPrice.setText("Price :");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Insert Money Amount ");

        jMoney.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMoney.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMoneyActionPerformed(evt);
            }
        });

        jLabelMoney.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelMoney.setText(".");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 153, 0));
        jButton1.setText("Pay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSlipNo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Check");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Insert Time : ");

        jTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("Count");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelSlip.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelSlip.setText(".");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Pay Check");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addComponent(jPrice)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabelMoney))))
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPlate)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSlipNo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelSlip))
                                    .addComponent(jSlot)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSlipNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(jPlate))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jLabelSlip))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSlot)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPrice)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelMoney)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMoneyActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMoneyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        check();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        count();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                pay();
                update_();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu a = new Menu();
        a.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Pay_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pay_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pay_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pay_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pay_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelMoney;
    private javax.swing.JLabel jLabelSlip;
    private javax.swing.JTextField jMoney;
    private javax.swing.JLabel jPlate;
    private javax.swing.JLabel jPrice;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jSlipNo;
    private javax.swing.JLabel jSlot;
    private javax.swing.JTextField jTime;
    // End of variables declaration//GEN-END:variables
}
