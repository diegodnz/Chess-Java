package chess;

import board.Position;

public class ChessPosition {

	private int row;
	private char column;
	
	public ChessPosition(int row, char column) throws ChessException {
		try {
			this.row = row;
			this.column = column;
		}
		catch (RuntimeException e) {
			throw new ChessException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}
	
	public Position toBoardPosition() {
		return new Position(8-row, column - 'a');
	}
	
	public static ChessPosition toChessPosition(Position position) throws ChessException {	
		return new ChessPosition(8-position.getRow(), (char)('a' + position.getColumn()));
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
