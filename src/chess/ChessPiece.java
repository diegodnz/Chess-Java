package chess;

import java.util.ArrayList;

import board.Board;
import board.Piece;
import board.Position;
import chess.pieces.Color;

public abstract class ChessPiece extends Piece{
	
	protected Color color;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public abstract ArrayList<Position> getMoves();

}
