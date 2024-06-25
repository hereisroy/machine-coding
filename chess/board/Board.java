package chess.board;

import chess.piece.Piece;
import chess.piece.PieceColor;
import chess.piece.Position;

public class Board {

	private int m, n;
	private Piece[][] pieces;
	private PieceColor currPlayer = PieceColor.WHITE;

	public Board(int m, int n) {
		this.m = m;
		this.n = n;
		pieces = new Piece[m][n];
	}

	public int[] getSize() {
		return new int[] {m, n};
	}

	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}

	void setPiece(Piece piece, int x, int y) {
		piece.setPos(x, y);
		pieces[x][y] = piece;
	}

	public boolean movePiece(int[] pos, int[] target) {
		if(pos[0]<0 || pos[0]>=m || pos[1]<0 || pos[1]>=n) return false;
		Piece piece = pieces[pos[0]][pos[1]];
		if(piece==null) return false;
		if(piece.getColor()!=currPlayer) return false;
		Position targetPos = new Position(target[0], target[1]);
		boolean status = piece.move(this, targetPos);
		if(!status) return false;
		pieces[pos[0]][pos[1]] = null;
		pieces[target[0]][target[1]] = piece;
		if(currPlayer==PieceColor.WHITE) currPlayer = PieceColor.BLACK;
		else currPlayer = PieceColor.WHITE;
		return true;
	}

	public void displayBoard() {
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(getPiece(i, j)==null) {
					System.out.print("-- ");
				} else {
					System.out.print(getPiece(i, j)+" ");
				}
			}
			System.out.println();
		}
	}

}
