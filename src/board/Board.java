package board;

public class Board {

	private int numRows;
	private int numColumns;
	protected Piece[][] board;
	
	public Board(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		board = new Piece[numRows][numColumns];
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public Piece[][] getBoard(){
		return board;
	}
	
	public Piece seePosition(int row, int column) {
		return board[row][column];
	}
	
}
