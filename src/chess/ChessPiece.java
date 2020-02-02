package chess;

import java.util.ArrayList;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.Bishop;
import chess.pieces.Color;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public abstract class ChessPiece extends Piece{
	
	protected Color color;	
	
	public ChessPiece(ChessBoard board, Color color) {
		super(board);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public static boolean threatenedPosition(Position position, Color color, Board board) {
		int i, j;
		ChessPiece possiblePiece;
		boolean opponentPiece, dangerPiece, foundPiece;		
		
		//CheckUP
		i = position.getRow();
		foundPiece = false;
		while(i < 7 && !foundPiece) {
			i++;
			possiblePiece = (ChessPiece)board.seePosition(i, position.getColumn());
			
			if(possiblePiece != null){
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Rook || 
						(possiblePiece instanceof King && i - 1 == position.getRow());
				if(opponentPiece && dangerPiece){
					return true;
				}					
			}
			
		}
		
		//CheckDOWN
		i = position.getRow();
		foundPiece = false;
		while(i > 0 && !foundPiece) {
			i--;
			possiblePiece = (ChessPiece)board.seePosition(i, position.getColumn());
			
			if(possiblePiece != null){
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Rook || 
						(possiblePiece instanceof King && i + 1 == position.getRow());
				if(opponentPiece && dangerPiece){
					return true;
				}				
			}
			
		}
		
		//CheckRIGHT
		j = position.getColumn();
		foundPiece = false;
		while(j < 7 && !foundPiece) {
			j++;
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), j);
			
			if(possiblePiece != null){
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Rook || 
						(possiblePiece instanceof King && j - 1 == position.getColumn());
				if(opponentPiece && dangerPiece){
					return true;
				}					
			}
			
		}
		
		//CheckLEFT
		j = position.getColumn();
		foundPiece = false;
		while(j > 0 && !foundPiece) {
			j--;
			possiblePiece = (ChessPiece)board.seePosition(position.getRow(), j);
			
			if(possiblePiece != null){
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Rook || 
						(possiblePiece instanceof King && j + 1 == position.getColumn());
				if(opponentPiece && dangerPiece){
					return true;
				}				
			}
			
		}
		
		//Check-UP-RIGHT
		i = position.getRow();
		j = position.getColumn();
		foundPiece = false;
		while(i < 7 && j < 7 && !foundPiece) {
			i++;
			j++;
			possiblePiece = (ChessPiece)board.seePosition(i, j);
			
			if(possiblePiece != null) {
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Bishop ||
								(
								(i - 1 == position.getRow() && j - 1 == position.getColumn()) 
								&&
								(possiblePiece instanceof Pawn || possiblePiece instanceof King)
								);
				if(opponentPiece && dangerPiece) {
					return true;
				}				
			}
			
		}
		
		//Check-UP-LEFT
		i = position.getRow();
		j = position.getColumn();
		foundPiece = false;
		while(i < 7 && j > 0 && !foundPiece) {
			i++;
			j--;
			possiblePiece = (ChessPiece)board.seePosition(i, j);
			
			if(possiblePiece != null) {
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Bishop ||
								(
								(i - 1 == position.getRow() && j + 1 == position.getColumn()) 
								&&
								(possiblePiece instanceof Pawn || possiblePiece instanceof King)
								);
				if(opponentPiece && dangerPiece) {
					return true;
				}				
			}
			
		}
		
		//Check-DOWN-RIGHT
		i = position.getRow();
		j = position.getColumn();
		foundPiece = false;
		while(i > 0 && j < 7 && !foundPiece) {
			i--;
			j++;
			possiblePiece = (ChessPiece)board.seePosition(i, j);
			
			if(possiblePiece != null) {
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Bishop ||
								(
								(i + 1 == position.getRow() && j - 1 == position.getColumn()) 
								&&
								(possiblePiece instanceof Pawn || possiblePiece instanceof King)
								);
				if(opponentPiece && dangerPiece) {
					return true;
				}				
			}
			
		}
		
		//Check-DOWN-LEFT
		i = position.getRow();
		j = position.getColumn();
		foundPiece = false;
		while(i > 0 && j > 0 && !foundPiece) {
			i--;
			j--;
			possiblePiece = (ChessPiece)board.seePosition(i, j);
			
			if(possiblePiece != null) {
				foundPiece = true;
				opponentPiece = possiblePiece.getColor() != color;
				dangerPiece = possiblePiece instanceof Queen || possiblePiece instanceof Bishop ||
								(
								(i + 1 == position.getRow() && j + 1 == position.getColumn()) 
								&&
								(possiblePiece instanceof Pawn || possiblePiece instanceof King)
								);
				if(opponentPiece && dangerPiece) {
					return true;
				}				
			}
			
		}
		
		//UP-UP-RIGHT
		if(position.getRow() < 6 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() + 1);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}
		
		//UP-UP-LEFT
		if(position.getRow() < 6 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 2, position.getColumn() - 1);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}	
		
		//UP-RIGHT-RIGHT
		if(position.getRow() < 7 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() + 2);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}			
		
		//UP-LEFT-LEFT
		if(position.getRow() < 7 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() + 1, position.getColumn() - 2);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}
		
		//DOWN-DOWN-RIGHT
		if(position.getRow() > 1 && position.getColumn() < 7) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() + 1);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}
		
		//DOWN-DOWN-LEFT
		if(position.getRow() > 1 && position.getColumn() > 0) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 2, position.getColumn() - 1);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}
		
		//DOWN-RIGHT-RIGHT
		if(position.getRow() > 0 && position.getColumn() < 6) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() + 2);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}		
		
		//DOWN-LEFT-LEFT
		if(position.getRow() > 0 && position.getColumn() > 1) {
			possiblePiece = (ChessPiece)board.seePosition(position.getRow() - 1, position.getColumn() - 2);
			if(possiblePiece != null && (possiblePiece instanceof Horse && possiblePiece.getColor() != color)) {
				return true;
			}
		}
		
		return false;
	}
	
	public abstract ArrayList<Position> getMoves();

}
