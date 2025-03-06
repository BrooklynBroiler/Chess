package service;

import dataaccess.AuthDAO;
import model.AuthModel;

public class LogoutService {
    private final AuthDAO authDAO;
    public LogoutService(AuthDAO authDAO){
        this.authDAO = authDAO;
    }
    public void deleteAuthToken(String authToken){
        authDAO.deleteAuthToken(authToken);
    }

}
