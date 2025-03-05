package server;

import dataaccess.*;
import exception.ResponseException;
import service.ClearService;
import spark.*;

public class Server {
    private final ClearService clearService;

    public Server(){
        UserDAO userDAO = new MemoryUserDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        AuthDAO authDAO = new MemoryAuthDAO();
        this.clearService = new ClearService(userDAO, gameDAO, authDAO);
    }
    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //This line initializes the server and can be removed once you have a functioning endpoint
        Spark.init();
        Spark.delete("/db",this::clearAllData);
        Spark.awaitInitialization();
        return Spark.port();
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
    private Object clearAllData(Request req, Response res) throws ResponseException {
        this.clearService.clear();
        res.status(200);
        return "";
    }
}