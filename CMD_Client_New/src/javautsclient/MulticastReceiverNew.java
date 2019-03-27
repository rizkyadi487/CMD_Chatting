
package javautsclient;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

public class MulticastReceiverNew implements KeyListener   {

    public static int HEADER_SIZE = 8;
    public static int SESSION_START = 128;
    public static int SESSION_END = 64;

    private static int DATAGRAM_MAX_SIZE = 65507;

    /* Default values */
    public static String IP_ADDRESS = "225.4.5.6";
    public static int PORT = 4444;

    public String test(String formatkiriman){//proram yang di pangil dari Client tadi
        MulticastReceiverNew receiver = new MulticastReceiverNew();//receiver multicast
        receiver.receiveImages(IP_ADDRESS, PORT,formatkiriman);//mengambil gambar
        return "selesai";
    }

    JFrame frame;
    boolean fullscreen = false;
    JWindow fullscreenWindow = null;

private void receiveImages(String multicastAddress, int port,String formatnya) {
        boolean debug = true;

        InetAddress ia = null;
        MulticastSocket ms = null;

        /* Constuct frame */
        JLabel labelImage = new JLabel();
        JLabel windowImage = new JLabel();

        frame = new JFrame("Multicast Image Receiver");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//supaya kalau di close yang Client gak ikut mati
        frame.getContentPane().add(labelImage);
        frame.setSize(300, 10);
        frame.addKeyListener(this);
        frame.setVisible(false);//di sembunyikan biar gak mengotori 
        /* Construct full screen window */
        fullscreenWindow = new JWindow();
        fullscreenWindow.getContentPane().add(windowImage);
        fullscreenWindow.addKeyListener(this);

        try {//sama saja kayak di client
                /* Get address */
                ia = InetAddress.getByName(multicastAddress);

                /* Setup socket and join group */
                ms = new MulticastSocket(port);
                ms.joinGroup(ia);

                int currentSession = -1;
                int slicesStored = 0;
                int[] slicesCol = null;
                byte[] imageData = null;
                boolean sessionAvailable = false;

                /* Setup byte array to store data received */
                byte[] buffer = new byte[DATAGRAM_MAX_SIZE];

                /* loop */
                while (true) {
                    /* ngambil  UDP packet */
                    DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                    ms.receive(dp);
                    byte[] data = dp.getData();

                    /* Read header infomation */
                    //kan pengiriman data itu ada header, data dsb... ini untuk mengambil informasi tersebut, supaya gambar yang di dapat bisa utuh
                    short session = (short) (data[1] & 0xff);
                    short slices = (short) (data[2] & 0xff);
                    int maxPacketSize = (int) ((data[3] & 0xff) << 8 | (data[4] & 0xff)); // mask
                    // the
                    // sign
                    // bit
                    short slice = (short) (data[5] & 0xff);
                    int size = (int) ((data[6] & 0xff) << 8 | (data[7] & 0xff)); // mask
                    // the
                    // sign
                    // bit

                    if (debug) {//ini informasi yang di keluarkan
                        System.out.println("------------- PACKET -------------");
                        System.out.println("SESSION_START = "
                                        + ((data[0] & SESSION_START) == SESSION_START));
                        System.out.println("SSESSION_END = "
                                        + ((data[0] & SESSION_END) == SESSION_END));
                        System.out.println("SESSION NR = " + session);
                        System.out.println("SLICES = " + slices);
                        System.out.println("MAX PACKET SIZE = " + maxPacketSize);
                        System.out.println("SLICE NR = " + slice);
                        System.out.println("SIZE = " + size);
                        System.out.println("------------- PACKET -------------\n");
                    }

                    /* If SESSION_START falg is set, setup start values */
                    if ((data[0] & SESSION_START) == SESSION_START) {//pengendali session
                        if (session != currentSession) {
                            currentSession = session;
                            slicesStored = 0;
                            /* Consturct a appropreately sized byte array */
                            imageData = new byte[slices * maxPacketSize];
                            slicesCol = new int[slices];
                            sessionAvailable = true;
                        }
                    }

                    /* If package belogs to current session */
                    if (sessionAvailable && session == currentSession) {//kalau ada banyak potongan maka akan di ambil lagi potongan berikutnya
                        if (slicesCol != null && slicesCol[slice] == 0) {
                            slicesCol[slice] = 1;
                            System.arraycopy(data, HEADER_SIZE, imageData, slice * maxPacketSize, size);
                            slicesStored++;
                        }
                    }

                    /* kalau sudah lengkap*/
                    if (slicesStored == slices) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        BufferedImage image = ImageIO.read(bis);
                        labelImage.setIcon(new ImageIcon(image));//menampilkan ke frame
                        windowImage.setIcon(new ImageIcon(image));
                        frame.setVisible(true);//yang tadi di sembunyikan sekarang di keluarkan, supaya kelihatan gambarnya.
                        frame.pack();
                        //save atau tidak
                        int reply = JOptionPane.showConfirmDialog(null, "Want save this image ?", "Save", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            JFileChooser jfc = new JFileChooser("D:\\");//directory yang default
                            jfc.showDialog(null,"Save");//tulisan tok
                            jfc.setVisible(true);//ditampilkan
                            File filename = jfc.getSelectedFile();//ambil url file
                            if(filename == null){//ini kalau gak jadi
                                System.out.println("file cancel");
                            }
                            else{
                                String namaFile = filename.getAbsolutePath();
                                //System.out.println("formatnya : "+formatnya);
                                File outputfile = new File(namaFile+"."+formatnya);//membuat file dengan nama dan format
                                if(formatnya.equals("jpg")){//pembuatan image sesui format
                                    ImageIO.write(image, "jpg", outputfile);
                                }
                                else if(formatnya.equals("png")){
                                    ImageIO.write(image, "png", outputfile);
                                }
                                else{
                                    System.out.println("rusak");
                                }
                                
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "GOODBYE");//kalau gak pingin ngesave
                        }
                        break;   
                    }
                    if (debug) {
                            System.out.println("STORED SLICES: " + slicesStored);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ms != null) {
                try {
                    /* Leave group and close socket */
                    ms.leaveGroup(ia);
                    ms.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public void keyPressed(KeyEvent keyevent) {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            /* Toggle full screen mode on key press */
            if (fullscreen) {
                    device.setFullScreenWindow(null);
                    fullscreenWindow.setVisible(false);
                    fullscreen = false;
            } else {
                    device.setFullScreenWindow(fullscreenWindow);
                    fullscreenWindow.setVisible(true);
                    fullscreen = true;
            }

    }

    public void keyReleased(KeyEvent keyevent) {
    }

    public void keyTyped(KeyEvent keyevent) {
    }


}
