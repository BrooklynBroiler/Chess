package dataaccess;

import chess.ChessGame;
import exception.ResponseException;
import model.GameModel;

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
    public HashMap<Integer, GameModel> listGames(){
        return gamesData;
    }

//    Functionality of joining a game
    @Override
    public void joinGame( String username, String playerColor, Integer gameID) throws ResponseException{

        if (gameID == null){
            throw new ResponseException(400, "Error: bad request");
        }

        GameModel gameToJoin = gamesData.get(gameID);
        if (gameToJoin == null){
            throw new ResponseException(500, "Error: game to join is null");
        }
        if (playerColor.equals("WHITE") && gameToJoin.whiteUsername() == null){
            GameModel whiteJoin = new GameModel(gameToJoin.gameID(), username, gameToJoin.blackUsername(), gameToJoin.gameName(), gameToJoin.game());
            gamesData.put(gameToJoin.gameID(), whiteJoin);
        }
        else if ( playerColor.equals("BLACK") && gameToJoin.blackUsername() == null){
            GameModel blackJoin = new GameModel(gameToJoin.gameID(), gameToJoin.whiteUsername(), username, gameToJoin.gameName(), gameToJoin.game());
            gamesData.put(gameToJoin.gameID(), blackJoin);
        }
        else{
            throw new ResponseException(403, "Error: already taken");
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
