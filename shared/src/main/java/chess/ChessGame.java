package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    TeamColor teamColor;
    ChessBoard theBoard;
    Collection<ChessMove> moves;
    public ChessGame() {
        this.teamColor = TeamColor.WHITE;
        this.theBoard = new ChessBoard();
        theBoard.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return teamColor;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        teamColor = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(theBoard.getPiece(startPosition) != null){
            return theBoard.getPiece(startPosition).pieceMoves(theBoard, startPosition);
        }
        else return null;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        Collection<ChessPosition> enemyEndPositions = enemyMoveEndPositions(teamColor);
        Collection<ChessMove> kingMoves = theBoard.getPiece(findKingPosition(teamColor, theBoard)).pieceMoves(theBoard, findKingPosition(teamColor, theBoard));

        }
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        enemyMoveEndPositions(theBoard.getPiece())
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        theBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return theBoard;
    }

    public Collection<ChessPosition> enemyMoveEndPositions(TeamColor teamColor, ChessBoard board){
        Collection<ChessPosition> endPositions = new ArrayList<>();


            for (int row = 1; row < 9; row++) {
                for (int column = 1; column < 9; column++){
                    ChessPosition newPosition = new ChessPosition(row, column);
                    if(board.getPiece(newPosition) != null && !board.getPiece(newPosition).pieceColor.equals(teamColor)){
                        Collection<ChessMove> moves = board.getPiece(newPosition).pieceMoves(board, newPosition);
                        for (ChessMove move : moves){
                            endPositions.add(move.endPosition);
                        }
                    }
                }
            }
        return endPositions;
    }
    public ChessPosition findKingPosition(TeamColor teamColor, ChessBoard board){
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++){
                ChessPosition newPosition = new ChessPosition(row, column);
                if(board.getPiece(newPosition).pieceColor.equals(teamColor) && board.getPiece(newPosition).pieceType.equals(ChessPiece.PieceType.KING)){
                    return newPosition;
                }

    }

}
