package dao;

import dto.Player;

import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
/*���ݳ־ò�ӿ�*/
public interface Data {

  /*�������*/
    List<Player>  loadData();

    /*�洢����*/
    void saveData(Player players);
}
