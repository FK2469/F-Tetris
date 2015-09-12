package config;

import org.dom4j.Element;
import java.awt.Point;
import java.util.*;

/**
 * Created by admin on 2015/5/23.
 */
public class SystemConfig {

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int levelUp;
    private final java.util.List<Point[]> typeconfig;
    private final List<Boolean> typeRound;
    private final Map<Integer,Integer> plusPoint;

    public SystemConfig(Element system) {
        this.minX = Integer.parseInt(system.attributeValue("minX"));
        this.maxX = Integer.parseInt(system.attributeValue("maxX"));
        this.minY = Integer.parseInt(system.attributeValue("minY"));
        this.maxY = Integer.parseInt(system.attributeValue("maxY"));
        this.levelUp = Integer.parseInt(system.attributeValue("levelUp"));



        List<Element> rects = system.elements("rect");
        typeconfig = new ArrayList<Point[]>(rects.size());
        typeRound = new ArrayList<Boolean>(rects.size());
        for (Element rect: rects){
            this.typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));
            //获得坐标对象
            List<Element> pointConfig = rect.elements("Point");
            //创建Point对象数组
            Point[] points = new Point[pointConfig.size()];
            //初始化Point对象数组
            for(int i = 0;i<points.length;i++){
                int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
                int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
                points[i] = new Point(x,y);
            }
            //把Point对象放到typeConfig中
            typeconfig.add(points);
        }

        //获得连续消行加分配置
        this.plusPoint = new HashMap<Integer, Integer>();
        List<Element> plusPointCfg = system.elements("plusPoint");

        for(Element cfg:plusPointCfg){
            int rm = Integer.parseInt(cfg.attributeValue("rm"));
            int point = Integer.parseInt(cfg.attributeValue("point"));
            this.plusPoint.put(rm,point);
        }
    }


    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getLevelUp() {
        return levelUp;
    }

    public Map<Integer, Integer> getPlusPoint() {
        return plusPoint;
    }

    public List<Boolean> getTypeRound() {
        return typeRound;
    }

    public List<Point[]> getTypeconfig() {
        return typeconfig;
    }
}
