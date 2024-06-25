package chess.piece;

import chess.board.Board;

public class Knight extends Piece {

	public Knight(PieceColor color) {
		super(color);
	}

	@Override
	public void calcMoves(Board board) {
		int x=pos.getX(), y=pos.getY();
		int boardSize[]=board.getSize();
		int m = boardSize[0], n = boardSize[1];
		int i,j;
		int[][] paths = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {1, -2}, {-1, -2}, {1, 2}, {-1, 2}};
		for(int[] path : paths) {
			i = x+path[0];
			j = y+path[1];
			if(i>=0 && i<m && j>=0 && j<n && 
					(board.getPiece(i, j)==null || board.getPiece(i, j).color!=this.color))
				validMoves.add(new Position(i, j));
		}
	}
	
	@Override
	public String toString() {
		if(this.color==PieceColor.WHITE) return "WN";
		return "BN";
	}

}
