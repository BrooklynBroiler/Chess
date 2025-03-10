package service;


import dataaccess.*;
import exception.ResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ClearServiceTests {




    @Test
    public void clearServiceSuccess() throws ResponseException {
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserDAO userDAO = new MemoryUserDAO();

        userDAO.createUser("something", "password", "email@email.com");
        gameDAO.createGame("Something");
        RegisterService new_service = new RegisterService(authDAO, userDAO);
        new_service.createAuthData("something");
        ClearService clearService = new ClearService(userDAO, gameDAO, authDAO);
        clearService.clear();

        Assertions.assertEquals(authDAO.getAuthData(), new HashMap<>());
        Assertions.assertEquals(gameDAO.getGameData(), new HashMap<>());
        Assertions.assertEquals(userDAO.getUserData(), new HashMap<>());

    }
}

