package dataaccess;

import model.GameModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    final private HashMap<Integer, GameModel> gamesData = new HashMap<>();
//    Clears all the games from the DataBase
    @Override
    public void clearAllGames(){
        gamesData.clear();
    }

    public HashMap<Integer, GameModel> getGameData(){
        return gamesData;
    }
}
