package chess;

import java.util.ArrayList;
import java.util.Scanner;

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
	private ChessPlayer whitePlayer;
	private ChessPlayer blackPlayer;
	private boolean whiteIsBot;
	private boolean blackIsBot;
	private boolean whiteIsRandom;
	private boolean blackIsRandom;	

	public ChessMatch(PlayerType player1, PlayerType player2) {
		board = new ChessBoard();
		turn = Turn.WHITETURN;

		if(player1 == PlayerType.PERSON) {
			whiteIsBot = false;
			whiteIsRandom = false;
		}else if(player1 == PlayerType.BOT) {
			whiteIsBot = true;
			whiteIsRandom = true;
		}else if(player1 == PlayerType.MINIMAX) {
			whiteIsBot = true;
			whiteIsRandom = false;
		}

		if(player2 == PlayerType.PERSON) {
			blackIsBot = false;
			blackIsRandom = false;
		}else if(player2 == PlayerType.BOT) {
			blackIsBot = true;
			blackIsRandom = true;
		}else if(player2 == PlayerType.MINIMAX) {
			blackIsBot = true;
			blackIsRandom = false;
		}	
			
		startMatch();
	}

	public ChessMatch() {
		board = new ChessBoard();
		turn = Turn.WHITETURN;
		whiteIsBot = false;
		blackIsBot = false;	
		whiteIsRandom = false;
		blackIsRandom = false;		
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

	public boolean whiteIsBot() {
		return whiteIsBot;
	}

	public boolean blackIsBot() {
		return blackIsBot;
	}

	public boolean whiteIsRandom() {
		return whiteIsRandom;
	}

	public boolean blackIsRandom() {
		return blackIsRandom;
	}

	public ChessPlayer getWhitePlayer() {
		return whitePlayer;
	}

	public ChessPlayer getBlackPlayer() {
		return blackPlayer;
	}

	public void turnOffBots() {
		whiteIsBot = false;
		blackIsBot = false;
	}

	public ChessPiece validPiece(Position position) {
		ChessPiece piece = (ChessPiece) board.seePosition(position);
		if (piece == null) {
			throw new BoardException("There is no piece in this position.");
		} else {
			boolean opponentPiece = (piece.getColor() == Color.BLACK && turn == Turn.WHITETURN)
					|| (piece.getColor() == Color.WHITE && turn == Turn.BLACKTURN);
			if (opponentPiece) {
				throw new ChessException("This piece belongs to your opponent");
			} else {
				return piece;
			}
		}
	}

	public static ChessPiece validPiece(Position position, ChessBoard board, Color colorTurn) {
		ChessPiece piece = (ChessPiece) board.seePosition(position);
		if (piece == null) {
			throw new BoardException("There is no piece in this position.");
		} else {
			boolean opponentPiece = (piece.getColor() == Color.BLACK && colorTurn == Color.WHITE)
					|| (piece.getColor() == Color.WHITE && colorTurn == Color.BLACK);
			if (opponentPiece) {
				throw new ChessException("This piece belongs to your opponent");
			} else {
				return piece;
			}
		}
	}

	public void changeTurn() {
		if (turn == Turn.WHITETURN) {
			turn = Turn.BLACKTURN;
		} else {
			turn = Turn.WHITETURN;
		}
	}

	public ChessPlayer getPlayer(Turn turn) {
		if (turn == Turn.BLACKTURN) {
			return blackPlayer;
		} else {
			return whitePlayer;
		}
	}

	public void peformMove(Scanner sc, ChessMove move) {
		//System.out.println(move.getSource().getRow() + " " + move.getSource().getColumn() + "  " + move.getTarget().getRow() + " " + move.getTarget().getColumn());
		int sourceRow = move.getSource().getRow();
		int sourceColumn = move.getSource().getColumn();
		int targetRow = move.getTarget().getRow();
		int targetColumn = move.getTarget().getColumn();

		if (getBoard().seePosition(move.getSource()) == null) {
			throw new ChessException("Invalid movement, there is no piece in the source position" + "\nSource: "
					+ move.getSource() + "\nTarget: " + move.getTarget());
		} else {
			ChessPiece piece = (ChessPiece) board.seePosition(sourceRow, sourceColumn);

			//Castle
			if (piece instanceof King) {			
				//Left castle
				if (targetColumn == sourceColumn - 2) {
					ChessPiece rook = (ChessPiece)board.seePosition(sourceRow, sourceColumn - 4);
					board.nullPosition(sourceRow, sourceColumn - 4);
					board.putInPosition((Rook)rook, sourceRow, sourceColumn - 1);
				}

				//Right castle
				if (targetColumn == sourceColumn + 2) {
					ChessPiece rook = (ChessPiece)board.seePosition(sourceRow, sourceColumn + 3);
					board.nullPosition(sourceRow, sourceColumn + 3);
					board.putInPosition((Rook)rook, sourceRow, sourceColumn + 1);
				}
				((King)piece).setMoved();

			} else if (piece instanceof Rook) {
				if ( !((Rook)piece).wasMoved() ) {
					//Left castle
					if (targetColumn == sourceColumn + 3) {
						ChessPiece possibleKing = (ChessPiece)board.seePosition(sourceRow, sourceColumn + 4);
						if (possibleKing != null && possibleKing instanceof King && !((King)possibleKing).wasMoved() ) {
							board.nullPosition(sourceRow, sourceColumn + 4);
							board.putInPosition((King)possibleKing, sourceRow, sourceColumn + 2);
						}
					}

					//Right castle
					if (targetColumn == sourceColumn - 2) {
						ChessPiece possibleKing = (ChessPiece)board.seePosition(sourceRow, sourceColumn - 3);
						if (possibleKing != null && possibleKing instanceof King && !((King)possibleKing).wasMoved() ) {
							board.nullPosition(sourceRow, sourceColumn - 3);
							board.putInPosition((King)possibleKing, sourceRow, sourceColumn - 1);
						}
					}
				

				}
				((Rook)piece).setMoved();
			}

			ChessPiece opponentPiece = (ChessPiece) board.seePosition(targetRow, targetColumn);

			// En Passant
			if (opponentPiece == null && piece instanceof Pawn && sourceColumn != targetColumn) {
				int enPassantRow;
				if (piece.getColor() == Color.WHITE) {
					enPassantRow = targetRow+1;					
				} else {
					enPassantRow = targetRow-1;
				}
				ChessPiece enPassantPiece = (ChessPiece) board.seePosition(enPassantRow, targetColumn);
				board.nullPosition(enPassantRow, targetColumn);

				if (turn == Turn.BLACKTURN) {
					whitePlayer.addLostPiece(enPassantPiece);
				} else {
					blackPlayer.addLostPiece(enPassantPiece);
				}
			}

			board.nullPosition(sourceRow, sourceColumn);
			board.doChessMove(piece, targetRow, targetColumn);

			if (opponentPiece != null) {
				if (turn == Turn.BLACKTURN) {
					whitePlayer.addLostPiece(opponentPiece);
				} else {
					blackPlayer.addLostPiece(opponentPiece);
				}
			}

			if (piece instanceof Pawn && (targetRow == 0 || targetRow == 7) ) {
				pawnPromotion(sc, targetRow, targetColumn);
			}
		}
	}

	private ChessPiece choosePiece(Scanner sc, int targetRow, int targetColumn, Color color) {
		System.out.println("Your Pawn can be promoted!! Choose one promotion piece: \nq - Queen \nr - Rook \nh - Horse \nb - Bishop");
		char promotionPiece;
		do {
			System.out.println("Enter the piece (q,r,h or b): ");
			promotionPiece = sc.next().charAt(0);
		} while (promotionPiece != 'q' && promotionPiece != 'r' && promotionPiece != 'h' && promotionPiece != 'b');

		if (promotionPiece == 'b') {
			return new Bishop(board, new Position(targetRow, targetColumn), color);
		} else if (promotionPiece == 'r') {
			return new Rook(board, new Position(targetRow, targetColumn), color);
		} else if (promotionPiece == 'h') {
			return new Horse(board, new Position(targetRow, targetColumn), color);
		} else {
			return new Queen(board, new Position(targetRow, targetColumn), color);
		}
	}

	public void pawnPromotion(Scanner sc, int targetRow, int targetColumn) {
		Position position = new Position(targetRow, targetColumn);
		if (turn == Turn.WHITETURN) {
			if (whiteIsBot) {
				Queen newPiece = new Queen(board, position, Color.WHITE);
				whitePlayer.promotePawn(newPiece);
				board.putInPosition(newPiece, position);
			} else {			
				ChessPiece newPiece = choosePiece(sc, targetRow, targetColumn, Color.WHITE);
				whitePlayer.promotePawn(newPiece);
				board.putInPosition(newPiece, position);				
			}
		} else {
			if (blackIsBot) {
				Queen newPiece = new Queen(board, position, Color.BLACK);
				blackPlayer.promotePawn(newPiece);
				board.putInPosition(newPiece, position);
			} else {
				ChessPiece newPiece = choosePiece(sc, targetRow, targetColumn, Color.BLACK);
				blackPlayer.promotePawn(newPiece);
				board.putInPosition(newPiece, position);
			}
		}
	}

	public boolean check() {
		if (
				(ChessPiece.threatenedPosition(blackPlayer.getKing().getPosition(), Color.BLACK, getBoard()))
						||
						(ChessPiece.threatenedPosition(whitePlayer.getKing().getPosition(), Color.WHITE, getBoard()))
		) {
			return true;
		}
		return false;
	}

	public static boolean check(ChessPiece blackKing, ChessPiece whiteKing, ChessBoard board) {
		if (
				(ChessPiece.threatenedPosition(blackKing.getPosition(), Color.BLACK, board))
						||
						(ChessPiece.threatenedPosition(whiteKing.getPosition(), Color.WHITE, board))
		) {
			return true;
		}
		return false;
	}

	private boolean canProtectKing(ChessPlayer player) {
		ChessBoard testBoard = new ChessBoard();
		board.clone(testBoard);
		King playerKing = player.getKing();

		if (!playerKing.getMoves().isEmpty()) {
			return true;
		}

		for (ChessPiece piece : player.getNormalPieces()) {
			if (piece != null) {
				if(!piece.getMoves(playerKing.getPosition()).isEmpty()) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean canProtectKing(ChessPiece king, ChessBoard board) {
		ChessBoard testBoard = new ChessBoard();
		board.clone(testBoard);
		ArrayList<ChessPiece> playerPieces = getNormalPieces(king, board);

		if (!king.getMoves(king.getPosition()).isEmpty()) {
			return true;
		}

		for (ChessPiece piece : playerPieces) {
			if(!piece.getMoves(king.getPosition()).isEmpty()) {
				return true;
			}
		}		

		return false;
	}

	private static ArrayList<ChessPiece> getNormalPieces(ChessPiece playerKing, ChessBoard board) {
		ArrayList<ChessPiece> playerPieces = new ArrayList<>();
		for (Piece[] row: board.getPieces()) {
			for (Piece piece : row) {
				if (piece != null) {
					ChessPiece chessPiece = (ChessPiece) piece;
					if (chessPiece.getColor() == playerKing.getColor()) {
						playerPieces.add(chessPiece);
					}
				}
			}
		}
		return playerPieces;
	}

	private boolean blackInCheck() {
		King blackKing = blackPlayer.getKing();
		if (ChessPiece.threatenedPosition(blackKing.getPosition(), blackKing.getColor(), board)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean whiteInCheck() {
		King whiteKing = whitePlayer.getKing();
		if (ChessPiece.threatenedPosition(whiteKing.getPosition(), whiteKing.getColor(), board)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean kingInCheck(ChessPiece king, ChessBoard board) {
		if (ChessPiece.threatenedPosition(king.getPosition(), king.getColor(), board)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkMate() {
		if (!(canProtectKing(whitePlayer)) || !(canProtectKing(blackPlayer))) {
			return true;
		} else if ((turn == Turn.WHITETURN && blackInCheck()) || (turn == Turn.BLACKTURN && whiteInCheck())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkMate(ChessPiece whiteKing, ChessPiece blackKing, ChessBoard board, Color colorTurn) {
		if (!(canProtectKing(whiteKing, board)) || !(canProtectKing(blackKing, board))) {
			return true;
		} else if ((colorTurn == Color.WHITE && kingInCheck(blackKing, board)) || (colorTurn == Color.BLACK && kingInCheck(whiteKing, board))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean draw() {
		if (turn == Turn.WHITETURN && !whitePlayer.hasPossibleMoves() && !whiteInCheck()) {
			return true;
		} else if (turn == Turn.BLACKTURN && !blackPlayer.hasPossibleMoves() && !blackInCheck()) {
			return true;
		} else if ( !(whitePlayer.hasPiecesToWin()) && !(blackPlayer.hasPiecesToWin()) ) {
			return true;
		} else {
			return false;
		}
	}

	private void startMatch() {
		blackPlayer = new ChessPlayer(new King(board, new Position(0, 4), Color.BLACK));
		whitePlayer = new ChessPlayer(new King(board, new Position(7, 4), Color.WHITE));
		
		for (int i = 0; i < 8; i++) { // Pawns			
			blackPlayer.addPiece(new Pawn(board, new Position(1, i), Color.BLACK), 7 + i);			
			whitePlayer.addPiece(new Pawn(board, new Position(6, i), Color.WHITE), 7 + i);
			
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
		for (ChessPiece piece : blackPlayer.getNormalPieces()) {
			board.putInPosition(piece, piece.getPosition());
		}

		board.putInPosition(whitePlayer.getKing(), whitePlayer.getKing().getPosition());
		for (ChessPiece piece : whitePlayer.getNormalPieces()) {
			board.putInPosition(piece, piece.getPosition());
		}
	}
}
