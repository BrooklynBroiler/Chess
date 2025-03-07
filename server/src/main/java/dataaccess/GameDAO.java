package dataaccess;

import exception.ResponseException;
import model.GameModel;

import java.util.HashMap;

public interface GameDAO {
        void clearAllGames();
        HashMap<Integer, GameModel> getGameData();
        int createGame(String gameName);
        HashMap<Integer, GameModel> listGames();
        void joinGame( String username, String playerColor, int gameID) throws ResponseException;
}
