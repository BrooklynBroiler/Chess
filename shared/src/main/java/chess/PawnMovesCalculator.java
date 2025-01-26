package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        if (board.getPiece(myPosition).pieceColor.equals(ChessGame.TeamColor.WHITE)) {
            int[][] pawnMoves = {};

            if (myPosition.getRow() == 2) {
                ChessPosition plusTwo = new ChessPosition(myPosition.getRow() + 2, myPosition.getColumn());
                ChessPiece piecePlusTwo = board.getPiece(plusTwo);
                ChessPosition plusOne = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn());
                ChessPiece piecePlusOne = board.getPiece(plusOne);
                if (piecePlusTwo == null && piecePlusOne == null) {
                    pawnMoves = new int[][]{{1, 0}, {2, 0}};
                }
                else if (piecePlusTwo != null && piecePlusOne == null) {
                    pawnMoves = new int[][]{{ 1, 0 }};
                }
            }
            else if (myPosition.getRow() > 2) {
                pawnMoves = new int[][]{{1, 0}};
            }
            for (int[] move : pawnMoves) {
                int nRow = myPosition.getRow() + move[0];
                int nColumn = myPosition.getColumn() + move[1];
                ChessPosition newPosition = new ChessPosition(nRow, nColumn);
                ChessMove possibleMove = new ChessMove(myPosition, newPosition, ChessPiece.PieceType.PAWN);
                if ( board.getPiece(newPosition) == null) {
                    if(possibleMove.endPosition.getRow() == 8){
                        promotionMoves(possibleMove, moves);

                    }
                    else {moves.add(possibleMove);}
                }
            }
            ChessPosition topLeftBlock = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() + 1);
            ChessPosition topRightBlock = new ChessPosition(myPosition.getRow() + 1, myPosition.getColumn() - 1);
            if(isinbounds(topLeftBlock) && board.getPiece(topLeftBlock) != null){
                if (!board.getPiece(topLeftBlock).pieceColor.equals(ChessGame.TeamColor.WHITE)) {
                    ChessMove newMove = new ChessMove(myPosition, topLeftBlock, ChessPiece.PieceType.PAWN);
                    if (newMove.endPosition.getRow() == 8){
                        promotionMoves(newMove, moves);
                    }
                    else {moves.add(newMove);}
                }

            }
            if(isinbounds(topRightBlock) && board.getPiece(topRightBlock) != null){
                if (!board.getPiece(topRightBlock).pieceColor.equals(ChessGame.TeamColor.WHITE)) {
                    ChessMove newMove = new ChessMove(myPosition, topRightBlock, ChessPiece.PieceType.PAWN);
                    if (newMove.endPosition.getRow() == 8){
                        promotionMoves(newMove, moves);
                    }
                    else {moves.add(newMove);}
                }
            }

        }
        if (board.getPiece(myPosition).pieceColor.equals(ChessGame.TeamColor.BLACK)) {
            int[][] pawnMoves = {};
            if (myPosition.getRow() == 7){
                ChessPosition minusTwo = new ChessPosition(myPosition.getRow() - 2, myPosition.getColumn());
                ChessPiece pieceMinusTwo = board.getPiece(minusTwo);
                ChessPosition minusOne = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn());
                ChessPiece pieceMinusOne = board.getPiece(minusOne);
                if (pieceMinusTwo == null && pieceMinusOne == null) {
                    pawnMoves = new int[][]{{1, 0}, {2, 0}};
                }
                else if (pieceMinusTwo != null && pieceMinusOne == null) {
                    pawnMoves = new int[][]{{ 1, 0 }};
                }
            } else if (myPosition.getRow() < 7) {
                pawnMoves = new int[][]{{1, 0}};
            }
            for (int[] move : pawnMoves) {
                int nRow = myPosition.getRow() - move[0];
                int nColumn = myPosition.getColumn() - move[1];
                ChessPosition newPosition = new ChessPosition(nRow, nColumn);
                ChessMove possibleMove = new ChessMove(myPosition, newPosition, ChessPiece.PieceType.PAWN);
                if (board.getPiece(newPosition) == null) {
                    if(possibleMove.endPosition.getRow() == 1){
                        promotionMoves(possibleMove, moves);
                        continue;
                    }
                    moves.add(possibleMove);
                }
            }
            ChessPosition BottomLeftBlock = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() - 1);
            ChessPosition BottomRightBlock = new ChessPosition(myPosition.getRow() - 1, myPosition.getColumn() + 1);
            if(isinbounds(BottomLeftBlock) && board.getPiece(BottomLeftBlock) != null){
                if (!board.getPiece(BottomLeftBlock).pieceColor.equals(ChessGame.TeamColor.BLACK)) {
                    ChessMove newMove = new ChessMove(myPosition, BottomLeftBlock, ChessPiece.PieceType.PAWN);
                    if (newMove.endPosition.getRow() == 1){
                        promotionMoves(newMove, moves);
                    }
                    else {moves.add(newMove);}
                }

            }
            if(isinbounds(BottomRightBlock) && board.getPiece(BottomRightBlock) != null){
                if (!board.getPiece(BottomRightBlock).pieceColor.equals(ChessGame.TeamColor.BLACK)) {
                    ChessMove newMove = new ChessMove(myPosition,BottomRightBlock, ChessPiece.PieceType.PAWN);
                    if (newMove.endPosition.getRow() == 1){
                        promotionMoves(newMove, moves);
                    }
                    else {moves.add(newMove);}
                }
            }

        }
        return moves;
    }


    @Override
    public boolean isValidMove(ChessMove move, ChessBoard board) {
        if (move.endPosition.getRow() >= 0 && move.endPosition.getColumn() >= 0 && move.endPosition.getRow() <= 8 && move.endPosition.getColumn() <= 8) {
            return true;
        }
        return false;
    }
    private boolean isinbounds(ChessPosition endPosition){
        return endPosition.getRow() >= 0 && endPosition.getColumn() >= 0 && endPosition.getRow() <= 8 && endPosition.getColumn() <= 8;
    }
    private void promotionMoves(ChessMove move, Collection<ChessMove> moves) {
        ChessPosition startPosition = move.getStartPosition();
        ChessPosition endPosition = move.getEndPosition();
        ChessMove QueenPromotion = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN);
        ChessMove BishopPromotion = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP);
        ChessMove RookPromotion = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK);
        ChessMove KnightPromotion = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT);

        moves.add(QueenPromotion);
        moves.add(BishopPromotion);
        moves.add(RookPromotion);
        moves.add(KnightPromotion);
    }
}
