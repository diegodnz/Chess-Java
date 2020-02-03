package chess;

import board.BoardException;
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
	private ChessPlayer whitePlayer;
	private ChessPlayer blackPlayer;
	
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
	
	public ChessPlayer getWhitePlayer() {
		return whitePlayer;
	}
	
	public ChessPlayer getBlackPlayer() {
		return blackPlayer;
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
	
	public void changeTurn() {
		if(turn == Turn.WHITETURN) {
			turn = Turn.BLACKTURN;
		}else {
			turn = Turn.WHITETURN;
		}
	}
	
	public ChessPlayer getPlayer(Turn turn) {
		if(turn == Turn.BLACKTURN) {
			return blackPlayer;
		}else {
			return whitePlayer;
		}
	}
	
	public void peformMove(ChessMove move) {
		int sourceRow = move.getSource().getRow();
		int sourceColumn = move.getSource().getColumn();
		int targetRow = move.getTarget().getRow();
		int targetColumn = move.getTarget().getColumn();
		
		if(getBoard().seePosition(move.getSource()) == null) {
			throw new ChessException("Invalid movement, there is no piece in the source position" + "\nSource: " 
		+ move.getSource() + "\nTarget: " + move.getTarget());
		}else {		
			ChessPiece piece = (ChessPiece)board.seePosition(sourceRow, sourceColumn);	
			ChessPiece opponentPiece = (ChessPiece)board.seePosition(targetRow, targetColumn);
			if(opponentPiece != null && (piece.getColor() == opponentPiece.getColor())) {
				throw new ChessException("Invalid move, you can't capture your own piece" + "\nSource: " 
						+ move.getSource() + "\nTarget: " + move.getTarget());
			}else {
				board.doChessMove(piece, targetRow, targetColumn);			
				board.nullPosition(sourceRow, sourceColumn);
			}		
			
			if(opponentPiece != null) {
				if(turn == Turn.BLACKTURN) {
					whitePlayer.addLostPiece(opponentPiece);
				}else {
					blackPlayer.addLostPiece(opponentPiece);
				}
			}
		}
	}
	
	public boolean check() {
		if( 
			( ChessPiece.threatenedPosition(blackPlayer.getKing().getPosition(), Color.BLACK, getBoard()) ) 
			||
			( ChessPiece.threatenedPosition(whitePlayer.getKing().getPosition(), Color.WHITE, getBoard()) )
			) {
			return true;
		}
		return false;
	}
	
	private boolean canProtectKing(ChessPlayer player) {
		ChessBoard testBoard = new ChessBoard();
		board.clone(testBoard);
		King playerKing = player.getKing();
		ChessPiece opponentPiece;
		
		for(ChessPiece piece: player.getNormalPieces()) {
			if(!player.capturedPiece(piece)) {
				for(Position move: piece.getMoves()) {							
					
					opponentPiece = (ChessPiece)testBoard.seePosition(move);
					testBoard.getPieces()[move.getRow()][move.getColumn()] = piece;
					testBoard.nullPosition(piece.getPosition());
					
					if(!ChessPiece.threatenedPosition(playerKing.getPosition(), playerKing.getColor(), testBoard)) {
						return true;
					}
					
					testBoard.getPieces()[piece.getPosition().getRow()][piece.getPosition().getColumn()] = piece;
					testBoard.getPieces()[move.getRow()][move.getColumn()] = opponentPiece;
				}
			}
		}
		
		if(!playerKing.getMoves().isEmpty()) {
			return true;
		}
		
		return false;		
	}
	
	private boolean blackInCheck() {
		King blackKing = blackPlayer.getKing();
		if(ChessPiece.threatenedPosition(blackKing.getPosition(), blackKing.getColor(), board)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean whiteInCheck() {
		King whiteKing = whitePlayer.getKing();
		if(ChessPiece.threatenedPosition(whiteKing.getPosition(), whiteKing.getColor(), board)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkMate() {
		if(!canProtectKing(whitePlayer) || !canProtectKing(blackPlayer)) {
			return true;		
		}else if( (turn == Turn.WHITETURN && blackInCheck()) || (turn == Turn.BLACKTURN && whiteInCheck()) ){
			return true;
		}else {
			return false;
		}
	}
	
	private void startMatch() {
		blackPlayer = new ChessPlayer(new King(board, new Position(0, 4), Color.BLACK));
		whitePlayer = new ChessPlayer(new King(board, new Position(7, 4), Color.WHITE));
		
		for(int i = 0; i < 8; i++) { // Pawns
			blackPlayer.addPiece(new Pawn(board, new Position(1, i), Color.BLACK), 7+i);
			whitePlayer.addPiece(new Pawn(board, new Position(6, i), Color.WHITE), 7+i);
		}	
		
		blackPlayer.addPiece(new Rook(board, new Position(0, 0), Color.BLACK), 0);
		blackPlayer.addPiece(new Rook(board, new Position(0, 7), Color.BLACK), 6);
		blackPlayer.addPiece(new Horse(board, new Position(0, 1), Color.BLACK), 1);
		blackPlayer.addPiece(new Horse(board, new Position(0, 6), Color.BLACK), 5);
		blackPlayer.addPiece(new Bishop(board, new Position(0, 2), Color.BLACK), 2);
		blackPlayer.addPiece(new Bishop(board, new Position(0, 5), Color.BLACK), 4);
		blackPlayer.addPiece(new Queen(board, new Position(0, 3), Color.BLACK), 3);
		
		whitePlayer.addPiece(new Rook(board, new Position(7, 0), Color.WHITE), 0);
		whitePlayer.addPiece(new Rook(board, new Position(7, 7), Color.WHITE), 6);
		whitePlayer.addPiece(new Horse(board, new Position(7, 1), Color.WHITE), 1);
		whitePlayer.addPiece(new Horse(board, new Position(7, 6), Color.WHITE), 5);
		whitePlayer.addPiece(new Bishop(board, new Position(7, 2), Color.WHITE), 2);
		whitePlayer.addPiece(new Bishop(board, new Position(7, 5), Color.WHITE), 4);
		whitePlayer.addPiece(new Queen(board, new Position(7, 3), Color.WHITE), 3);
		
		board.putInPosition(blackPlayer.getKing(), blackPlayer.getKing().getPosition());
		for(ChessPiece piece: blackPlayer.getNormalPieces()) {
			board.putInPosition(piece, piece.getPosition());
		}
		
		board.putInPosition(whitePlayer.getKing(), whitePlayer.getKing().getPosition());
		for(ChessPiece piece: whitePlayer.getNormalPieces()) {
			board.putInPosition(piece, piece.getPosition());
		}
	}
}
