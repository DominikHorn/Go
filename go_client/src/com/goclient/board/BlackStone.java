package com.goclient.board;

import com.goclient.main.*;

public class BlackStone extends GoStone {
	public BlackStone(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw() {
		ClientMain.blackImage.draw(this.getX(), this.getY());
	}
}
