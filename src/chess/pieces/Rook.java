package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;

public class Rook extends ChessPiece{
	
	public Rook(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {		
		return getMoves(position, board, color);		
	}
	
	public static ArrayList<Position> getMoves(Position position, Board board, Color color){
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		
		//UP
		for(int i = position.getRow(); i > 0; i--) {	
			possiblePiece = (ChessPiece)board.seePosition(i - 1, position.getColumn());
			if(possiblePiece == null) {
				moves.add(new Position(i - 1, position.getColumn()));
			}else {
				if(possiblePiece.getColor() != color) {			
					moves.add(new Position(i - 1, position.getColumn()));
				}
				break;
			}
		}		
		
		//DOWN
		for(int i = position.getRow(); i < 7; i++) {		
			possiblePiece = (ChessPiece)board.seePosition(i + 1, position.getColumn());
			if(possiblePiece == null) {
				moves.add(new Position(i + 1, position.getColumn()));
			}else {
				if(possiblePiece.getColor() != color) {			
					moves.add(new Position(i + 1, position.getColumn()));
				}
				break;
			}
		}
		
		//RIGHT
		for(int i = position.getColumn(); i < 7; i++) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), i + 1);
			if(possiblePiece == null) {
				moves.add(new Position(position.getRow(), i + 1));
			}else {
				if(possiblePiece.getColor() != color) {	
					moves.add(new Position(position.getRow(), i + 1));
				}
				break;
			}
		}
		
		//LEFT
		for(int i = position.getColumn(); i > 0; i--) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), i - 1);
			if(possiblePiece == null) {
				moves.add(new Position(position.getRow(), i - 1));
			}else {
				if(possiblePiece.getColor() != color) {			
					moves.add(new Position(position.getRow(), i - 1));
				}
				break;
			}
		}
		
		return moves;
	}

	@Override
	public ArrayList<Position> getProtectMoves(Position kingPosition) {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		((ChessBoard) board).nullPosition(this.position);
		int targetRow;
		int targetColumn;

		//UP
		targetColumn = position.getColumn();
		for (int i = position.getRow(); i > 0; i--) {
			targetRow = i - 1;
			possiblePiece = (ChessPiece) board.seePosition(i - 1, position.getColumn());
			((ChessBoard) board).putInPosition(new Rook((ChessBoard) board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
			boolean canProtectKing = !ChessPiece.threatenedPosition(kingPosition, color, board);
			if (possiblePiece == null) {
				if (canProtectKing) {
					((ChessBoard) board).putInPosition(this, position);
					return moves;
				}
			} else {
				if (canProtectKing) {
					((ChessBoard) board).putInPosition(this, position);
					((ChessBoard) board).putInPosition(possiblePiece, possiblePiece.getPosition());
				}
				break;
			}
		}

		//DOWN
		for (int i = position.getRow(); i < 7; i++) {
			possiblePiece = (ChessPiece) board.seePosition(i + 1, position.getColumn());
			if (possiblePiece == null) {
				moves.add(new Position(i + 1, position.getColumn()));
			} else {
				if (possiblePiece.getColor() != color) {
					moves.add(new Position(i + 1, position.getColumn()));
				}
				break;
			}
		}

		//RIGHT
		for (int i = position.getColumn(); i < 7; i++) {
			possiblePiece = (ChessPiece) board.seePosition(position.getRow(), i + 1);
			if (possiblePiece == null) {
				moves.add(new Position(position.getRow(), i + 1));
			} else {
				if (possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow(), i + 1));
				}
				break;
			}
		}

		//LEFT
		for (int i = position.getColumn(); i > 0; i--) {
			possiblePiece = (ChessPiece) board.seePosition(position.getRow(), i - 1);
			if (possiblePiece == null) {
				moves.add(new Position(position.getRow(), i - 1));
			} else {
				if (possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow(), i - 1));
				}
				break;
			}
		}

		((ChessBoard) board).putInPosition(this, position);
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
