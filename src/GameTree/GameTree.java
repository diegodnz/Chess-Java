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
        chessMiniMax(thisBoard, depth, turnColor, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return thisBoard.getBestAdjacent().getRepresantation();        
    }

    public void chessMiniMax(BoardNode board, int depth, Color turnColor, int max, int min) {  

        board.setMaxValue(max);
        board.setMinValue(min);   

        if (depth > 0) {            
            ArrayList<BoardNode> adjacents = ChessTree.getAdjacents(board, turnColor);

            if (turnColor == Color.BLACK) { // MIN

                turnColor = Color.WHITE;
                for (BoardNode adjacent: adjacents) {                                     
                    if (adjacent == null) {
                        board.setValue(10000);
                        min = 10000;
                        break;
                    }                       
                    chessMiniMax(adjacent, depth-1, turnColor, max, min); 
                    Integer thisBoardValue = board.getValue();         
                    Integer adjacentValue = adjacent.getValue();     
                    
                    if (thisBoardValue == null || adjacentValue < thisBoardValue) {
                        board.setValue(adjacentValue);
                        board.setBestAdjacent(adjacent);
                    }

                    if (adjacentValue < min) {
                        min = adjacentValue;
                    }
                    
                    if (max >= min) {
                        break;
                    }
                }  

            } else if (turnColor == Color.WHITE) { // MAX

                turnColor = Color.BLACK;
                for (BoardNode adjacent: adjacents) {
                    if (adjacent == null) {
                        board.setValue(-10000);
                        max = -10000;
                        break;
                    }  
                    chessMiniMax(adjacent, depth-1, turnColor, max, min);    
                    Integer thisBoardValue = board.getValue();         
                    Integer adjacentValue = adjacent.getValue();  
                    
                    if (thisBoardValue == null || adjacentValue > thisBoardValue) {
                        board.setValue(adjacentValue);
                        board.setBestAdjacent(adjacent);
                    }        
                    
                    if (adjacentValue > max) {
                        max = adjacentValue;
                    }
                
                    if (max >= min) {
                        break;
                    }
                }
            }

        } else {
            int nodeValue = ChessTree.getNodeValue(board.getRepresantation());
            board.setValue(nodeValue);
        }       
        
    }

}