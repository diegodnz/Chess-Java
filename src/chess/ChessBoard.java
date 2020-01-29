package chess;

import board.Board;
import board.Piece;
import board.Position;

public class ChessBoard extends Board{

	public ChessBoard() {
		super(8, 8);		
	}
	
	public void putInPosition(Piece piece, Position position) {	
		board[position.getRow()][position.getColumn()] = piece;
		piece.setPosition(position);
	}
}
