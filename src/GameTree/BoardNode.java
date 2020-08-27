package GameTree;

import chess.ChessMove;

public class BoardNode {

    private String represantation;
    private ChessMove lastMovement;   
    private BoardNode bestAdjacent; 
    private Integer value;
    private Integer max;
    private Integer min;

    public BoardNode() {}

    public BoardNode(String represantation, ChessMove lastMovement) {
        this.represantation = represantation;
        this.lastMovement = lastMovement;
        bestAdjacent = new BoardNode();
        value = null;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public String getRepresantation() {
        return represantation;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }    

    public void setBestAdjacent(BoardNode bestAdjacent) {
        this.bestAdjacent = bestAdjacent;
    }

    public BoardNode getBestAdjacent() {
        return bestAdjacent;
    }

    public Integer getMaxValue() {
        return max;
    }

    public void setMaxValue(Integer max) {
        this.max = max;
    }

    public Integer getMinValue() {
        return min;
    }

    public void setMinValue(Integer min) {
        this.min = min;
    }

    public ChessMove getLastMovement() {
        return lastMovement;
    }

    @Override
    public String toString() {
        return represantation + "\n" + lastMovement + "\n" + max + "\n" + min + "\n\n";
    }

}
