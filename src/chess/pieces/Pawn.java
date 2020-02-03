package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Pawn extends ChessPiece{
	
	public Pawn(ChessBoard board, Position position, Color color) {
		super(board, position, color);		
	}
	
	@Override 
	public ArrayList<Position> getMoves() {		
		return getMoves(position, board, color);		
	}
	
	public static ArrayList<Position> getMoves(Position position, Board board, Color color) {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		boolean canStepFoward = (color == Color.BLACK && position.getRow() < 7) || (color == Color.WHITE && position.getRow() > 0);
		boolean firstMove = (color == Color.BLACK && position.getRow() == 1) || (color == Color.WHITE && position.getRow() == 6);
		
		int stepFoward;
		if(color == Color.BLACK) {
			stepFoward = +1;
		}else {
			stepFoward = -1;
		}
				
		if(canStepFoward) {
			
			if(firstMove) {
				possiblePiece = (ChessPiece)board.seePosition(position.getRow() + (2*stepFoward), position.getColumn());
				if(possiblePiece == null) {
					moves.add(new Position(position.getRow() + (2*stepFoward), position.getColumn()));
				}
			}
			
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + stepFoward, position.getColumn());
			if(possiblePiece == null) {
				moves.add(new Position(position.getRow() + stepFoward, position.getColumn()));
			}
			
			if(position.getColumn() < 7) {
				possiblePiece = (ChessPiece)board.seePosition(position.getRow() + stepFoward, position.getColumn() + 1);
				if(possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward, position.getColumn() + 1));
				}
			}
			
			if(position.getColumn() > 0) {
				possiblePiece = (ChessPiece)board.seePosition(position.getRow() + stepFoward, position.getColumn() - 1);
				if(possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward, position.getColumn() - 1));
				}
			}
			
		}		
		
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return " P ";
		}else {
			return " p ";
		}
	}
}
