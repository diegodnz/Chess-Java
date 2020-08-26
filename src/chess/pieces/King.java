package chess.pieces;

import java.util.ArrayList;
import board.Position;
import chess.ChessBoard;
import chess.ChessPiece;

public class King extends ChessPiece {

	private boolean wasMoved; //Castle
	
	public King(ChessBoard board, Position position, Color color) {		
		super(board, position, color);	
		wasMoved = false;	
	}

	public boolean wasMoved() {
		return wasMoved;
	}

	public void setMoved() {
		wasMoved = true;
	}

	public void unsetMoved() {
		wasMoved = false;
	}

	@Override
	public ArrayList<Position> getMoves(Position kingPosition) {
		return getMoves();
	}

	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		ChessPiece possiblePiece;
		Position testPosition;
		board.nullPosition(position);

		//Left castle
		if (!wasMoved && position.getColumn() == 4) {
			ChessPiece leftRook = (ChessPiece)board.seePosition(position.getRow(), 0);
			if (leftRook instanceof Rook) {
				if (!((Rook)leftRook).wasMoved()) {
					boolean kingInCheck = ChessPiece.threatenedPosition(position, color, board);
					Position oneStep = new Position(position.getRow(), position.getColumn()-1);
					Position twoSteps = new Position(position.getRow(), position.getColumn()-2);
					Position threeSteps = new Position(position.getRow(), position.getColumn()-3);
					boolean oneStepInCheck = ChessPiece.threatenedPosition(oneStep, color, board);
					boolean twoStepsInCheck = ChessPiece.threatenedPosition(twoSteps, color, board);
					boolean noPieceLeft = board.seePosition(oneStep) == null;
					boolean noPieceLeft2 = board.seePosition(twoSteps) == null;
					boolean noPieceLeft3 = board.seePosition(threeSteps) == null;
					if (!kingInCheck && !oneStepInCheck && !twoStepsInCheck && noPieceLeft && noPieceLeft2 && noPieceLeft3) {
						moves.add(twoSteps);
					}
				}
			}
		}

		//Right castle
		if (!wasMoved && position.getColumn() == 4) {
			ChessPiece rightRook = (ChessPiece)board.seePosition(position.getRow(), 7);
			if (rightRook instanceof Rook) {
				if (!((Rook)rightRook).wasMoved()) {
					boolean kingInCheck = ChessPiece.threatenedPosition(position, color, board);
					Position oneStep = new Position(position.getRow(), position.getColumn()+1);
					Position twoSteps = new Position(position.getRow(), position.getColumn()+2);
					boolean oneStepInCheck = ChessPiece.threatenedPosition(oneStep, color, board);
					boolean twoStepsInCheck = ChessPiece.threatenedPosition(twoSteps, color, board);
					boolean noPieceRight = board.seePosition(oneStep) == null;
					boolean noPieceRight2 = board.seePosition(twoSteps) == null;
					if (!kingInCheck && !oneStepInCheck && !twoStepsInCheck && noPieceRight && noPieceRight2) {
						moves.add(twoSteps);
					}
				}
			}
		}
			

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				testPosition = new Position(position.getRow() + i, position.getColumn() + j);
				if (!(testPosition.equals(position)) && board.positionInBoard(testPosition)) {
					possiblePiece = (ChessPiece) board.seePosition(testPosition.getRow(), testPosition.getColumn());
					if (!(threatenedPosition(testPosition, color, board)) && (possiblePiece == null || possiblePiece.getColor() != color)) {
						moves.add(testPosition);
					}
				}
			}
		}

		board.putInPosition(this, position);

		return moves;
	}
	
	@Override
	public String toString() {
		if(color == Color.BLACK) {
			return "   K   ";
		}else {
			return "  (k)  ";
		}
	}

	@Override
	public char getLetter() {
		if (color == Color.BLACK) {
			return toString().trim().charAt(0);
		} else {
			return toString().trim().charAt(1);
		}
	}
}
