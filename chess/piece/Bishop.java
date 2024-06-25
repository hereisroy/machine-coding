package chess.piece;

import chess.board.Board;

public class Bishop extends Piece {

	public Bishop(PieceColor color) {
		super(color);
	}

	@Override
	public void calcMoves(Board board) {
		int x=pos.getX(), y=pos.getY();
		int boardSize[]=board.getSize();
		int m = boardSize[0], n = boardSize[1];
		for(int i=x-1, j=y+1; i>=0 && j<n; i--, j++) {
			if(board.getPiece(i, j)!=null) {
				if(board.getPiece(i, j).color==this.color) break;
				validMoves.add(new Position(i, j));
				break;
			}
			validMoves.add(new Position(i, j));
		}
		for(int i=x+1, j=y+1; i<m && j<n; i++, j++) {
			if(board.getPiece(i, j)!=null) {
				if(board.getPiece(i, j).color==this.color) break;
				validMoves.add(new Position(i, j));
				break;
			}
			validMoves.add(new Position(i, j));
		}
		for(int i=x+1, j=y-1; i<m && j>=0; i++, j--) {
			if(board.getPiece(i, j)!=null) {
				if(board.getPiece(i, j).color==this.color) break;
				validMoves.add(new Position(i, j));
				break;
			}
			validMoves.add(new Position(i, j));
		}
		for(int i=x-1, j=y-1; i>=0 && j>=0; i--, j--) {
			if(board.getPiece(i, j)!=null) {
				if(board.getPiece(i, j).color==this.color) break;
				validMoves.add(new Position(i, j));
				break;
			}
			validMoves.add(new Position(i, j));
		}
	}
	
	@Override
	public String toString() {
		if(this.color==PieceColor.WHITE) return "WB";
		return "BB";
	}
}
