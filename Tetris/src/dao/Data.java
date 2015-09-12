package dao;

import dto.Player;

import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
/*数据持久层接口*/
public interface Data {

  /*获得数据*/
    List<Player>  loadData();

    /*存储数据*/
    void saveData(Player players);
}
