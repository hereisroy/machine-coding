package chess.piece;

import chess.board.Board;

public class Rook extends Piece {

	public Rook(PieceColor color) {
		super(color);
	}
	
	@Override
	public void calcMoves(Board board) {
		int x=pos.getX(), y=pos.getY();
		int boardSize[]=board.getSize();
		int m = boardSize[0], n = boardSize[1];
		for(int i=x-1; i>=0; i--) {
			if(board.getPiece(i, y)!=null) {
				if(board.getPiece(i, y).color==this.color) break;
				validMoves.add(new Position(i, y));
				break;
			}
			validMoves.add(new Position(i, y));
		}
		for(int i=x+1; i<m; i++) {
			if(board.getPiece(i, y)!=null) {
				if(board.getPiece(i, y).color==this.color) break;
				validMoves.add(new Position(i, y));
				break;
			}
			validMoves.add(new Position(i, y));
		}
		for(int j=y-1; j>=0; j--) {
			if(board.getPiece(x, j)!=null) {
				if(board.getPiece(x, j).color==this.color) break;
				validMoves.add(new Position(x, j));
				break;
			}
			validMoves.add(new Position(x, j));
		}
		for(int j=y+1; j<n; j++) {
			if(board.getPiece(x, j)!=null) {
				if(board.getPiece(x, j).color==this.color) break;
				validMoves.add(new Position(x, j));
				break;
			}
			validMoves.add(new Position(x, j));
		}
	}
	
	
	@Override
	public String toString() {
		if(this.color==PieceColor.WHITE) return "WR";
		return "BR";
	}
}
