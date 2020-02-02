package chess;

import java.util.ArrayList;
import java.util.Scanner;

import board.BoardException;
import board.Position;
import chess.pieces.Color;

public class ChessUI {
	
	public static void printBoard(ChessMatch match) {
		System.out.println("   a b c d e f g h\n");
		for(int row = 0; row < match.getBoard().getNumRows(); row++) {			
			System.out.print(8-row + "  ");
			
			for(int column = 0; column < match.getBoard().getNumColumns(); column++) {
				ChessPiece piece = (ChessPiece)match.getBoard().seePosition(row, column);
				if(piece == null) {
					System.out.print(". ");
				}else if(piece.getColor() == Color.BLACK){
					System.out.print(piece + " ");
				}else {
					System.out.print(piece);
				}
			}
			
			System.out.println("  " + (8-row));			
		}		
		System.out.println("\n   a b c d e f g h");
	}
	
	private static Position getEntry(Scanner sc) throws ChessException {
		String entry = sc.next();
		int row = Integer.valueOf(entry.substring(1));
		char column = entry.charAt(0);
		ChessPosition chessPosition = new ChessPosition(row, column);
		return chessPosition.toBoardPosition();
	}
	
	public static Position readPosition(ChessMatch match, Scanner sc, String type, Position sourcePosition) {
		try {
			Position position;
			if(type.matches("Source")) {
				System.out.print("\nEnter the source position: ");
				position = getEntry(sc);
			
				ChessPiece sourcePiece = match.validPiece(position);
				ArrayList<Position> possibleMoves = sourcePiece.getMoves();
				
				if(possibleMoves.isEmpty()) {
					throw new ChessException("There is no movements to do with this piece.");
				}
				
			}else {
				ChessPiece sourcePiece = match.validPiece(sourcePosition);
				ArrayList<Position> possibleMoves = sourcePiece.getMoves();					
				
				System.out.print("Possible movements: ");
				for(Position move: possibleMoves) {
					System.out.print(ChessPosition.toChessPosition(move) + " ");
				}
				
				System.out.print("\nEnter the target position: ");
				position = getEntry(sc);				
				
				if(!possibleMoves.contains(position)) {	
					throw new ChessException("\nThis is not a valid move with the selected piece\n");
				}
			}
		
			return position;
		}		
		catch (NumberFormatException e) {
			System.out.println("\nInvalid position. Valid positions -> (a1, a2, ..., h7, h8)");
			return readPosition(match, sc, type, sourcePosition);
		}	
		catch (BoardException e) {
			System.out.println("\n" + e.getMessage());
			return readPosition(match, sc, type, sourcePosition);
		}
		catch (ChessException e) {
			System.out.println("\n" + e.getMessage());
			return readPosition(match, sc, type, sourcePosition);
		}
		
	
	}
	
	public static ChessMove play(ChessMatch match, Scanner sc) {
		if(match.getTurn() == Turn.WHITETURN){
			System.out.printf("\nPlayer1 turn!!");
		}else {
			System.out.printf("\nPlayer2 turn!!");
		}	
		
		Position sourcePosition = readPosition(match, sc, "Source", null);		
		Position targetPosition = readPosition(match, sc, "Target", sourcePosition);			
		return new ChessMove(sourcePosition, targetPosition);		
	}
}
