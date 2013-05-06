/*
 * This class construct all instance class to run Monkey-Lyric
 */
package monkeylyric;

import javax.swing.UIManager;
import monkeylyric.lyricshower.MiniLyricMode;
import monkeylyric.lyricshower.ScrollingMode;
import monkeylyric.preferences.GeneralSetting;

/**
 *
 * @author LeAnh
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            String cn = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(cn);
        } catch (Exception e) {
        }
        
        System.out.println("Welcome to Monkey-Lyric !");
        GeneralSetting.getInstance().getLyric().printLyric();
        
        ScrollingMode scroll = new ScrollingMode();
        scroll.showWindow();
        scroll.play();
        
        MiniLyricMode miniLyric = new MiniLyricMode();
        miniLyric.showWindow();
    }
}
