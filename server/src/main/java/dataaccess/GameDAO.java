package dataaccess;

import model.GameModel;

import java.util.HashMap;

public interface GameDAO {
        void clearAllGames();
        HashMap<Integer, GameModel> getGameData();
}
