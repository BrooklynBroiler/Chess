package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import exception.ResponseException;

public class JoinGameService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;
    public JoinGameService(GameDAO gameDAO, AuthDAO authDAO){
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }
    public String getUsername(String authToken)throws ResponseException{
        if (authDAO.getUsernameOfAuthToken(authToken) == null){
            throw new ResponseException(401, "Error: unauthorized");
        }
        return authDAO.getUsernameOfAuthToken(authToken);
    }
    public void joinGame(String username, int gameId, String playerColor)throws ResponseException{
        gameDAO.joinGame(username, playerColor, gameId);
    }
}
