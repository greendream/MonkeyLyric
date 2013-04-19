    /*
 * This class construct all instance class to run Monkey-Lyric
 */
package monkeylyric;

import javax.swing.UIManager;
import monkeylyric.lyric.Lyric;
import monkeylyric.lyricshower.ScrollingMode;

/**
 *
 * @author anhle
 */
public class MonkeyLyric {

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
        Lyric lyric = new Lyric("lyrics/One Direction-What Makes You Beautiful.lrc");
        lyric.printLyric();
        
        ScrollingMode scroll = new ScrollingMode();
        scroll.setLyric(lyric);
        scroll.setCurrentPlayTime(0);
        scroll.play();
        
        
    }
}
