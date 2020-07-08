package GameTree;

import chess.pieces.Color;

import java.util.ArrayList;
import java.util.Random;

public class GameTree {

    private Game game;
    private static Random gen;

    public GameTree(Game game) {        
        this.game = game;
        gen = new Random();
    }

    public String searchBestMove(String boardString, int depth, Color turnColor) {
        if(game == Game.CHESS) {          
            ChessTree.InitPiecesValues();
            return chessMiniMax(boardString, depth, turnColor).getRepresantation();
        }else {
            throw new RuntimeException("The game has not been defined");
        }
    }

    public Node chessMiniMax(String boardString, int depth, Color turnColor) {
        if(turnColor == Color.WHITE) { // MAX (WHITE)
            int maxValue = Integer.MIN_VALUE;
            String maxAdjacent = null;
            if(depth > 0) {
                ArrayList<String> adjacents = ChessTree.getAdjacents(boardString, turnColor);
                turnColor = Color.BLACK;
                for(String adjacent : adjacents) {
                    Node adjacentNode = chessMiniMax(adjacent, depth-1, turnColor);
                    int adjacentValue = adjacentNode.getValue(); 
                    if(adjacentValue > maxValue) {
                        maxValue = adjacentValue;
                        maxAdjacent = adjacent;
                    }else if(adjacentValue == maxValue && gen.nextInt(2) == 0) {
                        maxValue = adjacentValue;
                        maxAdjacent = adjacent;
                    }
                }
                Node bestAdjacent;
                if(maxAdjacent == null) {
                    bestAdjacent = new Node(null);
                    bestAdjacent.setValue(Integer.MIN_VALUE);
                }else {
                    bestAdjacent = new Node(maxAdjacent);
                    bestAdjacent.setValue(maxValue);
                }
                return bestAdjacent;
            }else {
                Node thisBoard = new Node(boardString);
                thisBoard.setValue(ChessTree.getNodeValue(boardString));
                return thisBoard;
            }
        }else { // MIN (BLACK)
            int minValue = Integer.MAX_VALUE;            
            String minAdjacent = null;
            if(depth > 0) {
                ArrayList<String> adjacents = ChessTree.getAdjacents(boardString, turnColor);
                turnColor = Color.WHITE;
                for(String adjacent : adjacents) {
                    Node adjacentNode = chessMiniMax(adjacent, depth-1, turnColor);
                    int adjacentValue = adjacentNode.getValue(); 
                    if(adjacentValue < minValue) {
                        minValue = adjacentValue;
                        minAdjacent = adjacent;
                    }else if(adjacentValue == minValue && gen.nextInt(2) == 0) {
                        minValue = adjacentValue;
                        minAdjacent = adjacent;
                    }
                }
                Node bestAdjacent;
                if(minAdjacent == null) {
                    bestAdjacent = new Node(null);
                    bestAdjacent.setValue(Integer.MAX_VALUE);
                }else {
                    bestAdjacent = new Node(minAdjacent);
                    bestAdjacent.setValue(minValue);
                }
                return bestAdjacent;
            }else {
                Node thisBoard = new Node(boardString);
                thisBoard.setValue(ChessTree.getNodeValue(boardString));
                return thisBoard;
            }
        }
        
    }

}