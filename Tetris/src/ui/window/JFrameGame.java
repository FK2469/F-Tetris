package ui.window;

/**
 * Created by admin on 2015/5/19.
 */

import config.FrameConfig;
import config.GameConfig;
import util.FrameUtil;

import javax.swing.*;

public class JFrameGame extends JFrame{

    public JFrameGame(JPanelGame panelGame){

    //获得游戏配置
    FrameConfig fCfg = GameConfig.getFrameConfig();

    //设置标题
    this.setTitle(fCfg.getTitle());

    //设置默认关闭属性
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //设置窗口大小
    this.setSize(1168,680);

    //不允许用户改变窗口大小
    this.setResizable(false);

    //居中
    FrameUtil.setFrameCenter(this);

    // 设置默认Panel
     this.setContentPane(panelGame);

     //默认该窗口可见
     this.setVisible(true);
    }

}
