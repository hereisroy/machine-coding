package chess.piece;

import chess.board.Board;

public class Pawn extends Piece {
	
	boolean firstMove = true;

	public Pawn(PieceColor color) {
		super(color);
	}
	
	@Override
	public void calcMoves(Board board) {
		int x=pos.getX(), y=pos.getY();
		int boardSize[]=board.getSize();
		int m = boardSize[0], n = boardSize[1];
		if(this.color==PieceColor.WHITE && x-1>=0) {
			if(	board.getPiece(x-1, y)==null) {
				validMoves.add(new Position(x-1, y));
				if(firstMove && x-2>=0 && board.getPiece(x-2, y)==null)
					validMoves.add(new Position(x-2, y));
			}	
			if(	y-1>=0 && 
				board.getPiece(x-1, y-1)!=null && 
				board.getPiece(x-1, y-1).getColor()!=this.color) 
				validMoves.add(new Position(x-1, y-1));
			if(	y+1<n && 
				board.getPiece(x-1, y+1)!=null && 
				board.getPiece(x-1, y+1).getColor()!=this.color) 
				validMoves.add(new Position(x-1, y+1));

		} else if(this.color==PieceColor.BLACK && x+1<m) {
			if( board.getPiece(x+1, y)==null) {
				validMoves.add(new Position(x+1, y));
				if(firstMove && x+2>=0 && board.getPiece(x+2, y)==null)
					validMoves.add(new Position(x+2, y));
			}	
			if(	y-1>=0 && 
				board.getPiece(x+1, y-1)!=null && 
				board.getPiece(x+1, y-1).getColor()!=this.color) 
				validMoves.add(new Position(x+1, y-1));
			if(	y+1<n && 
				board.getPiece(x+1, y+1)!=null && 
				board.getPiece(x+1, y+1).getColor()!=this.color) 
				validMoves.add(new Position(x+1, y+1));
		}
	}
	
	@Override
	public String toString() {
		if(this.color==PieceColor.WHITE) return "WP";
		return "BP";
	}

}
