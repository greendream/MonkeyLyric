/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.tool;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Browser{ 
/**
* Method to Open the Broser with Given URL
* @param url
* @Souce http://javaxden.blogspot.com/2007/09/launch-web-browser-through-java.html
*/
    public static boolean isInternetReachable(){
        try {
            InetAddress address = InetAddress.getByName("java.sun.com");
            if(address == null){
                return false;
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            return false;
        }catch (@SuppressWarnings("hiding") java.io.IOException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void openUrl(String url){
        String os = System.getProperty("os.name");
        Runtime runtime = Runtime.getRuntime();
        try{
        // Block for Windows Platform
        if (os.startsWith("Windows")){
            String cmd = "rundll32 url.dll,FileProtocolHandler "+ url;
            runtime.exec(cmd);
        }
        //Block for Mac OS
        else if(os.startsWith("Mac OS")){
            Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] {String.class});
            openURL.invoke(null, new Object[] {url});
        }
        //Block for UNIX Platform 
        else {
            String[] browsers = {"chromium","firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < 7 && browser == null; count++)
            if (runtime.exec(new String[] {"which", browsers[count]}).waitFor() == 0)
            browser = browsers[count];
            if (browser == null)
            throw new Exception("Could not find web browser");
            else 
            runtime.exec(new String[] {browser, url});
        }
        }catch(Exception x){
            System.err.println("Exception occurd while invoking Browser!");
            x.printStackTrace();
        }
    }
}
