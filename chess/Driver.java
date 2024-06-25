package chess;

import java.util.Scanner;

import chess.board.Board;
import chess.board.BoardHolder;
import chess.board.BoardInitUtil;
import chess.cli.Command;

public class Driver {
	
	public static void main(String args[]) {
		
		Board board = new Board(8, 8);
		BoardInitUtil.initEightByEightBoard(board);
		BoardHolder.setBoard(board);
		
		System.out.println("Welcome. Lets Play chess.");
		String line;
		Scanner inp = new Scanner(System.in);
		Command cmd;
		
		while(true) {
			System.out.print(">");
			line = inp.nextLine();
			if(line.equalsIgnoreCase("exit")) {
				System.out.println("Thanks for playing. Bye.");
				inp.close();
				break;
			}
			cmd = new Command(line);
			cmd.execute();
		}
		
	}

}
