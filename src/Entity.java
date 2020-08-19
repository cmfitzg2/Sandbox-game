package src;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity 
{
	public static final int DEFAULT_HEALTH = 10;
	protected int health;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int tileX, tileY;
	protected boolean active = true;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract boolean isFirstRender();
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public abstract void interactedWith();
	
	public abstract boolean isInteracting();

	public Tile getTileAtPosition() {
		return handler.getWorld().getTile(getTileXIndex(), getTileYIndex());
	}

	public void setTileAtPosition(Tile tile) {
		handler.getWorld().setTile(getTileXIndex(), getTileYIndex(), tile);
	}
	
	public void hurt(int amt) {
		health -= amt;
		if(health<=0)
		{
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities())
		{
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x+bounds.x+xOffset), (int) (y+bounds.y+yOffset), bounds.width, bounds.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getTileXIndex() {
		return Math.floorDiv(getCollisionBounds(0, 0).x, Tile.TILEWIDTH);
	}

	public int getTileYIndex() {
		return Math.floorDiv(getCollisionBounds(0, 0).y, Tile.TILEWIDTH);
	}

	public int getTileXIndexInFrontOf(String direction) {

		//TODO: this is broken. remove the conditionals to restore original functionality.
		int tileX = getTileXIndex();
		int playerX = (int) handler.getWorld().getEntityManager().getPlayer().x % Tile.TILEWIDTH;
		switch (direction) {
			case "left":
				if (playerX <= Tile.TILEWIDTH / 2) {
					return tileX - 1;
				}
				break;
			case "right":
				if (playerX >= Tile.TILEWIDTH / 2) {
					return tileX + 1;
				}
				break;
		}
		return tileX;
	}

	public int getTileYIndexInFrontOf(String direction) {
		int tileY = getTileYIndex();
		switch (direction) {
			case "up":
				return tileY - 1;
			case "down":
				return tileY + 1;
		}
		return tileY;
	}

	public int getTileXInFrontOf(String direction) {
		return getTileXIndexInFrontOf(direction) * Tile.TILEWIDTH;
	}

	public int getTileYInFrontOf(String direction) {
		return getTileYIndexInFrontOf(direction) * Tile.TILEHEIGHT;
	}
}
