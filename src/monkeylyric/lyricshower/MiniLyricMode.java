/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyricshower;

import javax.swing.ImageIcon;
import monkeylyric.custom.MFrame;
import monkeylyric.custom.MiniLyricPanel;
import monkeylyric.interfaces.ILyricPlayer;
import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.GeneralSetting;
import monkeylyric.preferences.MiniLyricModeSetting;

/**
 *
 * @author anhle
 */
public class MiniLyricMode extends      MFrame
                           implements   ILyricPlayer {
    
    private MiniLyricPanel _miniLyricPanel;
    
    private void init() {
        this.getClass().getResource("/resources/images/ICON.png");
        ImageIcon icon = new ImageIcon();
        this.setIconImage(icon.getImage());
        
        _miniLyricPanel = new MiniLyricPanel();
        this.getCenterPanel().add(_miniLyricPanel);
        this.setBounds(600, 200, 400, 200);
        //this.setLocationRelativeTo(null);
    }
    
    public MiniLyricMode() {
        super("Monkey-Lyric", MiniLyricModeSetting.getInstance().getBackGround()); 
        init();
    }

    public void showWindow() {
        this.setVisible(true);
        this.paintRoundRectangleBorder();
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

    @Override
    public long getCurrentPlayTime() {
        return GeneralSetting.getInstance().getTime();
    }

    @Override
    public void setCurrentPlayTime(long time) {
        
    }

    @Override
    public Lyric getLyric() {
        return GeneralSetting.getInstance().getLyric();
    }
}
