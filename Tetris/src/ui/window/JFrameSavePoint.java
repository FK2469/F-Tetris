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

    /*��ʾ����*/
    public void Show(int point){
        this.lbPoint.setText("Score:"+point);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

    }

    /*�����¼�����*/
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

    /*��ʼ���ؼ�*/
    private void  createCom(){
        //����������壨��ʽ���֣�
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //������������
        this.lbPoint = new JLabel("");
        //��ӷ������ֵ��������
        north.add(lbPoint);
        //��Ӵ�����Ϣ�ؼ����������
        this.errMsg = new JLabel();
        this.errMsg.setForeground(Color.RED);
        north.add(this.errMsg);
        //���������ӵ������
        this.add(north,BorderLayout.NORTH);
        //�����в����
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //�����ı���
        this.txName = new JTextField(10);
        this.txName.requestFocus();
        //this.txName.grabFocus();
        //��������
        center.add(new JLabel("Your Name"));
        //�ı�����ӵ��в����
        center.add(this.txName);
        //�в������ӵ������
        this.add(center,BorderLayout.CENTER);
        //�����ϲ���壨��ʽ���֣�
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //����ȷ����ť
        this.btnOK = new JButton("OK");
        //��ť��ӵ��ϲ����
        south.add(btnOK);
        //�ϲ������ӵ������
        this.add(south,BorderLayout.SOUTH);

    }

}
