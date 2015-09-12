package ui;

import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerButton extends Layer {
    public LayerButton(int x, int y, int w, int h){
        super(x,y,w,h);
    }

    public void paint(Graphics g){
        this.createWindow(g);
    }
}
