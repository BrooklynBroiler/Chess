package server;

import com.google.gson.Gson;
import dataaccess.*;
import exception.ResponseException;
import model.UserModel;
import service.ClearService;
import service.RegisterService;
import service.requestClasses.RegisterRequest;
import service.resultClasses.RegisterResult;
import spark.*;

public class Server {
    private final ClearService clearService;
    private final RegisterService registerService;
//    constructor for Server
    public Server(){
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserDAO userDAO = new MemoryUserDAO();
        this.clearService = new ClearService(userDAO, gameDAO, authDAO);
        this.registerService = new RegisterService(authDAO, userDAO);
    }
    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //This line initializes the server and can be removed once you have a functioning endpoint
        Spark.init();
        Spark.post("/user", this::registerUser);
        Spark.delete("/db",this::clearAllData);
        Spark.awaitInitialization();
        return Spark.port();
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
    private Object registerUser(Request req, Response res){
        var serializer = new Gson();
        var newUser = serializer.fromJson(req.body(),RegisterRequest.class);
        if (registerService.getUser(newUser.username()) == null){
            registerService.createUser(newUser.username(), newUser.password(), newUser.email());
        }
        String authToken = registerService.createAuthData(newUser.username());
        RegisterResult registerResult = new RegisterResult(newUser.username(), authToken);
        var jsonRegisterResult = serializer.toJson(registerResult);
        res.status(200);
        return jsonRegisterResult;

    }
    private Object clearAllData(Request req, Response res) throws ResponseException {
        clearService.clear();
        res.status(200);
        return "";
    }
}