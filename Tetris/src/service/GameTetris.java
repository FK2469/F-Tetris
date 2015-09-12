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

//游戏逻辑
public class GameTetris implements GameService {

    private GameDto dto;

    /*升级行数*/
    private final int LEVEL_UP= GameConfig.getSystemConfig().getLevelUp();

    //生成随机数
    private Random random = new Random();

    //方块种类
    private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeconfig().size() - 1;

    /*升级行数及其加分*/
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
        //边界判断
        if(this.dto.getGameAct().move(0,1,this.dto.getGameMap())) return false ;
        //获得地图对象
        boolean[][] map = this.dto.getGameMap();
        //获得方块对象
        Point[] act = this.dto.getGameAct().getActPoints();
        //将方块堆积到地图数组
        for (int i=0;i<act.length;i++){
            map[act[i].x][act[i].y] =true;
        }

        int plusExp = this.plusExp();
        if(plusExp > 0) {
            //增加经验值
            this.plusPoint(plusExp);
        }
        //创建下一个方块
        this.dto.getGameAct().init(this.dto.getNext());   //((int)(Math.random()*1000))%7
        //生成Next框中方块
        this.dto.setNext(random.nextInt(MAX_TYPE));

        //检查是否失败
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
        //作弊键
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

    /*消行处理*/

    //消行
    private void removeLine(int rowNumber , boolean[][]map){
        for(int x = 0;x<GameDto.GAMEZONE_W;x++){
            for(int y = rowNumber;y > 0;y--){
                map[x][y]=map[x][y-1];
            }
        map[x][0]=false;
        }
    }

    //加经验方法
    private int plusExp(){
        int exp = 0;

        //获得游戏地图
        boolean[][]map = this.dto.getGameMap();

        //扫描游戏地图
        for(int y = 0;y<GameDto.GAMEZONE_H;y++){
            if(isCanRemoveLine(y,map)){
                this.removeLine(y,map);
                exp++;
            }
        }
        return exp;
    }

    //判断某一行是否可消除
    private boolean isCanRemoveLine(int y , boolean[][]map){
        //扫描每一行
        for(int x = 0;x<GameDto.GAMEZONE_W;x++){
            if(!map[x][y]){
                return false ;
            }
        }
        return true;
    }


    /*升级处理*/
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
        //随机生成下一个方块
        this.dto.setNext(random.nextInt(MAX_TYPE));
        //随机生成第一个方块
        dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
        //把状态设为开始
        this.dto.setStart(true);
        //dto初始化
        this.dto.dtoInit();
    }


    private boolean isLose() {
        //获得现在的活动方块
        Point[] actPoints = this.dto.getGameAct().getActPoints();
        //获得现在的游戏地图
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
