package src;

import java.awt.Color;
import java.awt.Graphics;

public class ScreenFade 
{
	int gameWidth = 0;
	int gameHeight = 0;
	Handler handler; 
	
	public ScreenFade(Handler handler)
	{
		this.handler = handler;
		gameWidth = handler.getGame().getWidth();
		gameHeight = handler.getGame().getHeight();
	}
	
	void fadeScreen(Graphics g, int alpha)
	{
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, gameWidth, gameHeight);
	}
	
	void overlayScreen(Graphics g, Color c)
	{
		g.setColor(c);
		g.fillRect(0, 0, gameWidth, gameHeight);
	}
	
	
}
