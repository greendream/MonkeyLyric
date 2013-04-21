package monkeylyric.interfaces;

import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.General;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LeAnh
 */
public interface ILyricPlayer {
    public void play();
    public void pase();
    public void stop();
    public void seek(long time);
    public long getCurrentPlayTime();
    public void setCurrentPlayTime(long time);
    public Lyric getLyric();
}
