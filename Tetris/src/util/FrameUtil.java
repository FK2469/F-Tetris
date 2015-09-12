package util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 2015/5/23.
 */
public class FrameUtil {

    public static void setFrameCenter(JFrame jf){
        //¾ÓÖÐ
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int x = (screen.width-jf.getWidth()) >> 1;
        int y = (screen.height-jf.getHeight() >> 1)- 32;
        jf.setLocation(x,y);
    }
}
