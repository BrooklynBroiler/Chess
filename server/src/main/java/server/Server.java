package server;

import com.google.gson.Gson;
import dataaccess.*;
import exception.ResponseException;
import service.*;
import service.requestClasses.CreateGameRequest;
import service.requestClasses.JoinGameRequest;
import service.requestClasses.LoginRequest;
import service.requestClasses.RegisterRequest;
import service.resultClasses.CreateGameResult;
import service.resultClasses.ListGameResult;
import service.resultClasses.LoginResult;
import service.resultClasses.RegisterResult;
import spark.*;

public class Server {
    private final ClearService clearService;
    private final RegisterService registerService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final CreateGameService createGameService;
    private final ListGamesService listGamesService;
    private final JoinGameService joinGameService;
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
        this.listGamesService = new ListGamesService(authDAO, gameDAO);
        this.joinGameService = new JoinGameService(gameDAO, authDAO);
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
        Spark.get("/game", this::listGames);
        Spark.put("/game", this::joinGame);
        Spark.delete("/session", this::logoutUser);
        Spark.delete("/db",this::clearAllData);
        Spark.awaitInitialization();
        Spark.exception(ResponseException.class, this::exceptionHandler);
        return Spark.port();
    }


    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
    private void exceptionHandler(ResponseException ex, Request req, Response res) {
        res.status(ex.getStatusCode());
        res.body(ex.toJson());
    }

//    Handler Method that registers user
    private Object registerUser(Request req, Response res) throws ResponseException{
        var serializer = new Gson();
        var newUser = serializer.fromJson(req.body(),RegisterRequest.class);

        registerService.createUser(newUser.username(), newUser.password(), newUser.email());
        String authToken = registerService.createAuthData(newUser.username());
        RegisterResult registerResult = new RegisterResult(newUser.username(), authToken);
        var jsonRegisterResult = serializer.toJson(registerResult);
        res.status(200);
        return jsonRegisterResult;

    }

//    Handler method for the Login endpoint
    private Object loginUser(Request req, Response res) throws ResponseException{
        var serializer = new Gson();
        var user = serializer.fromJson(req.body(), LoginRequest.class);
        loginService.checkUserInfo(user.username(), user.password());
        String authToken = loginService.createAuthData(user.username());
        LoginResult loginResult = new LoginResult(user.username(), authToken);
        var jsonLoginResult = serializer.toJson(loginResult);
        res.status(200);
        return jsonLoginResult;
    }

//    Handler method for the Logout endpoint
    private Object logoutUser(Request req, Response res) throws ResponseException{
        if (req.headers("authorization") == null || req.headers("authorization").isEmpty()){
            throw new ResponseException(401, "Error: unauthorized");
        }
        logoutService.deleteAuthToken(req.headers("authorization"));
        res.status(200);
        return "";
    }

//    Handler method for the Create Game endpoint
    private Object createGame(Request req, Response res) throws ResponseException{
        int gameID;
        var serializer = new Gson();
        var game = serializer.fromJson(req.body(), CreateGameRequest.class);
        var authToken = req.headers("authorization");
        var username = createGameService.checkAuth(authToken);
        gameID = createGameService.createGame(game.gameName());
        CreateGameResult createGameResult = new CreateGameResult(gameID);
        var jsonCreateGameResult = serializer.toJson(createGameResult);
        res.status(200);
        return jsonCreateGameResult;

    }

//    Handler method for list games endpoint
    private Object listGames(Request req, Response res)throws ResponseException{
        var serializer = new Gson();
        var authToken = req.headers("authorization");
        if (listGamesService.checkAuth(authToken)== null){
            throw new ResponseException(401, "Error: unauthorized");
        }
        ListGameResult listGameResult = new ListGameResult(listGamesService.listGames().values());
        res.status(200);
        return serializer.toJson(listGameResult);
    }

//    Handler method for join game endpoint
    private Object joinGame(Request req, Response res) throws ResponseException {
        var serializer = new Gson();
        var authToken = req.headers("authorization");
        var player = serializer.fromJson(req.body(), JoinGameRequest.class);
        if (player.playerColor() == null ||(!player.playerColor().equals("WHITE") && !player.playerColor().equals("BLACK")) || player.gameID() == null){
            throw new ResponseException(400, "Error: bad request");
        }
        if (joinGameService.getUsername(authToken) !=null){
            joinGameService.joinGame(joinGameService.getUsername(authToken),player.gameID(), player.playerColor());
            res.status(200);
            return "";
        }
        return "";
    }
//    Handler method for the clear endpoint
    private Object clearAllData(Request req, Response res) throws ResponseException {
        clearService.clear();
        res.status(200);
        return "";
    }
}