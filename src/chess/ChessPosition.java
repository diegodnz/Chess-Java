package chess;

import board.Position;

public class ChessPosition {

	private int row;
	private char column;
	
	public ChessPosition(int row, char column) {
		this.row = row;
		this.column = column;
	}
	
	public Position toBoardPosition() {
		return new Position(row-1, column - 'a');
	}
}
