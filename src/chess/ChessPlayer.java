package chess;

import java.util.ArrayList;
import java.util.Random;

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

	public ChessMove aleatoryMove() {
		ArrayList<ChessPiece> playablePieces = new ArrayList<ChessPiece>();
		Random gen = new Random();

		for (ChessPiece piece : normalPieces) {
			if (piece != null && !piece.getMoves().isEmpty()) {
				playablePieces.add(piece);
			}
		}

		if (!king.getMoves().isEmpty()) {
			playablePieces.add(king);
		}

		System.out.println(playablePieces);

		int indexPiece = gen.nextInt(playablePieces.size());
		ChessPiece playPiece = playablePieces.get(indexPiece);

		int indexMove = gen.nextInt(playPiece.getMoves().size());
		Position targetMove = playPiece.getMoves().get(indexMove);

		return new ChessMove(playPiece.getPosition(), targetMove);

	}

}
