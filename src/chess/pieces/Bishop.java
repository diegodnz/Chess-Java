package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;

public class Bishop extends ChessPiece{
	
	public Bishop(ChessBoard board, Position position, Color color) {
		super(board, position, color); 		
	}
	
	@Override
	public ArrayList<Position> getMoves() {		
		return getMoves(position, board, color);		
	}
	
	public static ArrayList<Position> getMoves(Position position, Board board, Color color){		
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
			if(possiblePiece == null) {
				moves.add(new Position(line, column));
			}else {
				if(possiblePiece.getColor() != color) {
					moves.add(new Position(line, column));
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
			if(possiblePiece == null) {
				moves.add(new Position(line, column));
			}else {
				if(possiblePiece.getColor() != color) {
					moves.add(new Position(line, column));
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
			if(possiblePiece == null) {
				moves.add(new Position(line, column));
			}else {
				if(possiblePiece.getColor() != color) {
					moves.add(new Position(line, column));
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
			if(possiblePiece == null) {
				moves.add(new Position(line, column));
			}else {
				if(possiblePiece.getColor() != color) {
					moves.add(new Position(line, column));
				}
				break;
			}
		}
		
		return moves;
	}

	@Override
	public  ArrayList<Position> getProtectMoves(Position kingPosition) {
		return new ArrayList<>();
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
