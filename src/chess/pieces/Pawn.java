package chess.pieces;

import java.util.ArrayList;

import board.Board;
import board.Position;
import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessUI;

public class Pawn extends ChessPiece {

	public Pawn(ChessBoard board, Position position, Color color) {
		super(board, position, color);
	}

	@Override
	public ArrayList<Position> getMoves() {
		return getMoves(position, board, color);
	}

	public static ArrayList<Position> getMoves(Position position, Board board, Color color) {
		ArrayList<Position> moves = new ArrayList<>();
		ChessPiece possiblePiece;
		boolean canStepFoward = (color == Color.BLACK && position.getRow() < 7) || (color == Color.WHITE && position.getRow() > 0);
		boolean firstMove = (color == Color.BLACK && position.getRow() == 1) || (color == Color.WHITE && position.getRow() == 6);

		int stepFoward;
		if (color == Color.BLACK) {
			stepFoward = +1;
		} else {
			stepFoward = -1;
		}

		if (canStepFoward) {

			if (firstMove) {
				possiblePiece = (ChessPiece) board.seePosition(position.getRow() + (2 * stepFoward), position.getColumn());
				if (possiblePiece == null) {
					moves.add(new Position(position.getRow() + (2 * stepFoward), position.getColumn()));
				}
			}

			possiblePiece = (ChessPiece) board.seePosition(position.getRow() + stepFoward, position.getColumn());
			if (possiblePiece == null) {
				moves.add(new Position(position.getRow() + stepFoward, position.getColumn()));
			}

			if (position.getColumn() < 7) {
				possiblePiece = (ChessPiece) board.seePosition(position.getRow() + stepFoward, position.getColumn() + 1);
				if (possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward, position.getColumn() + 1));
				}
			}

			if (position.getColumn() > 0) {
				possiblePiece = (ChessPiece) board.seePosition(position.getRow() + stepFoward, position.getColumn() - 1);
				if (possiblePiece != null && possiblePiece.getColor() != color) {
					moves.add(new Position(position.getRow() + stepFoward, position.getColumn() - 1));
				}
			}

		}

		return moves;
	}

	@Override
	public ChessMove getProtectMove(Position kingPosition) {
		ChessMove protectionMove = null;
		ChessPiece possiblePiece;
		boolean canStepFoward = (color == Color.BLACK && position.getRow() < 7) || (color == Color.WHITE && position.getRow() > 0);
		boolean firstMove = (color == Color.BLACK && position.getRow() == 1) || (color == Color.WHITE && position.getRow() == 6);

		int stepFoward;
		if (color == Color.BLACK) {
			stepFoward = +1;
		} else {
			stepFoward = -1;
		}

		if (canStepFoward) {
			int targetRow;
			int targetColumn;
			( (ChessBoard)board ).nullPosition(position);

			if (firstMove) {
				targetRow = position.getRow() + (2 * stepFoward);
				targetColumn = position.getColumn();
				possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
				if (possiblePiece == null) {
					ChessMove protection = testProtection(targetRow, targetColumn, kingPosition);
					if (protection != null) {
						return protection;
					}
				}
			}

			targetRow = position.getRow() + stepFoward;
			targetColumn = position.getColumn();
			possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
			if (possiblePiece == null) {
				ChessMove protection = testProtection(targetRow, targetColumn, kingPosition);
				if (protection != null) {
					return protection;
				}
			}

			if (position.getColumn() < 7) {
				targetRow = position.getRow() + stepFoward;
				targetColumn = position.getColumn() + 1;
				possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
				if (possiblePiece != null && possiblePiece.getColor() != color) {
					((ChessBoard) board).putInPosition(new Pawn((ChessBoard)board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
					boolean canProtectKing = !(ChessPiece.threatenedPosition(kingPosition, color, board));
					((ChessBoard) board).nullPosition(targetRow, targetColumn);
					((ChessBoard) board).putInPosition(possiblePiece, possiblePiece.getPosition());
					if (canProtectKing) {
						((ChessBoard) board).putInPosition(this, position);
						return new ChessMove(position.getRow(), position.getColumn(), targetRow, targetColumn);
					}
				}
			}

			if (position.getColumn() > 0) {
				targetRow = position.getRow() + stepFoward;
				targetColumn = position.getColumn() - 1;
				possiblePiece = (ChessPiece) board.seePosition(targetRow, targetColumn);
				if (possiblePiece != null && possiblePiece.getColor() != color) {
					((ChessBoard) board).putInPosition(new Pawn((ChessBoard)board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
					boolean canProtectKing = !(ChessPiece.threatenedPosition(kingPosition, color, board));
					((ChessBoard) board).nullPosition(targetRow, targetColumn);
					((ChessBoard) board).putInPosition(possiblePiece, possiblePiece.getPosition());
					if (canProtectKing) {
						((ChessBoard) board).putInPosition(this, position);
						return new ChessMove(position.getRow(), position.getColumn(), targetRow, targetColumn);
					}
				}
			}

			((ChessBoard) board).putInPosition(this, position);
		}

		return null;
	}

	private ChessMove testProtection(int targetRow, int targetColumn, Position kingPosition) {
		((ChessBoard) board).putInPosition(new Pawn((ChessBoard)board, new Position(targetRow, targetColumn), color), targetRow, targetColumn);
		boolean canProtectKing = !(ChessPiece.threatenedPosition(kingPosition, color, board));
		((ChessBoard) board).nullPosition(targetRow, targetColumn);
		if (canProtectKing) {
			((ChessBoard) board).putInPosition(this, position);
			return new ChessMove(position.getRow(), position.getColumn(), targetRow, targetColumn);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		if (color == Color.BLACK) {
			return "   P   ";
		} else {
			return "   p   ";
		}
	}

	@Override
	public char getLetter() {
		return toString().trim().charAt(0);
	}

}
