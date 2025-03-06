package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;

public class CreateGameService {
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    public CreateGameService(AuthDAO authDAO, GameDAO gameDAO){
        this.gameDAO = gameDAO;
        this.authDAO = authDAO;
    }
//    returns True if user has authorization
    public boolean checkAuth(String authToken){
        return authDAO.checkAuthToken(authToken);
    }

    public int createGame(String gameName){
       return gameDAO.createGame(gameName);
    }

}
