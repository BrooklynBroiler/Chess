package dataaccess;

import chess.ChessGame;
import model.GameModel;

import java.util.HashMap;
import java.util.Random;

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
    @Override
    public int createGame(String gameName){
        var random = new Random();
        int randomID;

//        loops through until randomID is a number not used before
        do{
        randomID = 1000 + random.nextInt(9000);
        }while(gamesData.containsKey(randomID));

        GameModel newGame = new GameModel(randomID,null, null, gameName, new ChessGame());
        gamesData.put(randomID, newGame);
        return randomID; //the gameId
    }
}
