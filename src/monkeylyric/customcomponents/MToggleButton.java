/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.customcomponents;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author anhle
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
        _start = new ImageIcon(start);
        _toggleOn = new ImageIcon(toggleOn);
        _toggleOff = new ImageIcon(toggleOff);
        
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
