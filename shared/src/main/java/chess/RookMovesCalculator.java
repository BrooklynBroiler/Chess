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

        /**
         * Adding the right moves
         */
        ChessMove oneMoveRight = new ChessMove(myPosition, rightRow, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveLeft = new ChessMove(myPosition, leftRow, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveUp = new ChessMove(myPosition, upCol, ChessPiece.PieceType.ROOK);
        ChessMove oneMoveDown = new ChessMove(myPosition, downCol, ChessPiece.PieceType.ROOK);

        addMovesRight(2,oneMoveRight, board, moves);
        addMovesLeft(2,oneMoveLeft, board, moves);
        addMovesUp(2,oneMoveUp, board, moves);
        addMovesDown(2, oneMoveDown, board, moves);

//        while(board.getPiece(rightRow) == null && counter < 8) {
//             int nRow = rightRow.getRow() + counter - 1;
//            ChessPosition newPosition = new ChessPosition(nRow, myPosition.getColumn());
//            ChessMove possibleMove = new ChessMove(myPosition, newPosition, ChessPiece.PieceType.ROOK);
//            if (isValidMove(possibleMove, board)){
//                moves.add(possibleMove);
//            }
//            else {
//                counter = 1;
//                break;
//            }
//            counter++;
//        }
        /**
         * Adding the left moves
         */


        /**
         * Add all the column moves going down
         */

        /**
         * Add all the column moves going up
         */

        return moves;
    }
    private Collection<ChessMove> addMovesRight(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if(!isValidMove(move, board)){
            if(capturedPiece(move, board)){
                moves.add(move);
            }
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow()+counter, move.startPosition.getColumn());
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
            moves.add(move);
            return addMovesRight(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesLeft(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if(!isValidMove(move, board)){
            if(capturedPiece(move, board)){
                moves.add(move);
            }
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow() - counter, move.startPosition.getColumn());
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
            moves.add(move);
            return addMovesLeft(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesUp(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if(!isValidMove(move, board)){
            if(capturedPiece(move, board)){
                moves.add(move);
            }
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow(), move.startPosition.getColumn() + counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
            moves.add(move);
            return addMovesUp(counter + 1, nextPossibleMove, board, moves);
        }
    }

    private Collection<ChessMove> addMovesDown(int counter, ChessMove move, ChessBoard board, Collection<ChessMove> moves){
        if(!isValidMove(move, board)){
            if(capturedPiece(move, board)){
                moves.add(move);
            }
            return moves;
        }
        else{
            ChessPosition newPosition = new ChessPosition(move.startPosition.getRow(), move.startPosition.getColumn() - counter);
            ChessMove nextPossibleMove = new ChessMove(move.startPosition, newPosition, ChessPiece.PieceType.ROOK);
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
