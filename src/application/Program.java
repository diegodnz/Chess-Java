package application;

import java.util.Scanner;

import chess.PlayerType;
import chess.ChessMatch; 
import chess.ChessMove;
import chess.ChessUI;
import chess.Turn;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);		
		// Init match		
		PlayerType player1 = PlayerType.BOT;
		PlayerType player2 = PlayerType.BOT;
		ChessMatch match = new ChessMatch(player1, player2);
		boolean check = false;
		boolean checkMate = false;
		boolean draw = false;
		ChessMove move;

		while(!checkMate && !draw) {
			ClearScreen.clear();
			ChessUI.printBoard(match.getBoard());
			ChessUI.printCapturedPieces(match);			
			move = ChessUI.play(match, sc, check, true);			
			match.peformMove(move);
			check = match.check();
			match.changeTurn();

			if (match.checkMate()) {
				checkMate = true;
			}
			if (check) {
				//match.turnOffBots();
			}
			if (match.draw()) {
				draw = true;
			}
			//Thread.sleep(1000);
			System.out.println();
		}	

		ClearScreen.clear();
		ChessUI.printBoard(match.getBoard());

		if(!draw) {
			System.out.println("\nCHECKMATE!");
			if(match.getTurn() == Turn.BLACKTURN) {
				System.out.println("White player won!!");
			}else {
				System.out.println("Black player won!!");
			}
		} else {
			System.out.println("Draw!!");
		}
		sc.close();		
	}	
}


