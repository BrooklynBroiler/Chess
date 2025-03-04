package model;

import java.util.Objects;

public class AuthModel {
    private final String authToken;
    private final String username;
    AuthModel(String authToken, String username){
        this.authToken = authToken;
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthModel authModel = (AuthModel) o;
        return Objects.equals(authToken, authModel.authToken) && Objects.equals(username, authModel.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, username);
    }
}
