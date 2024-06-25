package chess.piece;

import java.util.HashSet;
import java.util.Set;

import chess.board.Board;

public abstract class Piece {
	
	Position pos;
	PieceColor color;
	Set<Position> validMoves;
	
	public Piece(PieceColor color) {
		this.color = color;
		validMoves = new HashSet<>();
	}
	
	public void setPos(int x, int y) {
		this.pos = new Position(x, y);
	}
	
	public PieceColor getColor() {
		return color;
	};

	public boolean move(Board board, Position target) {
		calcMoves(board);
		if(!validMoves.contains(target)) return false;
		pos = target;
		validMoves.clear();
		return true;
	}
	
	public abstract void calcMoves(Board board);
	
}
