package chess;

import board.BoardException;
import board.Piece;
import board.Position;
import chess.pieces.Bishop;
import chess.pieces.Color;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {
	
	private ChessBoard board;
	private Turn turn;
	
	public ChessMatch() {
		board = new ChessBoard();
		turn = Turn.WHITETURN;
		startMatch();
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public Turn getTurn() {
		return turn;
	}
	
	public void setTurn(Turn turn) {
		this.turn = turn;
	}
	
	public ChessPiece validPiece(Position position) throws BoardException, ChessException {
		ChessPiece piece = (ChessPiece)board.seePosition(position);		
		if(piece == null) {
			throw new BoardException("There is no piece in this position.");
		}else {
			boolean opponentPiece = (piece.getColor() == Color.BLACK && turn == Turn.WHITETURN) 
					|| (piece.getColor() == Color.WHITE && turn == Turn.BLACKTURN);
			if(opponentPiece){
				throw new ChessException("This piece belongs to your opponent");
			}else {
				return piece;
			}	
		}
	}
	
	public void peformMove(ChessMove move) {
		int sourceRow = move.getSource().getRow();
		int sourceColumn = move.getSource().getColumn();
		int targetRow = move.getTarget().getRow();
		int targetColumn = move.getTarget().getColumn();
		
		Piece piece = board.getPieces()[sourceRow][sourceColumn];
		piece.setPosition(move.getTarget());
		board.getPieces()[sourceRow][sourceColumn] = null;
		board.getPieces()[targetRow][targetColumn] = piece;
		
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
