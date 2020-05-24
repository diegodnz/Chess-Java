package GameTree;

import board.Piece;
import board.Position;
import chess.ChessBoard;
import chess.ChessMatch;
import chess.ChessMove;
import chess.ChessPiece;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessTree {

    private static HashMap<Character, Integer> piecesValue;

    public static void InitPiecesValues() {
        piecesValue = new HashMap<>();
        piecesValue.put('p', 10);
        piecesValue.put('r', 50);
        piecesValue.put('h', 30);
        piecesValue.put('b', 30);
        piecesValue.put('q', 90);
        piecesValue.put('k', 900);
        piecesValue.put('P', -10);
        piecesValue.put('R', -50);
        piecesValue.put('H', -30);
        piecesValue.put('B', -30);
        piecesValue.put('Q', -90);
        piecesValue.put('K', -900);
    }

    public static int getNodeValue(HashMap<Character, Integer> numOfPieces, Color playerColor, String nodeBoard) {
        int nodeValue = 0;
        HashMap<Character, Integer> nodeNumOfPieces = getNumOfPieces(nodeBoard);

        int colorFactor;
        if (playerColor == Color.WHITE) {
            colorFactor = 1;
        } else {
            colorFactor = -1;
        }

        int difference = 0;
        for (Character piece : piecesValue.keySet()) {
            difference = ( numOfPieces.get(piece) - nodeNumOfPieces.get(piece) ) * piecesValue.get(piece);
            nodeValue -= difference * colorFactor;
        }

        return nodeValue;
    }

    public static HashMap<Character, Integer> getNumOfPieces(String board) {
        HashMap<Character, Integer> numOfPieces = new HashMap<>();
        numOfPieces.put('p', 0);
        numOfPieces.put('r', 0);
        numOfPieces.put('h', 0);
        numOfPieces.put('b', 0);
        numOfPieces.put('q', 0);
        numOfPieces.put('k', 0);
        numOfPieces.put('P', 0);
        numOfPieces.put('R', 0);
        numOfPieces.put('H', 0);
        numOfPieces.put('B', 0);
        numOfPieces.put('Q', 0);
        numOfPieces.put('K', 0);
        for (int i = 0; i < board.length(); i++) {
            char piece = board.charAt(i);
            if (piece != '0') {
                numOfPieces.replace(piece, numOfPieces.get(piece) + 1);
            }
        }
        return numOfPieces;
    }

    public static ArrayList<String> getAdjacents(String boardString, Color playerColor) {        
        ChessBoard board = new ChessBoard();
        King whiteKing = null;
        King blackKing = null;
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
        if (ChessMatch.check(blackKing, whiteKing, board) && ChessMatch.checkMate(whiteKing, blackKing, board, playerColor)) {
            return adjacents; //CheckMate returns empty adjacents
        } else if (playerColor == Color.WHITE && ChessMatch.kingInCheck(whiteKing, board)) {
            getProtectMove(board, boardString, playerColor, adjacents, whiteKing);
        } else if (playerColor == Color.BLACK && ChessMatch.kingInCheck(blackKing, board)) {
            getProtectMove(board, boardString, playerColor, adjacents, blackKing);
        } else {
            Piece[][] pieces = board.getPieces();
            for (Piece[] row : pieces) {
                for (Piece piece: row) {
                    if (piece != null && playerColor ==  ((ChessPiece)piece).getColor()) {
                        ArrayList<Position> moves;
                        if (((ChessPiece) piece).getColor() == Color.WHITE) {
                            moves = ((ChessPiece) piece).getMoves(whiteKing.getPosition());
                        } else {
                            moves = ((ChessPiece) piece).getMoves(blackKing.getPosition());
                        }
                        for (Position movePosition: moves) {
                            adjacents.add(getMoveRepresentation(boardString, piece, piece.getPosition(), movePosition));
                        }
                    }
                }
            }
        }
        return adjacents;        
    }

    private static void getProtectMove(ChessBoard board, String boardString, Color playerColor, ArrayList<String> adjacents, King king) {
        Piece[][] pieces = board.getPieces();
        for (Piece[] row : pieces) {
            for (Piece piece: row) {
                if (piece != null) {
                    if (playerColor == ((ChessPiece) piece).getColor()) {
                        ArrayList<Position> protectMoves = ((ChessPiece) piece).getProtectMoves(king.getPosition());
                        if (!protectMoves.isEmpty()) {
                            for (Position move : protectMoves) {
                                adjacents.add(getMoveRepresentation(boardString, piece, piece.getPosition(), move));
                            }
                        }
                    }
                }
            }
        }
    }

    private static String getMoveRepresentation(String boardString, Piece movedPiece, Position sourcePosition, Position targetPosition) {
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
