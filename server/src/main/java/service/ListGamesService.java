package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import exception.ResponseException;
import model.GameModel;

import java.util.HashMap;

public class ListGamesService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;
    public ListGamesService(AuthDAO authDAO, GameDAO gameDAO){
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }
    public String checkAuth(String authToken)throws ResponseException {
        return authDAO.getUsernameOfAuthToken(authToken);
    }
    public HashMap<Integer, GameModel> listGames(){
        return gameDAO.listGames();
    }
}
