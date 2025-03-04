package dataaccess;

import model.AuthModel;

import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {
    final private HashMap<String, AuthModel> authData = new HashMap<>();

//    Clears all authData
    @Override
    public void clearAllAuth() {
        authData.clear();
    }

    public HashMap<String, AuthModel> getAuthData() {
        return authData;
    }
}
