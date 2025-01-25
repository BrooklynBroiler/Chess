package chess;
import java.util.*;
public class KingMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        int[][] kingMoves = {
                { -1, -1 }, { -1, 1 }, { -1, 0 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, 1 }, { 0, -1}
        };

        for (int[] move : kingMoves) {
            int nRow = myPosition.getRow() + move[0];
            int nColumn = myPosition.getColumn() + move[1];
            ChessPosition newPosition = new ChessPosition(nRow, nColumn);
            ChessMove possibleMove = new ChessMove(myPosition, newPosition, ChessPiece.PieceType.KING);
            if (isValidMove(possibleMove, board)){
                moves.add(possibleMove);
            }
        }
        return moves;
    }

    @Override
    public boolean isValidMove(ChessMove move, ChessBoard board) {
        if (move.endPosition.getRow() < 0 || move.endPosition.getColumn() < 0 || move.endPosition.getRow() > 7 || move.endPosition.getColumn() > 7) {
            return false;
        }
        else if (board.getPiece(move.endPosition) != null ) {
            return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
        }
        return true;
    }


}
