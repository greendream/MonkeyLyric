/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyricshower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import monkeylyric.interfaces.ILyricPlayer;
import monkeylyric.custom.MFrame;
import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.GeneralSetting;
import monkeylyric.preferences.KaraokeModeSetting;

/**
 *
 * @author LeAnh
 */
public class KaraokeMode extends MFrame implements ILyricPlayer,
                                                   ActionListener {
    
    private Timer _timer;
    
    public KaraokeMode() {
        super("Mini-Lyric", KaraokeModeSetting.getInstance().getBackGround()); 
    }
    
    @Override
    public void play() {
        _timer.start();
    }

    public void showWindow() {
        this.setVisible(true);
        this.paintRoundRectangleBorder();
    }
    
    @Override
    public void pase() {
        _timer.stop();
    }

    @Override
    public void stop() {
        _timer.stop();
//        _lyricScrollPanel.setTime(0);
        
    }
    
    @Override
    public void seek(long time) {
        
    }

    public void getLyric(Lyric lyric) {
        GeneralSetting.getInstance().setLyric(lyric);
    }
    
    public Lyric getLyric() {
        return GeneralSetting.getInstance().getLyric();
    }

    @Override
    public long getCurrentPlayTime() {
        return GeneralSetting.getInstance().getTime();
    }

    @Override
    public void setCurrentPlayTime(long time) {
        GeneralSetting.getInstance().setTime(time);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle when _timer event occour
        if (e.getSource() == _timer) {
//            setCurrentPlayTime(_lyricScrollPanel.getTime() + 10);
//            
//            // Repaint lyric
//            if (_lyricScrollPanel.getTime() >= getLyric().getWaitTimeToStart() - 50) {
//                _lyricScrollPanel.validate();
//                _lyricScrollPanel.setIsRepaintLyric(true);
//                this.repaint();
//            }
//            
//            if (_lyricScrollPanel.getTime() >= getLyric().getTotalPlayTime())
//            {
//                _timer.stop();
//                _lyricScrollPanel.setTime(0);
//                this.repaint();
//            }
        }
    }
}
