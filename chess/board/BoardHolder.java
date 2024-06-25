package chess.board;

public class BoardHolder {

	private static Board board;

	private BoardHolder() {}

	public static void setBoard(Board newBoard) {
		board = newBoard;
	}

	public static Board getBoard() {
		return board;
	}

}
