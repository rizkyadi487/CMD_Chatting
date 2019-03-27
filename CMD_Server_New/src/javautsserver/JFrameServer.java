package javautsserver;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JFrameServer extends javax.swing.JFrame {
    //kayak yang ada di client
    int timeRun = 2;
    int test = 0;
    String directory = "";
    
    boolean jRBON_selected=false;
        
    File filename;
    String bytefile="";
    
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();
    
    String data1;
    String data2;
    String data3;
    
    DefaultTableModel model;
    
    String namaFile;
    
    int loopingFile;
    
    public JFrameServer() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jL_Judul = new javax.swing.JLabel();
        jL_PortClient = new javax.swing.JLabel();
        jL_Action = new javax.swing.JLabel();
        jTF_PortClient = new javax.swing.JTextField();
        jCombo_Action = new javax.swing.JComboBox<>();
        jB_Process = new javax.swing.JButton();
        jLIPGroup = new javax.swing.JLabel();
        jTF_IPGroup = new javax.swing.JTextField();
        jB_Exit = new javax.swing.JButton();
        jRB_ON = new javax.swing.JRadioButton();
        jRB_OFF = new javax.swing.JRadioButton();
        jTF_FileURL = new javax.swing.JTextField();
        jL_SendFile = new javax.swing.JLabel();
        jB_Browse = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jB_Send = new javax.swing.JButton();
        jTF_IPSend = new javax.swing.JTextField();
        jB_SendToAll = new javax.swing.JButton();
        jL_JudulServer = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTA_ChatHistory = new javax.swing.JTextArea();
        jTF_Dir = new javax.swing.JTextField();
        jB_ChangeDir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_IPClient = new javax.swing.JTable();
        jB_Help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jL_Judul.setText("Program Kontrol Client v0.9.6.4");

        jL_PortClient.setText("Port Client");

        jL_Action.setText("Action : ");

        jTF_PortClient.setText("2345");

        jCombo_Action.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lock", "Shutdown", "Restart", "Sleep" }));

        jB_Process.setText("Process");
        jB_Process.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jB_ProcessMouseClicked(evt);
            }
        });

        jLIPGroup.setText("IP Group");

        jTF_IPGroup.setText("224.3.2.1");

        jB_Exit.setText("Exit");
        jB_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ExitActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRB_ON);
        jRB_ON.setText("Server ON");
        jRB_ON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_ONActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRB_OFF);
        jRB_OFF.setText("Server OFF");
        jRB_OFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_OFFActionPerformed(evt);
            }
        });

        jTF_FileURL.setText("File URL");

        jL_SendFile.setText("Send File");

        jB_Browse.setText("Browse");
        jB_Browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jB_BrowseMouseClicked(evt);
            }
        });

        jB_Send.setText("Send File");
        jB_Send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jB_SendMouseClicked(evt);
            }
        });

        jTF_IPSend.setText("IP");
        jTF_IPSend.setEnabled(false);

        jB_SendToAll.setText("Send Images");
        jB_SendToAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jB_SendToAllMouseClicked(evt);
            }
        });

        jL_JudulServer.setText("Server");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setText("History Pesan :");

        jTA_ChatHistory.setColumns(20);
        jTA_ChatHistory.setRows(5);
        jScrollPane2.setViewportView(jTA_ChatHistory);

        jTF_Dir.setText("D:\\test.baru2");

        jB_ChangeDir.setText("Change");
        jB_ChangeDir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jB_ChangeDirMouseClicked(evt);
            }
        });

        jTable_IPClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "IP", "OS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable_IPClient);

        jB_Help.setText("?");
        jB_Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_HelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jL_Judul)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRB_OFF)
                                    .addComponent(jRB_ON))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL_PortClient)
                                    .addComponent(jLIPGroup))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTF_IPGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTF_PortClient, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jB_Help)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jB_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jL_Action)
                                .addGap(18, 18, 18)
                                .addComponent(jCombo_Action, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jB_Process, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTF_IPSend, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTF_FileURL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jB_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jB_SendToAll))
                                            .addComponent(jB_Browse, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jL_SendFile)
                                    .addComponent(jL_JudulServer))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTF_Dir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jB_ChangeDir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jL_Judul)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jL_JudulServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRB_ON)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRB_OFF))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLIPGroup)
                                    .addComponent(jTF_IPGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTF_PortClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_PortClient))))
                        .addComponent(jL_SendFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_FileURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jB_Browse))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_IPSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jB_SendToAll)
                            .addComponent(jB_Send))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCombo_Action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jL_Action)
                            .addComponent(jB_Process))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jB_Exit)
                            .addComponent(jB_Help))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTF_Dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jB_ChangeDir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_ProcessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_ProcessMouseClicked
        //deklarasi
        String Pesan = jCombo_Action.getSelectedItem().toString()+"|";
        TX_ImageAndFile(Pesan);
    }//GEN-LAST:event_jB_ProcessMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jRB_OFF.setSelected(true);
    }//GEN-LAST:event_formWindowActivated

    private void jRB_ONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_ONActionPerformed
        OnChanged();
    }//GEN-LAST:event_jRB_ONActionPerformed

    private void jRB_OFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_OFFActionPerformed
        OnChanged();
    }//GEN-LAST:event_jRB_OFFActionPerformed

    private void jB_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jB_ExitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTA_ChatHistory.setEditable(false);//cek client kalau gak paham
        directory = jTF_Dir.getText();
        model = (DefaultTableModel) jTable_IPClient.getModel();

        membaca();
    }//GEN-LAST:event_formWindowOpened

    private void jB_ChangeDirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_ChangeDirMouseClicked
        jRB_OFF.doClick();
    }//GEN-LAST:event_jB_ChangeDirMouseClicked

    private void jB_BrowseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_BrowseMouseClicked
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg");
        JFileChooser jfc = new JFileChooser("D:\\");//mbuat JFC, sing dialog open iki.
        //jfc.setFileFilter(filter);
        
        jfc.showDialog(null,"Open");//tulisan tok
        jfc.setVisible(true);//di tampilkan
        filename = jfc.getSelectedFile();//nyimpen url file
        
        if(filename == null){
            System.out.println("file cancel");//kalo gak jadi
        }
        else{
            namaFile = filename.getAbsolutePath();// url file ke sting
            jTF_FileURL.setText(namaFile);//set ke JTF
            if(cekExtensiImage(namaFile)==true){//pengendali Multicast atau yang file biasa
                jB_Send.setEnabled(true);
                jB_SendToAll.setEnabled(true);
            }
            else{
                jB_Send.setEnabled(true);
                jB_SendToAll.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jB_BrowseMouseClicked

    private void jB_SendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_SendMouseClicked

        if(cekFileAda(jTF_FileURL.getText())){//berjalan bila file ada di directory
            //sendFileIP(jTF_IPSend.getText());
            String Pesan = null;
            try {
                Pesan = "File"+"|"+cekFormat(jTF_FileURL.getText())+"|"+InetAddress.getLocalHost().getHostAddress()+"|"+jTF_FileURL.getText()+"|";
            } catch (UnknownHostException ex) {
                Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendFileSingle();//menjalankan method sendFile
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException ex) {
                Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            TX_ImageAndFile(Pesan);//peringatan ke client kalau mau kirim file
        }
        else{//peringantan kalau file tidak ada
            JOptionPane.showMessageDialog(null, "file tidak ada, jangan ngarang sendiri", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        
    }//GEN-LAST:event_jB_SendMouseClicked

    private void jB_SendToAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_SendToAllMouseClicked
        
        if(cekFileAda(jTF_FileURL.getText())){//kalau filenya ada
            String Pesan = "Image"+"|"+cekFormat(jTF_FileURL.getText())+"|";
            TX_ImageAndFile(Pesan);//peringatan ke client kalau mau kirim image
            sendImageMulticast();//ngirim gambar
        }
        else{//kalau gak ada
            JOptionPane.showMessageDialog(null, "file tidak ada, jangan ngarang sendiri", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jB_SendToAllMouseClicked

    private void jB_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_HelpActionPerformed
        JOptionPane.showMessageDialog(null, "SendToAll works only for Images\nSend works for all format but need IP Address\nThey can active after you choose a file", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jB_HelpActionPerformed
    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jB_Browse;
    private javax.swing.JButton jB_ChangeDir;
    private javax.swing.JButton jB_Exit;
    private javax.swing.JButton jB_Help;
    private javax.swing.JButton jB_Process;
    private javax.swing.JButton jB_Send;
    private javax.swing.JButton jB_SendToAll;
    private javax.swing.JComboBox<String> jCombo_Action;
    private javax.swing.JLabel jLIPGroup;
    private javax.swing.JLabel jL_Action;
    private javax.swing.JLabel jL_Judul;
    private javax.swing.JLabel jL_JudulServer;
    private javax.swing.JLabel jL_PortClient;
    private javax.swing.JLabel jL_SendFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRB_OFF;
    private javax.swing.JRadioButton jRB_ON;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTA_ChatHistory;
    private javax.swing.JTextField jTF_Dir;
    private javax.swing.JTextField jTF_FileURL;
    private javax.swing.JTextField jTF_IPGroup;
    private javax.swing.JTextField jTF_IPSend;
    private javax.swing.JTextField jTF_PortClient;
    private javax.swing.JTable jTable_IPClient;
    // End of variables declaration//GEN-END:variables

    private void RX() {  
        new Thread(){
            public void run(){
            String[] IPnya;
            String AllClient = "";
            IPnya = new String[10];
            // initialize first element
            while(running){
                synchronized (pauseLock) {
                        if (!running) {break;}
                        if (paused) {
                            try {
                                pauseLock.wait();
                            } catch (InterruptedException ex) {
                                break;
                            }
                            if (!running) {break;}
                        }
                    }
                membaca();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                    InetAddress group = null;
                    int port = 0;
                    MulticastSocket ms = null;
                    try {

                        String IPGroup = jTF_IPGroup.getText();//ambil IPGroupdari JTF_IPGroupt
                        group = InetAddress.getByName(IPGroup);//memasukkan IP ke variabel group
                        String Port = "5432";//ambil PORT JTF_Port
                        port = Integer.parseInt(Port);//memasukan port ke variable port

                        ms = new MulticastSocket(port); //mengisi multicast socket dengan port yang dituju
                        ms.joinGroup(group);// join group dengan IPGroup

                        byte[] buffer = new byte[65536];// daftar buffer
                        DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
                        ms.receive(incoming);
                        String s = new String(incoming.getData( ));//memasukkan paket yang di terima ke String s
                        String[] real=s.split("\\|");//memcah isi string s ke array real
                        System.out.println(real[1]);
                    switch (real[1]) {
                        case "/reset":
                            String reset = new Fungsi().reset(real[0],jTF_Dir.getText());
                            break;
                        case "unsuport":
                            JOptionPane.showMessageDialog(null, "Unsupported operating system", "Info", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "ThisIP":
                            //JOptionPane.showMessageDialog(null, real[2], "This IP", JOptionPane.INFORMATION_MESSAGE);
                            if(real[4].equals("Add")){//pengendali tabel
                            data1 = real[0];
                            data2 = real[2];
                            data3 = real[3];
                            Object[] row = { data1, data2, data3};
                            model.addRow(row);
                            loopingFile=model.getRowCount();
                            for(int g=0;g<model.getRowCount();g++){
                                AllClient = AllClient+model.getValueAt(g, 0)+",";
                                System.out.println(AllClient);
                            }
                            String Pesan = "Client"+"|"+AllClient+"|";
                            TimeUnit.MILLISECONDS.sleep(400);
                            TX_ImageAndFile(Pesan);
                            AllClient="";
                            }
                            else{
                                for(int d=0;d<10;d++){
                                    if (model.getRowCount()==d) {
                                        break;
                                    }
                                    else{
                                        if(model.getValueAt(d, 0).equals(real[0]) && model.getValueAt(d, 1).equals(real[2])){
                                        model.removeRow(d);
                                        for(int g=0;g<model.getRowCount();g++){
                                            AllClient = AllClient+model.getValueAt(g, 0)+",";
                                            System.out.println(AllClient);
                                        }
                                        TimeUnit.MILLISECONDS.sleep(400);
                                        String Pesan = "Client"+"|"+AllClient+"|";
                                        TX_ImageAndFile(Pesan);
                                        AllClient="";
                                        break;
                                        }
                                        else{
                                            System.out.println(IPnya[d]);
                                        }
                                    }
                                }
                            }
                            
                            break;
                        default:
                            String ngechat = new Fungsi().chat(real[0],real[1],jTA_ChatHistory.getText(),jTF_Dir.getText());
                            break;
                    }
                        

                    }catch (IOException e) {
                        //System.err.println(e);
                    } catch (InterruptedException ex) {
                    Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                    finally {//untuk nutup group
                        if (ms != null) {
                            try {
                            ms.leaveGroup(group);
                            ms.close( );
                            }
                            catch (IOException ex) {}
                        }
                    }
                }
            }
        }.start();
    }

    private void TX() {//di client lengkap
        new Thread(){
            public void run(){
                InetAddress ia = null;
                int port = 0;
                byte ttl = (byte) 1;
                while(running){
                    synchronized (pauseLock) {
                        if (!running) {break;}
                        if (paused) {
                            try {
                                pauseLock.wait();
                            } catch (InterruptedException ex) {
                                break;
                            }
                            if (!running) {break;}
                        }
                    }
                    membaca();
                    try {
                        String IPGroup = jTF_IPGroup.getText();
                        ia = InetAddress.getByName(IPGroup);
                        String Port = jTF_PortClient.getText();
                        port = Integer.parseInt(Port);
                    }
                    catch (Exception ex) {System.err.println(ex);System.err.println("Usage: java MulticastSender multicast_address port ttl");System.exit(1);}

                    String Pesan = jTA_ChatHistory.getText()+"|";
                    byte[] data= (Pesan).getBytes();
                    DatagramPacket dp = new DatagramPacket(data,data.length, ia, port);
                    try {
                        MulticastSocket ms = new MulticastSocket(port);
                        ms.joinGroup(ia);
                        ms.send(dp, ttl); 
                        ms.leaveGroup(ia);
                        ms.close();
                    }
                    catch (SocketException ex) {System.err.println(ex+"test1");}
                    catch (IOException ex) {System.err.println(ex+"test2");}
                    try {TimeUnit.MILLISECONDS.sleep(400);} 
                    catch (InterruptedException ex) {Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);}
                }
            }
        }.start();
    }
    
    private void membaca(){//untuk membaca pesan
        try {
            String membaca = new Fungsi().read(jTF_Dir.getText());
            jTA_ChatHistory.setText(membaca);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //pengendali thread
    //di client lengkap
    public void stop() {
        running = false;
    }
    public void pause() {
        System.out.println("pause");
        paused = true;
    }
    public void resume() {
        synchronized (pauseLock) {
            System.out.println("resume");
            paused = false;
            pauseLock.notifyAll();
        }
    }

    private void sendImageMulticast() {
        System.out.println("NgeSent File Multicast");
        new MulticastSenderNew().test(namaFile);
    }
    
    public boolean cekExtensiImage(String name ){
        boolean balikan;
        String nameLc = name.toLowerCase();
       
        if(nameLc.endsWith(".jpg")){
            balikan = true;
        }
        else if(nameLc.endsWith(".png")){
            balikan = true;
        }
        else{
            balikan = false;
        }
      return balikan;
    }    

    private void TX_ImageAndFile(String Pesan) {
        InetAddress ia = null;
        int port = 0;
        byte ttl = (byte) 1;
        
        try {
            String IPGroup = jTF_IPGroup.getText();
            ia = InetAddress.getByName(IPGroup);
            String Port = jTF_PortClient.getText();
            port = Integer.parseInt(Port);
        }
        catch (Exception ex) {System.err.println(ex);System.err.println("Usage: java MulticastSender multicast_address port ttl");System.exit(1);}
        
        byte[] data= (Pesan).getBytes();
        DatagramPacket dp = new DatagramPacket(data,data.length, ia, port);
        try {
            MulticastSocket ms = new MulticastSocket( );
            ms.joinGroup(ia);
            ms.send(dp, ttl); 
            ms.leaveGroup(ia);
            ms.close( );
        }
        catch (SocketException ex) {System.err.println(ex);}
        catch (IOException ex) {System.err.println(ex);}
    }

    private void sendFileSingle() {
        
        new Thread(){
            public void run(){
            while(running){
                synchronized (pauseLock) {
                        if (!running) {break;}
                        if (paused) {
                            try {
                                pauseLock.wait();
                            } catch (InterruptedException ex) {
                                break;
                            }
                            if (!running) {break;}
                        }
                    System.out.println("NgeSent File Single");
                    try {
                        new SimpleFileServer().test(jTF_FileURL.getText(),loopingFile);
                    } catch (IOException ex) {
                        Logger.getLogger(JFrameServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    }
                }
            }
        }.start();
    }

    private String cekFormat(String names) {
        String balikan;
        String nameLc = names.toLowerCase();
        
        if(nameLc.endsWith(".jpg")){
            balikan = "jpg";
        }
        else if(nameLc.endsWith(".png")){
            balikan = "png";
        }
        else if(nameLc.endsWith(".txt")){
            balikan = "txt";
        }
        else if(nameLc.endsWith(".pdf")){
            balikan = "pdf";
        }
        else if(nameLc.endsWith(".rar")){
            balikan = "rar";
        }
        else{
            balikan = "UnknownImage";
        }
      return balikan;
    }

    private boolean cekFileAda(String file) {
        boolean balikan; 
        if(new File(file).exists()) {
            balikan = true;
        }
        else{
            balikan = false;
        }   
        return balikan;
    }

    private void OnChanged() {
         if(jRB_ON.isSelected()){
            if(jRBON_selected==false){
                System.out.println("ON");
                if(timeRun==2){
                    timeRun = 0;
                    TX();
                    RX();
                }
                else{
                    resume();
                }
                jTF_Dir.setEditable(false);
                jRBON_selected=true;
            }
        }else{
            if(jRBON_selected==true){
            System.out.println("OFF");
            pause();
            jTF_Dir.setEditable(true);
            jRBON_selected=false;
            }
        }
    }
  }


