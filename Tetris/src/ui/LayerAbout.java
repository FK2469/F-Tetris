package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerAbout extends Layer{
    
        public LayerAbout(int x, int y, int w, int h){
            super(x,y,w,h);
        }

        public void paint(Graphics g){
            this.createWindow(g);
            this.drawImageAtCenter(Img.SIGN,g);
        }
}
