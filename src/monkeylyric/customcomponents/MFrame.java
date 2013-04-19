/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monkeylyric.customcomponents;

import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import monkeylyric.preferences.ScrollingModeSetting;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author anhle
 */
public class MFrame extends JFrame implements MouseMotionListener {
    private JPanel _north;
    private JPanel _south;
    private JPanel _east;
    private JPanel _west;
    private JPanel _center;
    private int _borderWidth;
    protected MouseMotionAdapter _dragMouseAdapter;
    protected MouseAdapter _pressedMouseAdapter;
    protected Point _pressedPoint;
    
    public void paintRoundRectangleBorder() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        AWTUtilities.setWindowShape(this,
                new RoundRectangle2D.Float(0f,
                0f,
                (float) this.getWidth(),
                (float) this.getHeight(),
                15,
                15));
        
    }
    
    private void init() {
        _pressedPoint = new Point();
        _dragMouseAdapter = (new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mouseDragHandler(evt);
            }
        });
        
        _pressedMouseAdapter = (new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePressedHandler(evt);
            }
        });
        /**
         * Setting for windows
         */
        this._borderWidth = 8;
        this.setBounds(200, 200, 350, 280);
        this.setResizable(true);
        this.setUndecorated(true);
        this.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        
        this.setLayout(new BorderLayout());
        
        /* Setting for 4 panles corner */
        // North panel
        _north = new JPanel();
        _north.setBounds(0, 0, this.getPreferredSize().width, _borderWidth);
        _north.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        _north.addMouseMotionListener(this);
        this.getContentPane().add(_north, BorderLayout.NORTH);
       
        // Sounth panel
        _south = new JPanel();
        _south.setBounds(0, 0, this.getPreferredSize().width, _borderWidth);
        _south.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        _south.addMouseMotionListener(this);
        this.getContentPane().add(_south,BorderLayout.SOUTH);
        
        // East panel
        _east = new JPanel();
        _east.setBounds(0, 0, _borderWidth, this.getPreferredSize().height);
        _east.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        _east.addMouseMotionListener(this);
        this.getContentPane().add(_east, BorderLayout.EAST);
        
        // West panel
        _west = new JPanel();
        _west.setBounds(0, 0, _borderWidth, this.getPreferredSize().height);
        _west.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        _west.addMouseMotionListener(this);
        this.getContentPane().add(_west, BorderLayout.WEST);
        
        // Center panel
        _center = new JPanel();
        _center.setLayout(new BorderLayout());
        _center.setBackground(ScrollingModeSetting.getInstance().getBackGround());
        _center.addMouseMotionListener(_dragMouseAdapter);
        _center.addMouseListener(_pressedMouseAdapter);
        this.getContentPane().add(_center, BorderLayout.CENTER);
        
        this.paintRoundRectangleBorder();
    }
    
    public MFrame() {
        super();
        init();
    }
    
    public MFrame(GraphicsConfiguration gc) {
        super(gc);
        init();
    }
    
    public MFrame(String title) {
        super(title);
        init();
    }
    
    public MFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        init();
    }
    
    public JPanel getCenterPanel() {
        return _center;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Get curren rectangle of this JFrame
        Rectangle newRectangle = this.getBounds();
        int incX = 0;
        int incY = 0;        
        
        /** When use want resize north side
         *  Only change Y location and height of Frame
         */
        if (e.getSource() == _north
                && _north.getCursor().getType() == Cursor.N_RESIZE_CURSOR) {
            incY = this.getLocationOnScreen().y - e.getYOnScreen();
            newRectangle.x = this.getLocationOnScreen().x;
            newRectangle.y = e.getYOnScreen();
            newRectangle.height += incY;
        }
        
        /** When use want resize South side
         *  Only change height of Frame
         */
        if (e.getSource() == _south
                && _south.getCursor().getType() == Cursor.S_RESIZE_CURSOR) {
            incY = e.getYOnScreen() - (this.getLocationOnScreen().y + this.getHeight());
            newRectangle.x = this.getLocationOnScreen().x;
            newRectangle.y = this.getLocationOnScreen().y;
            newRectangle.height += incY;
        }
        
        /** When use want resize East side
         *  Only change width of Frame
         */
        if (e.getSource() == _east
                && _east.getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
            incX = e.getXOnScreen() - (this.getLocationOnScreen().x + this.getWidth());
            newRectangle.x = this.getLocationOnScreen().x;
            newRectangle.y = this.getLocationOnScreen().y;
            newRectangle.width += incX;
        }
        
        /** When use want resize West side
         *  Only change X location and width of Frame
         */
        if (e.getSource() == _west
                && _west.getCursor().getType() == Cursor.W_RESIZE_CURSOR) {            
            incX = this.getLocationOnScreen().x - e.getXOnScreen();
            newRectangle.x = e.getXOnScreen();
            newRectangle.y = this.getLocationOnScreen().y;
            newRectangle.width += incX;
        }
        
        /** When use want resize North-West edge
         */
        if (e.getSource() == _north
                && _north.getCursor().getType() == Cursor.NW_RESIZE_CURSOR) {
            
            incX = this.getLocationOnScreen().x - e.getXOnScreen();
            incY = this.getLocationOnScreen().y - e.getYOnScreen();
            newRectangle.x = e.getXOnScreen();
            newRectangle.y = e.getYOnScreen();
            newRectangle.width += incX;
            newRectangle.height += incY;
        }
        
        /** When use want resize North-East edge
         */
        if (e.getSource() == _north
                && _north.getCursor().getType() == Cursor.NE_RESIZE_CURSOR) {
            
            incX = e.getXOnScreen() - (this.getLocationOnScreen().x + this.getWidth());
            incY = this.getLocationOnScreen().y - e.getYOnScreen();
            newRectangle.x = this.getLocationOnScreen().x;
            newRectangle.y = this.getLocationOnScreen().y - incY;
            newRectangle.width += incX;
            newRectangle.height += incY;
        }
        
        /** When use want resize South-West edge
         */
        if (e.getSource() == _south
                && _south.getCursor().getType() == Cursor.SW_RESIZE_CURSOR) {
            
            incX = this.getLocationOnScreen().x - e.getXOnScreen();
            incY = e.getYOnScreen() - (this.getLocationOnScreen().y + this.getHeight());
            newRectangle.x = this.getLocationOnScreen().x - incX;
            newRectangle.y = this.getLocationOnScreen().y;
            newRectangle.width += incX;
            newRectangle.height += incY;
        }
        
        /** When use want resize South-East edge
         */
        if (e.getSource() == _south
                && _south.getCursor().getType() == Cursor.SE_RESIZE_CURSOR) {
            
            incX = e.getXOnScreen() - (this.getLocationOnScreen().x + this.getWidth());
            incY = e.getYOnScreen() - (this.getLocationOnScreen().y + this.getHeight());
            newRectangle.x = this.getLocationOnScreen().x;
            newRectangle.y = this.getLocationOnScreen().y;
            newRectangle.width += incX;
            newRectangle.height += incY;
        }
        
        if (e.getSource() == _center) {
            _center.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        /* Repaint if need */
        if (newRectangle.height > _borderWidth*10 && newRectangle.width > _borderWidth*10) {
            this.setBounds(newRectangle);
            this.paintRoundRectangleBorder();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        
        if (e.getSource() == _north) {            
            if (point.x <= _borderWidth) {
                // Mouse located at North-West edge
                _north.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
            } else if (point.x > _north.getWidth()-_borderWidth) {
                // Mouse located at North-East edge
                _north.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
            } else {
                // Mouse located at North side
                _north.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
            }            
        }
        
        if (e.getSource() == _south) {
            if (point.x <= _borderWidth) {
                // Mouse located at Sounth-West edge
                _south.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
            } else if (point.x > _south.getWidth()-_borderWidth) {
                // Mouse located at Sounth-East edge
                _south.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            } else {
                // Mouse located at Sounth side
                _south.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            }
        }
        
        if (e.getSource() == _east) {
            // Mouse located at East side
            _east.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
        }
        
        if (e.getSource() == _west) {
            // Mouse located at West side
            _west.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
        }   
    }   
    
    private void mousePressedHandler(java.awt.event.MouseEvent evt) {
        _pressedPoint = evt.getLocationOnScreen();
    }
    
    private void mouseDragHandler(java.awt.event.MouseEvent evt) {
        int deltaX = evt.getXOnScreen() - _pressedPoint.x;
        int deltaY = evt.getYOnScreen() - _pressedPoint.y;
        // Update new pressed point during mouse is dragging
        _pressedPoint = evt.getLocationOnScreen();        
        this.setLocation(this.getLocationOnScreen().x + deltaX,
                         this.getLocationOnScreen().y + deltaY);
    }
    
}
