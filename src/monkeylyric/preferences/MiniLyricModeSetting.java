/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.preferences;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author anhle
 */
public class MiniLyricModeSetting {
    private static MiniLyricModeSetting _instance;
    private Color _backGround;
    private Color _activeForeground;
    private java.awt.Color _deactiveForeground;
    private Font _font;
    
    // Set for default setting frame ScrollingMode
    public MiniLyricModeSetting() {
        // If set background alpha = 0. Frame will cancel all event
        _backGround = new Color(0, 0, 0, 20);
        _font = new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 40);
        _activeForeground = Color.YELLOW;
        _deactiveForeground = Color.BLUE;
    }

    /**
     * @return the _instance
     */
    static public MiniLyricModeSetting getInstance() {
        if (_instance == null) {
            _instance = new MiniLyricModeSetting();
        }
        return _instance;
    }

    /**
     * @return the _backGround
     */
    public java.awt.Color getBackGround() {
        return _backGround;
    }

    /**
     * @param backGround the _backGround to set
     */
    public void setBackGround(java.awt.Color backGround) {
        this._backGround = backGround;
    }

    /**
     * @return the _font
     */
    public Font getFont() {
        return _font;
    }

    /**
     * @param font the _font to set
     */
    public void setFont(Font font) {
        this._font = font;
    }

    /**
     * @return the _activeForeground
     */
    public Color getActiveForeground() {
        return _activeForeground;
    }

    /**
     * @param activeForeground the _activeForeground to set
     */
    public void setActiveForeground(Color activeForeground) {
        this._activeForeground = activeForeground;
    }

    /**
     * @return the _deactiveForeground
     */
    public java.awt.Color getDeactiveForeground() {
        return _deactiveForeground;
    }

    /**
     * @param deactiveForeground the _deactiveForeground to set
     */
    public void setDeactiveForeground(java.awt.Color deactiveForeground) {
        this._deactiveForeground = deactiveForeground;
    }

}
