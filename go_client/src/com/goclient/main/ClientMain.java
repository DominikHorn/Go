package com.goclient.main;

import org.newdawn.slick.*;

public class ClientMain extends BasicGame {
	public static final String GAME_TITLE = "Go Game";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	public static AppGameContainer appgc;

	public static void main(String argv[]) {
		try {
			appgc = new AppGameContainer(new ClientMain(GAME_TITLE));
			appgc.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}

	public ClientMain(String gamename) {
		super(gamename);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub

	}
}
