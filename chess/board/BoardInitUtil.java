package chess.board;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.PieceColor;
import chess.piece.Queen;
import chess.piece.Rook;

public class BoardInitUtil {

	public static void initEightByEightBoard(Board board) {
		for(int j=0;j<8;j++) 
			board.setPiece(new Pawn(PieceColor.BLACK), 1, j);
		board.setPiece(new Rook(PieceColor.BLACK), 0, 0);
		board.setPiece(new Rook(PieceColor.BLACK), 0, 7);
		board.setPiece(new Knight(PieceColor.BLACK), 0, 1);
		board.setPiece(new Knight(PieceColor.BLACK), 0, 6);
		board.setPiece(new Bishop(PieceColor.BLACK), 0, 2);
		board.setPiece(new Bishop(PieceColor.BLACK), 0, 5);
		board.setPiece(new Queen(PieceColor.BLACK), 0, 3);
		board.setPiece(new King(PieceColor.BLACK), 0, 4);
		for(int j=0;j<8;j++) 
			board.setPiece(new Pawn(PieceColor.WHITE), 6, j);
		board.setPiece(new Rook(PieceColor.WHITE), 7, 0);
		board.setPiece(new Rook(PieceColor.WHITE), 7, 7);
		board.setPiece(new Knight(PieceColor.WHITE), 7, 1);
		board.setPiece(new Knight(PieceColor.WHITE), 7, 6);
		board.setPiece(new Bishop(PieceColor.WHITE), 7, 2);
		board.setPiece(new Bishop(PieceColor.WHITE), 7, 5);
		board.setPiece(new Queen(PieceColor.WHITE), 7, 3);
		board.setPiece(new King(PieceColor.WHITE), 7, 4);
		
	}

}
