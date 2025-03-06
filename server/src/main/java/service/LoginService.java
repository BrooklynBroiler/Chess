package service;

import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import model.AuthModel;
import model.UserModel;

import java.util.Objects;
import java.util.UUID;

public class LoginService {
    private final AuthDAO authDAO;
    private final UserDAO userDAO;

//    Constructor for the Login service
    public LoginService(AuthDAO authDAO, UserDAO userDAO){
        this.authDAO = authDAO;
        this.userDAO = userDAO;
    }

//    accesses user information and return wrong password response
    public void checkUserInfo(String username, String password) {
        UserModel loginUser = userDAO.getUser(username);
    }
//    Creates Auth Data and adds it to the database
    public String createAuthData(String username){
        String authToken = UUID.randomUUID().toString();
        AuthModel authModel = new AuthModel(authToken, username);
        authDAO.mapAuthToken(username, authModel);
        return authModel.authToken();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginService that = (LoginService) o;
        return Objects.equals(authDAO, that.authDAO) && Objects.equals(userDAO, that.userDAO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authDAO, userDAO);
    }
}
