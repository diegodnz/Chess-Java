package board;

public class Piece {

	private Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
