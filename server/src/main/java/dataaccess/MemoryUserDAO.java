package dataaccess;

import model.UserModel;

import java.util.HashMap;

public class MemoryUserDAO implements UserDAO{

//    user data stored in a map that stores a User model as the value with a username as the key.
    final private HashMap<String, UserModel> Users = new HashMap<>();

//    storing the email and password of a user in a list
    @Override
    public void createUser(String username, String password, String email) throws DataAccessException {
//        storing the username and password of a user in a user model
        UserModel newUser = new UserModel(username, password, email);
        Users.put(username, newUser);
    }
//    Clears all users from the database
    @Override
    public void clearAllUsers(){
        Users.clear();
    }
//    Returns a user model
    @Override
    public UserModel getUser(String username) throws DataAccessException {
        return Users.get(username);
    }
}
