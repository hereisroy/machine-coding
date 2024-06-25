package chess.piece;

import java.util.Objects;

public class Position {
	
	private int x, y;

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==this) return true;
		if(obj==null || obj.getClass()!=getClass()) return false;
		Position pos = (Position) obj;
		return (pos.getX()==this.x && pos.getY()==this.y);
	}
}
