package com.goclient.board;

public abstract class GoStone {
	private int x;
	private int y;

	public GoStone(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void draw();

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
