package ui;

import dto.Player;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerDataBase extends LayerData {



    public LayerDataBase(int x, int y, int w, int h){
            super(x,y,w,h);
            SPA = (this.h - RECT_H *5 - (PADDING << 1)- Img.DB.getHeight(null))/MAX_ROW;
            START_Y  = PADDING +Img.DB.getHeight(null)+SPA;
        }

        public void paint(Graphics g){
            this.showData(Img.DB,this.dto.getDbRecode(),g);
        }
}
