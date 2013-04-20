/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.custom;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

/**
 *
 * @author LeAnh
 */
public class MToggleButton extends JToggleButton{
    private ImageIcon _start;
    private ImageIcon _toggleOn;
    private ImageIcon _toggleOff;

    public MToggleButton() {
        super();
    }
    
    public MToggleButton(ImageIcon start, ImageIcon toggleOn, ImageIcon toggleOff) {
        super();
        _start = start;
        _toggleOn = toggleOn;
        _toggleOff = toggleOff;
        
        this.setIcon(_start);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
    }
    
    public MToggleButton(String start, String toggleOn, String toggleOff) {
        super();
        
        _start = new ImageIcon(this.getClass().getResource(start));
        _toggleOn = new ImageIcon(this.getClass().getResource(toggleOn));
        _toggleOff = new ImageIcon(this.getClass().getResource(toggleOff));
        
        this.setIcon(_start);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
    }
    
    public void setToggleOn() {
        this.setIcon(_toggleOn);
        this.repaint();
    }
  
    public void setToggleOff() {
        this.setIcon(_toggleOff);
        this.repaint();
    }
        
}
