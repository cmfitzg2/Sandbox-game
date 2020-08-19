package src;

public class Handler 
{
	private Game game;
	private World world;
	private boolean playerFrozen = false, isInMenu = false;
	private int worldNumber = 1;
	private int currentTemperature = 0;
	private int currentTime = 0;
	private int currentThreat = 0;
	private int currentFear = 0;
	private int currentHunger = 0;
	private int currentThirst = 0;

	public Handler(Game game)
	{
		this.game = game;
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}
	
	public int getWidth()
	{
		return game.getWidth();
	}
	
	public int getHeight()
	{
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}
	
	public boolean getPlayerFrozen()
	{
		return playerFrozen;
	}
	
	public void setPlayerFrozen(boolean playerFrozen)
	{
		this.playerFrozen = playerFrozen;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getWorldNumber()
	{
		return worldNumber;
	}
	
	public void setWorldNumber(int worldNumber)
	{
		this.worldNumber = worldNumber;
	}
	
	public int getCurrentTemperature() 
	{
		return currentTemperature;
	}

	public void setCurrentTemperature(int currentTemperature) 
	{
		this.currentTemperature = currentTemperature;
	}
	
	public int getCurrentTime() 
	{
		return currentTime;
	}

	public void setCurrentTime(int currentTime) 
	{
		this.currentTime = currentTime;
	}
	public int getCurrentThreat() {
		return currentThreat;
	}

	public void setCurrentThreat(int currentThreat) {
		this.currentThreat = currentThreat;
	}

	public int getCurrentFear() {
		return currentFear;
	}

	public void setCurrentFear(int currentFear) {
		this.currentFear = currentFear;
	}

	public int getCurrentHunger() {
		return currentHunger;
	}

	public void setCurrentHunger(int currentHunger) {
		this.currentHunger = currentHunger;
	}

	public int getCurrentThirst() {
		return currentThirst;
	}

	public void setCurrentThirst(int currentThirst) {
		this.currentThirst = currentThirst;
	}
}
