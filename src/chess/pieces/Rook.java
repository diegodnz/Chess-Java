package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Rook extends ChessPiece{
	
	public Rook(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves(position, board, color, kingPosition);
	}

	public static ArrayList<Position> getMoves(Position position, Board board, Color color, Position kingPosition) {
		ArrayList<Position> moves = new ArrayList<>();
		ChessPiece possiblePiece;
		
		//UP
		for(int i = position.getRow(); i > 0; i--) {	
			possiblePiece = (ChessPiece)board.seePosition(i - 1, position.getColumn());
			Position movePosition = new Position(i - 1, position.getColumn());
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(possiblePiece == null && allowedMove) {
				moves.add(movePosition);
			}else if (possiblePiece != null) {
				if(possiblePiece.getColor() != color && allowedMove) {			
					moves.add(movePosition);
				}
				break;
			}
		}		
		
		//DOWN
		for(int i = position.getRow(); i < 7; i++) {		
			possiblePiece = (ChessPiece)board.seePosition(i + 1, position.getColumn());
			Position movePosition = new Position(i + 1, position.getColumn());
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(possiblePiece == null && allowedMove) {
				moves.add(movePosition);
			}else if (possiblePiece != null) {
				if(possiblePiece.getColor() != color && allowedMove) {			
					moves.add(movePosition);
				}
				break;
			}
		}
		
		//RIGHT
		for(int i = position.getColumn(); i < 7; i++) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), i + 1);
			Position movePosition = new Position(position.getRow(), i + 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(possiblePiece == null && allowedMove) {
				moves.add(movePosition);
			}else if (possiblePiece != null) {				
				if(possiblePiece.getColor() != color && allowedMove) {			
					moves.add(movePosition);
				}
				break;
			}
		}
		
		//LEFT
		for(int i = position.getColumn(); i > 0; i--) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), i - 1);
			Position movePosition = new Position(position.getRow(), i - 1);
			boolean allowedMove = kingWillBeSafe((ChessBoard)board, position, movePosition, kingPosition);
			if(possiblePiece == null && allowedMove) {
				moves.add(movePosition);
			}else if (possiblePiece != null) {
				if(possiblePiece.getColor() != color && allowedMove) {			
					moves.add(movePosition);
				}
				break;
			}
		}
		
		return moves;
	}

	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "   R   ";
		}else {
			return "  (r)  ";
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
