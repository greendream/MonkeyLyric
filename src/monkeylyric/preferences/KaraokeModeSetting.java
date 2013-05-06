/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.preferences;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author LeAnh
 */
public class KaraokeModeSetting {
    private static KaraokeModeSetting _instance;
    private Color _backGround;
    private Color _activeForeground;
    private java.awt.Color _deactiveForeground;
    private Font _font;
    
    // Set for default setting frame ScrollingMode
    public KaraokeModeSetting() {
        _backGround = new Color(200, 110, 50, 150);
        _font = new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 23);
        _activeForeground = Color.YELLOW;
        _deactiveForeground = Color.BLUE;
    }

    /**
     * @return the _instance
     */
    static public KaraokeModeSetting getInstance() {
        if (_instance == null) {
            _instance = new KaraokeModeSetting();
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
