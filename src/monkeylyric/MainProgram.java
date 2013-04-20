    /*
 * This class construct all instance class to run Monkey-Lyric
 */
package monkeylyric;

import javax.swing.UIManager;
import monkeylyric.lyric.Lyric;
import monkeylyric.lyricshower.ScrollingMode;
import monkeylyric.preferences.General;

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
        } catch (Exception cn) {
        }
        
        System.out.println("Welcome to Monkey-Lyric !");
        General.getInstance().getLyric().printLyric();
        
        ScrollingMode scroll = new ScrollingMode();
        scroll.play();
        
    }
}
