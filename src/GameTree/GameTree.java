package GameTree;

import board.Piece;
import chess.pieces.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class GameTree {

    private HashMap<Node, HashMap<Node, Integer>> tree;
    private HashMap<String, Node> stringToNode;
    private HashMap<Node, Node> maxAdjacent;
    private HashMap<Node, Node> minAdjacent;
    private Game game;

    public GameTree(Game game) {
        tree = new HashMap<>();
        stringToNode = new HashMap<>();
        maxAdjacent = new HashMap<>();
        minAdjacent = new HashMap<>();
        this.game = game;
    }

    @Override
    public String toString() {
        return String.format("%d %s", tree.size(), tree.get(tree.keySet().toArray()[0]));
    }

    public Node getBoardNode(String board) { return stringToNode.get(board); }

    private void linkNodes(Node parent, Node child) {
        if (!tree.containsKey(parent)) {
            tree.put(parent, new HashMap<Node, Integer>());
            stringToNode.put(parent.getRepresantation(), parent);
            maxAdjacent.put(parent, child);
            minAdjacent.put(parent, child);
        } else {
            Node maxAdjacentNode = maxAdjacent.get(parent);
            Node minAdjacentNode = minAdjacent.get(parent);
            if (child.getValue() > tree.get(parent).get(maxAdjacentNode)) {
                maxAdjacent.replace(parent, child);
            } else if ((child.getValue() < tree.get(parent).get(minAdjacentNode))) {
                minAdjacent.replace(parent, child);
            }
        }
        tree.get(parent).put(child, child.getValue());
    }

    public void buildTree(String boardString, int depth, Color playerColor) {
        HashMap<Character, Integer> numOfPieces = new HashMap<>();
        if (game == Game.CHESS) {
            numOfPieces = ChessTree.getNumOfPieces(boardString);
            ChessTree.InitPiecesValues();
        }
        Node board = new Node(boardString);
        buildTree(numOfPieces, playerColor, board, depth, playerColor);
    }

    private void buildTree(HashMap<Character, Integer> numOfPieces, Color startingColor, Node boardNode, int depth, Color turnColor) {
        if (depth > 0) {
            if (game == Game.CHESS) {
                ArrayList<String> adjacents = ChessTree.getAdjacents(boardNode.getRepresantation(), turnColor);
                if (adjacents.isEmpty()) {
                    int checkMate;
                    if (startingColor == turnColor) {
                        checkMate = Integer.MIN_VALUE;
                    } else {
                        checkMate = (Integer.MAX_VALUE);
                    }
                    boardNode.setValue(checkMate);
                } else {
                    for (String adjacent : adjacents) {
                        Node adjacentNode = new Node(adjacent);
                        buildTree(numOfPieces, startingColor, adjacentNode, depth - 1, changeColor(turnColor));
                        linkNodes(boardNode, adjacentNode);
                    }
                    if (turnColor == startingColor) {
                        boardNode.setValue(tree.get(boardNode).get(maxAdjacent.get(boardNode)));
                    } else {
                        boardNode.setValue(tree.get(boardNode).get(minAdjacent.get(boardNode)));
                    }
                }
            }
        } else {
            if (game == Game.CHESS) {
                boardNode.setValue(ChessTree.getNodeValue(numOfPieces, startingColor, boardNode.getRepresantation()));
            }
        }

    }

    private Color changeColor(Color color) {
        if (color == Color.BLACK) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
        return color;
    }

    public String miniMax(Node board) {
        System.out.println(tree.get(board).get(maxAdjacent.get(board)));
        return maxAdjacent.get(board).getRepresantation();
    }

}


