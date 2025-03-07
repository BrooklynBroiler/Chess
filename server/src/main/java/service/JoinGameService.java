package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;

public class JoinGameService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;
    public JoinGameService(GameDAO gameDAO, AuthDAO authDAO){
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
    }
    public String getUsername(String authToken){
        return authDAO.getUsernameOfAuthToken(authToken);
    }
    public void joinGame(String username, int gameId, String playerColor){
        gameDAO.joinGame(username, playerColor, gameId);
    }
}
