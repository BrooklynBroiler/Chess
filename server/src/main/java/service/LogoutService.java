package service;

import dataaccess.AuthDAO;
import exception.ResponseException;
import model.AuthModel;

public class LogoutService {
    private final AuthDAO authDAO;
    public LogoutService(AuthDAO authDAO){
        this.authDAO = authDAO;
    }
    public void deleteAuthToken(String authToken) throws ResponseException{
        if (authDAO.deleteAuthToken(authToken) == null){
            throw new ResponseException(401, "Error: unauthorized");
        }
    }

}
