package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece{
	
	public King(ChessBoard board, Color color) {
		super(board, color);		
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
				if(!testPosition.equals(position) && board.positionInBoard(testPosition)) {
					possiblePiece = (ChessPiece)board.seePosition(testPosition.getRow(), testPosition.getColumn());
					if( !threatenedPosition(testPosition, color, board) && (possiblePiece == null || possiblePiece.getColor() != color) ) {
						moves.add(testPosition);
					}
				}
			}
		}
		
		((ChessBoard) board).putInPosition(this, position);
		
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "K ";
		}else {
			return "k'";
		}
	}
}
