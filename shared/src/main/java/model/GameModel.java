package model;

import chess.ChessGame;

public record GameModel(int id, String whiteUsername, String blackUsername, String gameName, ChessGame game) {}
