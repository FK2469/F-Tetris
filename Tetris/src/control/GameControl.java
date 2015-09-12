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

    /*数据访问接口A*/
    private Data dataA;

    /*数据访问接口B*/
    private Data dataB;

    /*游戏逻辑层接口*/
    private GameService gameService;

    /*游戏数据源*/
    private GameDto dto = null;

    /*游戏界面层*/
    private JPanelGame panelGame;

    /*游戏设置窗口*/
    private JFrameConfig JFrameConfig;

    /*保存分数窗口*/
    private JFrameSavePoint frameSavePoint;

    /*游戏行为控制*/
    private Map<Integer,Method> actionList;

    /*游戏线程*/
    private Thread gameThread = null;




    public GameControl(){

        //创建游戏数据源
        this.dto =new GameDto();

        //创建游戏逻辑块（连接游戏数据源）
        this.gameService = new GameTetris(dto);

        //创建数据接口A
        this.dataA = createDataObject(GameConfig.getDataConfig().getDataA());

        //设置数据库记录到游戏
        this.dto.setDbRecode(dataA.loadData());

        //创建数据接口B
        this.dataB = createDataObject(GameConfig.getDataConfig().getDataB());

        //设置本地磁盘记录到游戏
        this.dto.setDiskRecode(dataB.loadData());

        //创建游戏面板
        this.panelGame = new JPanelGame(this,dto);

         //读取用户控制设置
        this.setControlConfig();

        //初始化用户配置窗口
        this.JFrameConfig =new JFrameConfig(this);

        //初始化保存分数窗口
        this.frameSavePoint = new JFrameSavePoint(this);

        //创建游戏主窗口（连接游戏面板）
        new JFrameGame(this.panelGame);
    }


    /*读取用户控制设置*/
    private void setControlConfig(){

        //创建键盘与方法名的映射数组
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



    /*创建数据对象*/
    private Data createDataObject(DataInterfaceConfig cfg){

        try {
            //获得类对象
            Class<?> cls = Class.forName(cfg.getClassName());
            //获得构造函数
            Constructor<?> ctr =cls.getConstructor(HashMap.class);

            //调用构造函数创建对象
            return (Data)ctr.newInstance(cfg.getParam());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*根据玩家控制来决定行为*/
    public void actionByKeyCode(int keyCode){

        try {
            if(!this.actionList.containsKey(keyCode)) return;
            this.actionList.get(keyCode).invoke(this.gameService); //获得方法对象，调用方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.panelGame.repaint();
    }

    public void showUserConfig(){
        this.JFrameConfig.setVisible(true);
    }


    /*子窗口关闭事件*/
    public void setOver(){
        this.panelGame.repaint();
        this.setControlConfig();
    }

    /*开始按钮事件*/
    public void start(){
        //按钮设置为不可点击
        this.panelGame.buttonSwitch(false);
        //关闭窗口
        this.JFrameConfig.setVisible(false);
        this.frameSavePoint.setVisible(false);
        //游戏数据初始化
        this.gameService.startGame();
        //初始化线程对象
        this.gameThread = new MainThread();
        //启动线程
        this.gameThread.start();
        //刷新画面
        this.panelGame.repaint();
    }

    public void afterLose(){
        //显示保存得分窗口
        this.frameSavePoint.Show(this.dto.getNowPoint());
        //使按钮可以点击
        this.panelGame.buttonSwitch(true);
    }

    /*保存分数*/
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
            //刷新画面
            panelGame.repaint();
            //主循环
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
