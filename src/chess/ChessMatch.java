package chess;

import board.ChessBoard;
import board.Position;
import chess.pieces.*;

public class ChessMatch {
	
	private ChessBoard board;
	
	public ChessMatch() {
		board = new ChessBoard();
		startMatch();
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	private void startMatch() {
		for(int i = 0; i < 8; i++) { // Pawns
			board.putInPosition(new Pawn(board, Color.BLACK), new Position(1, i));
			board.putInPosition(new Pawn(board, Color.WHITE), new Position(6, i));
		}
		board.putInPosition(new Rook(board, Color.BLACK), new Position(0, 0));
		board.putInPosition(new Rook(board, Color.BLACK), new Position(0, 7));
		board.putInPosition(new Horse(board, Color.BLACK), new Position(0, 1));
		board.putInPosition(new Horse(board, Color.BLACK), new Position(0, 6));
		board.putInPosition(new Bishop(board, Color.BLACK), new Position(0, 2));
		board.putInPosition(new Bishop(board, Color.BLACK), new Position(0, 5));
		board.putInPosition(new Queen(board, Color.BLACK), new Position(0, 3));
		board.putInPosition(new King(board, Color.BLACK), new Position(0, 4));
		
		board.putInPosition(new Rook(board, Color.WHITE), new Position(7, 0));
		board.putInPosition(new Rook(board, Color.WHITE), new Position(7, 7));
		board.putInPosition(new Horse(board, Color.WHITE), new Position(7, 1));
		board.putInPosition(new Horse(board, Color.WHITE), new Position(7, 6));
		board.putInPosition(new Bishop(board, Color.WHITE), new Position(7, 2));
		board.putInPosition(new Bishop(board, Color.WHITE), new Position(7, 5));
		board.putInPosition(new Queen(board, Color.WHITE), new Position(7, 3));
		board.putInPosition(new King(board, Color.WHITE), new Position(7, 4));
		
	}
}
