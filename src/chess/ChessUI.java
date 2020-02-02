package chess;

import java.util.ArrayList;
import java.util.Scanner;

import application.ClearScreen;
import board.BoardException;
import board.Piece;
import board.Position;

public class ChessUI {
	
	public static void printBoard(ChessBoard board) {
		System.out.println("   a b c d e f g h\n");
		for(int row = 0; row < board.getNumRows(); row++) {			
			System.out.print(8-row + "  ");
			
			for(int column = 0; column < board.getNumColumns(); column++) {
				Piece piece = board.seePosition(row, column);
				if(piece == null) {
					System.out.print(". ");
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
	
	public static Position readPosition(ChessMatch match, Scanner sc, String type, Position sourcePosition, boolean printBoard) {
		try {
			Position position;
			if(type.matches("Source")) {
				System.out.print("\nEnter the source position: ");
				position = getEntry(sc);
			
				ChessPiece sourcePiece = match.validPiece(position);
				ArrayList<Position> possibleMoves = sourcePiece.getMoves();
				
				if(possibleMoves.isEmpty()) {
					throw new ChessException("There are no movements to do with this piece.");
				}
				
			}else {
				ChessPiece sourcePiece = match.validPiece(sourcePosition);
				ArrayList<Position> possibleMoves = sourcePiece.getMoves();					
				
				if(printBoard) {
					ClearScreen.clear();
					ChessBoard movementBoard = new ChessBoard();
					for(int i = 0; i < 8; i++) {
						movementBoard.getPieces()[i] = match.getBoard().getPieces()[i].clone();
					}
					for(Position move: possibleMoves) {
						Piece possiblePiece = movementBoard.seePosition(move);
						if(possiblePiece == null) {
							movementBoard.getPieces()[move.getRow()][move.getColumn()] = new Piece(movementBoard, "* ");
						}else {
							movementBoard.getPieces()[move.getRow()][move.getColumn()] 
									= new Piece(movementBoard, possiblePiece.toString().charAt(0) + "<");
						}
					}
					printBoard(movementBoard);
				}
				
				System.out.print("\nPossible movements: ");
				for(Position move: possibleMoves) {
					System.out.print(ChessPosition.toChessPosition(move) + " ");
				}
				
				System.out.print("\nEnter the target position: ");
				position = getEntry(sc);				
				
				if(!possibleMoves.contains(position)) {						
					throw new ChessException("This is not a valid move with the selected piece");					
				}
			}
		
			return position;
		}		
		catch (NumberFormatException e) {
			System.out.println("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)");
			return readPosition(match, sc, type, sourcePosition, false);
		}	
		catch (BoardException e) {
			System.out.println(e.getMessage());
			return readPosition(match, sc, type, sourcePosition, false);
		}
		catch (ChessException e) {
			System.out.println(e.getMessage());
			return readPosition(match, sc, type, sourcePosition, false);
		}		
	
	}
	
	public static ChessMove play(ChessMatch match, Scanner sc) {
		if(match.getTurn() == Turn.WHITETURN){
			System.out.printf("\nPlayer1 turn!!");
		}else {
			System.out.printf("\nPlayer2 turn!!");
		}	
		
		Position sourcePosition = readPosition(match, sc, "Source", null, false);		
		Position targetPosition = readPosition(match, sc, "Target", sourcePosition, true);			
		return new ChessMove(sourcePosition, targetPosition);		
	}
}
