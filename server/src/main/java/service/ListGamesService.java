package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import model.GameModel;

import java.util.ArrayList;

public class ListGamesService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;
    public ListGamesService(AuthDAO authDAO, GameDAO gameDAO){
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }
    public ArrayList<GameModel> listGames(){
        return gameDAO.listGames();
    }
}
