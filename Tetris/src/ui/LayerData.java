package ui;

import config.GameConfig;
import dto.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
public abstract class LayerData extends Layer {

    /*���������*/
    protected static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();

    /*���*/
    protected int SPA =0;

    /*��ʼY����*/
    protected  int START_Y =0;

    /*ֵ���⾶*/
    protected static final int RECT_H = IMG_RECT_H + 4;

    public LayerData(int x, int y, int w, int h) {
        super(x, y, w, h);
        SPA = (this.h - RECT_H *5 - (PADDING << 1)- Img.DB.getHeight(null))/MAX_ROW;
        START_Y  = PADDING +Img.DB.getHeight(null)+SPA;
    }


    /*���Ƹô�������ֵ��*/
    public void showData(Image imgTitle,List<Player> players,Graphics g){
        this.createWindow(g);
        g.drawImage(imgTitle,this.x + PADDING,this.y + PADDING,null);

        int nowPoint= this.dto.getNowPoint();
        for(int i=0;i<MAX_ROW;i++){
            /*���һ����Ҽ�¼*/
            Player player = players.get(i);
            /*��ø���ҷ���*/
            int recodePoint = player.getPoint();
            /*�������ڷ������¼������ֵ*/
            double percent =(double) nowPoint /(double)recodePoint;
            /*����Ƽ�¼����ѱ�ֵ��Ϊ100%*/
            percent = percent >1? 1.0: percent;
            /*���Ƶ�����¼*/
            String strPoint = recodePoint == 0 ? null:Integer.toString(recodePoint);
            this.drawRect(START_Y + i*(RECT_H + SPA),
                    player.getName(),null,
                    percent,g);

            this.drawNumberLeftPad(this.x+PADDING +100,START_Y + i*(RECT_H + SPA),recodePoint,6,g);
        }
    }


    @Override
    abstract public void paint(Graphics g);

}
