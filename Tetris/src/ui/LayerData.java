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

    /*最大数据行*/
    protected static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();

    /*间距*/
    protected int SPA =0;

    /*起始Y坐标*/
    protected  int START_Y =0;

    /*值槽外径*/
    protected static final int RECT_H = IMG_RECT_H + 4;

    public LayerData(int x, int y, int w, int h) {
        super(x, y, w, h);
        SPA = (this.h - RECT_H *5 - (PADDING << 1)- Img.DB.getHeight(null))/MAX_ROW;
        START_Y  = PADDING +Img.DB.getHeight(null)+SPA;
    }


    /*绘制该窗口所有值槽*/
    public void showData(Image imgTitle,List<Player> players,Graphics g){
        this.createWindow(g);
        g.drawImage(imgTitle,this.x + PADDING,this.y + PADDING,null);

        int nowPoint= this.dto.getNowPoint();
        for(int i=0;i<MAX_ROW;i++){
            /*获得一条玩家记录*/
            Player player = players.get(i);
            /*获得该玩家分数*/
            int recodePoint = player.getPoint();
            /*计算现在分数与记录分数比值*/
            double percent =(double) nowPoint /(double)recodePoint;
            /*如果破纪录，则把比值设为100%*/
            percent = percent >1? 1.0: percent;
            /*绘制单条记录*/
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
