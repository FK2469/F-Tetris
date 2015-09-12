package dto;

import java.io.Serializable;

/**
 * Created by admin on 2015/5/20.
 */
public class Player implements Comparable<Player>,Serializable {

    private String name;

    private int point;

    public Player(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int compareTo(Player player) {
        return player.point-this.point;
    }
}
