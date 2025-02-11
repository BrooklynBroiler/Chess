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
        Collection<ChessMove> validMoves = new ArrayList<>();
        if (theBoard.getPiece(startPosition) == null){
            return null;
        }

//        if(theBoard.getPiece(startPosition).pieceType.equals(ChessPiece.PieceType.KING)){
//            Collection<ChessMove> kingMoves = theBoard.getPiece(startPosition).pieceMoves(theBoard, startPosition);
//            Collection<ChessPosition> enemyEndPositions = enemyMoveEndPositions(theBoard.getPiece(startPosition).getTeamColor(), theBoard);
//            kingMoves.removeIf(kingMove -> enemyEndPositions.contains(kingMove.endPosition));
//            validMoves.addAll(kingMoves);
//        }
        else{
                ChessPiece piece = new ChessPiece(theBoard.getPiece(startPosition).getTeamColor(), theBoard.getPiece(startPosition).getPieceType());
                Collection<ChessMove> pieceMoves = piece.pieceMoves(theBoard, startPosition);
                for (ChessMove move : pieceMoves) {
                    ChessBoard copyBoard = new ChessBoard(theBoard);
                    makeMoveCopy(move, copyBoard);
                    ChessPiece copyPiece = copyBoard.getPiece(move.endPosition);
                    Collection<ChessPosition> enemyEndPositions = enemyMoveEndPositions(copyPiece.getTeamColor(), copyBoard);
                    ChessPosition kingPosition = findKingPosition(copyPiece.getTeamColor(), copyBoard);
                    if (!enemyEndPositions.contains(kingPosition)) {
                        validMoves.add(move);
                    }

                }
        }
        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (theBoard.getPiece(move.startPosition) == null){
            throw new InvalidMoveException();
        }
        if (theBoard.getPiece(move.startPosition) != null) {
            Collection<ChessMove> validMoves = validMoves(move.startPosition);
            if (validMoves.contains(move)) {
                ChessPiece piece = theBoard.getPiece(move.startPosition);
                if (piece.pieceType.equals(ChessPiece.PieceType.PAWN) && (move.endPosition.getRow() == 1 || move.endPosition.getRow() == 8)) {
                    ChessPiece promotionPiece = new ChessPiece(piece.getTeamColor(), move.promotionPiece);
                    theBoard.addPiece(move.endPosition, promotionPiece);
                    theBoard.addPiece(move.startPosition, null);
                    changeColor();
                } else {
                    theBoard.addPiece(move.endPosition, piece);
                    theBoard.addPiece(move.startPosition, null);
                    changeColor();
                }

            } else {
                throw new InvalidMoveException();
            }

        }
        else if (!theBoard.getPiece(move.startPosition).getTeamColor().equals(getTeamTurn())){
            throw new InvalidMoveException();
        }
//        if (theBoard.getPiece(move.startPosition) != null){
//            Collection<ChessMove> validMoves = validMoves(move.startPosition);
//            if(validMoves.contains(move)){
//                ChessPiece piece = theBoard.getPiece(move.startPosition);
//                if (piece.pieceType.equals(ChessPiece.PieceType.PAWN) && (move.endPosition.getRow() == 1 || move.endPosition.getRow() == 8)){
//                     ChessPiece promotionPiece= new ChessPiece(piece.getTeamColor(), move.promotionPiece);
//                     theBoard.addPiece(move.endPosition, promotionPiece);
//                     theBoard.addPiece(move.startPosition, null);
//                     changeColor();
//                }
//                else{
//                theBoard.addPiece(move.endPosition, piece);
//                theBoard.addPiece(move.startPosition, null);
//                changeColor();
//                }
//
//            }
//            else{
//                throw new InvalidMoveException();
//            }
//        }

    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        Collection<ChessPosition> enemyEndPositions = enemyMoveEndPositions(teamColor, theBoard);
        ChessPosition kingPosition= findKingPosition(teamColor, theBoard);
        return enemyEndPositions.contains(kingPosition);

    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                ChessPosition newPosition = new ChessPosition(row, column);
                if (theBoard.getPiece(newPosition) != null && theBoard.getPiece(newPosition).getTeamColor().equals(teamColor)){
                    Collection<ChessMove> moves= validMoves(newPosition);
                    validMoves.addAll(moves);
                }
            }
        }
        return validMoves.isEmpty() && isInCheck(teamColor);

    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        Collection<ChessMove> validMoves = new ArrayList<>();
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                ChessPosition newPosition = new ChessPosition(row, column);
                if (theBoard.getPiece(newPosition) != null && theBoard.getPiece(newPosition).getTeamColor().equals(teamColor)){
                    Collection<ChessMove> moves= validMoves(newPosition);
                    validMoves.addAll(moves);
                }
            }
        }
        return validMoves.isEmpty() && !isInCheck(teamColor);

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
    public ChessPosition findKingPosition(TeamColor teamColor, ChessBoard board) {
        ChessPosition somePosition = null;
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                ChessPosition newPosition = new ChessPosition(row, column);
                if(board.getPiece(newPosition)!= null) {
                    if (board.getPiece(newPosition).getTeamColor().equals(teamColor) && board.getPiece(newPosition).pieceType.equals(ChessPiece.PieceType.KING)) {
                        return newPosition;
                    }
                    //else return null;
                }
            }
        }
        return somePosition;
    }
    public void makeMoveCopy(ChessMove move, ChessBoard board){
        if (board.getPiece(move.startPosition).pieceType.equals(ChessPiece.PieceType.PAWN) && (move.endPosition.getRow() == 1 || move.endPosition.getRow() == 8)){
            ChessPiece promotionPiece= new ChessPiece(board.getPiece(move.startPosition).getTeamColor(), move.promotionPiece);
            board.addPiece(move.endPosition, promotionPiece);
            board.addPiece(move.startPosition, null);
            changeColor();
        }
        else{
            board.addPiece(move.endPosition, board.getPiece(move.startPosition));
            board.addPiece(move.startPosition, null);
            changeColor();
        }
    }
    public void changeColor(){
        if (teamColor.equals(TeamColor.WHITE)){
            teamColor = TeamColor.BLACK;
        }
        else{
            teamColor = TeamColor.WHITE;
        }
    }

}

