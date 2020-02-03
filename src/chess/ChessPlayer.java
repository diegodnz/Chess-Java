package chess;

import board.Piece;
import chess.pieces.Color;
import chess.pieces.King;

public class ChessPlayer {

	private King king;
	private Color color;
	private ChessPiece[] normalPieces;
	
	public ChessPlayer(King king) {
		this.king = king;
		color = king.getColor();
		normalPieces = new ChessPiece[15];
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
	
	public void addPiece(ChessPiece piece, int index) {
		normalPieces[index] = piece;
	}

}
