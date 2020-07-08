package chess;

import java.util.ArrayList;
import java.util.Random;

import GameTree.GameTree;
import GameTree.Game;
import board.Position;
import chess.pieces.Color;
import chess.pieces.King;

public class ChessPlayer {

	private King king;
	private Color color;
	private ChessPiece[] normalPieces;
	private ArrayList<ChessPiece> lostPieces;

	public ChessPlayer(King king) {
		this.king = king;
		color = king.getColor();
		normalPieces = new ChessPiece[15];
		lostPieces = new ArrayList<ChessPiece>();
	}

	public King getKing() {
		return king;
	}

	public Color getColor() {
		return color;
	}

	public ChessPiece[] getNormalPieces() {
		return normalPieces;
	}

	public ArrayList<ChessPiece> getLostPieces() {
		return lostPieces;
	}

	public boolean capturedPiece(ChessPiece piece) {
		if (lostPieces.contains(piece)) {
			return true;
		} else {
			return false;
		}
	}

	public void addLostPiece(ChessPiece piece) {
		for (int i = 0; i < normalPieces.length; i++) {
			if (normalPieces[i] == piece) {
				normalPieces[i] = null;
				break;
			}
		}
		lostPieces.add(piece);
	}

	public void addPiece(ChessPiece piece, int index) {
		normalPieces[index] = piece;
	}

	public ChessMove randomMove() {
		ArrayList<ChessPiece> playablePieces = new ArrayList<ChessPiece>();
		Random gen = new Random();

		for (ChessPiece piece : normalPieces) {
			if (piece != null && !(piece.getMoves(king.getPosition()).isEmpty())) {
				playablePieces.add(piece);
			}
		}

		if (!king.getMoves().isEmpty()) {
			playablePieces.add(king);
		}

		int indexPiece = gen.nextInt(playablePieces.size());
		ChessPiece playPiece = playablePieces.get(indexPiece);

		int indexMove = gen.nextInt(playPiece.getMoves(king.getPosition()).size());
		Position targetMove = playPiece.getMoves(king.getPosition()).get(indexMove);

		return new ChessMove(playPiece.getPosition(), targetMove);
	}

	public ChessMove gameTreeMove(String board) {
		GameTree gameTree = new GameTree(Game.CHESS);
		String movementBoard = gameTree.searchBestMove(board, 4, this.color);
		return getMove(board, movementBoard);
	}

	private ChessMove getMove(String mainBoard, String moveBoard) {
		Position sourcePosition = null;
		Position targetPosition = null;
		for (int i = 0; i < mainBoard.length(); i++) {
			char mainBoardChar = mainBoard.charAt(i);
			char moveBoardChar = moveBoard.charAt(i);
			if (mainBoardChar != moveBoardChar) {
				if (moveBoardChar == '0') {
					sourcePosition = new Position(i/8, i - ( 8*(i/8) ));
				} else {
					targetPosition = new Position(i/8, i - ( 8*(i/8) ));
				}
			}
		}
		return new ChessMove(sourcePosition, targetPosition);
	}

}
