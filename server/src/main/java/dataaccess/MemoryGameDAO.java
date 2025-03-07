package dataaccess;

import chess.ChessGame;
import model.GameModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class MemoryGameDAO implements GameDAO{
    final private HashMap<Integer, GameModel> gamesData = new HashMap<>();
//    Clears all the games from the DataBase
    @Override
    public void clearAllGames(){
        gamesData.clear();
    }
//    returns the map of gamesData
    public HashMap<Integer, GameModel> getGameData(){
        return gamesData;
    }

//    creates a game
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

//    Returns a list of all the games in the database
    public ArrayList<GameModel> listGames(){
        ArrayList<GameModel> listOfGames = new ArrayList<>();
        for(Integer gameID : gamesData.keySet())
            if (gamesData.get(gameID) != null){
                listOfGames.add(gamesData.get(gameID));
            }
        return listOfGames;
    }

//    Functionality of joining a game
    @Override
    public void joinGame( String username, String playerColor, int gameID){
        GameModel gameToJoin = gamesData.get(gameID);
        if (playerColor.equals("WHITE") && gameToJoin.whiteUsername() == null){
            GameModel whiteJoin = new GameModel(gameID, username, gameToJoin.blackUsername(), gameToJoin.gameName(), gameToJoin.game());
            gamesData.put(gameID, whiteJoin);
        }
        else if ( playerColor.equals("BLACK") && gameToJoin.blackUsername() == null){
            GameModel blackJoin = new GameModel(gameID, gameToJoin.whiteUsername(), username, gameToJoin.gameName(), gameToJoin.game());
            gamesData.put(gameID, blackJoin);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemoryGameDAO that = (MemoryGameDAO) o;
        return Objects.equals(gamesData, that.gamesData);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(gamesData);
    }
}
