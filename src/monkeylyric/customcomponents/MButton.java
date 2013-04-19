/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.customcomponents;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import monkeylyric.preferences.ScrollingModeSetting;

/**
 *
 * @author anhle
 */
public class MButton extends JButton{
    private ImageIcon _startButton;
    private ImageIcon _startButtonHover;
    private ImageIcon _startButtonActive;

    public MButton() {
        super();
    }
    
    public MButton(String text) {
        super(text);
    }
    
    public MButton(ImageIcon start, ImageIcon hover, ImageIcon active) {
        super();
        _startButton = start;
        _startButtonHover = hover;
        _startButtonActive = active;
        
        this.setIcon(_startButton);
        this.setRolloverIcon(_startButtonHover);
        this.setPressedIcon(_startButtonActive);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setBackground(ScrollingModeSetting.getInstance().getBackGround());
    }
    
    public MButton(String start, String hover, String active) {
        super();
        _startButton = new ImageIcon(start);
        _startButtonHover = new ImageIcon(hover);
        _startButtonActive = new ImageIcon(active);
        
        this.setIcon(_startButton);
        this.setRolloverIcon(_startButtonHover);
        this.setPressedIcon(_startButtonActive);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setContentAreaFilled(false);
        this.setFocusable(false);
    }
    
    
    /**
     * @return the _startButton
     */
    public ImageIcon getStartButton() {
        return _startButton;
    }

    /**
     * @param startButton the _startButton to set
     */
    public void setStartButton(ImageIcon startButton) {
        this._startButton = startButton;
    }

    /**
     * @return the _startButtonHover
     */
    public ImageIcon getStartButtonHover() {
        return _startButtonHover;
    }

    /**
     * @param startButtonHover the _startButtonHover to set
     */
    public void setStartButtonHover(ImageIcon startButtonHover) {
        this._startButtonHover = startButtonHover;
    }

    /**
     * @return the _startButtonActive
     */
    public ImageIcon getStartButtonActive() {
        return _startButtonActive;
    }

    /**
     * @param startButtonActive the _startButtonActive to set
     */
    public void setStartButtonActive(ImageIcon startButtonActive) {
        this._startButtonActive = startButtonActive;
    }
        
}
