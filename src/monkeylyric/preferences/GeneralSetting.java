/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.preferences;

import monkeylyric.lyric.Lyric;

/**
 *
 * @author LeAnh
 */
public class GeneralSetting {
    
    private static GeneralSetting _instance;    
    private Lyric _lyric;
    private long _time;
    
    public GeneralSetting() {
         _lyric = new Lyric("lyrics/One Direction-What Makes You Beautiful.lrc");
         _time = 0;
    }
    
    public static GeneralSetting getInstance() {
        if (_instance == null) {
            _instance = new GeneralSetting();
        }
        
        return _instance;
    }

    /**
     * @return the _lyric
     */
    public Lyric getLyric() {
        return _lyric;
    }

    /**
     * @param lyric the _lyric to set
     */
    public void setLyric(Lyric lyric) {
        this._lyric = lyric;
    }

    /**
     * @return the _time
     */
    public long getTime() {
        return _time;
    }

    /**
     * @param time the _time to set
     */
    public void setTime(long time) {
        this._time = time;
    }
}
