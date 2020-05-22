package chess;

import board.Board;
import board.BoardException;
import board.Piece;
import board.Position;
import chess.pieces.Color;

import java.util.ArrayList;

public class ChessBoard extends Board{

	private ChessPiece whiteKing = null;
	private ChessPiece blackKing = null;

	public ChessBoard() {
		super(8, 8);
	}

	public ChessPiece getWhiteKing() {
		return whiteKing;
	}

	public ChessPiece getBlackKing() {
		return blackKing;
	}

	public void clone(ChessBoard board) {
		int i = 0;
		for(Piece[] line: pieces) {
			board.pieces[i] = line.clone();
			i++;
		}
	}	
	
	public void doChessMove(ChessPiece piece, Position position) {		
		putInPosition(piece, position);
	}
	
	public void doChessMove(ChessPiece piece, int row, int column) {		
		putInPosition(piece, row, column);
	}

	public void putInPosition(ChessPiece piece, Position position) {
		if (positionInBoard(position)) {
			if ( piece.getLetter() == 'K' ) {
				if (piece.getColor() == Color.WHITE) {
					if (whiteKing != null) {
						throw new ChessException("There is already a white king on the board");
					} else {
						whiteKing = piece;
					}
				} else if (piece.getColor() == Color.BLACK) {
					if (blackKing != null) {
						throw new ChessException("There is already a black king on the board");
					} else {
						blackKing = piece;
					}
				}
			}
			pieces[position.getRow()][position.getColumn()] = piece;
			piece.setPosition(position);
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}

	public void putInPosition(ChessPiece piece, int row, int column) {
		if (positionInBoard(row, column)) {
			if ( piece.getLetter() == 'K' ) {
				if (piece.getColor() == Color.WHITE) {
					if (whiteKing != null) {
						throw new ChessException("There is already a white king on the board");
					} else {
						whiteKing = piece;
					}
				} else if (piece.getColor() == Color.BLACK) {
					if (blackKing != null) {
						throw new ChessException("There is already a black king on the board");
					} else {
						blackKing = piece;
					}
				}
			}
			pieces[row][column] = piece;
			piece.setPosition(row, column);
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}


	public void nullPosition(Position position) {
		if (positionInBoard(position)) {
			Piece piece = pieces[position.getRow()][position.getColumn()];
			if (piece != null) {
				char pieceLetter = ((ChessPiece) piece).getLetter();
				if (pieceLetter == 'K') {
					blackKing = null;
				} else if (pieceLetter == 'k') {
					whiteKing = null;
				}
				pieces[position.getRow()][position.getColumn()] = null;
			} else {
				throw new BoardException("This position is already null");
			}
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}

	public void nullPosition(int row, int column) {
		if (positionInBoard(row, column)) {
			Piece piece = pieces[row][column];
			if (piece != null) {
				char pieceLetter = ((ChessPiece) piece).getLetter();
				if (pieceLetter == 'K') {
					blackKing = null;
				} else if (pieceLetter == 'k') {
					whiteKing = null;
				}
				pieces[row][column] = null;
			} else {
				throw new BoardException("This position is already null");
			}
		} else {
			throw new BoardException("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)\n");
		}
	}
}
