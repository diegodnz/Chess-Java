package application;

import java.util.Scanner;

import GameTree.GameTree;
import GameTree.Game;
import GameTree.ChessTree;
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
		ChessMove move;

		while(!checkMate) {
			ClearScreen.clear();
			ChessUI.printBoard(match.getBoard());
			ChessUI.printCapturedPieces(match);			
			move = ChessUI.play(match, sc, check, true);
			match.peformMove(move);
			check = match.check();
			match.changeTurn();
			if (check && match.checkMate()) {
				checkMate = true;
			}
			if (check) {
				match.turnOffBots();
			}
			//Thread.sleep(500);
			System.out.println();
		}		
		ClearScreen.clear();
		ChessUI.printBoard(match.getBoard());
		System.out.println("\nCHECKMATE!");
		if(match.getTurn() == Turn.BLACKTURN) {
			System.out.println("White player won!!");
		}else {
			System.out.println("Black player won!!");
		}
		sc.close();
		
		/*
		GameTree gameTree = new GameTree(Game.CHESS);
		gameTree.buildTree("RHBQKBHRPPPPPPPP00000000000000000000000000000000pppppppprhbqkbhr", 5, Color.WHITE);
		System.out.println(gameTree);
		*/
		 
	}	
}


