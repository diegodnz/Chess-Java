package board;

public class Piece {

	protected Position position;
	protected Board board;
	protected String name;
	
	public Piece(Board board, String name) {
		this.board = board;		
		this.name = name;
	}
	
	public Piece(Board board, Position position) {
		this.board = board;
		this.position = position;
	}
	
	public Position getPosition() {
		return position;
	}
		
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(int row, int column) {
		this.position = new Position(row, column);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
