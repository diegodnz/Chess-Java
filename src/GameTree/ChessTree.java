package GameTree;

import board.Piece;
import board.Position;
import chess.ChessBoard;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.pieces.*;

import java.util.ArrayList;

public class ChessTree {

    public static ArrayList<String> getAdjacents(String boardString, Color playerColor) {        
        ChessBoard board = new ChessBoard();
        ChessPiece whiteKing = null;
        ChessPiece blackKing = null;
        int stringIndex;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                stringIndex = row*8 + column;
                char piece = boardString.charAt(stringIndex);
                Position position = new Position(row, column);
                if (piece == 'p') {
                    board.putInPosition(new Pawn(board, position, Color.WHITE), position);
                } else if (piece == 'r') {
                    board.putInPosition(new Rook(board, position, Color.WHITE), position);
                } else if (piece == 'h') {
                    board.putInPosition(new Horse(board, position, Color.WHITE), position);
                } else if (piece == 'b') {
                    board.putInPosition(new Bishop(board, position, Color.WHITE), position);
                } else if (piece == 'q') {
                    board.putInPosition(new Queen(board, position, Color.WHITE), position);
                } else if (piece == 'k') {
                    whiteKing = new King(board, position, Color.WHITE);
                    board.putInPosition(whiteKing, position);
                } else if (piece == 'P') {
                    board.putInPosition(new Pawn(board, position, Color.BLACK), position);
                } else if (piece == 'R') {
                    board.putInPosition(new Rook(board, position, Color.BLACK), position);
                } else if (piece == 'H') {
                    board.putInPosition(new Horse(board, position, Color.BLACK), position);
                } else if (piece == 'B') {
                    board.putInPosition(new Bishop(board, position, Color.BLACK), position);
                } else if (piece == 'Q') {
                    board.putInPosition(new Queen(board, position, Color.BLACK), position);
                } else if (piece == 'K') {
                    blackKing = new King(board, position, Color.BLACK);
                    board.putInPosition(blackKing, position);
                }
            }
        }

        ArrayList<String> adjacents = new ArrayList<>();
        if (ChessMatch.checkMate(whiteKing, blackKing, board, playerColor)) {
            adjacents.add("CheckMate");
            return adjacents;
        } else if (ChessMatch.kingInCheck(whiteKing, board)) {

        }
        Piece[][] pieces = board.getPieces();
        for (Piece[] row : pieces) {
            for (Piece piece: row) {
                if (playerColor ==  ((ChessPiece)piece).getColor()) {
                    ArrayList<Position> moves = ((ChessPiece)piece).getMoves();
                    for (Position movePosition: moves) { 
                        adjacents.add(getMoveRepresentation(boardString, piece, piece.getPosition(), movePosition));
                    }
                }
            }
        }
        
        return adjacents;        
    }

    public static String getMoveRepresentation(String boardString, Piece movedPiece, Position sourcePosition, Position targetPosition) {
        StringBuilder moveString = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (row == sourcePosition.getRow() && column == sourcePosition.getColumn()) {
                    moveString.append("0");
                } else if (row == targetPosition.getRow() && column == targetPosition.getColumn()) {
                    moveString.append(((ChessPiece)movedPiece).getLetter());
                } else {
                    moveString.append(boardString.charAt(row*8+column));
                }
            }
        }
        return moveString.toString();
    }

}
