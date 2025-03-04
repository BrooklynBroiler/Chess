package dataaccess;
import org.eclipse.jetty.util.security.Password;

import java.util.ArrayList;
import java.util.HashMap;
public class MemoryUserDAO implements UserDAO{

//    user data stored in a map that stores a List with the Password stored as the first string object in the list,
//     then the Email as the second.
//    Map<key> = username
//    List[0] = Password
//    List[1] = Email
    final private HashMap<String, ArrayList<String>> Users = new HashMap<>();

//    storing the email and password of a user in a list
    @Override
    public void createUser(String username, String password, String email) throws DataAccessException {
        //storing the username and password of a user in a list
        ArrayList<String> userData = new ArrayList<String>();
        userData.add(password);
        userData.add(email);
        Users.put(username, userData);
    }
//    Empties users from the database
    @Override
    public void clearAllUsers() throws DataAccessException {
        Users.clear();
    }

    @Override
    public ArrayList<String> getUser(String username) throws DataAccessException {
        return Users.getOrDefault(username, null);
    }

}
