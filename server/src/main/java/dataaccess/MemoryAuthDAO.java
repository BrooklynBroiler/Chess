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
    public String createAuthToken(){
        return UUID.randomUUID().toString();
    }
    public HashMap<String, AuthModel> getAuthData() {
        return authData;
    }
}
