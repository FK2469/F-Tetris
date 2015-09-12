package entity;

import config.GameConfig;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by admin on 2015/5/20.
 */
public class GameAct {

    /*方块数组*/
    private Point[] actPoints= null;

    /*方块编号*/
    private int typeCode;

    private static int MIN_X = GameConfig.getSystemConfig().getMinX();
    private static int MAX_X = GameConfig.getSystemConfig().getMaxX();
    private static int MIN_Y = GameConfig.getSystemConfig().getMinY();
    private static int MAX_Y = GameConfig.getSystemConfig().getMaxY();

    private final static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeconfig();
    private final static List<Boolean> TYPE_ROUND = GameConfig.getSystemConfig().getTypeRound();

    public GameAct(int typeCode){
        this.init(typeCode);
    }

    /*static {
        TYPE_CONFIG = new ArrayList<Point[]>(7);
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(4,1)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(3,1)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(3,1),new Point(4,1)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(4,1),new Point(5,1)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(5,1)});
        TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(4,1),new Point(5,1)});
    }
*/

    public void init(int typeCode){
        // ERROE    actPoints = TYPE_CONFIG.get(actCode);
        this.typeCode = typeCode;
        Point[] points = TYPE_CONFIG.get(typeCode);
        actPoints = new Point[points.length];
        for(int i = 0;i<points.length;i++){
            actPoints[i] = new Point(points[i].x,points[i].y);
        }
    }

    public Point[] getActPoints() {
        return actPoints;
    }



    public boolean move(int moveX,int moveY,boolean[][] gameMap){
        for(int i = 0;i<actPoints.length;i++){
            int newX = actPoints[i].x+moveX;
            int newY = actPoints[i].y+moveY;
            if(isOverZone(newX, newY, gameMap)) return false;
        }
        for(int i=0;i<actPoints.length;i++)
        {
            actPoints[i].x += moveX;
            actPoints[i].y += moveY;
        }
        return true;
    }

    /*
顺时针：
B.x = O.x - O.y + A.y
B.y = O.x + O.y - A.x

逆时针：
A.x = O.y + O.x - B.y
A.y = O.y - O.x + B.x
*/
    public void round(boolean[][] gameMap){
        if(!TYPE_ROUND.get(this.typeCode)){
            return;
        }
        for(int i =1;i<actPoints.length;i++) {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            if (isOverZone(newX, newY, gameMap)) {
                return;
            }
        }
            for(int i =1;i<actPoints.length;i++) {
                int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
                int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
                actPoints[i].x = newX;
                actPoints[i].y = newY;
            }

    }

    private boolean isOverZone(int x,int y,boolean[][] gameMap){
        return x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y];
    }


    /*获得方块类型编号*/

    public int getTypeCode() {
        return typeCode;
    }
}
