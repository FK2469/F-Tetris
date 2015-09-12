package ui;

import config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
public class Img {
    private Img(){};

    /*标题图片（分数）*/
    public static final Image POINT = new ImageIcon("graphics/string/point.png").getImage();

    /*标题图片（消行）*/
    public static final Image RMLINE = new ImageIcon("graphics/string/rmline.png").getImage();

    /*背景图片*/
    public static Image GB_TEMP = new ImageIcon("graphics/background/Sea.jpg").getImage();

    /*签名图片*/
    public static final Image SIGN = new ImageIcon("graphics/string/sign.png").getImage();

    /*标题图片（数据库）*/
    public static final Image DB = new ImageIcon("graphics/string/db.png").getImage();

    /*标题图片（本地磁盘）*/
    public static final Image DISK = new ImageIcon("graphics/string/disk.png").getImage();

    /*方块图片*/
    public static final Image ACT =new ImageIcon("graphics/game/rect.png").getImage();

    /*标题图片（等级）*/
    public static final Image LV = new ImageIcon("graphics/string/level.png").getImage();

    /*槽值图片*/
    public static final Image RECT = new ImageIcon("graphics/window/rect.png").getImage();

    /*边框图片*/
    public static final Image WINDOW = new ImageIcon("graphics/window/Window.png").getImage();

    /*数字图片 260 36*/
    public static final Image NUMBER =new ImageIcon("graphics/string/num.png").getImage();

    /*阴影图片*/
    public static Image SHADOW =new ImageIcon("graphics/game/shadow.png").getImage();

    /*开始按钮*/
    public static final ImageIcon BTN_START =new ImageIcon("graphics/string/start.png");

    /*设置按钮*/
    public static final ImageIcon BTN_CONFIG =new ImageIcon("graphics/string/config.png");

    public static  final Image PAUSE = new ImageIcon("graphics/string/pause.png").getImage();

    /*下一个图片数组*/
    public static final Image[] NEXT_ACT ;

    public static final List<Image> BG_LIST;

    static {
        //下一个方块图片
        NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeconfig().size()];
        for(int i = 0;i<NEXT_ACT.length;i++){
            NEXT_ACT[i] = new ImageIcon("graphics/game/"+ i +".png").getImage();
        }

        //背景图片数组
        File dir = new File("graphics/background");
        File[] files= dir.listFiles();
        BG_LIST = new ArrayList<Image>();
        for(File file:files){
            if(file.isDirectory())  continue;
            BG_LIST.add(new ImageIcon(file.getPath()).getImage());
        }
    }





}
