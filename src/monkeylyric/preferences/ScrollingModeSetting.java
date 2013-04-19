/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.preferences;

import java.awt.Font;

/**
 *
 * @author LeAnh
 */
public class ScrollingModeSetting {
    private static ScrollingModeSetting _instance;
    private java.awt.Color _backGround;
    private Font _font;
    
    // Set for default setting frame ScrollingMode
    public ScrollingModeSetting() {
        _backGround = new java.awt.Color(200, 210, 200, 150);
        _font = new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20);
    }

    /**
     * @return the _instance
     */
    static public ScrollingModeSetting getInstance() {
        if (_instance == null) {
            _instance = new ScrollingModeSetting();
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

}
