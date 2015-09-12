package ui;

import config.GameConfig;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerPoint extends Layer {

    /*分数最大位数*/
    private static final int POINT_BIT = 5;

    /*分数X坐标*/
    private final int comX;

    /*消行Y坐标*/
    private final int rmlineY;

    /*分数Y坐标*/
    private final int pointY;

    /*升级行数*/
    private final int LEVEL_UP= GameConfig.getSystemConfig().getLevelUp();

    /*经验值槽Y坐标*/
    private final int expY;

    /*经验值槽宽度*/
    private final int expW;


    public LayerPoint(int x, int y, int w, int h){
            super(x,y,w,h);
            /*初始化分数、消行显示的X坐标*/
            this.comX = this.w -IMG_NUMBER_W *POINT_BIT- PADDING;

            /*初始化分数显示的Y坐标*/
            this.pointY = PADDING;

            /*初始化消行显示的Y坐标*/
            this.rmlineY = this.pointY + Img.RMLINE .getHeight(null)+(PADDING);

            /*初始化经验值槽显示的Y坐标*/
            this.expY = rmlineY + Img.RMLINE .getHeight(null) + (PADDING);

            /*初始化经验值槽显示的宽度*/
            this.expW = this.w -(PADDING << 1);

        }

        public void paint(Graphics g){
            this.createWindow(g);

            //窗口标题（分数）
            g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY,null);

            //窗口标题（消行）
            g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY,null);

            //显示分数
            this.drawNumberLeftPad(comX, pointY,this.dto.getNowPoint(),POINT_BIT,g);

            //显示消行
            this.drawNumberLeftPad(comX, rmlineY,this.dto.getNowRemoveLine(),POINT_BIT,g);
            int rmLine = this.dto.getNowRemoveLine();
            this.drawRect(this.expY,"Next",null,(double)(rmLine % LEVEL_UP) / (double)LEVEL_UP,g);
        }



}
