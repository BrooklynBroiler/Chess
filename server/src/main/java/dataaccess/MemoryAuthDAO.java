package dataaccess;

import model.AuthModel;

import java.util.HashMap;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {
    final private HashMap<String, AuthModel> authData = new HashMap<>();

//    Clears all authData
    @Override
    public void clearAllAuth() {
        authData.clear();
    }

//    Stores an AuthModel with the given username as the key
    public void mapAuthToken(String username, AuthModel authModel){
       authData.put(username, authModel);
    }

    public HashMap<String, AuthModel> getAuthData() {
        return authData;
    }
}
