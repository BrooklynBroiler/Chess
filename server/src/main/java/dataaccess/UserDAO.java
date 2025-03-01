package dataaccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface UserDAO {
    void createUser(String username, String password, String email) throws DataAccessException;
    void deleteAllUsers() throws DataAccessException;
    ArrayList<String> getUser(String username) throws DataAccessException;
}
