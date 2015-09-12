package ui.window;

import control.GameControl;
import util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 2015/5/26.
 */
public class JFrameSavePoint extends JFrame {

    private JButton btnOK = null;

    private JLabel lbPoint = null;

    private JTextField txName = null;

    private JLabel errMsg = null;

    private GameControl gameControl =null;

    public JFrameSavePoint(GameControl gameControl) {
        this.gameControl =gameControl;
        this.setTitle("Save Record");
        this.setSize(256,128);
        FrameUtil.setFrameCenter(this);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.createCom();
        this.createAction();
    }

    /*显示窗口*/
    public void Show(int point){
        this.lbPoint.setText("Score:"+point);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

    }

    /*创建事件监听*/
    private void createAction() {
        this.btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name =txName.getText();
                if(name.length()>16||"".equals(name)){
                    errMsg.setText("Error!");
                }else {
                    setVisible(false);
                    gameControl.savePoint(name);
                }
                errMsg.setText("");
            }
        });
    }

    /*初始化控件*/
    private void  createCom(){
        //创建北部面板（流式布局）
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //创建分数文字
        this.lbPoint = new JLabel("");
        //添加分数文字到北部面板
        north.add(lbPoint);
        //添加错误信息控件到北部面板
        this.errMsg = new JLabel();
        this.errMsg.setForeground(Color.RED);
        north.add(this.errMsg);
        //北部面板添加到主面板
        this.add(north,BorderLayout.NORTH);
        //创建中部面板
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //创建文本框
        this.txName = new JTextField(10);
        this.txName.requestFocus();
        //this.txName.grabFocus();
        //设置文字
        center.add(new JLabel("Your Name"));
        //文本框添加到中部面板
        center.add(this.txName);
        //中部面板添加到主面板
        this.add(center,BorderLayout.CENTER);
        //创建南部面板（流式布局）
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //创建确定按钮
        this.btnOK = new JButton("OK");
        //按钮添加到南部面板
        south.add(btnOK);
        //南部面板添加到主面板
        this.add(south,BorderLayout.SOUTH);

    }

}
