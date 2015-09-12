package ui;

import config.GameConfig;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerPoint extends Layer {

    /*�������λ��*/
    private static final int POINT_BIT = 5;

    /*����X����*/
    private final int comX;

    /*����Y����*/
    private final int rmlineY;

    /*����Y����*/
    private final int pointY;

    /*��������*/
    private final int LEVEL_UP= GameConfig.getSystemConfig().getLevelUp();

    /*����ֵ��Y����*/
    private final int expY;

    /*����ֵ�ۿ��*/
    private final int expW;


    public LayerPoint(int x, int y, int w, int h){
            super(x,y,w,h);
            /*��ʼ��������������ʾ��X����*/
            this.comX = this.w -IMG_NUMBER_W *POINT_BIT- PADDING;

            /*��ʼ��������ʾ��Y����*/
            this.pointY = PADDING;

            /*��ʼ��������ʾ��Y����*/
            this.rmlineY = this.pointY + Img.RMLINE .getHeight(null)+(PADDING);

            /*��ʼ������ֵ����ʾ��Y����*/
            this.expY = rmlineY + Img.RMLINE .getHeight(null) + (PADDING);

            /*��ʼ������ֵ����ʾ�Ŀ��*/
            this.expW = this.w -(PADDING << 1);

        }

        public void paint(Graphics g){
            this.createWindow(g);

            //���ڱ��⣨������
            g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY,null);

            //���ڱ��⣨���У�
            g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY,null);

            //��ʾ����
            this.drawNumberLeftPad(comX, pointY,this.dto.getNowPoint(),POINT_BIT,g);

            //��ʾ����
            this.drawNumberLeftPad(comX, rmlineY,this.dto.getNowRemoveLine(),POINT_BIT,g);
            int rmLine = this.dto.getNowRemoveLine();
            this.drawRect(this.expY,"Next",null,(double)(rmLine % LEVEL_UP) / (double)LEVEL_UP,g);
        }



}
