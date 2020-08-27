package GameTree;

import chess.ChessMove;
import chess.pieces.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameTree {
    
    private static Random gen;

    public GameTree(Game game) {       
        gen = new Random();
    }

    public String searchChessBestMove(String boardString, ChessMove lastMovement, int depth, Color turnColor) {               
        ChessTree.InitPiecesValues();
        BoardNode thisBoard = new BoardNode(boardString, lastMovement);
        return chessMiniMax(thisBoard, depth, turnColor).getRepresantation();        
    }

    public BoardNode chessMiniMax(BoardNode board, int depth, Color turnColor) {        
        if (turnColor == Color.WHITE) { // MAX (WHITE)
            int maxValue = -10000;
            String maxAdjacent = null;
            ChessMove maxChessMove = null;
            if (depth > 0) {
                ArrayList<BoardNode> adjacents = ChessTree.getAdjacents(board, turnColor);
                turnColor = Color.BLACK;
                for (BoardNode adjacent : adjacents) {                    
                    String adjacentString = adjacent.getRepresantation();
                    BoardNode bestAdjacentOfAdjacent = chessMiniMax(adjacent, depth-1, turnColor);
                    int adjacentValue = bestAdjacentOfAdjacent.getValue(); 
                    if (maxValue == -10000 || adjacentValue > maxValue) {
                        maxValue = adjacentValue;
                        maxAdjacent = adjacentString;
                        maxChessMove = adjacent.getLastMovement();
                    } else if (adjacentValue == maxValue && gen.nextInt(5) == 0) {
                        maxValue = adjacentValue;
                        maxAdjacent = adjacentString;
                        maxChessMove = adjacent.getLastMovement();
                    }                    
                }
                BoardNode bestAdjacent;
                if (maxAdjacent == null) {
                    bestAdjacent = new BoardNode(null, null);
                    bestAdjacent.setValue(Integer.MIN_VALUE);
                } else {
                    bestAdjacent = new BoardNode(maxAdjacent, maxChessMove);
                    bestAdjacent.setValue(maxValue);
                }                
                return bestAdjacent;
            } else {                
                board.setValue(ChessTree.getNodeValue(board.getRepresantation()));
                return board;
            }
        } else { // MIN (BLACK)
            int minValue = 10000;            
            String minAdjacent = null;
            ChessMove minChessMove = null;
            if (depth > 0) {
                ArrayList<BoardNode> adjacents = ChessTree.getAdjacents(board, turnColor);
                turnColor = Color.WHITE;
                for (BoardNode adjacent : adjacents) {
                    String adjacentString = adjacent.getRepresantation();
                    BoardNode bestAdjacentOfAdjacent = chessMiniMax(adjacent, depth-1, turnColor);
                    int adjacentValue = bestAdjacentOfAdjacent.getValue(); 
                    if (minValue == 10000 || adjacentValue < minValue) {
                        minValue = adjacentValue;
                        minAdjacent = adjacentString;
                        minChessMove = adjacent.getLastMovement();
                    } else if (adjacentValue == minValue && gen.nextInt(5) == 0) {
                        minValue = adjacentValue;
                        minAdjacent = adjacentString;
                        minChessMove = adjacent.getLastMovement();
                    }
                }
                BoardNode bestAdjacent;
                if (minAdjacent == null) {
                    bestAdjacent = new BoardNode(null, null);
                    bestAdjacent.setValue(Integer.MAX_VALUE);
                } else {
                    bestAdjacent = new BoardNode(minAdjacent, minChessMove);
                    bestAdjacent.setValue(minValue);
                }                
                return bestAdjacent;
            } else {                
                board.setValue(ChessTree.getNodeValue(board.getRepresantation()));
                return board;
            }
        }
        
    }

}