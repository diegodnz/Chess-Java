package chess.pieces;

import board.ChessBoard;
import board.Piece;

public class Horse extends Piece{

	private Color color;
	
	public Horse(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "H";
		}else {
			return "h";
		}
	}
}
