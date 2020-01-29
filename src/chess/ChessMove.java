package chess;

import board.Position;

public class ChessMove {

	private Position source;
	private Position target;	
	
	public ChessMove(Position source, Position target) {
		this.source = source;
		this.target = target;	
	}
	
	public Position getSource() {
		return source;
	}
	
	public Position getTarget() {
		return target;
	}
	
}
