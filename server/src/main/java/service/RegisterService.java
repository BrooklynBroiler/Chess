package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
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
//    Returns a user model, or returns null
    public UserModel getUser(String username) {
        return userDAO.getUser(username);
    }
//    Adds user data to DB
    public void createUser(String username, String password, String email){
        userDAO.createUser(username, password, email);
    }
//    Creates Auth Data and adds it to the database
    public String createAuthData(String username){
        String authToken = UUID.randomUUID().toString();
        AuthModel authModel = new AuthModel(authToken, username);
        authDAO.mapAuthToken(username, authModel);
        return authModel.authToken();
    }
}
