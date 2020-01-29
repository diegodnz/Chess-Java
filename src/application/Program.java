package application;

import java.io.IOException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessMove;
import chess.ChessUI;

public class Program {
	
	public static void clearScreen() { 
		// https://stackoverflow.com/questions/2979383/java-clear-the-console
		try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}  
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch match = new ChessMatch(); 
		while(true) {
			clearScreen();
			ChessUI.printBoard(match.getBoard());
			ChessMove move = ChessUI.play(match.getTurn(), sc);
			match.peformMove(move);
			System.out.println();
		}		
		
	}
	
}


