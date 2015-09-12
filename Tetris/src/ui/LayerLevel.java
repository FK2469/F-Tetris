package ui;

import jdk.nashorn.internal.objects.NativeUint16Array;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerLevel extends Layer {

    private static final int IMG_LV_W = Img.LV.getWidth(null);

    public LayerLevel(int x, int y, int w, int h){
        super(x,y,w,h);
    }

    public void paint(Graphics g){
        this.createWindow(g);
        //Title
        int centerX = this.w - IMG_LV_W >>1;
        g.drawImage(Img.LV, this.x + centerX, this.y + PADDING,null);

        //Level
        this.drawNumberLeftPad(centerX,64,this.dto.getNowLevel(),2,g);

    }
}
