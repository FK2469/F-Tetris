package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerNext extends Layer {



    public LayerNext(int x, int y, int w, int h){
        super(x,y,w,h);
    }

    public void paint(Graphics g){
        this.createWindow(g);
        /*����ǿ�ʼ����Ż���*/
        if(this.dto.isStart()) drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()],g);

    }


}
