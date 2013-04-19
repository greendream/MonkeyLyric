
package monkeylyric.tool;

import java.awt.Window;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JOptionPane;

public class GraphicsConfig {
    /*
     * source: http://java.sun.com/developer/technicalArticles/GUI/translucent_shaped_windows/
     * edit by Pham Duy
     */
    
    private static boolean isShapeSupported;
    private static boolean isOpacitySupported;
    private static boolean isTranslucencySupported;
    private static GraphicsConfiguration translucencyCapableGC;
    @SuppressWarnings("unused")
	private static ComponentListener shapeListener = null;

    public GraphicsConfig(){
        setGraphicsValues();
    }
    
    static {
        setGraphicsValues();
    }
    
    public static boolean setGraphicsValues(){
        isShapeSupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSPARENT);
        isOpacitySupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.TRANSLUCENT);
        isTranslucencySupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSLUCENT);
        
        translucencyCapableGC = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
        //JOptionPane.showMessageDialog(null, "Opacity Supported: " + isOpacitySupported + " \n" + "Translucency Supported: " + isTranslucencySupported);
        
        if (!AWTUtilitiesWrapper.isTranslucencyCapable(translucencyCapableGC)) {
            JOptionPane.showMessageDialog(null, "DefaultConfiguration: is not Translucency Capable Graphics Configuration !"
                    + "                     \n... Cau hinh lai Graphics Configuration...");
            translucencyCapableGC = null;

            GraphicsEnvironment env =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();

            for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {
                GraphicsConfiguration[] configs = devices[i].getConfigurations();
                for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {
                    if (AWTUtilitiesWrapper.isTranslucencyCapable(configs[j])) {
                        translucencyCapableGC = configs[j];
                    }
                }
            }
            if (translucencyCapableGC == null) {
                isTranslucencySupported = false;
            }
        }
        
        return (isOpacitySupported && isTranslucencySupported);
    }
 
    public static GraphicsConfiguration getGC(){
        return translucencyCapableGC;
    }
    
    public static synchronized void applyOpacity(Window win, int value) {
        if (!isOpacitySupported) {
            return;
        }
        AWTUtilitiesWrapper.setWindowOpacity(win, value / 100.0f);
    }
    
    public static synchronized void applyShape(Window win) {
        if (!isShapeSupported) {
           return;
        }
        final Window fd = win;
        fd.addComponentListener(shapeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                Shape shape = null;
                shape = new RoundRectangle2D.Float(0, 0, fd.getWidth(), fd.getHeight(), 15, 15);
                AWTUtilitiesWrapper.setWindowShape(fd, shape);
            }
        });
    }
    
    public static synchronized void applyShape(Window win, final int x, final int y) {
        if (!isShapeSupported) {
            return;
        }
        final Window fd = win;
        fd.addComponentListener(shapeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                Shape shape = null;
                shape = new RoundRectangle2D.Float(0, 0, fd.getWidth(), fd.getHeight(), x, y);
                AWTUtilitiesWrapper.setWindowShape(fd, shape);
            }
        });
    }
}
