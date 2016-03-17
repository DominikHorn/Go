package com.goclient.main;

import java.util.*;

import org.newdawn.slick.*;

import com.goclient.board.*;

public class ClientMain extends BasicGame {
	public static final String GAME_TITLE = "Go Game";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	public static AppGameContainer appgc;

	private Image tileImage;
	public static Image blackImage;
	public static Image whiteImage;

	private boolean blacksTurn = true;
	private List<BlackStone> blackStones;
	private List<WhiteStone> whiteStones;
	private GoStone currentStone;

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

		this.blackStones = new ArrayList<>();
		this.whiteStones = new ArrayList<>();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Draw background
		g.setColor(new Color(237, 191, 64));
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		for (int y = SCREEN_HEIGHT / 11; y < SCREEN_HEIGHT - 2 * (SCREEN_HEIGHT / 11); y += SCREEN_HEIGHT / 11)
			for (int x = SCREEN_WIDTH / 11; x < SCREEN_WIDTH - 2 * (SCREEN_WIDTH / 11); x += SCREEN_WIDTH / 11)
				this.tileImage.draw(x, y);

		for (GoStone stone : this.blackStones)
			stone.draw();
		
		for (GoStone stone : this.whiteStones)
			stone.draw();
		

		// Draw last
		if (this.currentStone != null)
			this.currentStone.draw();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.tileImage = new Image("gfx/feld.png");
		blackImage = new Image("gfx/GoBlack.png");
		whiteImage = new Image("gfx/GoWhite.png");
		gc.getInput().addMouseListener(this);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		makeNewStone(gc);
	}

	/** Mouse listener methods */
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (this.currentStone != null) {
			this.currentStone.setX(newx - this.tileImage.getWidth() / 2);
			this.currentStone.setY(newy - this.tileImage.getHeight() / 2);
		}
	}

	public void mousePressed(int button, int x, int y) {
		System.out.println("mousePressed(" + button + ", " + x + ", " + y + ");");

		// Place new Stone
		if (this.blacksTurn)
			this.blackStones.add((BlackStone) this.currentStone);
		else
			this.whiteStones.add((WhiteStone) this.currentStone);

		// Toggle turns
		this.blacksTurn = !this.blacksTurn;
		this.currentStone = null;
	}

	public void mouseReleased(int button, int x, int y) {
		// Do nothing
		// System.out.println("mouseReleased(" + button + ", " + x + ", " + y +
		// ");");
	}

	public void mouseWheelMoved(int change) {
		// Do nothing
		// System.out.println("mouseWheelMoved(" + change + ");");
	}

	private void makeNewStone(GameContainer gc) {
		int stoneX = gc.getInput().getMouseX() - this.tileImage.getWidth() / 2;
		int stoneY = gc.getInput().getMouseY() - this.tileImage.getHeight() / 2;
		if (this.currentStone == null) {
			if (this.blacksTurn)
				this.currentStone = new BlackStone(stoneX, stoneY);
			else
				this.currentStone = new WhiteStone(stoneX, stoneY);
		}
	}
}
