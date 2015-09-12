package service;

/**
 * Created by admin on 2015/5/24.
 */
public interface GameService {

    public boolean keyUp();

    public boolean keyDown();

    public boolean keyLeft();

    public boolean keyRight();

    /*三角*/
    public boolean keyFunUp();

    /*叉*/
    public boolean keyFunDown();

    /*方块*/
    public boolean keyFunLeft();

    /*圆圈*/
    public boolean keyFunRight();

    /*开始游戏*/
    public void startGame();

    /*游戏主要行为*/
    public void mainAction();

}
