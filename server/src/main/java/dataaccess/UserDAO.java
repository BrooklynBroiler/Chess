package dataaccess;

import model.UserModel;

public interface UserDAO {
    void createUser(String username, String password, String email) throws DataAccessException;
    void clearAllUsers();
    UserModel getUser(String username) throws DataAccessException;
}
