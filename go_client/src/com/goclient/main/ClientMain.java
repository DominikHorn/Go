package com.goclient.main;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;

import com.goclient.board.*;

public class ClientMain extends BasicGame {
	public static final int BOARD_SIZE = 9;
	public static final int TILE_SIZE = 72;
	public static final String GAME_TITLE = "Go Game";
	public static final int SCREEN_WIDTH = TILE_SIZE * (BOARD_SIZE + 2);
	public static final int SCREEN_HEIGHT = SCREEN_WIDTH;
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
		for (int y = SCREEN_HEIGHT / (BOARD_SIZE + 2); y <= SCREEN_HEIGHT
				- 2 * (SCREEN_HEIGHT / (BOARD_SIZE + 2)); y += SCREEN_HEIGHT / (BOARD_SIZE + 2))
			for (int x = SCREEN_WIDTH / (BOARD_SIZE + 2); x <= SCREEN_WIDTH
					- 2 * (SCREEN_WIDTH / (BOARD_SIZE + 2)); x += SCREEN_WIDTH / (BOARD_SIZE + 2))
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
			Point newPosition = getGridAlignedCoordinates(newx, newy);
			this.currentStone.setX((int) newPosition.getX());
			this.currentStone.setY((int) newPosition.getY());
		}
	}

	public void mousePressed(int button, int x, int y) {
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
		// Do nothing (tmp)
	}

	public void mouseWheelMoved(int change) {
		// Do nothing
	}

	private void makeNewStone(GameContainer gc) {
		Point newPosition = getGridAlignedCoordinates(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		if (this.currentStone == null) {
			if (this.blacksTurn)
				this.currentStone = new BlackStone((int) newPosition.getX(), (int) newPosition.getY());
			else
				this.currentStone = new WhiteStone((int) newPosition.getX(), (int) newPosition.getY());
		}
	}

	private Point getGridAlignedCoordinates(int x, int y) {
		x -= this.tileImage.getWidth() / 2;
		y -= this.tileImage.getHeight() / 2;
		int newX = ((x / (SCREEN_WIDTH / (BOARD_SIZE + 2))) * (SCREEN_WIDTH / (BOARD_SIZE + 2)))
				+ (SCREEN_WIDTH / (BOARD_SIZE + 2));
		int newY = ((y / (SCREEN_HEIGHT / (BOARD_SIZE + 2))) * (SCREEN_HEIGHT / (BOARD_SIZE + 2)))
				+ (SCREEN_HEIGHT / (BOARD_SIZE + 2));
		newX -= this.tileImage.getWidth() / 2;
		newY -= this.tileImage.getHeight() / 2;

		// Limit values on each axis to a maximum
		if (newX > SCREEN_WIDTH - 1.5 * TILE_SIZE)
			newX = (int) (SCREEN_WIDTH - 1.5  * TILE_SIZE);
		if (newY > SCREEN_HEIGHT - 1.5 * TILE_SIZE)
			newY = (int) (SCREEN_HEIGHT - 1.5 * TILE_SIZE);

		return new Point(newX, newY);
	}
}
