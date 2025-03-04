package model;

import chess.ChessGame;

import java.util.Objects;

public class GameModel {
    private final int gameId;
    private final String whiteUsername;
    private final String blackUsername;
    private final String gameName;
    private final ChessGame game;

    GameModel(int gameId, String whiteUsername, String blackUsername, String gameName, ChessGame game){
        this.gameId = gameId;
        this. whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.game = game;
    }

    public int getGameId() {
        return gameId;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public String getGameName() {
        return gameName;
    }

    public ChessGame getGame() {
        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameModel gameModel = (GameModel) o;
        return gameId == gameModel.gameId
                && Objects.equals(whiteUsername, gameModel.whiteUsername)
                && Objects.equals(blackUsername, gameModel.blackUsername)
                && Objects.equals(gameName, gameModel.gameName)
                && Objects.equals(game, gameModel.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, whiteUsername, blackUsername, gameName, game);
    }
}
