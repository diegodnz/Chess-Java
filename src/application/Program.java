package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessMove;
import chess.ChessUI;
import chess.Turn;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch match = new ChessMatch(); 
		boolean check = false;
		while(true) {
			ClearScreen.clear();
			ChessUI.printBoard(match.getBoard());
			ChessMove move = ChessUI.play(match, sc, check);
			check = match.peformMove(move);
			if(match.getTurn() == Turn.WHITETURN) {
				match.setTurn(Turn.BLACKTURN);
			}else {
				match.setTurn(Turn.WHITETURN);
			}
			System.out.println();
		}		
		
	}
	
}


