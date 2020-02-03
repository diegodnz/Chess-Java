package chess;

import board.Board;
import board.Piece;
import board.Position;

public class ChessBoard extends Board{

	public ChessBoard() {
		super(8, 8);		
	}
	
	public void clone(ChessBoard board) {
		int i = 0;
		for(Piece[] line: pieces) {
			board.pieces[i] = line.clone();
			i++;
		}
	}	
	
	public void doChessMove(ChessPiece piece, Position position) {		
		super.putInPosition(piece, position);	
	}
	
	public void doChessMove(ChessPiece piece, int row, int column) {		
		super.putInPosition(piece, row, column);		
	}
}
