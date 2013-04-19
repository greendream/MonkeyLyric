/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyric;


/**
 *
 * @author TheRise
 */
public class SubLyric implements Comparable<Object>{
    private long _time;      // _time to start show this line of lyric
    private String _content; // _content of this line

    public SubLyric() {
        _content = "";
        _time = 0;
    }
    
    public SubLyric(long time, String content) {
        this._time = time;
        this._content = content;
    }

    @Override
    public int compareTo(Object o) {
        SubLyric p = (SubLyric) o; 
        return (int)(this.getTime() - p.getTime() );     
    }

    /**
     * @return the _time
     */
    public long getTime() {
        return _time;
    }

    /**
     * @param _time the _time to set
     */
    public void setTime(long time) {
        this._time = time;
    }

    /**
     * @return the _content
     */
    public String getContent() {
        return _content;
    }

    /**
     * @param _content the _content to set
     */
    public void setContent(String content) {
        this._content = content;
    }
}
