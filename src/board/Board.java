package board;

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
	
	public Piece[][] getPieces(){
		return pieces;
	}
	
	public Piece seePosition(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece seePosition(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
}
