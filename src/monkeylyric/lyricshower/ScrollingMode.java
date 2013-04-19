/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.lyricshower;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import monkeylyric.ILyricPlayer;
import monkeylyric.customcomponents.MButton;
import monkeylyric.customcomponents.MFrame;
import monkeylyric.customcomponents.MToggleButton;
import monkeylyric.lyric.Lyric;
import monkeylyric.preferences.ScrollingModeSetting;

public class ScrollingMode extends      MFrame
                           implements   ILyricPlayer,
                                        ActionListener{
    private Timer _timer;
    private JPanel _panelNorth;
    private LyricScrollPanel _lyricScrollPanel;
    private JPanel _panelSounth;
    private MButton _closeButton;
    private MToggleButton _alwaysOnTopToggleButton;
    private JPanel _gridPanel;
    private MButton _backButton;
    private MButton _playButton;
    private MButton _pauseButton;
    private MButton _nextButton;    

    public ScrollingMode() {
        super("Monkey-Lyric");
        _timer = new Timer(100, this);

        /**
         * Setting for _panelNorth This panel content button for quick change
         * setting: Always On Top | Close window
         */
        _panelNorth = new JPanel();
        _panelNorth.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        _panelNorth.setBackground(new java.awt.Color(0, 0, 0, 0));
        _panelNorth.setLayout(new BorderLayout());
        
        _alwaysOnTopToggleButton = new MToggleButton("resources/images/non_top.png",
                                                     "resources/images/non_top.png",
                                                     "resources/images/top.png");
        _alwaysOnTopToggleButton.setToolTipText("Toggle On Always on Top");
        _alwaysOnTopToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alwaysOnTopToggleButtonClickHandler(evt);
            }
        });
        
        _closeButton = new MButton("resources/images/close_hover.png",
                                     "resources/images/close_hover.png", 
                                     "resources/images/close_active.png");
        _closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonClickHandler(evt);
            }
        });
        _closeButton.setToolTipText("Exit");
        _panelNorth.add(_alwaysOnTopToggleButton, BorderLayout.WEST);
        _panelNorth.add(_closeButton, BorderLayout.EAST);
        this.getCenterPanel().add(_panelNorth, BorderLayout.NORTH);
        // End of define _panelNorth

        /**
         * Setting for _lyricScrollPanel which show _lyric inside
         */
        _lyricScrollPanel = new LyricScrollPanel();
        this.getCenterPanel().add(_lyricScrollPanel, BorderLayout.CENTER);

        /**
         * Setting for _panelSounth Content button for control player: Play |
         * Pause | Back | Next song
         */
        _panelSounth = new JPanel();
        _panelSounth.setBackground(new java.awt.Color(0, 0, 0, 0));
        _panelSounth.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        _panelSounth.setLayout(new BorderLayout());
        _gridPanel = new JPanel();
        _gridPanel.setBounds(0, 0, 60, 50);
        _gridPanel.setLayout(new GridLayout(1, 3, 0, 0));
        _gridPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        _gridPanel.setBackground(new java.awt.Color(0, 0, 0, 0));
        
        _backButton = new MButton("resources/images/back_start.png",
                                     "resources/images/back_hover.png", 
                                     "resources/images/back_active.png");
        _backButton.setToolTipText("Back Track");
        
        _playButton = new MButton("resources/images/play_start.png",
                                     "resources/images/play_hover.png", 
                                     "resources/images/play_active.png");
        _playButton.setToolTipText("Play");
        _playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonClickHandler(evt);
            }
        });
        
        _pauseButton = new MButton("resources/images/pause_start.png",
                                     "resources/images/pause_hover.png", 
                                     "resources/images/pause_active.png");
        _pauseButton.setToolTipText("Pause");
        _pauseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pauseButtonClickHandler(evt);
            }
        });
        
        _nextButton = new MButton("resources/images/next_start.png",
                                     "resources/images/next_hover.png", 
                                     "resources/images/next_active.png");
        _nextButton.setToolTipText("Next Track");
        
        _gridPanel.add(_backButton);
        _gridPanel.add(_pauseButton);
        _gridPanel.add(_nextButton);        
        _panelSounth.setLayout(new FlowLayout(FlowLayout.CENTER));
        _panelSounth.add(_gridPanel);
        this.getCenterPanel().add(_panelSounth, BorderLayout.SOUTH);
        
        this.getCenterPanel().setToolTipText("Using your mouse wheel to seek !");
        // Add Mouse wheel event listener
        this.getCenterPanel().addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                mouseWheelMovedHandler(evt);
            }
        });
        this.setVisible(true);        
        this.paintRoundRectangleBorder();
    }

    @Override
    public void play() {
        _timer.start();
    }

    @Override
    public void pase() {
        _timer.stop();
    }

    @Override
    public void stop() {
        _timer.stop();
        _lyricScrollPanel.setTime(0);
        
    }

    @Override
    public void seek(long time) {
    }

    @Override
    public long getCurrentPlayTime() {
        return _lyricScrollPanel.getTime();
    }

    @Override
    public void setCurrentPlayTime(long time) {
        _lyricScrollPanel.setTime(time);
    }

    @Override
    public Lyric getLyric() {
        return _lyricScrollPanel.getLyric();
    }

    @Override
    public void setLyric(Lyric lrc) {
        _lyricScrollPanel.setLyric(lrc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle when _timer event occour
        if (e.getSource() == _timer) {
            setCurrentPlayTime(_lyricScrollPanel.getTime() + 10);
            
            // Repaint lyric
            if (_lyricScrollPanel.getTime() >= getLyric().getWaitTimeToStart() - 50) {
                _lyricScrollPanel.validate();
                _lyricScrollPanel.setIsRepaintLyric(true);
                this.repaint();
            }
            
            if (_lyricScrollPanel.getTime() >= getLyric().getTotalPlayTime())
            {
                _timer.stop();
                _lyricScrollPanel.setTime(0);
                this.repaint();
            }
        }
    }
    
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        ((Graphics2D) g).setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(ScrollingModeSetting.getInstance().getBackGround());
        g.drawRoundRect(x, y, width, height, 16, 16);
        System.out.println("paintBorder");
    }
    
    private void closeButtonClickHandler(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }
    
    private void alwaysOnTopToggleButtonClickHandler(java.awt.event.MouseEvent evt) {
        if (this.isAlwaysOnTop()) {
            this.setAlwaysOnTop(false);
            _alwaysOnTopToggleButton.setToggleOn();
            _alwaysOnTopToggleButton.setToolTipText("Toggle On Always on Top");
        }
        else {
            this.setAlwaysOnTop(true);
            _alwaysOnTopToggleButton.setToggleOff();
            _alwaysOnTopToggleButton.setToolTipText("Toggle Off Always on Top");
        }
    }
    
    private void playButtonClickHandler(java.awt.event.MouseEvent evt) {
        this.play();
        _gridPanel.removeAll();
        _gridPanel.add(_backButton, 0);
        _gridPanel.add(_pauseButton, 1);
        _gridPanel.add(_nextButton, 2);
        _gridPanel.invalidate();
        this.validate();
    }
    
    private void pauseButtonClickHandler(java.awt.event.MouseEvent evt) {
        this.pase();
        _gridPanel.removeAll();
        _gridPanel.add(_backButton, 0);
        _gridPanel.add(_playButton, 1);
        _gridPanel.add(_nextButton, 2);
        this.validate();
    }
    
    private void mouseWheelMovedHandler(java.awt.event.MouseWheelEvent evt) {
        _lyricScrollPanel.mouseWheelMovedHandler(evt.getWheelRotation());
    }
}