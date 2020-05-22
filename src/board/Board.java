package board;

import chess.ChessPiece;

public class Board {

	private int numRows;
	private int numColumns;
	protected Piece[][] pieces;

	public Board(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		pieces = new Piece[numRows][numColumns];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

	public void setPiecesRow (Piece[] row, int rowNumber) { pieces[rowNumber] = row; }

	public Piece[] getPiecesRow (int i) { return pieces[i]; }

	public boolean positionInBoard(Position position) {
		return position.getRow() < numRows && position.getColumn() < numColumns
				&& position.getRow() >= 0 && position.getColumn() >= 0;
	}

	public boolean positionInBoard(int row, int column) {
		return row < numRows && column < numColumns
				&& row >= 0 && column >= 0;
	}

	public Piece seePosition(int row, int column) {
		if (positionInBoard(row, column)) {
			return pieces[row][column];
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}

	public Piece seePosition(Position position) {
		if (positionInBoard(position)) {
			return pieces[position.getRow()][position.getColumn()];
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}

}
