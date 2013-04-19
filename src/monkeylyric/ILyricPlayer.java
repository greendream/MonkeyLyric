package monkeylyric;

import monkeylyric.lyric.Lyric;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anhle
 */
public interface ILyricPlayer {
    public void play();
    public void pase();
    public void stop();
    public void seek(long time);
    public Lyric getLyric();
    public void setLyric(Lyric lrc);
    public long getCurrentPlayTime();
    public void setCurrentPlayTime(long time);
}
