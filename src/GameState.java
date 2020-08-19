package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameState extends State
{
	private ScreenFade screenFade;
	private long startTime;
	private World world1, world2;
	private Rectangle loadZoneWest, loadZoneEast, loadZoneNorth, loadZoneSouth;
	public static boolean playStarted = false;
	static AudioClip overworldMusic;
	static AudioClip overworldMusicLoop;
	boolean switcher = false;
	
	public GameState(Handler handler)
	{
		super(handler);
		world1 = new World(handler,"world1.txt");
		world2 = new World(handler,"world2.txt");
		screenFade = new ScreenFade(handler);
		handler.setWorld(world1);
	}
	
	
	
	@Override
	public void tick() 
	{		
		getLoadZones();
		if(firstTime)
		{
			world1.tick();
			world2.tick();
			firstTime = false;
		}
		
		if(handler.getWorldNumber() == 1)
		{
			world1.tick();

			if(Player.playerRec.intersects(loadZoneWest))
			{
				handler.setWorld(world2);
				handler.setWorldNumber(2);
				justTransitioned = true;
				world2.tick();
			}
		}
		
		
		else if(handler.getWorld() == world2)
		{
			world2.tick();
			
			if(Player.playerRec.intersects(loadZoneEast))
			{
				handler.setWorld(world1);
				handler.setWorldNumber(1);
				justTransitioned = true;
				world1.tick();
			}
		}
	}
	private boolean firstTime = true, justTransitioned = false;
	private int alpha = 255;
	
	@Override
	public void render(Graphics g) 
	{
		
		if(handler.getWorldNumber() == 1)
		{			
			world1.render(g);
			if(justTransitioned)
			{
				handler.getWorld().getEntityManager().getPlayer().setX(20);
				handler.getWorld().getEntityManager().getPlayer().setY(496);
				justTransitioned = false;
			}
			g.drawRect((int) -handler.getGameCamera().getxOffset(),(int) (511 - handler.getGameCamera().getyOffset()),10,64);
		}
		
		
		
		else if(handler.getWorldNumber() == 2)
		{
			world2.render(g);
			if(justTransitioned)
			{
				handler.getWorld().getEntityManager().getPlayer().setX(1130);
				handler.getWorld().getEntityManager().getPlayer().setY(304);
				justTransitioned = false;
			}
			g.drawRect((int) (1200 - handler.getGameCamera().getxOffset()),(int) (320 - handler.getGameCamera().getyOffset()),10,64);
		}
		g.setColor(Color.BLACK);
	}
	
	private void getLoadZones()
	{
		if(handler.getWorldNumber() == 1)
		{
			loadZoneWest = new Rectangle((int) -handler.getGameCamera().getxOffset(),(int) (511 - handler.getGameCamera().getyOffset()),10,64);
		}
		else if(handler.getWorldNumber() == 2)
		{
			loadZoneEast = new Rectangle((int) (1200 - handler.getGameCamera().getxOffset()),(int) (320 - handler.getGameCamera().getyOffset()),10,64);
		}
		
	}
	
	public void playMusic()
	{
		playStarted = true;
		overworldMusic = Applet.newAudioClip(getClass().getResource("ocean.au"));
		overworldMusicLoop = Applet.newAudioClip(getClass().getResource("ocean.au"));
		
		overworldMusic.play();
		startTime = System.currentTimeMillis();
	}
	
	public static void stopMusic()
	{
		playStarted = false;
		overworldMusic.stop();
	}
	
}
