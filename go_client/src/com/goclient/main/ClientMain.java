package com.goclient.main;

import org.newdawn.slick.*;

public class ClientMain extends BasicGame {
	public static final String GAME_TITLE = "Go Game";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	public static AppGameContainer appgc;

	private Image img;

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
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Draw background
		g.setColor(new Color(237, 191, 64));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		for (int y = SCREEN_HEIGHT / 11; y < SCREEN_HEIGHT - 2 * (SCREEN_HEIGHT / 11); y += SCREEN_HEIGHT / 11)
			for (int x = SCREEN_WIDTH / 11; x < SCREEN_WIDTH - 2 * (SCREEN_WIDTH / 11); x += SCREEN_WIDTH / 11)
				this.img.draw(x, y);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.img = new Image("gfx/feld.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}
}
