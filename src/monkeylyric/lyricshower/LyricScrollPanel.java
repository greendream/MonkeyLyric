/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyricshower;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import monkeylyric.lyric.Lyric;
import monkeylyric.lyric.SubLyric;
import monkeylyric.preferences.General;
import monkeylyric.preferences.ScrollingModeSetting;

/**
 *
 * @author LeAnh
 */
public class LyricScrollPanel extends JPanel {

    public static final int boderSize = 10;
    public static final int lineDistance = 2;
    private int _oldPlayLine;
    private long _nextY;
    private long _currY;
    private boolean _isPaintLyric;
    
    public LyricScrollPanel() {
        super();
        setBorder(BorderFactory.createEmptyBorder(boderSize,
                boderSize,
                boderSize,
                boderSize));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350, 250));
        setBackground(new java.awt.Color(0, 0, 0, 0));
        _oldPlayLine = 0;
        _currY = 0;
        _nextY = 0;
        _isPaintLyric = false;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        // Prepare for draw lyric
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(ScrollingModeSetting.getInstance().getFont());
        
        // Don't draw lyric and Show name song + wait time to start
        if (_isPaintLyric == false) {
            // Draw wait time
            long wait = getLyric().getWaitTimeToStart() - getTime();
            String waitTime = wait/100 + " seconds.";
            g2.setColor(ScrollingModeSetting.getInstance().getActiveForeground());
            // Draw song name
            g2.drawString(getLyric().getSongName(),
                          10,
                          this.getHeight()/2
                         );
            // Draw wait time
            g2.drawString(waitTime,
                          this.getWidth()/2 - getFontWidth(waitTime)/2,
                          this.getHeight()/2 + getFontHeight() + LyricScrollPanel.lineDistance
                         );
            return;
        }
        
        /* Draw current sub string is playing */
        // Determine current play line
        int currPlayLine = getLyric().indexOf(getTime());
        
        // If change line is playing
        if (_oldPlayLine < currPlayLine) {       
            _oldPlayLine = currPlayLine;
            // Compute next currY
            _currY = _nextY;
        } else {
            // Not change line is playing
            if (_currY == 0) {
                _currY = this.getHeight()/2 + getFontHeight()/2;
            } else {
                if (_isPaintLyric) {
                    _currY -= 1;
                }
            }
            _nextY = _currY + lineDistance + getFontHeight() - 1;
        }
        g2.setColor(ScrollingModeSetting.getInstance().getActiveForeground());
        g2.drawString(getLyric().getSubLyricPlaying(getTime()).getContent(), 10, _currY);
        

        /* Draw previous sub string of the sub string which is playing */
        g2.setColor(ScrollingModeSetting.getInstance().getDeactiveForeground());
        long previousLine; // the munber of previous line need to show
        previousLine = _currY / getFontHeight();
        
        if (_currY % getFontHeight() > 0) {
            previousLine++;
        }
        
        for (int i = 1; i <= previousLine; i++) {
            if (getLyric().indexOf(getTime()) - i >= 0) {
                SubLyric sub = getLyric().getSubLyric(getLyric().indexOf(getTime()) - i);
                long previouseY = _currY - getFontHeight() * i - lineDistance;
                g2.drawString(sub.getContent(), 10, previouseY);
            } else {
                break;
            }
        }

        /* Draw next sub string of the sub string which is playing */
        g2.setColor(ScrollingModeSetting.getInstance().getDeactiveForeground());
        long forwardLine; // the munber of forward line need to show
        forwardLine = (this.getHeight() - _currY) / getFontHeight();
        
        if ((this.getHeight() - _currY) / getFontHeight() > 0) {
            forwardLine++;
        }
        
        for (int i = 1; i <= forwardLine; i++) {
            if (getLyric().indexOf(getTime()) + i < getLyric().getNumberOfSublyric()) {
                SubLyric sub = getLyric().getSubLyric(getLyric().indexOf(getTime()) + i);
                long forwardY = _currY + getFontHeight() * i + lineDistance;
                g2.drawString(sub.getContent(), 10, forwardY);
            } else {
                break;
            }
        }
        g2.dispose();
        _isPaintLyric = false;
    }

    public void setTime(long time) {
        General.getInstance().setTime(time);
    }
    
    public void setIsRepaintLyric(boolean isPaintLyric) {
        _isPaintLyric = isPaintLyric;
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
    
    public void mouseWheelMovedHandler(int rotation) {
        setTime(getLyric().getSubLyric(getLyric().indexOf(getTime()) + rotation).getTime());
        _oldPlayLine = getLyric().indexOf(getTime()) + 1;        
        _currY = this.getHeight()/2 + getFontHeight()/2;
        _nextY = _currY + getFontHeight() + lineDistance;
    }

    /**
     * @return the _time
     */
    public long getTime() {
        return General.getInstance().getTime();
    }

    /**
     * @return the _lyric
     */
    public Lyric getLyric() {
        return General.getInstance().getLyric();
    }
}
