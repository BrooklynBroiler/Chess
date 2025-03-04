package dataaccess;

import java.util.HashMap;
import model.UserModel
public class MemoryUserDAO implements UserDAO{

//    user data stored in a map that stores a User model as the value with a username as the key.
    final private HashMap<String, UserModel> Users = new HashMap<>();

//    storing the email and password of a user in a list
    @Override
    public void createUser(String username, String password, String email) throws DataAccessException {
        //storing the username and password of a user in a list
        UserModel newUser = new UserModel(username, password, email);
        Users.put(username, newUser);
    }
//    Empties users from the database
    @Override
    public void clearAllUsers() throws DataAccessException {
        Users.clear();
    }

    @Override
    public UserModel getUser(String username) throws DataAccessException {
        return Users.getOrDefault(username,null  );
    }
}
