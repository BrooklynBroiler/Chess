package service;

import dataaccess.*;
import service.requestClasses.ClearRequest;

public class ClearService {
    private final UserDAO userDAO;
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;
    public ClearService(UserDAO userDAO, GameDAO gameDAO, AuthDAO authDAO){
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }
    public void clear(){
        authDAO.clearAllAuth();
        gameDAO.clearAllGames();
        userDAO.clearAllUsers();
    }
}
