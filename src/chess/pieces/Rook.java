package chess.pieces;

import chess.ChessBoard;
import board.Piece;

public class Rook extends Piece{

	private Color color;
	
	public Rook(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "R";
		}else {
			return "r";
		}
	}
}
