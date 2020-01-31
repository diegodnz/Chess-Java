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
		return new Position(8-row, column - 'a');
	}
	
	public static ChessPosition toChessPosition(Position position) {
		return new ChessPosition(8-position.getRow(), (char)('a' + position.getColumn()));
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
