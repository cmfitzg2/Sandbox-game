package src;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Assets {
	
	private static final int width = 32, height = 32;
	private static final int textboxWidth = 228, textboxHeight = 96;
	
	static BufferedImage dirt, grass, stone, tree, water,
								playerDownNormal, playerUpNormal, playerLeftNormal, playerRightNormal;
	static BufferedImage[] btn_start;
	static BufferedImage[] player_down, player_up, player_left, player_right;
	static BufferedImage textbox_player = ImageLoader.loadImage("/res/textures/tb1.png");
	static BufferedImage textbox = ImageLoader.loadImage("/res/textures/tb.png");
	static BufferedImage glow = ImageLoader.loadImage("/res/textures/glow.png");
	static Font philosopher;
	static void init(){
		try {
		    //create the font to use. Specify the size!
		    philosopher = Font.createFont(Font.TRUETYPE_FONT, new File("philosopher.ttf")).deriveFont(72f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("philosopher.ttf")));
		} catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		}

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/sheet.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/charactersheet.png"));
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/Start.png"));

		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0,0,width*2,height);
		btn_start[1] = menuSheet.crop(width*2, 0, width, height);
		
		player_down = new BufferedImage[4]; 		//4 = frame count
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[6];
		player_right = new BufferedImage[6];
		
		player_down[0] = playerSheet.crop(0, 0, width, height);
		player_down[1] = playerSheet.crop(width, 0, width, height);
		player_down[2] = playerSheet.crop(width*2, 0, width, height);
		player_down[3] = playerSheet.crop(width*3, 0, width, height);
		
		player_up[0] = playerSheet.crop(0, height, width, height);
		player_up[1] = playerSheet.crop(width, height, width, height);
		player_up[2] = playerSheet.crop(width*2, height, width, height);
		player_up[3] = playerSheet.crop(width*3, height, width, height);
		
		player_left[0] = playerSheet.crop(0, height*2, width, height);
		player_left[1] = playerSheet.crop(width, height*2, width, height);
		player_left[2] = playerSheet.crop(width*2, height*2, width, height);
		player_left[3] = playerSheet.crop(width*3, height*2, width, height);
		player_left[4] = playerSheet.crop(width*4, height*2, width, height);
		player_left[5] = playerSheet.crop(width*5, height*2, width, height);
		
		player_right[0] = playerSheet.crop(0, height*3, width, height);
		player_right[1] = playerSheet.crop(width, height*3, width, height);
		player_right[2] = playerSheet.crop(width*2, height*3, width, height);
		player_right[3] = playerSheet.crop(width*3, height*3, width, height);
		player_right[4] = playerSheet.crop(width*4, height*3, width, height);
		player_right[5] = playerSheet.crop(width*5, height*3, width, height);
		
		playerDownNormal = player_down[0];
		playerLeftNormal = player_left[5];
		playerRightNormal = player_right[5];
		playerUpNormal = player_up[0];
				
		dirt = sheet.crop(0,0,width,height);
		grass = sheet.crop(width, 0, width, height);
		stone = sheet.crop(width*2, 0, width, height);
		tree = sheet.crop(0, height*2, width*2, height*2);
	}
	public static int getTextboxHeight()
	{
		return textboxHeight;
	}
	
	public static int getTextboxWidth()
	{
		return textboxWidth;
	}
}
