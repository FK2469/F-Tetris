package dao;

import dto.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
public class DataTest {

    public List<Player> loadData() {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("AF",100));
        players.add(new Player("AF",1000));
    /*    players.add(new Player("AF",200));
        players.add(new Player("AF",3000));*/
        players.add(new Player("AF",4300));
        return players;
    }


    public void saveData(List<Player> players) {
        System.out.print("AVD");
    }
}
