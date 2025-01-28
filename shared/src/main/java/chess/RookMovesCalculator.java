package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMovesCalculator implements PieceMovesCalculator{
    private int counter;

    public RookMovesCalculator() {
        counter = 1;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPosition rightRow = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
        ChessPosition leftRow = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
        ChessPosition downCol = new ChessPosition(myPosition.getRow(),myPosition.getColumn() - 1);
        ChessPosition upCol = new ChessPosition( myPosition.getRow(),myPosition.getColumn() + 1);

        ChessMove oneMoveRight = new ChessMove(myPosition, rightRow, null);
        ChessMove oneMoveLeft = new ChessMove(myPosition, leftRow, null);
        ChessMove oneMoveUp = new ChessMove(myPosition, upCol, null);
        ChessMove oneMoveDown = new ChessMove(myPosition, downCol, null);

        addMovesRight(2,oneMoveRight, board, moves);
        addMovesLeft(2,oneMoveLeft, board, moves);
        addMovesUp(2,oneMoveUp, board, moves);
        addMovesDown(2, oneMoveDown, board, moves);

        return moves;
    }
    private Collection<ChessMove> addMovesRight(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if (move.endPosition.getRow() < 1 || move.endPosition.getRow() > 8 ||
                move.endPosition.getColumn() < 1 || move.endPosition.getColumn() > 8) {
            return moves; // Stop recursion if out of bounds
        }
        else if(capturedPiece(move, board)){
            moves.add(move);
            return moves;
        }
        else if (board.getPiece(move.endPosition)!= null && !capturedPiece(move, board)) {
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow()+counter, move.startPosition.getColumn());
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, null);
            moves.add(move);
            return addMovesRight(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesLeft(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if (move.endPosition.getRow() < 1 || move.endPosition.getRow() > 8 ||
                move.endPosition.getColumn() < 1 || move.endPosition.getColumn() > 8) {
            return moves; // Stop recursion if out of bounds
        }
        else if(capturedPiece(move, board)){
            moves.add(move);
            return moves;
        }
        else if (board.getPiece(move.endPosition)!= null && !capturedPiece(move, board)) {
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() - counter, move.startPosition.getColumn());
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, null);
            moves.add(move);
            return addMovesLeft(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesUp(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if (move.endPosition.getRow() < 1 || move.endPosition.getRow() > 8 ||
                move.endPosition.getColumn() < 1 || move.endPosition.getColumn() > 8) {
            return moves; // Stop recursion if out of bounds
        }
        else if(capturedPiece(move, board)){
            moves.add(move);
            return moves;
        }
        else if (board.getPiece(move.endPosition)!= null && !capturedPiece(move, board)) {
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow(), move.startPosition.getColumn() + counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, null);
            moves.add(move);
            return addMovesUp(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesDown(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if (move.endPosition.getRow() < 1 || move.endPosition.getRow() > 8 ||
                move.endPosition.getColumn() < 1 || move.endPosition.getColumn() > 8) {
            return moves; // Stop recursion if out of bounds
        }
        else if(capturedPiece(move, board)){
                moves.add(move);
                return moves;
            }
        else if (board.getPiece(move.endPosition)!= null && !capturedPiece(move, board)) {
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow(), move.startPosition.getColumn() - counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, null);
            moves.add(move);
            return addMovesDown(counter + 1, nextPossibleMove, board, moves);
        }
    }

    @Override
    public boolean isValidMove(ChessMove move, ChessBoard board) {
        if (move.endPosition.getRow() < 1 || move.endPosition.getColumn() < 1 || move.endPosition.getRow() > 8 || move.endPosition.getColumn() > 8) {
            return false;
        }
        else if (board.getPiece(move.endPosition) != null ) {
            return capturedPiece(move, board);
        }
        return true;
    }
    private boolean capturedPiece(ChessMove move, ChessBoard board){
        if (board.getPiece(move.endPosition) != null){
            return !board.getPiece(move.endPosition).pieceColor.equals(board.getPiece(move.startPosition).pieceColor);
        }
        return false;
    }
}
