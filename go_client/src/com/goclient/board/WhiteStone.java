package com.goclient.board;

import org.newdawn.slick.*;

import com.goclient.main.*;

public class WhiteStone extends GoStone {

	public WhiteStone(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw() {
		ClientMain.whiteImage.draw(this.getX(), this.getY());
	}

}
