package dataaccess;

import model.AuthModel;

public interface AuthDAO {
    void clearAllAuth();
    void mapAuthToken(String username, AuthModel authModel);
}
