package chess.pieces;

import java.util.ArrayList;

import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Rook extends ChessPiece{
	
	public Rook(ChessBoard board, Color color) {
		super(board, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		
		//UP
		while(position.getRow() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow()-1, position.getColumn());
			if(possiblePiece != null && possiblePiece.getColor() != color)
			moves.add(new Position(position.getRow()-1, position.getColumn()));
			
		}
		
		
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "R";
		}else {
			return "r'";
		}
	}
}
