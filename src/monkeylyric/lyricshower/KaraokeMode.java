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
import monkeylyric.preferences.General;

/**
 *
 * @author LeAnh
 */
public class KaraokeMode extends MFrame implements ILyricPlayer,
                                                   ActionListener {
    
    private Timer _timer;
    
    public KaraokeMode() {
        super();
    }
    
    public KaraokeMode(String title) {
        super(title);
    }

    @Override
    public void play() {
        _timer.start();
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
        General.getInstance().setLyric(lyric);
    }
    
    public Lyric getLyric() {
        return General.getInstance().getLyric();
    }

    @Override
    public long getCurrentPlayTime() {
        return General.getInstance().getTime();
    }

    @Override
    public void setCurrentPlayTime(long time) {
        General.getInstance().setTime(time);
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
