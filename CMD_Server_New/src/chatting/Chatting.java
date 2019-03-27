package chatting;

import java.io.Serializable;

public class Chatting implements Serializable{
    private String jam;
    private String nama;
    private String chat;
    private String tambahan;
    
    public Chatting(String jam,String nama, String chat, String tambahan) {
        this.jam = jam;
        this.nama = nama;
        this.chat = chat;
        this.tambahan = tambahan;
    }
    public String cetak(){
        return this.tambahan+this.jam+" "+this.nama+" = "+this.chat+"\n";
    }
}
