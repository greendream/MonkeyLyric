/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.tool;

import java.io.File;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
/**
 *
 * @author Pham Duy
 */
public class ImageTools {
    
    public static java.awt.Image loadImage(String url){
        try {
            File f = new File("");
            f = new File(f.getAbsoluteFile()+"\\"+url);
            if (!f.exists()){
                return null;
            }
            Image img = new ImageIcon(f.getPath()).getImage(); // faster
            return img;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static Image loadImageResource(URL url){
        return new ImageIcon(url).getImage();
    }
    
    // default version of createBufferedImage
    public static BufferedImage createBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage
           (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }

    public static int getAlpha(BufferedImage img, int x, int y){
        int pixel = img.getRGB(x, y);
        return (pixel >> 24) & 0x000000FF;
    }
}
