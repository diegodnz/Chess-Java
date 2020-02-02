package board;

public class Position extends Object{

	private int row;
	private int column;
	
	public Position(int row, int column) {		
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		Position position = (Position) o;		
		return position.getRow() == row && position.getColumn() == column;
	}
}
