package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenMovesCalculator implements PieceMovesCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        /**
         * Bishop logic here
         */
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPosition topRightBox = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
        ChessPosition topLeftBox = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
        ChessPosition bottomLeftBox = new ChessPosition(myPosition.getRow() - 1,myPosition.getColumn() - 1);
        ChessPosition bottomRightBox = new ChessPosition( myPosition.getRow() + 1,myPosition.getColumn() - 1);

        ChessMove oneMoveTopRight = new ChessMove(myPosition, topRightBox, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveTopLeft = new ChessMove(myPosition, topLeftBox, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveBottomLeft= new ChessMove(myPosition, bottomLeftBox, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveBottomRight= new ChessMove(myPosition, bottomRightBox, ChessPiece.PieceType.QUEEN);


        addMovesTopRightLine(2,oneMoveTopRight, board, moves);
        addMovesTopLeftLine(2,oneMoveTopLeft, board, moves);
        addMovesBottomLeftLine(2,oneMoveBottomLeft, board, moves);
        addMovesBottomRightLine(2, oneMoveBottomRight, board, moves);

        /**
         * Rook logic here
         */

        ChessPosition rightRow = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
        ChessPosition leftRow = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
        ChessPosition downCol = new ChessPosition(myPosition.getRow(),myPosition.getColumn() - 1);
        ChessPosition upCol = new ChessPosition( myPosition.getRow(),myPosition.getColumn() + 1);

        ChessMove oneMoveRight = new ChessMove(myPosition, rightRow, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveLeft = new ChessMove(myPosition, leftRow, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveUp = new ChessMove(myPosition, upCol, ChessPiece.PieceType.QUEEN);
        ChessMove oneMoveDown = new ChessMove(myPosition, downCol, ChessPiece.PieceType.QUEEN);

        addMovesRight(2,oneMoveRight, board, moves);
        addMovesLeft(2,oneMoveLeft, board, moves);
        addMovesUp(2,oneMoveUp, board, moves);
        addMovesDown(2, oneMoveDown, board, moves);

        return moves;
    }

    /**
     *Bishop Recursive helper functions here
     */
    private Collection<ChessMove> addMovesTopRightLine(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
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
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() + counter, move.startPosition.getColumn() + counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
            moves.add(move);
            return addMovesTopRightLine(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesTopLeftLine(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
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
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() - counter, move.startPosition.getColumn() + counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
            moves.add(move);
            return addMovesTopLeftLine(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesBottomLeftLine(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
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
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() - counter, move.startPosition.getColumn() - counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
            moves.add(move);
            return addMovesBottomLeftLine(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesBottomRightLine(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
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
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() + counter, move.startPosition.getColumn() - counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
            moves.add(move);
            return addMovesBottomRightLine(counter + 1, nextPossibleMove, board, moves);
        }
    }

    /**
     *Rook helper functions here
     */
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.QUEEN);
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
