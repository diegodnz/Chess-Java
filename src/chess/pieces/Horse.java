package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Horse extends ChessPiece{
	
	public Horse(ChessBoard board, Color color) {
		super(board, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		return getMoves(position, board, color);
	}
	
	public static ArrayList<Position> getMoves(Position position, Board board, Color color){
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		
		//UP-UP-RIGHT
		if(position.getRow() < 6 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() + 1);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() + 2, position.getColumn() + 1));
			}
		}
		
		//UP-UP-LEFT
		if(position.getRow() < 6 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() - 1);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() + 2, position.getColumn() - 1));
			}
		}	
		
		//UP-RIGHT-RIGHT
		if(position.getRow() < 7 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() + 2);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() + 1, position.getColumn() + 2));
			}
		}			
		
		//UP-LEFT-LEFT
		if(position.getRow() < 7 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() - 2);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() + 1, position.getColumn() - 2));
			}
		}
		
		//DOWN-DOWN-RIGHT
		if(position.getRow() > 1 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() + 1);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() - 2, position.getColumn() + 1));
			}
		}
		
		//DOWN-DOWN-LEFT
		if(position.getRow() > 1 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() - 1);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() - 2, position.getColumn() - 1));
			}
		}
		
		//DOWN-RIGHT-RIGHT
		if(position.getRow() > 0 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() + 2);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() - 1, position.getColumn() + 2));
			}
		}		
		
		//DOWN-LEFT-LEFT
		if(position.getRow() > 0 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() - 2);
			if(possiblePiece == null || possiblePiece.getColor() != color) {
				moves.add(new Position(position.getRow() - 1, position.getColumn() - 2));
			}
		}
		
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "H ";
		}else {
			return "h'";
		}
	}
}
