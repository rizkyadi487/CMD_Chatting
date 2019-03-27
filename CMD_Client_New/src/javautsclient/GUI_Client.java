package javautsclient;
//import yang dibutuhkan
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GUI_Client extends javax.swing.JFrame {
    
    int timeRun = 2;//deklarasi time run untuk pertama kali
    int troll=0;//gak usah di hiraukan
    int test=0;//cuma buat debug, gak usah dihiraukan
    
    String format;//untuk menyimpan format dari file yang di pilih
    String IPServer;//menyimpan IPServer yang akan digunakan oleh Client
    String namaFilenya;//meyimpan namaFile yang dipilih
    
    private volatile boolean running = true;//pengendali thread running
    private volatile boolean paused = false;//pengendali thread pause
    private final Object pauseLock = new Object();//pengendali thread pauseLock

    String operatingSystem2 = System.getProperty("os.name");//cari tahu OS
    
    DefaultListModel model = new DefaultListModel();
    
    public void shutdown() {//fungsi shutdown
        try {
            //deklarasi
            String shutdownCommand = null;
            String operatingSystem = System.getProperty("os.name");//cari type OS
            
            if (null != operatingSystem) switch (operatingSystem) {//milih OS
                case "Linux":
                case "Mac OS X":
                    shutdownCommand = "shutdown -h now";//command untuk shutdown Linux dan MAC
                    jTA_Chat.append("Maaf, Komputer di matikan paksa SERVER\n");TimeUnit.SECONDS.sleep(2);//pemberitahuan
                    break;
                case "Windows 7":
                case "Windows 10":
                case "Windows 8":
                case "Windows 8.1":
                    shutdownCommand = "shutdown.exe -s -t 0";//command untuk shutdown Windows
                    jTA_Chat.append("Maaf, Komputer di matikan paksa SERVER\n");TimeUnit.SECONDS.sleep(2);
                    break;
                default:
                    jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");//balikan jika tidak support
            }
            
            Runtime.getRuntime().exec(shutdownCommand);//menjalankan command
        } 
        catch (Exception ex) {System.out.println(ex.getMessage());}       
    }
    public void lock() { //fungsi lock mirip mirip lah
        try {
            //deklarasi
            String lockCommand = null;
            String operatingSystem = System.getProperty("os.name");//cari type OS

            if (null != operatingSystem) switch (operatingSystem) {
                case "Linux":
                case "Mac OS X":
                    jTA_Chat.append("Maaf, Komputer di lock paksa SERVER\n");TimeUnit.SECONDS.sleep(2);
                    lockCommand = "gnome-screensaver-command -l";
                    break;
                case "Windows 7":
                case "Windows 10":
                case "Windows 8":
                case "Windows 8.1":
                    jTA_Chat.append("Maaf, Komputer di lock paksa SERVER\n");TimeUnit.SECONDS.sleep(2);
                    lockCommand = "rundll32.exe user32.dll, LockWorkStation";
                    break;
                default:
                    jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");
            }            
            Runtime.getRuntime().exec(lockCommand);
        } 
        catch (Exception ex) {System.out.println(ex.getMessage());}       
    }
    public void restart() { //fungsi restart
        try {
            //deklarasi
            String shutdownCommand = null;
            String operatingSystem = System.getProperty("os.name");//cari type OS
            
            if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem) || "Windows 7".equals(operatingSystem) || "Windows 10".equals(operatingSystem) || "Windows 8".equals(operatingSystem) || "Windows 8.1".equals(operatingSystem)){jTA_Chat.append("Maaf, Komputer di restart paksa SERVER\n");TimeUnit.SECONDS.sleep(2);}else{jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");}
            
            if (null != operatingSystem) switch (operatingSystem) {
                case "Linux":
                case "Mac OS X":
                    shutdownCommand = "shutdown.exe ‐r ‐t 0";
                    break;
                case "Windows 7":
                case "Windows 10":
                case "Windows 8":
                case "Windows 8.1":
                    shutdownCommand = "shutdown.exe -r -t 0";
                    break;
                default:
                    jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");
            }
            
            Runtime.getRuntime().exec(shutdownCommand);
        } 
        catch (Exception ex) {System.out.println(ex.getMessage());}
    }
    public void sleeps() { //fungsi sleep
        try {
            //deklarasi
            String shutdownCommand = null;
            String operatingSystem = System.getProperty("os.name");//cari type OS
            
            //if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem) || "Windows 7".equals(operatingSystem) || "Windows 10".equals(operatingSystem) || "Windows 8".equals(operatingSystem) || "Windows 8.1".equals(operatingSystem)){jTA_Chat.append("Maaf, Komputer di sleep paksa SERVER\n");TimeUnit.SECONDS.sleep(2);}else{jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");}
            
            if (null != operatingSystem) switch (operatingSystem) {
                case "Linux":
                case "Mac OS X":
                    jTA_Chat.append("Maaf, Komputer di sleep paksa SERVER\n");TimeUnit.SECONDS.sleep(2);
                    shutdownCommand = "pm-suspend-hybrid";
                    break;
                case "Windows 7":
                case "Windows 10":
                case "Windows 8":
                case "Windows 8.1":
                    jTA_Chat.append("Maaf, Komputer di sleep paksa SERVER\n");TimeUnit.SECONDS.sleep(2);
                    shutdownCommand = "rundll32.exe powrprof.dll,SetSuspendState 0,1,0";
                    break;
                default:
                    jTF_Pesan.setText("unsuport");jB_Send.doClick();jTF_Pesan.setText("");
            }
            
            Runtime.getRuntime().exec(shutdownCommand);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public GUI_Client() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_Client = new javax.swing.JList<>();
        jB_Exit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTA_Chat = new javax.swing.JTextArea();
        jTF_Pesan = new javax.swing.JTextField();
        jB_Send = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jB_LogIn = new javax.swing.JButton();
        jB_LogOut = new javax.swing.JButton();
        jTF_Name = new javax.swing.JTextField();
        jTF_IPGroup = new javax.swing.JTextField();
        jTF_Port = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Name : ");

        jScrollPane1.setViewportView(jList_Client);

        jB_Exit.setText("Exit");
        jB_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ExitActionPerformed(evt);
            }
        });

        jTA_Chat.setColumns(20);
        jTA_Chat.setRows(5);
        jScrollPane2.setViewportView(jTA_Chat);

        jTF_Pesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTF_PesanKeyPressed(evt);
            }
        });

        jB_Send.setText("Send");
        jB_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_SendActionPerformed(evt);
            }
        });

        jButton3.setText("?");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jB_LogIn.setText("Log In");
        jB_LogIn.setMaximumSize(new java.awt.Dimension(71, 23));
        jB_LogIn.setMinimumSize(new java.awt.Dimension(71, 23));
        jB_LogIn.setPreferredSize(new java.awt.Dimension(71, 23));
        jB_LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_LogInActionPerformed(evt);
            }
        });

        jB_LogOut.setText("Log Out");
        jB_LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_LogOutActionPerformed(evt);
            }
        });

        jTF_Name.setText("Guest");
        jTF_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_NameActionPerformed(evt);
            }
        });
        jTF_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTF_NameKeyPressed(evt);
            }
        });

        jTF_IPGroup.setText("224.3.2.1");

        jTF_Port.setText("2345");
        jTF_Port.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTF_PortKeyPressed(evt);
            }
        });

        jLabel2.setText("IP Group : ");

        jLabel3.setText("PORT :");

        jLabel4.setText("Client v.0.9.2.3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTF_Pesan)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 244, Short.MAX_VALUE)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jB_Send, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jB_Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTF_IPGroup)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTF_Port, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jB_LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jB_LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTF_Port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTF_IPGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jB_LogIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTF_Pesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jB_Send))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_Exit)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jB_LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_LogInActionPerformed
        if(jTF_Name.getText().equals("")){//jika namanya kosong gak bisa login
            JOptionPane.showMessageDialog(null, "Name can't be empty!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        else{//kalau ada namanya langsung masuk sini
            jTF_Name.setEnabled(false);//mematikan nama, ipgroup dan port. supaya gak diganti ganti pas login
            jTF_IPGroup.setEnabled(false);
            jTF_Port.setEnabled(false);
            jB_LogIn.setVisible(false);
            jB_LogOut.setVisible(true);
            try {//ngirim informasi kalau dia login ke server
                    System.out.println(InetAddress.getLocalHost().getHostAddress());
                    jTF_Pesan.setText("ThisIP|"+InetAddress.getLocalHost().getHostAddress()+"|"+operatingSystem2+"|Add");
                    jB_Send.doClick();
                    jTF_Pesan.setText("");
                } catch (UnknownHostException ex) {
                    Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            if(timeRun==2){//untuk mengatifkan thread, satu kali saja
                timeRun = 0;
                RX();//menjalankan thread untuk Receiver atau penerima pesan
            }
            else{
                resume();//untuk resume thread
            }
        }
    }//GEN-LAST:event_jB_LogInActionPerformed

    private void jB_LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_LogOutActionPerformed
        try {//sama kayak button login cuma dibalik
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            jTF_Pesan.setText("ThisIP|"+InetAddress.getLocalHost().getHostAddress()+"|"+operatingSystem2+"|Remove");
            jB_Send.doClick();
            jTF_Pesan.setText("");
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        jB_LogOut.setVisible(false);
        jTF_IPGroup.setEnabled(true);
        jTF_Port.setEnabled(true);
        jTF_Name.setEnabled(true);
        jB_LogIn.setVisible(true);
        model.removeAllElements();
        pause();
          
    }//GEN-LAST:event_jB_LogOutActionPerformed

    private void jB_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_SendActionPerformed
        if(jB_LogIn.isVisible()==true){//ya wes ngono iku lah
            JOptionPane.showMessageDialog(null, "You must Login !", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        else{            
            //deklarasi
            InetAddress ia = null;
            int port = 0;
            byte ttl = (byte) 1;

            try {
                String IPGroup = jTF_IPGroup.getText();//ambil IPgroup dari ...
                ia = InetAddress.getByName(IPGroup);//sama kayak atasnya
                String Port = "5432";//setting port untuk ngirim, kalau menerima pake yang ngisi di Jframe tadi
                port = Integer.parseInt(Port);
            }
            catch (Exception ex) {System.err.println(ex);System.err.println("Usage: java MulticastSender multicast_address port ttl");System.exit(1);}

            String Pesan = jTF_Name.getText()+"|"+jTF_Pesan.getText()+"|";//isi pesan
            byte[] data= (Pesan).getBytes();//mengubah pesan dalam daray data bentuk byte
            DatagramPacket dp = new DatagramPacket(data,data.length, ia, port);//mengisi datagram paket dengan pesan
            try {
                MulticastSocket ms = new MulticastSocket( );//membuat multicastsocket
                ms.joinGroup(ia);//join group
                ms.send(dp, ttl); //ngirim
                ms.leaveGroup(ia);//leave group
                ms.close( );//tutup
            }
            catch (SocketException ex) {System.err.println(ex);}
            catch (IOException ex) {System.err.println(ex);}
        }
    }//GEN-LAST:event_jB_SendActionPerformed

    private void jB_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jB_ExitActionPerformed

    private void jTF_PesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_PesanKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {//supaya kalau ngirim pesan tinggal pencet enter
            jB_Send.doClick();
            jTF_Pesan.setText("");
        }
    }//GEN-LAST:event_jTF_PesanKeyPressed

    private void jTF_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_NameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {//sama kayak atasnya
            jB_LogIn.doClick();
        }
    }//GEN-LAST:event_jTF_NameKeyPressed

    private void jTF_PortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTF_PortKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_PortKeyPressed

    private void jTF_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_NameActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTA_Chat.setEditable(false);//biar gak bisa di edit
        
        if(jB_LogIn.isVisible()==false){//eruh dewe lah...
            jB_LogOut.setVisible(true);
        }
        else{
            jB_LogOut.setVisible(false);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        die();
    }//GEN-LAST:event_jButton3ActionPerformed
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
            java.util.logging.Logger.getLogger(GUI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Exit;
    private javax.swing.JButton jB_LogIn;
    private javax.swing.JButton jB_LogOut;
    private javax.swing.JButton jB_Send;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList_Client;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTA_Chat;
    private javax.swing.JTextField jTF_IPGroup;
    private javax.swing.JTextField jTF_Name;
    private javax.swing.JTextField jTF_Pesan;
    private javax.swing.JTextField jTF_Port;
    // End of variables declaration//GEN-END:variables

    private void RX() {//method RX
        new Thread(){//threadnya
            public void run(){//mulai programnya
            while(running){//berjalan sesuai kondisi (lihat pengendali thread di bawah sendiri)
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
                test++;//cuma buat debug, gak usah di hiraukan
                try {
                    TimeUnit.MILLISECONDS.sleep(100);//supaya dia selama 100 millisecond
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //deklarasi
                    InetAddress group = null;
                    int port = 0;
                    MulticastSocket ms = null;
                    try {

                        String IPGroup = jTF_IPGroup.getText();//ambil IPGroupdari JTF_IPGroupt
                        group = InetAddress.getByName(IPGroup);//memasukkan IP ke variabel group
                        String Port = jTF_Port.getText();//ambil PORT JTF_Port
                        port = Integer.parseInt(Port);//memasukan port ke variable port

                        ms = new MulticastSocket(port); //mengisi multicast socket dengan port yang dituju
                        ms.joinGroup(group);// join group dengan IPGroup

                        byte[] buffer = new byte[65536];// daftar buffer
                        DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
                        //System.out.println("nunggu");
                        ms.receive(incoming);
                        String s = new String(incoming.getData( ));//memasukkan paket yang di terima ke String s
                        String[] real=s.split("\\|");//memcah isi string s ke array real

                        switch(real[0]){//pemilihan aksi
                            case "Shutdown" : shutdown(); break; 
                            case "Restart"  : restart(); break;
                            case "Sleep"    : sleeps(); break;
                            case "Lock"     : lock(); break;
                            case "Client"   : SetListClient(real[1]); break;
                            case "File"     : format=real[1];IPServer=real[2];namaFilenya=real[3];RX_File(); break;//pengisian variable dan pemangilan method
                            case "Image"    : format=real[1];ImageRX(); break;
                            default : jTA_Chat.setText(real[0]);break;
                        }

                    }catch (IOException e) {
                        //System.err.println(e);
                    } 
//                    catch (InterruptedException ex) {
//                        Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
//                    }
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

    private void ImageRX(){
        
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
                    String mak = new MulticastReceiverNew().test(format);//memangil method dengan parameter format
                    System.out.println(mak);//untuk mengetahuiberhasil atau tidak
                    break;
                    }
                }
            }
        }.start();
    }
    
    private void RX_File() {
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
                    String mak;
                    try {
                        mak = new SimpleFileClient().test(format,IPServer,namaFilenya);//podo koyok nduwur'e
                    } catch (IOException ex) {
                        Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    }
                }
            }
        }.start();
    }
    
    private void die() {
        JOptionPane.showMessageDialog(null, "NGE-TROLL SEK WKAKAKAKK\nNGE-TROLL SEK WKAKAKAKK\nNGE-TROLL SEK WKAKAKAKK\nNGE-TROLL SEK WKAKAKAKK\nNGE-TROLL SEK WKAKAKAKK\n", "Info", JOptionPane.INFORMATION_MESSAGE);
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            troll++;
        } catch (InterruptedException ex) {
            Logger.getLogger(GUI_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(troll==2){
            shutdown();
        }
        die();
    }
    
    private void SetListClient(String client) {
        model.removeAllElements();
        String[] real=client.split("\\,");//memcah isi string s ke array real
        for(int x=0;x<10;x++){
            if (real.length==x) {
                break;
            }
            else{
                model.addElement(real[x]);
                //System.out.println(real[x]);
            }
        }
        jList_Client.setModel(model);
    }
    
    
    //pengendali thread
    public void stop() {// ini kalau stop, gak bisa jalan lagi, harus di pangil ulang threadnya
        running = false;
    }
    public void pause() {//ini kalau pause
        System.out.println("pause");
        paused = true;
    }
    public void resume() {// resume, untuk play thread yang pause tadi
        synchronized (pauseLock) {
            System.out.println("resume");
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
    }
}
