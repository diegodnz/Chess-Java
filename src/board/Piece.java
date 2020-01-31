package board;

public class Piece {

	protected Position position;
	protected Board board;
	
	public Piece(Board board) {
		this.board = board;
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
	
}
