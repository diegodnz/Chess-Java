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
	
	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}
	
	public boolean positionInBoard(Position position) {
		return position.getRow() < numRows && position.getColumn() < numColumns
				&& position.getRow() >= 0 && position.getColumn() >= 0;
	}
	
	public Piece seePosition(int row, int column) {		
		return pieces[row][column];
	}
	
	public Piece seePosition(Position position) throws BoardException {
		try {
			return pieces[position.getRow()][position.getColumn()];
		}
		catch (ArrayIndexOutOfBoundsException e) {			
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}
	
}
