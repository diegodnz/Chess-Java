package chess.pieces;

import java.util.ArrayList;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece{
	
	public King(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}

	@Override
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves();
	}

	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		Position testPosition;
		board.nullPosition(position);

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				testPosition = new Position(position.getRow() + i, position.getColumn() + j);
				if (!(testPosition.equals(position)) && board.positionInBoard(testPosition)) {
					possiblePiece = (ChessPiece) board.seePosition(testPosition.getRow(), testPosition.getColumn());
					if (!(threatenedPosition(testPosition, color, board)) && (possiblePiece == null || possiblePiece.getColor() != color)) {
						moves.add(testPosition);
					}
				}
			}
		}

		board.putInPosition(this, position);

		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "   K   ";
		}else {
			return "  (k)  ";
		}
	}

	@Override
	public char getLetter() {
		if (color == Color.BLACK) {
			return toString().trim().charAt(0);
		} else {
			return toString().trim().charAt(1);
		}
	}
}
