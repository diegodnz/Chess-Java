package chess.pieces;

import chess.ChessBoard;
import board.Piece;

public class King extends Piece{

	private Color color;
	
	public King(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "K";
		}else {
			return "k";
		}
	}
}
