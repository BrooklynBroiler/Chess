package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import exception.ResponseException;
import model.AuthModel;
import model.UserModel;

import java.util.UUID;

public class RegisterService {
    private final AuthDAO authDAO;
    private final UserDAO userDAO;
    public RegisterService(AuthDAO authDAO, UserDAO userDAO){
        this.authDAO = authDAO;
        this.userDAO = userDAO;
    }
//    Adds user data to DB
    public void createUser(String username, String password, String email) throws ResponseException {
        userDAO.createUser(username, password, email);

    }
//    Creates Auth Data and adds it to the database
    public String createAuthData(String username) throws ResponseException{
        String authToken = UUID.randomUUID().toString();
        AuthModel authModel = new AuthModel(authToken, username);
        authDAO.mapAuthToken(username, authModel);
        return authModel.authToken();
    }
}
