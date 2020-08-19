package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;

public class MenuState extends State
{

	private UIManager uiManager;
	AudioClip soundFile;

	public MenuState(final Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		soundFile = Applet.newAudioClip(getClass().getResource("piano.au"));
		handler.getMouseManager().setUIManager(uiManager);
		soundFile.loop();

		uiManager.addObject(new UIImageButton(386, 256, 128, 64, Assets.btn_start, new ClickListener(){

			@Override
			public void onClick()
			{
				handler.getMouseManager().setUIManager(null);
				soundFile.stop();
				State.setState(handler.getGame().gameState);
			}}));
	}

	@Override
	public void tick()
	{
		uiManager.tick();
	}

	@Override
	public void render(Graphics g)
	{
		uiManager.render(g);
	}

}