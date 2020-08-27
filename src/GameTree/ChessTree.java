package GameTree;

import board.Piece;
import board.Position;
import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChessTree {

    private static HashMap<Character, Integer> piecesValue;

    public static void InitPiecesValues() {
        piecesValue = new HashMap<>();
        piecesValue.put('p', 10);
        piecesValue.put('r', 50);
        piecesValue.put('h', 30);
        piecesValue.put('b', 30);
        piecesValue.put('q', 90);   
        piecesValue.put('k', 10000);     
        piecesValue.put('P', -10);
        piecesValue.put('R', -50);
        piecesValue.put('H', -30);
        piecesValue.put('B', -30);
        piecesValue.put('Q', -90);  
        piecesValue.put('K', -10000);      
    }

    public static int getNodeValue(String boardString) {
        int value = 0;        
        for(int i = 0; i < boardString.length(); i++) {
            char piece = boardString.charAt(i);
            if(piece != '0') {                
                value += piecesValue.get(piece);
            }
        }        
        return value;
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

    public static ArrayList<BoardNode> getAdjacents(BoardNode boardNode, Color turnColor) {        
        ChessBoard board = new ChessBoard();
        String boardString = boardNode.getRepresantation();
        board.setLastMovement(boardNode.getLastMovement());
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

        ArrayList<BoardNode> adjacents = new ArrayList<>();
        Piece[][] pieces = board.getPieces();     
        int[] rows = {0,1,2,3,4,5,6,7};
        int[] columns = {0,1,2,3,4,5,6,7}; 
        shuffleVect(rows, 8);
        shuffleVect(columns, 8);        
        for (int i = 0; i < 8; i++) {           
            for (int j = 0; j < 8; j++) {
                Piece piece = pieces[ rows[i] ][ columns[j] ];
                if (piece != null && turnColor == ((ChessPiece)piece).getColor()) {
                    ArrayList<Position> moves;
                    if (((ChessPiece) piece).getColor() == Color.WHITE) {
                        moves = ((ChessPiece) piece).getMoves(whiteKing.getPosition());
                    } else {
                        moves = ((ChessPiece) piece).getMoves(blackKing.getPosition());
                    }
                    
                    for (Position movePosition: moves) {
                        String adjacentBoard = getMoveRepresentation(boardString, piece, piece.getPosition(), movePosition);
                        BoardNode adjacent = new BoardNode(adjacentBoard, new ChessMove(piece.getPosition(), movePosition));
                        adjacents.add(adjacent);
                    }
                }
            }
        }

        // Add null object in adjacents means a check-mate
        if (turnColor == Color.BLACK) {
            if (adjacents.isEmpty() && ChessPiece.threatenedPosition(board.getBlackKing().getPosition(), Color.BLACK, board)) {
                adjacents.add(null);
            }
        } else if (turnColor == Color.WHITE) {
            if (adjacents.isEmpty() && ChessPiece.threatenedPosition(board.getWhiteKing().getPosition(), Color.WHITE, board)) {
                adjacents.add(null);
            }
        }

        return adjacents;        
    }

    private static String getMoveRepresentation(String boardString, Piece movedPiece, Position sourcePosition, Position targetPosition) {
        StringBuilder moveString = new StringBuilder();
        int sourceRow = sourcePosition.getRow();
        int sourceColumn = sourcePosition.getColumn();
        int targetRow = targetPosition.getRow();
        int targetColumn = targetPosition.getColumn();   

        // En Passant
        // Also used in pawn promotion
        boolean isPawn = movedPiece instanceof Pawn;  
        boolean moveSide = sourceColumn != targetColumn;
        boolean targetHasPiece = false;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (row == sourceRow && column == sourceColumn) {
                    moveString.append("0");
                } else if (row == targetRow && column == targetColumn) {
                    if (boardString.charAt(row*8+column) != '0') {
                        targetHasPiece = true;
                    } 

                    // Pawn Promotion
                    if (isPawn && (targetRow == 0 || targetRow == 7)) {
                        if ( ((ChessPiece)movedPiece).getColor() == Color.WHITE ) {
                            moveString.append('q');
                        } else {
                            moveString.append('Q');
                        }
                    } else {
                        moveString.append(((ChessPiece)movedPiece).getLetter());
                    }
                } else {
                    moveString.append(boardString.charAt(row*8+column));
                }
            }
        }

        // En Passant
        if (isPawn && moveSide && !targetHasPiece) {
            if ( ((ChessPiece)movedPiece).getColor() == Color.WHITE) {
                int enPassantIndex = (targetRow*8+targetColumn)-8;
                moveString.replace(enPassantIndex, enPassantIndex+1, "0");
            } else {
                int enPassantIndex = (targetRow*8+targetColumn)+8;
                moveString.replace(enPassantIndex, enPassantIndex+1, "0");
            }
        }

        return moveString.toString();
    }

    private static void shuffleVect(int[] vect, int size) {
        Random gen = new Random();
        for (int i = 0; i < 2; i++) {
            int randomIndex = gen.nextInt(size-1) + 1;
            int first = vect[0];
            vect[0] = vect[randomIndex];
            vect[randomIndex] = first;
        }
    }

}
