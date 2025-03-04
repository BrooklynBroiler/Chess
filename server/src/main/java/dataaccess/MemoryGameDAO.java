package dataaccess;

import model.GameModel;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    final private HashMap<Integer, GameModel> gamesData = new HashMap<>();
    @Override
    public void clearAllGames(){
        gamesData.clear();
    }

    public HashMap<Integer, GameModel> getGamesData() throws DataAccessException{
        return gamesData;
    }
}
