package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator implements PieceMovesCalculator{


    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] knightMoves = {
                { -2, -1 }, { -1, -2 }, { 2, -1 }, { 1, -2 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }
        };

        for (int[] move : knightMoves) {
            int nRow = myPosition.getRow() + move[0];
            int nColumn = myPosition.getColumn() + move[1];
            ChessPosition newPosition = new ChessPosition(nRow, nColumn);
            ChessMove possibleMove = new ChessMove(myPosition, newPosition, null);
            if (isValidMove(possibleMove, board)){
                moves.add(possibleMove);
            }
        }
        return moves;
    }

    @Override
    public boolean isValidMove(ChessMove move, ChessBoard board) {
        if (move.endPosition.getRow() < 1 || move.endPosition.getColumn() < 1 || move.endPosition.getRow() > 8 || move.endPosition.getColumn() > 8) {
            return false;
        }
        else if (board.getPiece(move.endPosition) != null ) {
            return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
        }
        return true;
    }
}

