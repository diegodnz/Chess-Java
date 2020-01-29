package application;

import chess.ChessMatch;
import chess.ChessUI;

public class Program {
	
	public static void main(String[] args) {
		
	ChessMatch match = new ChessMatch(); 
	ChessUI.printBoard(match.getBoard());
		
	}
	
}