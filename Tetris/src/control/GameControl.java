package control;

import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;
import dto.Player;
import service.GameService;
import service.GameTetris;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import ui.window.JFrameConfig;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2015/5/20.
 */


public class GameControl {

    /*���ݷ��ʽӿ�A*/
    private Data dataA;

    /*���ݷ��ʽӿ�B*/
    private Data dataB;

    /*��Ϸ�߼���ӿ�*/
    private GameService gameService;

    /*��Ϸ����Դ*/
    private GameDto dto = null;

    /*��Ϸ�����*/
    private JPanelGame panelGame;

    /*��Ϸ���ô���*/
    private JFrameConfig JFrameConfig;

    /*�����������*/
    private JFrameSavePoint frameSavePoint;

    /*��Ϸ��Ϊ����*/
    private Map<Integer,Method> actionList;

    /*��Ϸ�߳�*/
    private Thread gameThread = null;




    public GameControl(){

        //������Ϸ����Դ
        this.dto =new GameDto();

        //������Ϸ�߼��飨������Ϸ����Դ��
        this.gameService = new GameTetris(dto);

        //�������ݽӿ�A
        this.dataA = createDataObject(GameConfig.getDataConfig().getDataA());

        //�������ݿ��¼����Ϸ
        this.dto.setDbRecode(dataA.loadData());

        //�������ݽӿ�B
        this.dataB = createDataObject(GameConfig.getDataConfig().getDataB());

        //���ñ��ش��̼�¼����Ϸ
        this.dto.setDiskRecode(dataB.loadData());

        //������Ϸ���
        this.panelGame = new JPanelGame(this,dto);

         //��ȡ�û���������
        this.setControlConfig();

        //��ʼ���û����ô���
        this.JFrameConfig =new JFrameConfig(this);

        //��ʼ�������������
        this.frameSavePoint = new JFrameSavePoint(this);

        //������Ϸ�����ڣ�������Ϸ��壩
        new JFrameGame(this.panelGame);
    }


    /*��ȡ�û���������*/
    private void setControlConfig(){

        //���������뷽������ӳ������
        this.actionList = new HashMap<Integer, Method>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
            HashMap<Integer,String> cfgSet = (HashMap)ois.readObject();
            Set<Map.Entry<Integer,String>> entryset =cfgSet.entrySet();
            for(Map.Entry<Integer,String> e:entryset){
                actionList.put(e.getKey(),this.gameService.getClass().getMethod(e.getValue()));
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /*�������ݶ���*/
    private Data createDataObject(DataInterfaceConfig cfg){

        try {
            //��������
            Class<?> cls = Class.forName(cfg.getClassName());
            //��ù��캯��
            Constructor<?> ctr =cls.getConstructor(HashMap.class);

            //���ù��캯����������
            return (Data)ctr.newInstance(cfg.getParam());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*������ҿ�����������Ϊ*/
    public void actionByKeyCode(int keyCode){

        try {
            if(!this.actionList.containsKey(keyCode)) return;
            this.actionList.get(keyCode).invoke(this.gameService); //��÷������󣬵��÷���
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.panelGame.repaint();
    }

    public void showUserConfig(){
        this.JFrameConfig.setVisible(true);
    }


    /*�Ӵ��ڹر��¼�*/
    public void setOver(){
        this.panelGame.repaint();
        this.setControlConfig();
    }

    /*��ʼ��ť�¼�*/
    public void start(){
        //��ť����Ϊ���ɵ��
        this.panelGame.buttonSwitch(false);
        //�رմ���
        this.JFrameConfig.setVisible(false);
        this.frameSavePoint.setVisible(false);
        //��Ϸ���ݳ�ʼ��
        this.gameService.startGame();
        //��ʼ���̶߳���
        this.gameThread = new MainThread();
        //�����߳�
        this.gameThread.start();
        //ˢ�»���
        this.panelGame.repaint();
    }

    public void afterLose(){
        //��ʾ����÷ִ���
        this.frameSavePoint.Show(this.dto.getNowPoint());
        //ʹ��ť���Ե��
        this.panelGame.buttonSwitch(true);
    }

    /*�������*/
    public void savePoint(String name) {
        Player player = new Player(name,this.dto.getNowPoint());
        this.dataA.saveData(player);
        this.dataB.saveData(player);
        this.dto.setDbRecode(dataA.loadData());
        this.dto.setDiskRecode(dataB.loadData());
        this.panelGame.repaint();
    }

    private class MainThread extends Thread{
        @Override
        public void run(){
            //ˢ�»���
            panelGame.repaint();
            //��ѭ��
            while (dto.isStart()){
                try {
                    Thread.sleep(400);
                    if(dto.isPause()){
                        continue;
                    }
                        gameService.mainAction();
                        panelGame.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            afterLose();
        }
    }
}
