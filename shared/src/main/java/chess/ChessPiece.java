package chess;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    ChessGame.TeamColor pieceColor;
    ChessPiece.PieceType pieceType;
    Map<ChessPiece.PieceType, PieceMovesCalculator> calculatorMap;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.pieceType = type;
        calculatorMap = new HashMap<>();
        calculatorMap.put(ChessPiece.PieceType.KING, new KingMovesCalculator());
        calculatorMap.put(ChessPiece.PieceType.KNIGHT, new KnightMovesCalculator());
        calculatorMap.put(ChessPiece.PieceType.ROOK, new RookMovesCalculator());
        calculatorMap.put(ChessPiece.PieceType.BISHOP, new BishopMovesCalculator());
        calculatorMap.put(ChessPiece.PieceType.QUEEN, new QueenMovesCalculator());
        calculatorMap.put(ChessPiece.PieceType.PAWN, new PawnMovesCalculator());
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {

        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {

        return pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return calculatorMap.get(board.getPiece(myPosition).pieceType).pieceMoves(board, myPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

}
