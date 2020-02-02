package chess.pieces;

import java.util.ArrayList;

import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class Queen extends ChessPiece{
	
	public Queen(ChessBoard board, Color color) {
		super(board, color);		
	}
	
	@Override
	public ArrayList<Position> getMoves() {
		ArrayList<Position> bishopMoves = Bishop.getMoves(position, board, color);
		ArrayList<Position> rookMoves = Rook.getMoves(position, board, color);
		ArrayList<Position> moves = new ArrayList<Position>();
		moves.addAll(bishopMoves);
		moves.addAll(rookMoves);
		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "Q";
		}else {
			return "q'";
		}
	}
}
