package chess.pieces;

import chess.ChessBoard;
import board.Piece;

public class Queen extends Piece{

	private Color color;
	
	public Queen(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "Q";
		}else {
			return "q";
		}
	}
}
