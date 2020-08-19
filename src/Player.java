package src;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
	private static boolean down = true, up = false, left = false, right = false;
	private boolean interactedWith = false;
	private ItemManager itemManager;
	//Animations	
	private Animation animDown, animUp, animLeft, animRight;
	public static Rectangle playerRec, interactionRectangle;
	private ScreenFade screenFade;
	//Font
	Font f;

	public Player(Handler handler, float x, float y)
	{
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;

		//Animations
		animDown = new Animation(175, Assets.player_down);
		animLeft = new Animation(100, Assets.player_left);
		animUp = new Animation(175, Assets.player_up);
		animRight = new Animation(100, Assets.player_right);

		//ScreenFade
		screenFade = new ScreenFade(handler);

		//Font
		f = new Font("overlay", Font.ITALIC|Font.BOLD, 16);

		//Items
		itemManager = new ItemManager(handler, this);
	}

	@Override
	public void tick()
	{
		currentPlayerRectangle();
		if(handler.getPlayerFrozen())
			return;

		itemManager.tick();

		//Animations
		animDown.tick();
		animLeft.tick();
		animUp.tick();
		animRight.tick();

		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		checkInteraction();
	}

	@Override
	public void render(Graphics g) {
		if(handler.getPlayerFrozen()) {
			if(up) {
				g.drawImage(Assets.playerUpNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
			if(down) {
				g.drawImage(Assets.playerDownNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
			if(left) {
				g.drawImage(Assets.playerLeftNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
			if(right) {
				g.drawImage(Assets.playerRightNormal, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
		else {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),  (int) (y-handler.getGameCamera().getyOffset()), width, height, null);


			g.setColor(Color.RED);
			g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
					(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
					bounds.width, bounds.height);

			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("Current (x,y): (" + x + ", " + y + ")", 16, handler.getHeight() - 16);
			g.drawString("Current (tilex,tiley): (" + getTileXIndex() + ", " + getTileYIndex() + ")", 16, handler.getHeight() - 48);
		}

		illuminateTileInFront(g);
	}

	private void currentPlayerRectangle()
	{
		playerRec = new Rectangle((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}

	private void checkInteraction() {
		Rectangle collisionBounds = getCollisionBounds(0,0);
		interactionRectangle = new Rectangle();
		int interactionSize = 20;
		interactionRectangle.width = interactionSize;
		interactionRectangle.height = interactionSize;
		if(up)
		{
			interactionRectangle.x = collisionBounds.x + collisionBounds.width/2 - interactionSize/2;
			interactionRectangle.y = collisionBounds.y - interactionSize;
		}
		else if(down)
		{
			interactionRectangle.x = collisionBounds.x + collisionBounds.width/2 - interactionSize/2;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height;
		}
		else if(left)
		{
			interactionRectangle.x = collisionBounds.x - interactionSize;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height/2 - interactionSize/2;
		}
		else if(right)
		{
			interactionRectangle.x = collisionBounds.x + collisionBounds.width;
			interactionRectangle.y = collisionBounds.y + collisionBounds.height/2 - interactionSize/2;
		}
	}


	@Override
	public void die()
	{
		System.out.println("you lose");
	}

	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		runSpeed = 8.0f;
		speed = 4.0f;

		if(handler.getKeyManager().up) {
			if (handler.getKeyManager().shift) {
				yMove = -runSpeed;
			}
			else {
				yMove = -speed;
			}
		}
		if(handler.getKeyManager().down) {
			if (handler.getKeyManager().shift) {
				yMove = runSpeed;
			}
			else {
				yMove = speed;
			}
		}
		if(handler.getKeyManager().left) {
			if (handler.getKeyManager().shift) {
				xMove = -runSpeed;
			}
			else {
				xMove = -speed;
			}
		}
		if(handler.getKeyManager().right) {
			if (handler.getKeyManager().shift) {
				xMove = runSpeed;
			}
			else {
				xMove = speed;
			}
		}

		if(handler.getKeyManager().c) {
			if (!handler.getKeyManager().isStillHoldingC()) {
				handler.getKeyManager().setStillHoldingC(true);
			}
		}

		if (handler.getKeyManager().z) {
			if (!handler.getKeyManager().isStillHoldingZ()) {
				handler.getKeyManager().setStillHoldingZ(true);

				String direction = getDirection();
				handler.getWorld().getEntityManager().addEntity(new Tree(handler,
						getTileXInFrontOf(direction) - GeneralConstants.treeXBounds
								+ (float) Tile.TILEWIDTH / 2 - (float) GeneralConstants.treeWidth / 2,
						getTileYInFrontOf(direction) - GeneralConstants.treeYBounds
								+ (float) Tile.TILEHEIGHT / 2 - (float) GeneralConstants.treeHeight / 2, 128, 128));
				int tileXIndexInFrontOf = getTileXIndexInFrontOf(direction);
				int tileYIndexInFrontOf = getTileYIndexInFrontOf(direction);
				Tile tileInFrontOf = handler.getWorld().getTile(tileXIndexInFrontOf, tileYIndexInFrontOf);
				if (tileInFrontOf.equals(Tile.dirtTile)) {
					handler.getWorld().setTile(tileXIndexInFrontOf, tileYIndexInFrontOf, Tile.grassTile);
				} else {
					handler.getWorld().setTile(tileXIndexInFrontOf, tileYIndexInFrontOf, Tile.dirtTile);
				}

				for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
					if (e.equals(this)) {
						//an entity cannot interact with itself
						continue;
					}
					if (e.getCollisionBounds(0, 0).intersects(interactionRectangle)) {
						if (e.isInteracting()) {
							return;
						} else {
							//call interaction function specified by any class extending entity
							e.interactedWith();
						}
						return;
					}
				}
			}
		}
	}

	private void autoMove() {

	}

	private void illuminateTileAtPosition(Graphics g) {
		g.drawImage(Assets.glow,
				Tile.TILEWIDTH * Math.round(x / Tile.TILEWIDTH) - (int) handler.getGameCamera().getxOffset(),
				Tile.TILEHEIGHT * Math.round(y / Tile.TILEHEIGHT) - (int) handler.getGameCamera().getyOffset(), null);
	}

	private void illuminateTileInFront(Graphics g) {
		g.drawImage(Assets.glow, getTileXInFrontOf(getDirection())  - (int) handler.getGameCamera().getxOffset(),
				getTileYInFrontOf(getDirection()) - (int) handler.getGameCamera().getyOffset(), null);
	}

	private BufferedImage getCurrentAnimationFrame()
	{
		if(xMove<0) {
			left = true; right = false; up = false; down = false;
			return animLeft.getCurrentFrame();
		}
		else if(xMove>0) {
			right = true; left = false; up = false; down = false;
			return animRight.getCurrentFrame();
		}
		else if(yMove<0) {
			up = true; left = false; right = false; down = false;
			return animUp.getCurrentFrame();
		}
		else if(yMove>0) {
			down = true; left = false; up = false; right = false;
			return animDown.getCurrentFrame();
		}
		//not moving
		else if(right) {
			return Assets.playerRightNormal;
		}
		else if(up) {
			return Assets.playerUpNormal;
		}
		else if(left) {
			return Assets.playerLeftNormal;
		}
		else {
			return Assets.playerDownNormal;
		}

	}

	public void interactedWith() {
		//Player should never be interacted with
		interactedWith = true;
		System.out.println("Interaction with " + this);
		interactedWith = false;
	}

	public boolean isInteracting() {
		return interactedWith;
	}
	public static Rectangle getPlayerRec() {
		return playerRec;
	}

	@Override
	public boolean isFirstRender() {
		// TODO Auto-generated method stub
		return false;
	}

	public static String getDirection() {
		if (up) {
			return "up";
		} else if (down) {
			return "down";
		} else if (left) {
			return "left";
		} else if (right) {
			return "right";
		}
		return "";
	}

	public void setDirection(String dir) {
		if(dir.equals(Boolean.toString(up))) {
			up = true; down = false; left = false; right = false;
		}
		if(dir.equals(Boolean.toString(down))) {
			down = true; left = false; right = false; up = false;
		}
		if(dir.equals(Boolean.toString(left))) {
			left = true; down = false; up = false; right = false;
		}
		if(dir.equals(Boolean.toString(right))) {
			right = true; up = false; down = false; left = false;
		}
	}

	public ItemManager getItemManager() {
		return itemManager;
	}
}
