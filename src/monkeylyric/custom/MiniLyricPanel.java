/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.custom;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.GeneralSetting;
import monkeylyric.preferences.ScrollingModeSetting;

/**
 *
 * @author anhle
 */
public class MiniLyricPanel extends JPanel{
    
    private void init() {
        setBackground(new java.awt.Color(0, 0, 0, 10));
    }
    
    public MiniLyricPanel() {
        super();
        init();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        /* Draw last lyric */
        
        /* Draw current lyric */
        
        /* Draw next lyric */
        
    }
    
    public void setTime(long time) {
        GeneralSetting.getInstance().setTime(time);
    }
    
    public int getFontHeight() {
        Font font = ScrollingModeSetting.getInstance().getFont();
        int height = this.getGraphics().getFontMetrics(font).getHeight();
        return height;
    }
    
    public int getFontWidth(String s) {
        Font font = ScrollingModeSetting.getInstance().getFont();
        int width = this.getGraphics().getFontMetrics(font).stringWidth(s);
        return width;
    }

    /**
     * @return the time
     */
    public long getTime() {
        return GeneralSetting.getInstance().getTime();
    }

    /**
     * @return the lyric
     */
    public Lyric getLyric() {
        return GeneralSetting.getInstance().getLyric();
    }

}
