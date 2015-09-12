package ui;

import config.GameConfig;
import entity.GameAct;
import javafx.scene.paint.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/**
 * Created by admin on 2015/5/19.
 */
public class LayerGame extends Layer{


        private static final int SIZE_ROL = GameConfig.getFrameConfig().getSizeRol();

        private static final int LEFT_SIDE = 0;

        private static final int RIGHT_SIDE = GameConfig.getSystemConfig().getMaxX();

        private static final int LOSE_IDX= GameConfig.getFrameConfig().getLoseIdx();

        public LayerGame(int x, int y, int w, int h){
            super(x,y,w,h);
        }

        public void paint(Graphics g) {
            this.createWindow(g);

            /*��÷������鼯��*/
            GameAct act = this.dto.getGameAct();
            if(act!=null){
            Point[] points =act.getActPoints();

            /*������Ӱ*/

            this.drawShadow(points,g);

            this.drawMainAct(g,points);}

            this.drawMap(g);

            //��ͣ
            if(this.dto.isPause()){
                this.drawImageAtCenter(Img.PAUSE,g);
            }
        }


    /*���ƻ����*/
    private void drawMainAct(Graphics g,Point[] points) {

        /*��÷������ͱ��(0~6)*/
        int typeCode = this.dto.getGameAct().getTypeCode();
        //���Ʒ���
        for (int i = 0; i < points.length; i++) {
            drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
        }
    }



    /*���Ƶ�ͼ*/
    private void drawMap(Graphics g) {
        //���Ƶ�ͼ
        boolean[][] map = this.dto.getGameMap();

        //���㵱ǰ�ѻ���ɫ
        int lv = this.dto.getNowLevel();
        int imgIdx = lv == 0 ? 0:(lv-1) % 7 + 1;

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    drawActByPoint(x, y, imgIdx , g);
                }
            }
        }
    }

    /*������Ӱ*/
    private void drawShadow(Point[] points,Graphics g){
                if(!this.dto.isShowShadow()) return;

                int leftX =RIGHT_SIDE;
                int rightX = LEFT_SIDE;

                for(Point p :points){
                    leftX = p.x <leftX ? p.x :leftX;
                    rightX = p.x >rightX ? p.x :rightX;
                }
                Color c = new Color(255,255,0,128);
                g.setColor(c);
                g.fillRect(this.x+BORDER +(leftX<<SIZE_ROL),
                        this.y + BORDER,
                        (rightX - leftX + 1) << SIZE_ROL,
                        this.h - (BORDER << 1) );
/*
                g.drawImage(Img.SHADOW,
                        this.x+BORDER +(leftX<<SIZE_ROL),
                        this.y + BORDER,
                        (rightX-leftX+1)<<SIZE_ROL,
                        this.h - (BORDER<<1),null);*/
            }

            private void drawActByPoint(int x ,int y,int imgIdx, Graphics g){
                //imgIdx ��ʾ�ڼ���ͼƬ
                imgIdx =this.dto.isStart()? imgIdx :LOSE_IDX;
                g.drawImage(Img.ACT,
                                this.x +( x <<SIZE_ROL) + BORDER,
                                this.y +( y <<SIZE_ROL) + BORDER,
                                this.x + (x + 1<<SIZE_ROL) + BORDER,
                                this.y + (y + 1<<SIZE_ROL) + BORDER,
                                imgIdx<<SIZE_ROL, 0, (imgIdx+1)<<SIZE_ROL, 1<<SIZE_ROL, null);
            }
}
