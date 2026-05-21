/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameLogin;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import arogyaapp.dao.AppointmentDao;
import arogyaapp.dao.DoctorsDao;
import arogyaapp.dao.PatientDao;
import arogyaapp.dbutil.DBConnection;
import arogyaapp.pojo.AppointmentPojo;
import arogyaapp.pojo.PatientPojo;
import arogyaapp.utility.OTPSender;
import arogyaapp.utility.Sender;

public class VerifyOTPScreen extends javax.swing.JFrame {

    private PatientPojo patient;
    private String mob;

    public VerifyOTPScreen() {
        initComponents();
    }

    public VerifyOTPScreen(PatientPojo patient) {
        this();
        txtName.setText(patient.getFirst_name() + " " + patient.getLast_name());
        txtPhoneNo.setText(patient.getMobile_no());
        this.patient = patient;
    }

    public VerifyOTPScreen(PatientPojo patient, boolean flag) {
        this();
        txtName.setText(patient.getFirst_name() + " " + patient.getLast_name());
        txtPhoneNo.setText(patient.getMobile_no());
        this.patient = patient;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSendOTP = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtPhoneNo = new javax.swing.JTextField();
        txtOTP = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        btnVerify = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sanjeevaniapp/icons/icons/HomePageBG1.jpg"))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "PatientDetails", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Patient Name");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Enter OTP");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Phone No.");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, -1, -1));

        btnSendOTP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSendOTP.setText("SEND OTP");
        btnSendOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendOTPActionPerformed(evt);
            }
        });
        jPanel2.add(btnSendOTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 260, -1));

        txtName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 230, -1));

        txtPhoneNo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(txtPhoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 170, -1));

        txtOTP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(txtOTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 230, -1));

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel2.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        btnVerify.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnVerify.setText("VERIFY");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });
        jPanel2.add(btnVerify, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, -1, -1));

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 192, 990, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendOTPActionPerformed
        // TODO add your handling code here:
//        mob = txtPhoneNo.getText().trim();
//        boolean ans = isContactValid(mob);
//        if (ans) {
//            patient.setMobile_no(mob);
//        } else {
//            JOptionPane.showMessageDialog(null, "Invalid mobile No ");
//            return;
//        }
//        try {
//            Sender sender = new OTPSender();
//
//            String pOTP = String.valueOf(patient.getOtp());
//            boolean result = sender.send(patient.getMobile_no(), pOTP);
//            if (result) {
//                JOptionPane.showMessageDialog(null, "OTP sent Successfully ");
//
//            } else {
//                JOptionPane.showMessageDialog(null, "cannot sent OTP please check the mobile no ");
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Server issue");
//            e.printStackTrace();
//        }
 
mob = txtPhoneNo.getText().trim();
    if (!isContactValid(mob)) {
        JOptionPane.showMessageDialog(null, "Invalid mobile number. Must be 10 digits.");
        return;
    }
    patient.setMobile_no(mob);
    try {
        Sender sender = new OTPSender();
        String pOTP = String.valueOf(patient.getOtp());
        boolean result = sender.send(patient.getMobile_no(), pOTP);
        if (result) {
            JOptionPane.showMessageDialog(null, "OTP sent successfully to " + mob);
        } else {
            JOptionPane.showMessageDialog(null, "Could not send OTP. Check mobile number.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSendOTPActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        // TODO add your handling code here:

        if (!isValidOTP()) {

            JOptionPane.showMessageDialog(null, "Invalid OTP. Try again!");
            return;

        }
        try {
            AppointmentPojo appojo = new AppointmentPojo();
            appojo.setAppointmentDate(patient.getP_date().toString());
            appojo.setMobileNo(patient.getMobile_no());
            appojo.setOpd(patient.getOpd());
            appojo.setPatientId(patient.getPatient_Id());
            appojo.setPatientName(txtName.getText().trim());
            appojo.setStatus("REQUEST");
            String docName = DoctorsDao.getDoctorNamebyDoctorId(patient.getDoctor_id());
            appojo.setDoctorName(docName);

            boolean result = PatientDao.addPatient(patient);
            boolean result1 = AppointmentDao.addAppointment(appojo);
            if (result && result1) {
                JOptionPane.showMessageDialog(null, "Patient record saved!!");
                ViewAllPatientAfterVerify fr = new ViewAllPatientAfterVerify();
                fr.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! could not save the patient record!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in DB Add Patient Frame " + e);
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        AddPatientScreen fr = new AddPatientScreen();
        fr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int ans;
        ans = JOptionPane.showConfirmDialog(null, "Are you sure to Logout", "LOGOUT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ans == JOptionPane.YES_OPTION) {

            LoginScrrenn fr = new LoginScrrenn();
            fr.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(VerifyOTPScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifyOTPScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifyOTPScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifyOTPScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifyOTPScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnSendOTP;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtOTP;
    private javax.swing.JTextField txtPhoneNo;
    // End of variables declaration//GEN-END:variables

    private boolean isValidOTP() {

        boolean result = false;
        try {
            int otp = Integer.parseInt(txtOTP.getText().trim());
            if (otp == patient.getOtp()) {
                result = true;
            }
        } catch (NumberFormatException e) {

        } finally {
            return result;
        }
    }

    private boolean isContactValid(String mo) {

        char[] mob = mo.toCharArray();
        for (char ch : mob) {
            if (Character.isDigit(ch) == false) {
                return false;
            }

        }
        if (patient.getMobile_no().length() == 10) {
            return true;
        }
        return false;

    }
}
