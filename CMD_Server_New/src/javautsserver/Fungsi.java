package javautsserver;

import chatting.Chatting;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFileChooser;

public class Fungsi {
    public String jam(){
            Calendar cal = new GregorianCalendar();
            int hour = cal.get(Calendar.HOUR);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int AM_PM = cal.get(Calendar.AM_PM);
            
            String day_night = "";
            String ssec = "";
            String mmin = "";
            String hhour = "";
            if(AM_PM ==1)
        {
            day_night = "PM";
        }
            else
        {
            day_night = "AM";
        }
            if(sec<10){ssec = "0"+sec;}else{ssec = ""+sec;}
            if(min<10){mmin = "0"+min;}else{mmin = ""+min;}
            if(hour<10){hhour = "0"+hour;}else{hhour = ""+hour;}
            String time = hour + ":" + mmin + ":" + ssec;
            String hasil = time+" "+day_night;
            return hasil;
        }
        
    public String chat(String nama, String chat, String chatting, String dir){
        Chatting aaa = new Chatting(jam(),nama, chat, chatting);
        try{
        FileOutputStream fos = new FileOutputStream(dir);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(aaa);
        oos.flush();
        }
        catch(IOException e){
            System.out.println("error"+e);
            System.exit(1);
        }
        return "";
    }
    
    public String reset(String nama,String dir){
    Chatting aaa = new Chatting(jam(),nama, "Reset", "");
        try{
        FileOutputStream fos = new FileOutputStream(dir);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(aaa);
        oos.flush();
        }
        catch(IOException e){
            System.out.println("error"+e);
            System.exit(1);
        }
        return "";
    }
   
    public String mkfile(){
        Chatting aaa = new Chatting(jam(),"File", "Baru", "Dibuat");
        JFileChooser SDialog = new JFileChooser();
        if(SDialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            try(FileOutputStream out = new FileOutputStream(SDialog.getSelectedFile().getAbsolutePath())){
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(aaa);
                oos.flush();
            }
            catch(IOException x){
               // System.err.format("IOException; ",x);
            }
        }
        return "";
    }
    
    public String setfile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //File selectedFile = fileChooser.getSelectedFile();
            String hasil = (selectedFile.getAbsolutePath());
            try {
                InputStream fileInput = new FileInputStream (selectedFile.getAbsolutePath());
                
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(Chatting.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("gak jadi");
            }
        }
        File selectedFile = fileChooser.getSelectedFile();
        String hasil = (selectedFile.getAbsolutePath());
        
        return hasil;
    }
    
    public String read(String dir) throws ClassNotFoundException{
        String cetakan="";
        try{
            FileInputStream fis = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Chatting aaa = (Chatting) ois.readObject();
            //jTextArea1.setText(aaa.cetak());
            cetakan = (aaa.cetak());
        }
        catch(IOException e){chat("System", "Error, but now is normaly working", "File Deleted\n", "D:\\test.baru");}
        return cetakan;
//          return "";
    }
}
