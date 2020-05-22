package GameTree;

import chess.pieces.Color;

import java.util.HashMap;

public class GameTree {

    private HashMap<Node, HashMap<Node, Integer>> tree;
    //"RHBQKBHRPPPPPPPP00000000000000000000000000000000pppppppprhbqkbhr"

    public GameTree() {
        tree = new HashMap<>();
    }

    public void buildTree(String board, int depth, Color botColor) {
        Node startingBoard = new Node(board);
        for (String adjacent : ChessTree.getAdjacents(board, botColor)) {

        }
    }

}


