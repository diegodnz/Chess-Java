package chess;

import java.util.ArrayList;
import java.util.Scanner;

import application.ClearScreen;
import board.BoardException;
import board.Piece;
import board.Position;

public class ChessUI {

	/*
	public static void printBoard(ChessBoard board) {
		System.out.println("    a  b  c  d  e  f  g  h \n");
		for(int row = 0; row < board.getNumRows(); row++) {			
			System.out.print(8-row + "  ");
			
			for(int column = 0; column < board.getNumColumns(); column++) {
				Piece piece = board.seePosition(row, column);
				if(piece == null) {
					System.out.print(" - ");
				}else {
					System.out.print(piece);				
				}
			}
			
			System.out.println("  " + (8-row));
		}		
		System.out.println("\n    a  b  c  d  e  f  g  h ");
	}
	 */

	public static void printBoard(ChessBoard board) {
		System.out.println("         a      b      c      d      e      f      g      h \n\n");
		for(int row = 0; row < board.getNumRows(); row++) {
			System.out.print(8-row + "     ");

			for(int column = 0; column < board.getNumColumns(); column++) {
				Piece piece = board.seePosition(row, column);
				if(piece == null) {
					System.out.print("   -   ");
				} else {
					System.out.print(piece);
				}
			}

			System.out.println("      " + (8-row) + "\n\n");
		}
		System.out.println("\n         a      b      c      d      e      f      g      h \n");
	}
	
	public static void printCapturedPieces(ChessMatch match) {
		System.out.print("\nWhite lost pieces: ");
		System.out.println(match.getWhitePlayer().getLostPieces());
		System.out.print("Black lost pieces: ");
		System.out.println(match.getBlackPlayer().getLostPieces());
	}
	
	private static Position getEntry(Scanner sc) throws ChessException {
		String entry = sc.next();
		int row = Integer.valueOf(entry.substring(1));
		char column = entry.charAt(0);
		ChessPosition chessPosition = new ChessPosition(row, column);
		return chessPosition.toBoardPosition();
	}

	private static ChessPlayer getTurnPlayer(ChessMatch match) {
		ChessPlayer player;
		if (match.getTurn() == Turn.WHITETURN) {
			player = match.getWhitePlayer();
		} else {
			player = match.getBlackPlayer();
		}
		return player;
	}
	
	public static Position readMove(ChessMatch match, Scanner sc, String type, boolean check, Position sourcePosition, boolean printBoard) {
		try {
			Position position;
			if (type.matches("Source")) {
				System.out.print("\nEnter the source position: ");
				position = getEntry(sc);
				ChessPiece sourcePiece = match.validPiece(position);

				ChessPlayer player = getTurnPlayer(match);
				if (!check) {
					ArrayList<Position> possibleMoves = sourcePiece.getMoves();
					if (possibleMoves.isEmpty()) {
						throw new ChessException("There are no movements to do with this piece.");
					}
				} else if (sourcePiece.getProtectMove(player.getKing().getPosition()) == null) {
					throw new ChessException("This piece can't protect the king!!");
				}
			} else { //type = Target
				ChessPiece sourcePiece = match.validPiece(sourcePosition);
				ArrayList<Position> possibleMoves;
				ChessPlayer player = getTurnPlayer(match);
				if (!check) {
					possibleMoves = sourcePiece.getMoves();
				} else {
					possibleMoves = new ArrayList<>();
					possibleMoves.add(sourcePiece.getProtectMove(player.getKing().getPosition()).getTarget());
				}

				if (printBoard) {
					//ClearScreen.clear();
					ChessBoard movementBoard = new ChessBoard();
					for (int i = 0; i < 8; i++) {
						movementBoard.setPiecesRow(match.getBoard().getPiecesRow(i).clone(), i);
					}
					for (Position move : possibleMoves) {
						Piece possiblePiece = movementBoard.seePosition(move);
						if (possiblePiece == null) {
							movementBoard.getPieces()[move.getRow()][move.getColumn()] = new Piece(movementBoard, "   *   ");
						} else {
							movementBoard.getPieces()[move.getRow()][move.getColumn()]
									= new Piece(movementBoard, "  (" + ((ChessPiece) possiblePiece).getLetter() + ")  ");
						}
					}
					printBoard(movementBoard);
				}

				System.out.print("\nPossible movements: ");
				for (Position move : possibleMoves) {
					System.out.print(ChessPosition.toChessPosition(move) + " ");
				}

				System.out.print("\nEnter the target position: ");
				position = getEntry(sc);

				if (!possibleMoves.contains(position)) {
					throw new ChessException("This is not a valid move with the selected piece");
				}
			}
			return position;

		} catch (NumberFormatException e) {
			System.out.println("Invalid position. Valid positions -> (a1, a2, ..., h7, h8)");
			return readMove(match, sc, type, check, sourcePosition, false);
		} catch (BoardException e) {
			System.out.println(e.getMessage());
			return readMove(match, sc, type, check, sourcePosition, false);
		} catch (ChessException e) {
			System.out.println(e.getMessage());
			return readMove(match, sc, type, check, sourcePosition, false);
		}
	}

	public static ChessMove play(ChessMatch match, Scanner sc, boolean check, boolean printUI) {
		if (printUI) {
			if (check) {
				System.out.println("\nCHECK!!");
			}
			if (match.getTurn() == Turn.WHITETURN) {
				System.out.printf("\nWhite player turn!!");
			} else {
				System.out.printf("\nBlack player turn!!");
			}
		}

		if (match.hasBot() && match.getTurn() == match.getBotTurn()) {
			//if (true) {
			if (match.getTurn() == Turn.WHITETURN) {
				if (check) {
					return match.getWhitePlayer().protectRandomMove();
				} else {
					return match.getWhitePlayer().randomMove();
				}
			} else {
				if (check) {
					return match.getBlackPlayer().protectRandomMove();
				} else {
					return match.getBlackPlayer().randomMove();
				}
			}
		} else {
			Position sourcePosition = readMove(match, sc, "Source", check, null, false);
			System.out.println();
			Position targetPosition = readMove(match, sc, "Target", check, sourcePosition, true);
			return new ChessMove(sourcePosition, targetPosition);
		}

	}
}
