package chess.pieces;

import board.ChessBoard;
import board.Piece;

public class Bishop extends Piece{

	private Color color;
	
	public Bishop(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "B";
		}else {
			return "b";
		}
	}
}
