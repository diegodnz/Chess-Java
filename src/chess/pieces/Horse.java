package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Horse extends ChessPiece{
	
	public Horse(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves(position, board, color, kingPosition);
	}

	public static ArrayList<Position> getMoves(Position position, Board board, Color color, Position kingPosition) {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		
		//UP-UP-RIGHT
		if(position.getRow() < 6 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() + 1);
			Position movePosition = new Position(position.getRow() + 2, position.getColumn() + 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}
		
		//UP-UP-LEFT
		if(position.getRow() < 6 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() - 1);
			Position movePosition = new Position(position.getRow() + 2, position.getColumn() - 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}	
		
		//UP-RIGHT-RIGHT
		if(position.getRow() < 7 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() + 2);
			Position movePosition = new Position(position.getRow() + 1, position.getColumn() + 2);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}			
		
		//UP-LEFT-LEFT
		if(position.getRow() < 7 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() - 2);
			Position movePosition = new Position(position.getRow() + 1, position.getColumn() - 2);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}
		
		//DOWN-DOWN-RIGHT
		if(position.getRow() > 1 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() + 1);
			Position movePosition = new Position(position.getRow() - 2, position.getColumn() + 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}
		
		//DOWN-DOWN-LEFT
		if(position.getRow() > 1 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() - 1);
			Position movePosition = new Position(position.getRow() - 2, position.getColumn() - 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}
		
		//DOWN-RIGHT-RIGHT
		if(position.getRow() > 0 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() + 2);
			Position movePosition = new Position(position.getRow() - 1, position.getColumn() + 2);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}		
		
		//DOWN-LEFT-LEFT
		if(position.getRow() > 0 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() - 2);
			Position movePosition = new Position(position.getRow() - 1, position.getColumn() - 2);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(allowedMove && (possiblePiece == null || possiblePiece.getColor() != color) ) {
				moves.add(movePosition);
			}
		}
		
		return moves;
	}

	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "   H   ";
		}else {
			return "  (h)  ";
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
