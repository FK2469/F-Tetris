package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by admin on 2015/5/20.
 */

public class PlayerControl extends KeyAdapter{

    private GameControl gameControl;

    public PlayerControl(GameControl gameControl){
        this.gameControl = gameControl;
    }

    /*KeyAdapter是继承KeyListener的类，因此这里不必覆盖其他两个方法*/

    @Override
    public void keyPressed(KeyEvent e) {
        this.gameControl.actionByKeyCode(e.getKeyCode());
    }//以前用Switch实现，现在直接映射键值和方法

}
