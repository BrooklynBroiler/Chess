package model;

import chess.ChessGame;

public record GameModel(Integer gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {}
