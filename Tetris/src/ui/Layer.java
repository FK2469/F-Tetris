package ui;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

import java.awt.*;

/**
 * Created by admin on 2015/5/19.
 */

/*
绘制窗口
* @author FK2469*/

public abstract class Layer {


    /*边框宽度*/
    protected static final int BORDER ;

    /*内边距*/
    protected static final int PADDING;


    static {
        //获得游戏配置
        FrameConfig fCfg = GameConfig.getFrameConfig();
        PADDING = fCfg.getPadding();
        BORDER = fCfg.getBorder();
    }


    /*数字切片的宽度*/
    protected static final int IMG_NUMBER_W =Img.NUMBER.getWidth(null)/10;

    /*数组切片的高度*/
    private static final int IMG_NUMBER_H =Img.NUMBER.getHeight(null);

    private static int WINDOW_W = Img.WINDOW.getWidth(null);

    private static int WINDOW_H = Img.WINDOW.getWidth(null);



    /*槽值图片高度*/
    protected static final int IMG_RECT_H  = Img.RECT.getHeight(null);

    /*槽值图片宽度*/
    private static final int IMG_RECT_W = Img.RECT.getWidth(null);

    /*初始化经验值槽的宽度*/
    private final int rect_W;




    /*下一级 字体*/
    private static final Font DEF_FONT =new Font("黑体",Font.BOLD,30);


    /*
    * 窗口左上角X,Y坐标，以及窗口宽度、高度
    * */
    protected int x;
    protected int y;
    protected int w;
    protected int h;

    /*游戏数据*/
    protected GameDto dto = null;

    protected Layer(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rect_W =this.w -(PADDING << 1);
    }

    /*绘制窗口*/
    protected void createWindow(Graphics g){
        //左上
        g.drawImage(Img.WINDOW,x,y,x+BORDER,y+BORDER,0,0,BORDER,BORDER,null);
        //中上
        g.drawImage(Img.WINDOW,x+BORDER,y,x+w-BORDER,y+BORDER,BORDER,0,WINDOW_W-BORDER,BORDER,null);
        //右上
        g.drawImage(Img.WINDOW,x+w-BORDER,y,x+w,y+BORDER,WINDOW_W-BORDER,0,WINDOW_W,BORDER,null);
        //左中
        g.drawImage(Img.WINDOW,x,y+BORDER,x+BORDER,y+h-BORDER,0,BORDER,BORDER,WINDOW_H-BORDER,null);
        //中
        g.drawImage(Img.WINDOW,x+BORDER,y+BORDER,x+w-BORDER,y+h-BORDER,BORDER,BORDER,WINDOW_W-BORDER,WINDOW_H-BORDER,null);
        //右中
        g.drawImage(Img.WINDOW,x+w-BORDER,y+BORDER,x+w,y+h-BORDER,WINDOW_W-BORDER,BORDER,WINDOW_W,WINDOW_H-BORDER,null);
        //左下
        g.drawImage(Img.WINDOW,x,y+h-BORDER,x+BORDER,y+h,0,WINDOW_H-BORDER,BORDER,WINDOW_H,null);
        //中下
        g.drawImage(Img.WINDOW,x+BORDER,y+h-BORDER,x+w-BORDER,y+h,BORDER,WINDOW_H-BORDER,WINDOW_W-BORDER,WINDOW_H,null);
        //右下
        g.drawImage(Img.WINDOW,x+w-BORDER,y+h-BORDER,x+w,y+h,WINDOW_W-BORDER,WINDOW_H-BORDER,WINDOW_W,WINDOW_H,null);
    }

    public void setDto(GameDto dto) {
        this.dto = dto;
    }

    protected void drawNumberLeftPad(int x,int y,int num,int maxBit, Graphics g){

        //把要打印的数字转换成字符串
        String strNum = Integer.toString(num);

        //令绘制数字右对齐
        for(int i = 0;i<maxBit;i++){
            //判断是否满足绘制条件
            if(maxBit-i<= strNum.length()){
                //获得数字在字符串中的下标
                int idx = i - maxBit +strNum.length();
                //把数字number中的每一位取出
                int bit = strNum.charAt(idx)-'0';
                //绘制数字
                g.drawImage(Img.NUMBER,
                        this.x + x +IMG_NUMBER_W * i,
                        this.y + y,
                        this.x + x+IMG_NUMBER_W *(i+1),
                        this.y + y+IMG_NUMBER_H,
                        bit*IMG_NUMBER_W,
                        0,(bit+1)*IMG_NUMBER_W,
                        IMG_NUMBER_H,null
                );
            }
        }
    }


    protected void drawRect(int y,String title,String number,double percent,Graphics g){

        int rect_x = this.x +PADDING;
        int rect_y = this.y +y;

        //绘制背景
        g.setColor(Color.BLACK);
        g.fillRect(rect_x,rect_y-5,this.rect_W,IMG_RECT_H +4+9);
        g.setColor(Color.WHITE);
        g.fillRect(rect_x+1 ,rect_y + 1-5,this.rect_W - 2,IMG_RECT_H +2+9);
        g.setColor(Color.BLACK);
        g.fillRect(rect_x +2 ,rect_y + 2-5,this.rect_W - 4,IMG_RECT_H+9);


        /*绘制值槽*/

        //求出宽度
        int w = (int)((percent*(this.rect_W - 4)));
        //求出颜色
        int subIdx = (int)(percent*IMG_RECT_W)-1;

        g.drawImage(Img.RECT,
                rect_x+2,rect_y + 2,
                rect_x+2+ w,rect_y +2+IMG_RECT_H,
                subIdx,0,subIdx+1,IMG_RECT_H,
                null
        );

        g.setColor(Color.WHITE);
        g.setFont(DEF_FONT);
        g.drawString(title, rect_x+5, rect_y+28);
        if(number !=null){
            g.drawString(number, rect_x+232, rect_y+28);
        }

    }

    /*正中绘图*/
    protected void drawImageAtCenter(Image img , Graphics g){
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        int imgX = this.w - imgW >>1;
        int imgY = this.h - imgH >>1;
        g.drawImage(img,this.x+imgX,this.y+imgY,null);
    }

    /*刷新游戏具体内容*/
    abstract public void paint(Graphics g);



 }
