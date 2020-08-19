package src;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//Screenfading
	public ScreenFade screenFade;
	public int alpha = 0;
	private boolean fadeOut = false;
	private boolean fadeIn = true;
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) 
	{
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() 
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0,0);
		screenFade = new ScreenFade(handler);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	private void tick()
	{
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render()
	{
	bs = display.getCanvas().getBufferStrategy();
	if(bs == null)
		{
		display.getCanvas().createBufferStrategy(3);
		return;
		}
	g = bs.getDrawGraphics();
	//clear screen
	g.clearRect(0, 0, width, height);	
	
	//draw
	if(State.getState() != null)
		State.getState().render(g);
	else
		System.out.println("no state");
	if(fadeOut)
	{
		alpha++;
		if(alpha<=255)
			screenFade.fadeScreen(g, alpha);
		else
			screenFade.fadeScreen(g, 255);
	}
	
	if(fadeIn)
	{
		alpha--;
		if(alpha>=0)
			screenFade.fadeScreen(g, alpha);
		else
			screenFade.fadeScreen(g, 0);
	}
	
	//done drawing
	bs.show();
	g.dispose();
	}
	@Override
	public void run() 
	{
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if(delta >= 1)
			{
				tick();
				render();
				delta = 0;
			}

		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}

	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop()
	{
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setFadeIn(boolean fade) {
		this.fadeIn = fade;
		alpha = 255;
	}

	public boolean isFadeIn() {
		return fadeIn;
	}
	
	public void setFadeOut(boolean fade){
		this.fadeOut = fade;
		alpha = 0;
	}
	
	public boolean isFadeOut() {
		return fadeOut;
	}

}
