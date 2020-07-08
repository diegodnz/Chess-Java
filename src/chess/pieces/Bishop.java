package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Bishop extends ChessPiece{
	
	public Bishop(ChessBoard board, Position position, Color color) {
		super(board, position, color); 		
	}
	
	@Override
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves(position, board, color, kingPosition);
	}

	public static ArrayList<Position> getMoves(Position position, Board board, Color color, Position kingPosition) {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		int line, column;
		
		//UP-RIGHT
		line = position.getRow();
		column = position.getColumn();
		while(line < 7 && column < 7) {
			line++;
			column++;
			possiblePiece = (ChessPiece)board.seePosition(line, column);
			Position movePosition = new Position(line, column);
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
		
		//UP-LEFT
		line = position.getRow();
		column = position.getColumn();
		while(line < 7 && column > 0) {
			line++;
			column--;
			possiblePiece = (ChessPiece)board.seePosition(line, column);
			Position movePosition = new Position(line, column);
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
		
		//DOWN-RIGHT
		line = position.getRow();
		column = position.getColumn();
		while(line > 0 && column < 7) {
			line--;
			column++;
			possiblePiece = (ChessPiece)board.seePosition(line, column);
			Position movePosition = new Position(line, column);
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
		
		//DOWN-LEFT
		line = position.getRow();
		column = position.getColumn();
		while(line > 0 && column > 0) {
			line--;
			column--;
			possiblePiece = (ChessPiece)board.seePosition(line, column);
			Position movePosition = new Position(line, column);
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
			return "   B   ";
		}else {
			return "  (b)  ";
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
