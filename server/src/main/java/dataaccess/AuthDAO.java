package dataaccess;

import exception.ResponseException;
import model.AuthModel;

public interface AuthDAO {
    void clearAllAuth();
    //    Removes AuthModel from DataBase
    String deleteAuthToken(String authToken) throws ResponseException;
    void mapAuthToken(String username, AuthModel authModel);
    String getUsernameOfAuthToken(String authToken) throws ResponseException;
}
