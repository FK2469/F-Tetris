package service;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;

import java.awt.*;
import java.util.Map;
import java.util.Random;

/**
 * Created by admin on 2015/5/20.
 */

//��Ϸ�߼�
public class GameTetris implements GameService {

    private GameDto dto;

    /*��������*/
    private final int LEVEL_UP= GameConfig.getSystemConfig().getLevelUp();

    //���������
    private Random random = new Random();

    //��������
    private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeconfig().size() - 1;

    /*������������ӷ�*/
    private static final Map<Integer,Integer> PLUS_POINT = GameConfig.getSystemConfig().getPlusPoint();

    public GameTetris(GameDto dto) {
        this.dto = dto;
    }


    public boolean keyUp(){
        if(this.dto.isPause()){
            return true;
        }
        synchronized (this.dto) {
            this.dto.getGameAct().round(this.dto.getGameMap());
        }
        return true;
    }

    public boolean  keyDown(){
        if(this.dto.isPause()){
            return true;
        }
        synchronized (this.dto) {
        //�߽��ж�
        if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())) return false ;
        //��õ�ͼ����
        boolean[][] map = this.dto.getGameMap();
        //��÷������
        Point[] act = this.dto.getGameAct().getActPoints();
        //������ѻ�����ͼ����
        for (int i=0;i<act.length;i++){
            map[act[i].x][act[i].y] =true;
        }

        int plusExp = this.plusExp();
        if(plusExp > 0) {
            //���Ӿ���ֵ
            this.plusPoint(plusExp);
        }
        //������һ������
        this.dto.getGameAct().init(this.dto.getNext());   //((int)(Math.random()*1000))%7
        //����Next���з���
        this.dto.setNext(random.nextInt(MAX_TYPE));

        //����Ƿ�ʧ��
        if(this.isLose()){
            this.dto.setStart(false);
            }
        }
        return true;
    }


    public boolean keyLeft(){
        if(this.dto.isPause()){
            return true;
        }
        synchronized (this.dto) {
            this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
        }
        return true;
    }

    public boolean keyRight(){
        if(this.dto.isPause()){
            return true;
        }
        synchronized (this.dto) {
        this.dto.getGameAct().move(1,0,this.dto.getGameMap());
        }
        return true;
    }

    @Override
    public boolean keyFunUp() {
        synchronized (this.dto) {
        //���׼�
        this.plusPoint(5);
        }
        return true;
    }

    @Override
    public boolean keyFunDown() {
        if(this.dto.isPause()){
            return true;
        }

        while (!this.keyDown());
        return true;
    }

    @Override
    public boolean keyFunLeft() {
        synchronized (this.dto) {
        this.dto.changeShowShadow(this.dto.isShowShadow());}
        return true;
    }

    @Override
    public boolean keyFunRight() {
        if(this.dto.isStart())
        this.dto.changePause();
        return true;
    }

    /*���д���*/

    //����
    private void removeLine(int rowNumber , boolean[][]map){
        for(int x = 0;x<GameDto.GAMEZONE_W;x++){
            for(int y = rowNumber;y > 0;y--){
                map[x][y]=map[x][y-1];
            }
        map[x][0]=false;
        }
    }

    //�Ӿ��鷽��
    private int plusExp(){
        int exp = 0;

        //�����Ϸ��ͼ
        boolean[][]map = this.dto.getGameMap();

        //ɨ����Ϸ��ͼ
        for(int y = 0;y<GameDto.GAMEZONE_H;y++){
            if(isCanRemoveLine(y,map)){
                this.removeLine(y,map);
                exp++;
            }
        }
        return exp;
    }

    //�ж�ĳһ���Ƿ������
    private boolean isCanRemoveLine(int y , boolean[][]map){
        //ɨ��ÿһ��
        for(int x = 0;x<GameDto.GAMEZONE_W;x++){
            if(!map[x][y]){
                return false ;
            }
        }
        return true;
    }


    /*��������*/
    private void plusPoint(int plusExp){
        int level = this.dto.getNowLevel();
        int removeLine = this.dto.getNowRemoveLine();
        int point = this.dto.getNowPoint();
        if(removeLine % LEVEL_UP + plusExp >= LEVEL_UP){
            this.dto.setNowLevel(++level);
        }
        this.dto.setNowRemoveLine(removeLine+plusExp);
        this.dto.setNowPoint(point + PLUS_POINT.get(plusExp));
    }

    @Override
    public void startGame() {
        //���������һ������
        this.dto.setNext(random.nextInt(MAX_TYPE));
        //������ɵ�һ������
        dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
        //��״̬��Ϊ��ʼ
        this.dto.setStart(true);
        //dto��ʼ��
        this.dto.dtoInit();
    }


    private boolean isLose() {
        //������ڵĻ����
        Point[] actPoints = this.dto.getGameAct().getActPoints();
        //������ڵ���Ϸ��ͼ
        boolean[][] map = this.dto.getGameMap();
        for(int i= 0;i<actPoints.length;i++){
            if(map[actPoints[i].x][actPoints[i].y]){
                return true;
            }
        }
        return false;
    }




    @Override
    public void mainAction() {
        this.keyDown();
    }
}
