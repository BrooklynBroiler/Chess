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
//    returns username if user has authorization and null if there is no authorization
    public String checkAuth(String authToken){
        return authDAO.getUsernameOfAuthToken(authToken);
    }

    public int createGame(String gameName){
       return gameDAO.createGame(gameName);
    }

}
