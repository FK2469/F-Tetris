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

    /*KeyAdapter�Ǽ̳�KeyListener���࣬������ﲻ�ظ���������������*/

    @Override
    public void keyPressed(KeyEvent e) {
        this.gameControl.actionByKeyCode(e.getKeyCode());
    }//��ǰ��Switchʵ�֣�����ֱ��ӳ���ֵ�ͷ���

}
