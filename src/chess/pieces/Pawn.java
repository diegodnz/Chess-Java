package chess.pieces;

import board.ChessBoard;
import board.Piece;

public class Pawn extends Piece{

	private Color color;
	
	public Pawn(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "P";
		}else {
			return "p";
		}
	}
}
