package monkeylyric.tool;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Random;
    
public class WindowTools {
    
    // variables difination
    public static Dimension SCR_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static java.awt.GraphicsEnvironment GRAPHICS_ENV = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
    // end variable definaton
    
    public static void setMaximize(javax.swing.JFrame fr){
        fr.setMaximizedBounds(GRAPHICS_ENV.getMaximumWindowBounds());
        fr.setExtendedState( javax.swing.JFrame.MAXIMIZED_BOTH);
    }
    
    public static void showDialog(Window win, java.awt.Point mouseLocation){
        int x = mouseLocation.x;
        int y = mouseLocation.y;
        if(y < win.getHeight() && SCR_SIZE.height - y < win.getHeight() ){
            if(SCR_SIZE.width - x >= win.getWidth()){
                win.setLocation(x,  (SCR_SIZE.height-win.getHeight())/2);
            } else {
                win.setLocation(x - win.getWidth(),  (SCR_SIZE.height-win.getHeight())/2);
            }
        } else if(y >= win.getHeight()){
            if(SCR_SIZE.width - x >= win.getWidth()){
                win.setLocation(x, y - win.getHeight());
            } else {
                win.setLocation(x - win.getWidth(), y - win.getHeight());
            }
        } else {
            if(SCR_SIZE.width - x >= win.getWidth()){
                win.setLocation(x, y);
            } else {
                win.setLocation(x - win.getWidth(), y);
            }
        }
        win.setVisible(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Get Location Methods">
    public static Point getCenterLocation(Dimension winSize){
        return new Point((SCR_SIZE.width-winSize.width)/2,(SCR_SIZE.height-winSize.height)/2);
    }
    
    public static Point getTopRightLocation(Dimension winSize, int margin){
        return new Point((SCR_SIZE.width - winSize.width) - margin, margin);
    }
    
    public static Point getTopCenterLocation(Dimension winSize, int margin){
        return new Point ((SCR_SIZE.width - winSize.width) / 2, margin);
    }
 
    public static Point getLeftCenterLocation(Dimension winSize, int margin){
        return new Point(margin, (SCR_SIZE.height-winSize.height)/2 + margin);
    }
    
    public static Point getRightCenterLocation(Dimension winSize, int margin){
        return new Point((SCR_SIZE.width-winSize.width) - margin, (SCR_SIZE.height-winSize.height) / 2);
    }

    public static Point getRandomLocationOutsideSCR(Dimension windowSize){
//        long time = System.nanoTime();
        Random r = new Random();
        int[] x = new int[3];
        x[0] = - ( r.nextInt( SCR_SIZE.width ) + windowSize.width ); // left
        if (SCR_SIZE.width - windowSize.width <= 0 ){ // inside screen
            x[1] = 0;
        } else {
            x[1] = r.nextInt(SCR_SIZE.width - windowSize.width) ; // inside screen
        }
        x[2] = SCR_SIZE.width + r.nextInt( SCR_SIZE.width ); // right
        
        int[] y = new int[3];
        y[0] = - ( r.nextInt( SCR_SIZE.height ) + windowSize.height ); // above
        if (SCR_SIZE.height - windowSize.height <= 0 ){ // inside screen
            y[1] = 0;
        } else {
            y[1] = r.nextInt(SCR_SIZE.height - windowSize.height) ; // inside screen
        }
        y[2] = SCR_SIZE.height + r.nextInt( SCR_SIZE.height ); // behind
        
        int i = r.nextInt(3);
        int j;
        while((j = r.nextInt(3)) == i);
//        System.out.println(System.nanoTime() - time);
        return new Point(x[i], y[j]);
    }
    
    public static Point getRandomLocationInsideSCR(Dimension winSize){
        Random r = new Random();
        int x = SCR_SIZE.width - winSize.width - 500;
        if (x > 0) {
            x = r.nextInt(x) + 200;
        } else {
            x = 0;
        }
        int y = SCR_SIZE.height - winSize.height - 300;
        if (y > 0) {
            y = r.nextInt(y) + 100;
        } else {
            y = 0;
        }
        return new Point( x, y );
    }
    
    public static Point getOppLCOutsideSCR(Dimension midWinSize, Point point1, Point midPoint){
        if(point1.x == midPoint.x && point1.y == midPoint.y){
            return new Point(- midWinSize.width, 0);
        }
        Point point2 = new Point(2* midPoint.x - point1.x, 2* midPoint.y - point1.y);
        float a = (float)(point1.x - midPoint.x) / (point1.y - midPoint.y);
        float b = - a * point1.y + point1.x;
        while( (point2.x + midWinSize.width > 0 && point2.x < SCR_SIZE.width) // location inside screen
                && (point2.y + midWinSize.height > 0 && point2.y < SCR_SIZE.height)){
            point2.y += ( point1.x > midPoint.x) ?  -20 : 20;
            point2.x += a*point2.y + b;
        }
        //System.out.println(point2);
        return point2;
    }
    //</editor-fold>
    
}