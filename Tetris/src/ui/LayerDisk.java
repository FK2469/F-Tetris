package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerDisk extends LayerData {

    public LayerDisk(int x, int y, int w, int h){
            super(x,y,w,h);
        }

        public void paint(Graphics g){
            this.createWindow(g);
            this.showData(Img.DISK,this.dto.getDiskRecode(),g);
        }
}
