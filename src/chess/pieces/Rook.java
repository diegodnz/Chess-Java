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
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves(position, board, color, kingPosition);
	}

	public static ArrayList<Position> getMoves(Position position, Board board, Color color, Position kingPosition) {
		ArrayList<Position> moves = new ArrayList<>();
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
		ArrayList<Position> moves = new ArrayList<>();
		ChessPiece possiblePiece;
		board.nullPosition(this.position);
		int targetRow;
		int targetColumn;

		//UP
		for (int i = position.getRow(); i > 0; i--) {
			targetRow = i - 1;
			targetColumn = position.getColumn();
			possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
			board.putInPosition(new Rook((ChessBoard) board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
			boolean canProtectKing = !ChessPiece.threatenedPosition(kingPosition, color, board);
			if (possiblePiece == null) {
				if (canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				board.nullPosition(targetRow, targetColumn);
			} else {
				board.putInPosition(possiblePiece, possiblePiece.getPosition());
				if (possiblePiece.getColor() != color && canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				break;
			}
		}

		//DOWN
		for (int i = position.getRow(); i < 7; i++) {
			targetRow = i + 1;
			targetColumn = position.getColumn();
			possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
			board.putInPosition(new Rook((ChessBoard) board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
			boolean canProtectKing = !ChessPiece.threatenedPosition(kingPosition, color, board);
			if (possiblePiece == null) {
				if (canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				board.nullPosition(targetRow, targetColumn);
			} else {
				board.putInPosition(possiblePiece, possiblePiece.getPosition());
				if (possiblePiece.getColor() != color && canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				break;
			}
		}

		//RIGHT
		for (int j = position.getColumn(); j < 7; j++) {
			targetRow = position.getRow();
			targetColumn = j + 1;
			possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
			board.putInPosition(new Rook((ChessBoard) board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
			boolean canProtectKing = !ChessPiece.threatenedPosition(kingPosition, color, board);
			if (possiblePiece == null) {
				if (canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				board.nullPosition(targetRow, targetColumn);
			} else {
				board.putInPosition(possiblePiece, possiblePiece.getPosition());
				if (possiblePiece.getColor() != color && canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				break;
			}
		}

		//LEFT
		for (int j = position.getColumn(); j > 0; j--) {
			targetRow = position.getRow();
			targetColumn = j - 1;
			possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
			board.putInPosition(new Rook((ChessBoard) board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
			boolean canProtectKing = !ChessPiece.threatenedPosition(kingPosition, color, board);
			if (possiblePiece == null) {
				if (canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				board.nullPosition(targetRow, targetColumn);
			} else {
				board.putInPosition(possiblePiece, possiblePiece.getPosition());
				if (possiblePiece.getColor() != color && canProtectKing) {
					moves.add(new Position(targetRow, targetColumn));
				}
				break;
			}
		}

		board.putInPosition(this, position);
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
