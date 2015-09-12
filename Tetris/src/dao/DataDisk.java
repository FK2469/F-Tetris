package dao;

import dto.Player;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2015/5/22.
 */
public class DataDisk implements Data {

    private final String filePath;

    public DataDisk(HashMap<String,String> param) {
        this.filePath = param.get("path");
    }

    @Override
    public List<Player> loadData() {
        ObjectInputStream ois = null;
        List<Player> players = null;
        try{
            ois= new ObjectInputStream(new FileInputStream(filePath));
            players = (List<Player>)ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return players;
    }

    //TODO
    @Override
    public void saveData(Player player) {
        //��ȡ������
        List<Player> players =this.loadData();

        //TODO �жϼ�¼�Ƿ񳬹�5��������
        //׷���¼�¼
        players.add(player);
        //����д��
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(filePath)
            );
            oos.writeObject(players);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                oos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

