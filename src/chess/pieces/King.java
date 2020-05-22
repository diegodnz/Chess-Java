package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;

public class King extends ChessPiece{
	
	public King(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {		
		return getMoves(position, board, color);		
	}
	
	public ArrayList<Position> getMoves(Position position, Board board, Color color){
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		Position testPosition;		
		((ChessBoard) board).nullPosition(position);
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <=1; j++) {
				testPosition = new Position(position.getRow() + i, position.getColumn() + j);
				if(!(testPosition.equals(position)) && board.positionInBoard(testPosition)) {
					possiblePiece = (ChessPiece)board.seePosition(testPosition.getRow(), testPosition.getColumn());
					if( !(threatenedPosition(testPosition, color, board)) && (possiblePiece == null || possiblePiece.getColor() != color) ) {
						moves.add(testPosition);
					}
				}
			}
		}
		
		((ChessBoard) board).putInPosition(this, position);
		
		return moves;
	}

	@Override
	public ChessMove getProtectMove(Position kingPosition) {
		return null;
	}

	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "   K   ";
		}else {
			return "   k   ";
		}
	}

	@Override
	public char getLetter() {
		return toString().trim().charAt(0);
	}

}
