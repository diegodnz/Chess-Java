package chess.pieces;

import java.util.ArrayList;

import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece{
	
	public King(ChessBoard board, Color color) {
		super(board, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "K";
		}else {
			return "k'";
		}
	}
}
