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

    /*����ͼƬ��������*/
    public static final Image POINT = new ImageIcon("graphics/string/point.png").getImage();

    /*����ͼƬ�����У�*/
    public static final Image RMLINE = new ImageIcon("graphics/string/rmline.png").getImage();

    /*����ͼƬ*/
    public static Image GB_TEMP = new ImageIcon("graphics/background/Sea.jpg").getImage();

    /*ǩ��ͼƬ*/
    public static final Image SIGN = new ImageIcon("graphics/string/sign.png").getImage();

    /*����ͼƬ�����ݿ⣩*/
    public static final Image DB = new ImageIcon("graphics/string/db.png").getImage();

    /*����ͼƬ�����ش��̣�*/
    public static final Image DISK = new ImageIcon("graphics/string/disk.png").getImage();

    /*����ͼƬ*/
    public static final Image ACT =new ImageIcon("graphics/game/rect.png").getImage();

    /*����ͼƬ���ȼ���*/
    public static final Image LV = new ImageIcon("graphics/string/level.png").getImage();

    /*��ֵͼƬ*/
    public static final Image RECT = new ImageIcon("graphics/window/rect.png").getImage();

    /*�߿�ͼƬ*/
    public static final Image WINDOW = new ImageIcon("graphics/window/Window.png").getImage();

    /*����ͼƬ 260 36*/
    public static final Image NUMBER =new ImageIcon("graphics/string/num.png").getImage();

    /*��ӰͼƬ*/
    public static Image SHADOW =new ImageIcon("graphics/game/shadow.png").getImage();

    /*��ʼ��ť*/
    public static final ImageIcon BTN_START =new ImageIcon("graphics/string/start.png");

    /*���ð�ť*/
    public static final ImageIcon BTN_CONFIG =new ImageIcon("graphics/string/config.png");

    public static  final Image PAUSE = new ImageIcon("graphics/string/pause.png").getImage();

    /*��һ��ͼƬ����*/
    public static final Image[] NEXT_ACT ;

    public static final List<Image> BG_LIST;

    static {
        //��һ������ͼƬ
        NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeconfig().size()];
        for(int i = 0;i<NEXT_ACT.length;i++){
            NEXT_ACT[i] = new ImageIcon("graphics/game/"+ i +".png").getImage();
        }

        //����ͼƬ����
        File dir = new File("graphics/background");
        File[] files= dir.listFiles();
        BG_LIST = new ArrayList<Image>();
        for(File file:files){
            if(file.isDirectory())  continue;
            BG_LIST.add(new ImageIcon(file.getPath()).getImage());
        }
    }





}
