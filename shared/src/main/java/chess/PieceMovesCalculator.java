package chess;

import java.util.Collection;

public interface PieceMovesCalculator {

    Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);

    boolean isValidMove(ChessMove move, ChessBoard board);
}

