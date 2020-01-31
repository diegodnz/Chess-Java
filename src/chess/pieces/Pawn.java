package chess.pieces;

import java.util.ArrayList;

import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Pawn extends ChessPiece{
	
	public Pawn(ChessBoard board, Color color) {
		super(board, color);		
	}
	
	private int stepFoward() {
		if(color == Color.BLACK) {
			return +1;
		}else {
			return -1;
		}
	}
	
	private ChessPiece checkPiece(int row, int column) {
		return (ChessPiece)board.seePosition(row, column);
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		boolean canStepFoward = (color == Color.BLACK && position.getRow() < 7) || (color == Color.WHITE && position.getRow() > 0);
		boolean firstMove = (color == Color.BLACK && position.getRow() == 1) || (color == Color.WHITE && position.getRow() == 6);
				
		if(canStepFoward) {
			
			if(firstMove) {
				possiblePiece = checkPiece(position.getRow() + (2*stepFoward()), position.getColumn());
				if(possiblePiece == null) {
					moves.add(new Position(position.getRow() + (2*stepFoward()), position.getColumn()));
				}
			}
			
			possiblePiece = checkPiece(position.getRow() + stepFoward(), position.getColumn());
			if(possiblePiece == null) {
				moves.add(new Position(position.getRow() + stepFoward(), position.getColumn()));
			}
			
			if(position.getColumn() < 7) {
				possiblePiece = (ChessPiece)board.seePosition(position.getRow() + stepFoward(), position.getColumn() + 1);
				if(possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward(), position.getColumn() + 1));
				}
			}
			
			if(position.getColumn() > 0) {
				possiblePiece = (ChessPiece)board.seePosition(position.getRow() + stepFoward(), position.getColumn() - 1);
				if(possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward(), position.getColumn() - 1));
				}
			}
			
		}
		
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "P";
		}else {
			return "p'";
		}
	}
}
