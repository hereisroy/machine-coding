package chess.cli;

import chess.board.Board;
import chess.board.BoardHolder;

public class Command {
	
	int[] pos;
	int[] target;
	
	
	public Command(String line) {
		Board board = BoardHolder.getBoard();
		int[] size = board.getSize();
		String[] tokens = line.split(" ");
		int x1, y1, x2, y2;
		x1 = size[0]-(tokens[0].charAt(1)-48);
		y1 = tokens[0].charAt(0)-97;
		x2 = size[0]-(tokens[1].charAt(1)-48);
		y2 = tokens[1].charAt(0)-97;
		pos = new int[] {x1, y1};
		target = new int[] {x2, y2};
		
		//System.out.printf("%s->(%d, %d) %s->(%d, %d)\n", tokens[0], x1, y1, tokens[1], x2, y2);
	}
	
	public void execute() {
		Board board = BoardHolder.getBoard();
		boolean status = board.movePiece(pos, target);
		if(status) board.displayBoard();
		else System.out.println("Invalid Move");
	}

}
