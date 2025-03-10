package dataaccess;

import exception.ResponseException;
import model.AuthModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MemoryAuthDAO implements AuthDAO {
    final private HashMap<String, ArrayList<AuthModel>> authData = new HashMap<>();

//    Clears all authData
    @Override
    public void clearAllAuth() {
        authData.clear();
    }
//    removes AuthModel from data base
    @Override
    public String deleteAuthToken(String authToken)throws ResponseException{
        for(String username : authData.keySet()) {
            ArrayList<AuthModel> authModelList = authData.get(username);
            if (authModelList != null && authData.get(username).contains(new AuthModel(authToken, username))) {
                authModelList.removeIf(authModel -> authModel.authToken().equals(authToken));
                return username;
            }
        }
        return null;
    }

//    Stores an AuthModel with the given username as the key
    public void mapAuthToken(String username, AuthModel authModel){
        authData.computeIfAbsent(username, k -> new ArrayList<>());
        authData.get(username).add(authModel);
    }
// returns the username if user has authorization, and returns null if there is no authorization
    @Override
    public String getUsernameOfAuthToken(String authToken){
        for(String username : authData.keySet())
            if (authData.get(username) != null && authData.get(username).contains(new AuthModel(authToken, username))){
                return username;
            }
        return null;
    }
//    getter for auth data map
    @Override
    public HashMap<String, ArrayList<AuthModel>> getAuthData() {
        return authData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemoryAuthDAO that = (MemoryAuthDAO) o;
        return Objects.equals(authData, that.authData);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authData);
    }
}
