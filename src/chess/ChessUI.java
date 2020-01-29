package chess;

import board.Board;
import board.ChessBoard;
import board.Piece;

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

}
