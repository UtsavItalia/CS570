package Maze;

public class PairInt {
	private int x;
	private int y;

	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object p) {
		PairInt objOfP = (PairInt) p;
		if (objOfP.getX() == x && objOfP.getY() == y) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String str = "(" + x + "," + y + ")";
		return str;
	}

	public PairInt copy() {
		PairInt objOfP2 = new PairInt(x, y);
		return objOfP2;
	}

}
