package server;

import com.google.gson.Gson;
import dataaccess.*;
import exception.ResponseException;
import service.*;
import service.requestClasses.CreateGameRequest;
import service.requestClasses.LoginRequest;
import service.requestClasses.RegisterRequest;
import service.resultClasses.CreateGameResult;
import service.resultClasses.LoginResult;
import service.resultClasses.RegisterResult;
import spark.*;

public class Server {
    private final ClearService clearService;
    private final RegisterService registerService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final CreateGameService createGameService;
//    constructor for Server
    public Server(){
        AuthDAO authDAO = new MemoryAuthDAO();
        GameDAO gameDAO = new MemoryGameDAO();
        UserDAO userDAO = new MemoryUserDAO();
        this.clearService = new ClearService(userDAO, gameDAO, authDAO);
        this.registerService = new RegisterService(authDAO, userDAO);
        this.loginService = new LoginService(authDAO, userDAO);
        this.logoutService = new LogoutService(authDAO);
        this.createGameService = new CreateGameService(authDAO, gameDAO);
    }
    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.

        //This line initializes the server and can be removed once you have a functioning endpoint
        Spark.init();
        Spark.post("/user", this::registerUser);
        Spark.post("/session", this::loginUser);
        Spark.post("/game", this::createGame);
        Spark.delete("/session", this::logoutUser);
        Spark.delete("/db",this::clearAllData);
        Spark.awaitInitialization();
        return Spark.port();
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }

//    Handler Method that registers user
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

//    Handler method for the Login endpoint
    private Object loginUser(Request req, Response res){
        var serializer = new Gson();
        var user = serializer.fromJson(req.body(), LoginRequest.class);
       String authToken = loginService.createAuthData(user.username());
        LoginResult loginResult = new LoginResult(user.username(), authToken);
        var jsonLoginResult = serializer.toJson(loginResult);
        res.status(200);
        return jsonLoginResult;
    }

//    Handler method for the Logout endpoint
    private Object logoutUser(Request req, Response res){
        logoutService.deleteAuthToken(req.headers("authorization"));
        res.status(200);
        return "";
    }

//    Handler method for the Create Game endpoint
    private Object createGame(Request req, Response res){
        int gameID;
        var serializer = new Gson();
        var game = serializer.fromJson(req.body(), CreateGameRequest.class);
        var authToken = req.headers("authorizaton");
        gameID = createGameService.createGame(game.gameName());
        CreateGameResult createGameResult = new CreateGameResult(gameID);
        var jsonCreateGameResult = serializer.toJson(createGameResult);
        res.status(200);
        return jsonCreateGameResult;

    }
//    Handler method for the clear endpoint
    private Object clearAllData(Request req, Response res) throws ResponseException {
        clearService.clear();
        res.status(200);
        return "";
    }
}