package javautsserver;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;

public class MulticastSenderNew {
	
	public static int HEADER_SIZE = 8;
	public static int MAX_PACKETS = 255;
	public static int SESSION_START = 128;
	public static int SESSION_END = 64;
	public static int DATAGRAM_MAX_SIZE = 65507 - HEADER_SIZE;
	public static int MAX_SESSION_NUMBER = 255;

	public static String OUTPUT_FORMAT = "jpg";
        public static String IMAGENYA = null;

	public static int COLOUR_OUTPUT = BufferedImage.TYPE_INT_RGB;

	public static double SCALING = 0.5;
	public static int SLEEP_MILLIS = 3000;
	public static String IP_ADDRESS =  "225.4.5.6";
	public static int PORT = 4444;
	public static boolean SHOW_MOUSEPOINTER = true;

	public static BufferedImage getScreenshot() throws AWTException,ImageFormatException, IOException {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);

            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRect);

            return image;
	}

	public static BufferedImage getRandomImageFromDir(File dir) throws IOException {//kalau image random
            String[] images = dir.list(new ImageFileFilter());
            int random = new Random().nextInt(images.length);

            String fileName = dir.getAbsoluteFile() + File.separator + images[random];
            File imageFile = new File(fileName);
            return ImageIO.read(imageFile);
	}
        
        public static BufferedImage getNotRandomImageFromDir(File dir) throws IOException {//kalau sudah di tentukan
            String fileName = IMAGENYA;
            File imageFile = new File(fileName);
            return ImageIO.read(imageFile);
	}

	public static byte[] bufferedImageToByteArray(BufferedImage image, String format) throws IOException {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, format, baos);
            return baos.toByteArray();
	}

	public static BufferedImage scale(BufferedImage source, int w, int h) {
		Image image = source
				.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING);
		BufferedImage result = new BufferedImage(w, h, COLOUR_OUTPUT);
		Graphics2D g = result.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return result;
	}

	public static BufferedImage shrink(BufferedImage source, double factor) {
            int w = (int) (source.getWidth() * factor);
            int h = (int) (source.getHeight() * factor);
            return scale(source, w, h);
	}

	public static BufferedImage copyBufferedImage(BufferedImage image) {
            BufferedImage copyOfIm = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            Graphics2D g = copyOfIm.createGraphics();
            g.drawRenderedImage(image, null);
            g.dispose();
            return copyOfIm;
        }
        
	private boolean sendImage(byte[] imageData, String multicastAddress,int port) {
            InetAddress ia;

            boolean ret = false;
            int ttl = 2;

            try {
                ia = InetAddress.getByName(multicastAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return ret;
            }

            MulticastSocket ms = null;

            try {
                ms = new MulticastSocket();
                ms.setTimeToLive(ttl);
                DatagramPacket dp = new DatagramPacket(imageData, imageData.length,
                                ia, port);
                ms.send(dp);
                ret = true;
            } catch (IOException e) {
                e.printStackTrace();
                ret = false;
            } finally {
                if (ms != null) {
                    ms.close();
                }
            }
            return ret;
	}
        
	public String test(String Directorynya) {
            MulticastSenderNew sender = new MulticastSenderNew();
            int sessionNumber = 0;
            boolean multicastImages = false;
            
            IMAGENYA = Directorynya;
            
            System.out.println("ini : "+Directorynya);

            if(new File(IMAGENYA).exists()) {
                    System.out.println("Multicasting images...");
                    multicastImages = true;
            }
            else {
                    System.out.println("Multicasting screenshots...");
            }

            

            try {
                BufferedImage image;

                /* Get image or screenshot */
                if(multicastImages) {
                    //image = getRandomImageFromDir(new File("images"));
                    image = getNotRandomImageFromDir(new File("images"));
                }
                else {
                    image = getScreenshot();
                }

                /* Scale image */
                image = shrink(image, SCALING);
                byte[] imageByteArray = bufferedImageToByteArray(image, OUTPUT_FORMAT);
                int packets = (int) Math.ceil(imageByteArray.length / (float)DATAGRAM_MAX_SIZE);

                /* If image has more than MAX_PACKETS slices -> error */
                if(packets > MAX_PACKETS) {
                    System.out.println("Image is too large to be transmitted!");

                }

                /* Loop through slices */
                for(int i = 0; i <= packets; i++) {
                        int flags = 0;
                        flags = i == 0 ? flags | SESSION_START: flags;
                        flags = (i + 1) * DATAGRAM_MAX_SIZE > imageByteArray.length ? flags | SESSION_END : flags;

                        int size = (flags & SESSION_END) != SESSION_END ? DATAGRAM_MAX_SIZE : imageByteArray.length - i * DATAGRAM_MAX_SIZE;

                        /* Set additional header */
                        byte[] data = new byte[HEADER_SIZE + size];//set headernya supaya client gak bingung
                        data[0] = (byte)flags;
                        data[1] = (byte)sessionNumber;
                        data[2] = (byte)packets;
                        data[3] = (byte)(DATAGRAM_MAX_SIZE >> 8);
                        data[4] = (byte)DATAGRAM_MAX_SIZE;
                        data[5] = (byte)i;
                        data[6] = (byte)(size >> 8);
                        data[7] = (byte)size;

                        /* Copy current slice to byte array */
                        System.arraycopy(imageByteArray, i * DATAGRAM_MAX_SIZE, data, HEADER_SIZE, size);
                        /* Send multicast packet */
                        sender.sendImage(data, IP_ADDRESS, PORT);

                        /* Leave loop if last slice has been sent */
                        if((flags & SESSION_END) == SESSION_END) break;
                }
                /* Sleep */
                Thread.sleep(SLEEP_MILLIS);

                /* Increase session number */
                sessionNumber = sessionNumber < MAX_SESSION_NUMBER ? ++sessionNumber : 0;
                    
            } catch (Exception e) {
                    e.printStackTrace();
            }
            return "";
	}

}

class ImageFileFilter implements FilenameFilter
{
    public boolean accept( File dir, String name )
    {
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
}
