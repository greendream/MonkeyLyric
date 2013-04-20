/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyric;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.mozilla.universalchardet.UniversalDetector;


/**
 *
 * @author LeAnh
 */
public class Lyric {
    
    private ArrayList<SubLyric> _lyricsArray;

    public void init() {
        _lyricsArray = new ArrayList<>(); 
    }
    
    public Lyric() {
        init();
    }

    /**
     * Constructor for new lyric
     * @param path path to lyric file
     */
    public Lyric(String path) {
        init();
        readFile(path);
    }
    
    /**
     * Detect char-set save file
     * @param fileName
     * @return
     * @throws java.io.IOException 
     */
    public static String detectCharset(String fileName)throws java.io.IOException {
        byte[] buf = new byte[4096];

        FileInputStream fis = new FileInputStream(fileName);

        // (1)
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
          detector.handleData(buf, 0, nread);
        }
        // (3)
        detector.dataEnd();

        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            return encoding;
        }

        // (5)
        detector.reset();
        return "UTF-8";
    }
    
    /**
     * Load content of lyric file
     * @param filename 
     */
    private void readFile(String fileName) {
            try {
                // Open file as File input stream.
                FileInputStream is = new FileInputStream(fileName);
                Scanner input = new Scanner(is,detectCharset(fileName));

                ArrayList<SubLyric> sublyricArray = new ArrayList<>();
                while(input.hasNextLine()) {          
                    //Đọc 1 dòng
                    String text = input.nextLine();

                    List<String> l = split1(text);
                    if(l.size()>0) {
                        for(int i = 0; i < l.size()-1; i++) {
                            long time = split3(l.get(i));
                            String _s = l.get(l.size()-1);
                            sublyricArray.add(new SubLyric(time, _s));
                        }  
                    }
                }
                
                Collections.sort(sublyricArray);           
                this.setLyricsArray(sublyricArray);
                
                // Release resource used to read file
                is.close();
                input.close();
                
                System.out.println("Read File: '" + fileName + "' complete !");
                
            } catch (IOException e) {            
            }
        }



    /**
    * Return list of lyric string and the end lyric
    * @param s
    * @return 
    */
    public List<String> split1(String s) {
         List<String> list = new ArrayList<>();
         int i1 = 0;
         for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '[') {
                i1 = i;
            }
            
            if(s.charAt(i) == ']') {
                 String s1 = "";
                 while(i1 < i-1) {
                     s1 = s1 + s.charAt(i1 + 1);
                     i1++;
                 }
                 
                 list.add(s1);
             }
         }

         if(!s.equals("")){
            String t2 = s.substring(i1+2);
            list.add(t2);
         }
         
         return list;
     }
    
    /**
     * Return info of lyric (title, _ar, _id)
     * @param s
     * @return 
     */
    public List<String> split2(String s){
        List<String> list = new ArrayList<>();
        String s1 = "";
        
        for(int i = 0; i < s.length(); i++){
             if(s.charAt(i) == ':')
             {
                 s1 = s.substring(0,i);
                 list.add(s1);
                 s1 = s.substring(i+1);
                 list.add(s1);
             }
        }
         
        return list;
     }

    /**
     * Return the time
     * @param s
     * @return 
     */
    public long split3(String s){
        long time = 0;
        String s1 = "";
        String s2 = "";
        try {
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == ':')
                {
                    s1 = s.substring(0,i);
                    time += Integer.parseInt(s1) * 6000;
                   s2 = s.substring(i+1);
                }
            }
        
            for(int i = 0; i < s2.length(); i++) {
                if(s2.charAt(i) == '.'){
                    s1 = s2.substring(0, i);
                    time += Integer.parseInt(s1) * 100;
                    s2 = s2.substring(i + 1);
                    time += Integer.parseInt(s2);
                }
            }
        } catch (Exception e) {
            
        }
        
        return time;
     }

    /**
     * @return the _lyricsArray
     */
    public ArrayList<SubLyric> getLyricsArray() {
        return _lyricsArray;
    }

    /**
     * @param _lyricsArray the _lyricsArray to set
     */
    public void setLyricsArray(ArrayList<SubLyric> lyricsArray) {
        this._lyricsArray = lyricsArray;
    }
    
    public void printLyric() {
        for (int i = 0; i < _lyricsArray.size(); i++) {
            System.out.println("["+ _lyricsArray.get(i).getTime()+"] : "
                                + _lyricsArray.get(i).getContent());
        }
    }
    
    /**
     * Determine index of sub-lyric need to show at this time parameter
     * @param time
     * @return 
     */
    public int indexOf(long time) {
        for (int i = 0; i < _lyricsArray.size(); i++) {
            if (time >= _lyricsArray.get(i).getTime()) {
                if ((i+1) < _lyricsArray.size()) {
                    if (time < _lyricsArray.get(i+1).getTime()) {
                       return i; 
                    }
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Total of time to play lyric
     * @return 
     */
    public long getTotalPlayTime() {
        SubLyric last = _lyricsArray.get(_lyricsArray.size() - 1);        
        return last.getTime() + getTimeToPlaySublyric(_lyricsArray.size() - 1);
    }
    
    /**
     * Time to play sub-lyric has index if i
     * @param i
     * @return 
     */
    public long getTimeToPlaySublyric(int i) {
        // return start time of last sublyric
        if ((i+1) < _lyricsArray.size()) {
            return _lyricsArray.get(i+1).getTime()
                    - _lyricsArray.get(i).getTime();
        }
        
        return 0;        
    }
    
    /**
     * Time to play sub-lyric at time play
     * @param time
     * @return 
     */
    public long getTimeToPlaySublyricAt(long time) {
        int i = indexOf(time);
        return getTimeToPlaySublyric(i);
    }
    
    /**
     * Get Sub-lyric is playing at the moment
     * @param time
     * @return 
     */
    public SubLyric getSubLyricPlaying(long time) {
        for (int i = 0; i < _lyricsArray.size(); i++) {
            if (time >= _lyricsArray.get(i).getTime()) {
                if ((i+1) < _lyricsArray.size()) {
                    if (time < _lyricsArray.get(i+1).getTime()) {
                       return _lyricsArray.get(i); 
                    }
                }
            }
        }
        
        return new SubLyric();
    }
    
    
    /**
     * Get Sub-lyric has index i
     * @param i
     * @return 
     */
    public SubLyric getSubLyric(int i) {
        // get abs(i)
        i = i > 0 ? i : (-i);
        i %= _lyricsArray.size();
        return _lyricsArray.get(i);
        
    }
    
    /**
     * Get the number of Sub-lyric
     * @return 
     */
    public long getNumberOfSublyric() {
        return _lyricsArray.size();
    }
    
    /**
     * Get time wait for start play lyric
     * @return 
     */
    public long getWaitTimeToStart() {
        long wait = 0; 
        
        for (int i = 0; i < _lyricsArray.size(); i++) {
            if (!_lyricsArray.get(i).getContent().equals("")) {
                return _lyricsArray.get(i).getTime();
            }
        }  
        
        return wait;
    }
}
