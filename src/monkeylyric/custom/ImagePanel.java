
package monkeylyric.custom;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class ImagePanel extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image img = null;

  public ImagePanel(String imgUrl) {
        setImage(imgUrl);
        init();
  }

  public ImagePanel(Image img) {
        this.img = img;
        init();
  }
  
  private void init(){
      setOpaque(false);
  }

  public void setImage(Image img) {
      this.img = img;
      repaint();
  }
  
  public void setImage(String imgUrl){
      ImageIcon imgIcon = new ImageIcon(getClass().getResource(imgUrl));
      if (imgIcon != null) {
          setImage(imgIcon.getImage());
      } else {
          img = null;
      }
  }
  
   @Override
   protected void paintComponent(Graphics g) {
       if(img != null){
           g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), // des
                   0, 0, img.getWidth(null), img.getHeight(null), null); // source
       }
       super.paintComponent(g);
   }
}
