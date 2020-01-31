package chess;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public static Position readPosition(Scanner sc) {
		String entry = sc.next();
		int row = Integer.valueOf(entry.substring(1));
		char column = entry.charAt(0);
		ChessPosition chessPosition= new ChessPosition(row, column);
		return chessPosition.toBoardPosition();
	}
	
	public static ChessMove play(ChessMatch match, Scanner sc) {
		if(match.getTurn() == Turn.WHITETURN){
			System.out.printf("\nPlayer1 turn!!");
		}else {
			System.out.printf("\nPlayer2 turn!!");
		}		
		
		try {
			System.out.print("\nEnter the source position: ");
			Position sourcePosition = readPosition(sc);
			while(!match.validPiece(sourcePosition)) {
				System.out.print("Please, enter a position that has a piece of yours: ");
				sourcePosition = readPosition(sc);
			}			
			
			ChessPiece sourcePiece = (ChessPiece)(match.getBoard().seePosition(sourcePosition));
			ArrayList<Position> possibleMoves = sourcePiece.getMoves();
			
			if(possibleMoves.isEmpty()) {
				System.out.println("There is no movements to do with this piece.");
				return play(match, sc);
			}
			
			System.out.print("\nPossible movements: ");		
			for(Position move: possibleMoves) {
				System.out.print(ChessPosition.toChessPosition(move) + " ");
			}			
			System.out.print("\n\n");
			System.out.print("Enter the target position: ");
			Position targetPosition = readPosition(sc);
			
			return new ChessMove(sourcePosition, targetPosition);
		}catch (RuntimeException e) {
			System.out.println("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)");			
			return play(match, sc);
		}
	}

}
