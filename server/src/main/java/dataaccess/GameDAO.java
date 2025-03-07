package dataaccess;

import model.GameModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface GameDAO {
        void clearAllGames();
        HashMap<Integer, GameModel> getGameData();
        int createGame(String gameName);
        ArrayList<GameModel> listGames();
        void joinGame( String username, String playerColor, int gameID);
}
