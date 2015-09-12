package ui.window;

import control.GameControl;
import util.FrameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2015/5/23.
 */
public class JFrameConfig extends JFrame{

    private JButton btnOK =new JButton("OK");

    private JButton btnCancel =new JButton("Cancel");

    private JButton btnUser =new JButton("Apply");

    private TextCtrl[] keyText = new TextCtrl[8];

    private JLabel errorMsg = new JLabel();

    private GameControl gameControl;

    private final static Image IMG_PSP = new ImageIcon("data/PSP.jpg").getImage();

    private final static String[] METHOD_NAMES = {
            "keyRight", "keyUp", "keyLeft","keyDown",
            "keyFunLeft","keyFunUp", "keyFunRight","keyFunDown"
    };

    private final static String PATH = "data/control.dat";

    public JFrameConfig(GameControl gameControl){

        //�����Ϸ����������
        this.gameControl = gameControl;

        //�߽粼��
        this.setLayout(new BorderLayout());

        //����
        this.setTitle("Setting");

        //��ʼ����������
        this.initKeyText();

        //��������
        this.add(this.createMainPanel(),BorderLayout.CENTER);

        //��Ӱ�ť���
        this.add(this.createButtonPanel(),BorderLayout.SOUTH);

        //����
        FrameUtil.setFrameCenter(this);
        this.setSize(782,450);
        this.setResizable(false);
    }



    /*��ʼ�����������*/
    private void initKeyText(){
        int x =0;
        int y =70;
        int w = 95;
        int h = 20;
        for(int i=0;i<4;i++){
            keyText[i] = new TextCtrl(x,y,w,h,METHOD_NAMES[i]);
            y+=43;
        }
        x=675;
        y=80;
        for(int i=4;i<8;i++ ){
            keyText[i]= new TextCtrl(x,y,w,h,METHOD_NAMES[i]);
            y+=40;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
            HashMap<Integer,String> cfgSet = (HashMap)ois.readObject();
            ois.close();
            Set<Map.Entry<Integer,String>> entryset =cfgSet.entrySet();
            for(Map.Entry<Integer,String> e:entryset){
                for(TextCtrl tc :keyText){
                    if(tc.getMethodName().equals(e.getValue()))
                        tc.setKeyCode(e.getKey());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
    ������ť���*/
    public Component createButtonPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        errorMsg.setForeground(Color.RED);
        jp.add(errorMsg);


        jp.add(this.btnOK);
        //��ȷ����ť�����¼�����
        this.btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(writeConfig()) {
                     setVisible(false);
                     gameControl.setOver();
                 }


            }
        });

        jp.add(this.btnCancel);
        /*��ȡ����ť�����¼�����*/
        this.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameControl.setOver();
            }
        });

        jp.add(this.btnUser);
        /*��Ӧ�ð�ť�����¼�����*/
        this.btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeConfig();
            }
        });

        return jp;
    }


    /*���������*/
    public Component createMainPanel(){
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Control Setting",this.createControlPanel());
        jtp.addTab("Feeling Setting",new JLabel("Feeling"));
        return jtp;

    }

    /*��ҿ����������*/
    private JPanel createControlPanel(){
        JPanel jp = new JPanel(){
          @Override
            public void paintComponent(Graphics g){
              g.drawImage(IMG_PSP,0,0,null);
          }
        };

        /*���ò��ֹ�����*/
        jp.setLayout(null);
        for(int i =0;i<keyText.length;i++){
          jp.add(keyText[i]);
        }
        return jp;
      }


    /*д����Ϸ����*/
    private boolean writeConfig(){
        HashMap<Integer,String> keySet = new HashMap<Integer,String>();
        for(int i=0;i<this.keyText.length;i++)
        {
            int keyCode = this.keyText[i].getKeyCode();
            if(keyCode ==0) {
                this.errorMsg.setText("ERROR");
                return false;
            }
            keySet.put(keyCode,this.keyText[i].getMethodName());
        }
        if(keySet.size()!=8) {this.errorMsg.setText("ERROR"); return false;}
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
            oos.writeObject(keySet);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            this.errorMsg.setText(e.getMessage());
            return false;
        }
        this.errorMsg.setText(null);
        return true;
    }
}
