package dto;

import config.GameConfig;
import entity.GameAct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 2015/5/20.
 */
public class GameDto {

    public static final int GAMEZONE_W = GameConfig.getSystemConfig().getMaxX() + 1;

    public static final int GAMEZONE_H =GameConfig.getSystemConfig().getMaxY() + 1;

    private boolean start;

    public GameDto() {
        dtoInit();
    }

    public void dtoInit(){
        this.gameMap = new boolean[10][18];
        this.nowLevel = 0;
        this.nowPoint = 0;
        this.nowRemoveLine = 0;
        this.pause = false;
    }

    /*���ݿ��¼*/
    private List<Player> dbRecode;

    /*���ؼ�¼*/
    private List<Player> diskRecode;

    /*��Ϸ��ͼ*/
    private boolean[][] gameMap;

    /*���䷽��*/
    private GameAct gameAct;

    /*��һ������*/
    private int next;

    /*�ȼ�*/
    private int nowLevel;

    /*����*/
    private int nowPoint;

    /*����*/
    private int nowRemoveLine;

    /*�Ƿ���ʾ��Ӱ*/
    private boolean showShadow;

    /*�Ƿ���ͣ*/
    private boolean pause;

    public List<Player> getDbRecode() {
        return dbRecode;
    }

    public void setDbRecode(List<Player> dbRecode) {
        this.dbRecode = this.setFillRecode(dbRecode);
    }

    public List<Player> getDiskRecode() {
        return diskRecode;
    }

    public void setDiskRecode(List<Player> diskRecode) {
        this.diskRecode = this.setFillRecode(diskRecode);
    }

    private List<Player> setFillRecode(List<Player> players){

        /*����������ǿգ��򴴽�*/
        if(players==null){
            players =new ArrayList<Player>();
        }
        /*���С��5����������*/
        while (players.size()< 5){
            players.add(new Player("No Data",0));
        }
        Collections.sort(players);
        return players;
    }

    public boolean[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(boolean[][] gameMap) {
        this.gameMap = gameMap;
    }

    public GameAct getGameAct() {
        return gameAct;
    }

    public void setGameAct(GameAct gameAct) {
        this.gameAct = gameAct;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getNowLevel() {
        return nowLevel;
    }

    public void setNowLevel(int nowLevel) {
        this.nowLevel = nowLevel;
    }

    public int getNowPoint() {
        return nowPoint;
    }

    public void setNowPoint(int nowPoint) {
        this.nowPoint = nowPoint;
    }

    public int getNowRemoveLine() {
        return nowRemoveLine;
    }

    public void setNowRemoveLine(int nowRemoveLine) {
        this.nowRemoveLine = nowRemoveLine;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isShowShadow() {
        return showShadow;
    }

    public void changeShowShadow(boolean showShadow) {
        this.showShadow = !this.showShadow ;
    }

    public boolean isPause() {
        return pause;
    }

    public void changePause() {
        this.pause = !this.pause;
    }
}