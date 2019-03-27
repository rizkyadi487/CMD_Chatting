package javautsclient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SimpleFileClient {

  public final static int SOCKET_PORT = 13267;      // you may change this
  public static String SERVER = "192.168.1.87";  // localhost
  public static String FILE_TO_RECEIVED = "D:/kuci.rarasdf";  // you may change this, I give a
                                                            // different name because i don't want to
                                                            // overwrite the one used by server...

  public final static int FILE_SIZE = 6022386; // file size temporary hard coded
                                               // should bigger than the file to be downloaded

    public String test (String formatnya, String Servernya, String Namanya) throws IOException {
    SERVER = Servernya;
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Connecting...");

      // receive file
      

      int reply = JOptionPane.showConfirmDialog(null, "The new file has been received from : \n "+Namanya+", want to save ?", "Save", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {//wes ngerti kan??? lihat di MulticastReceiverNew
            
            JFileChooser jfc = new JFileChooser("D:\\");
            jfc.showDialog(null,"Save");
            jfc.setVisible(true);
            File filename = jfc.getSelectedFile();
            String namaFile = filename.getAbsolutePath();
            byte [] mybytearray  = new byte [FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(namaFile+"."+formatnya);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;
            FILE_TO_RECEIVED = namaFile+"."+formatnya;
            do {
               bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
               if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);
            if(filename == null){
                System.out.println("file cancel");
            }
            else{
                bos.write(mybytearray, 0 , current);//ini nulis(mulai save file)
                bos.flush();
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "GOODBYE");
        }
      
//      bos.write(mybytearray, 0 , current);
//      bos.flush();
      System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + current + " bytes read)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
    return "berhasil terima";
  }

}