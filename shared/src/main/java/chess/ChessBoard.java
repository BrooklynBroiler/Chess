package chess;
import java.util.*;
/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
       squares[position.getRow() - 1][position.getColumn() - 1] = piece;


    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {

        return squares[position.getRow() - 1][position.getColumn() - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                if (row == 2){
                    ChessPosition whtPawnPosition = new ChessPosition(row, column);
                    ChessPiece whtPawnPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN );
                    addPiece(whtPawnPosition, whtPawnPiece);
                }
                else if (row == 7){
                    ChessPosition blkPawnPosition = new ChessPosition(row, column);
                    ChessPiece blkPawnPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN );
                    addPiece(blkPawnPosition, blkPawnPiece);
                }
                else if (row == 8 && (column == 1 || column == 8)){
                    ChessPosition blkRookPosition = new ChessPosition(row, column);
                    ChessPiece blkRookPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK );
                    addPiece(blkRookPosition, blkRookPiece);
                }
                else if (row == 1 && (column == 1 || column == 8)){
                    ChessPosition whtRookPosition = new ChessPosition(row, column);
                    ChessPiece whtRookPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK );
                    addPiece(whtRookPosition, whtRookPiece);
                }
                else if (row == 8 && (column == 2 || column == 7)){
                    ChessPosition blkKnightPosition = new ChessPosition(row, column);
                    ChessPiece blkKnightPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT );
                    addPiece(blkKnightPosition, blkKnightPiece);
                }
                else if (row == 1 && (column == 2 || column == 7)){
                    ChessPosition whtKnightPosition = new ChessPosition(row, column);
                    ChessPiece whtKnightPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT );
                    addPiece(whtKnightPosition, whtKnightPiece);
                }
                else if (row == 8 && (column == 3 || column == 6)){
                    ChessPosition blkBishopPosition = new ChessPosition(row, column);
                    ChessPiece blkBishopPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
                    addPiece(blkBishopPosition, blkBishopPiece);
                }
                else if (row == 1 && (column == 3 || column == 6)){
                    ChessPosition whtBishopPosition = new ChessPosition(row, column);
                    ChessPiece whtBishopPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP );
                    addPiece(whtBishopPosition, whtBishopPiece);
                }
                else if (row == 8 && column == 4){
                    ChessPosition blkQueenPosition = new ChessPosition(row, column);
                    ChessPiece blkQueenPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
                    addPiece(blkQueenPosition, blkQueenPiece);
                }
                else if (row == 1 && column == 4){
                    ChessPosition whtQueenPosition = new ChessPosition(row, column);
                    ChessPiece whtQueenPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN );
                    addPiece(whtQueenPosition, whtQueenPiece);
                }
                else if (row == 8){
                    ChessPosition blkKingPosition = new ChessPosition(row, column);
                    ChessPiece blkKingPiece = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
                    addPiece(blkKingPosition, blkKingPiece);
                }
                else if (row == 1){
                    ChessPosition whtKingPosition = new ChessPosition(row, column);
                    ChessPiece whtKingPiece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING );
                    addPiece(whtKingPosition, whtKingPiece);
                }

            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }

}
