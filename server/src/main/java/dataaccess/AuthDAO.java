package dataaccess;

import model.AuthModel;

public interface AuthDAO {
    void clearAllAuth();
    //    Removes AuthModel from DataBase
    void deleteAuthToken(String authToken);
    void mapAuthToken(String username, AuthModel authModel);
    boolean checkAuthToken(String authToken);
}
