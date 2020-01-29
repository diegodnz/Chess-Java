package chess;

import java.util.InputMismatchException;
import java.util.Scanner;

import board.Piece;
import board.Position;

public class ChessUI {
	
	public static void printBoard(ChessBoard board) {
		for(int row = 0; row < board.getNumRows(); row++) {
			
			System.out.print(row+1 + " ");
			
			for(int column = 0; column < board.getNumColumns(); column++) {
				Piece piece = board.seePosition(row, column);
				if(piece == null) {
					System.out.print(". ");
				}else {
					System.out.print(piece + " ");
				}			
			}
			
			System.out.println();			
		}		
		System.out.println("\n  a b c d e f g h");
	}
	
	public static Position readPosition(Scanner sc) {
		String entry = sc.next();
		int row = Integer.valueOf(entry.substring(1));
		char column = entry.charAt(0);
		ChessPosition chessPosition= new ChessPosition(row, column);
		return chessPosition.toBoardPosition();
	}
	
	public static ChessMove play(String turn, Scanner sc) {
		if(turn.matches("WHITE")){
			System.out.printf("Player1 turn!! \nEnter the position: ");
		}else {
			System.out.printf("Player2 turn!! \nEnter the source position: ");
		}		
		
		try {
			Position sourcePosition = readPosition(sc);
			
			System.out.print("Enter the target position: ");
			Position targetPosition = readPosition(sc);
			
			return new ChessMove(sourcePosition, targetPosition);
		}catch (RuntimeException e) {
			throw new InputMismatchException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)");
		}
	}

}
