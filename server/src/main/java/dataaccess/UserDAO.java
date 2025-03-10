package dataaccess;

import exception.ResponseException;
import model.UserModel;

import java.util.HashMap;

public interface UserDAO {
    void createUser(String username, String password, String email) throws ResponseException;
    void clearAllUsers();
    UserModel getUser(String username);
    HashMap<String, UserModel> getUserData();
}
