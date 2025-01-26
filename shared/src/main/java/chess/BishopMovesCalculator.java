package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator implements PieceMovesCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();

        ChessPosition topRightBox = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
        ChessPosition topLeftBox = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
        ChessPosition bottomLeftBox = new ChessPosition(myPosition.getRow() - 1,myPosition.getColumn() - 1);
        ChessPosition bottomRightBox = new ChessPosition( myPosition.getRow() + 1,myPosition.getColumn() - 1);

        ChessMove oneMoveTopRight = new ChessMove(myPosition, topRightBox, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveTopLeft = new ChessMove(myPosition, topLeftBox, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveBottomLeft= new ChessMove(myPosition, bottomLeftBox, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveBottomRight= new ChessMove(myPosition, bottomRightBox, ChessPiece.PieceType.ROOK);


        addMovesTopRightLine(2,oneMoveTopRight, board, moves);
        addMovesTopLeftLine(2,oneMoveTopLeft, board, moves);
        addMovesBottomLeftLine(2,oneMoveBottomLeft, board, moves);
        addMovesBottomRightLine(2, oneMoveBottomRight, board, moves);
        return moves;
    }

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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
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
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
            moves.add(move);
            return addMovesBottomRightLine(counter + 1, nextPossibleMove, board, moves);
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
