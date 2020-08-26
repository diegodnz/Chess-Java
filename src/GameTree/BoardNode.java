package GameTree;

import chess.ChessMove;

public class BoardNode {

    private String represantation;
    private ChessMove lastMovement;
    private Integer value = null;

    public BoardNode(String represantation, ChessMove lastMovement) {
        this.represantation = represantation;
        this.lastMovement = lastMovement;
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

    public ChessMove getLastMovement() {
        return lastMovement;
    }

    @Override
    public String toString() {
        return represantation + "\n" + lastMovement + "\n" + value + "\n\n";
    }

}
