/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyricshower;

import monkeylyric.interfaces.ILyricPlayer;
import monkeylyric.custom.MFrame;
import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.General;

/**
 *
 * @author LeAnh
 */
public class KaraokeMode extends MFrame implements ILyricPlayer{
    public KaraokeMode() {
        super();
    }
    
    public KaraokeMode(String title) {
        super(title);
    }

    @Override
    public void play() {
        
    }

    @Override
    public void pase() {
            }

    @Override
    public void stop() {
        
    }

    @Override
    public void seek(long time) {
        
    }

    public Lyric getLyric() {
        return General.getInstance().getLyric();
    }

    @Override
    public long getCurrentPlayTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrentPlayTime(long time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
