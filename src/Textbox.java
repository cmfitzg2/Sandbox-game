package src;

import java.awt.Graphics;

public class Textbox 
{
	private int textLengthFirst = 1, textLengthSecond = 1, textLengthThird = 1;
	private String firstLine = "", secondLine = "", thirdLine = "";
	private String currentTextFirst = "", currentTextSecond = "", currentTextThird = "";
	boolean textFinished = false;
	
					//entity number, part of game, textbox number for that part
	public void updateTextbox(int id, int gameFlag, int textBox)
	{		
			if(textLengthFirst <= firstLine.length())
			{
				currentTextFirst = firstLine.substring(0, textLengthFirst);
				textLengthFirst++;
			}
			
			else if(textLengthSecond <= secondLine.length())
			{
				currentTextSecond = secondLine.substring(0, textLengthSecond);
				textLengthSecond++;
			}
			
			else if(textLengthThird <= thirdLine.length())
			{
				currentTextThird = thirdLine.substring(0, textLengthThird);
				textLengthThird++;
			}
			
			else
				textFinished = true;
	}
	
	public boolean isTextFinished() {
		return textFinished;
	}

	public void clearText()
	{
		textLengthFirst = 1; textLengthSecond = 1; textLengthThird = 1;
		firstLine = ""; secondLine = ""; thirdLine = "";
		currentTextFirst = ""; currentTextSecond = ""; currentTextThird = "";
		textFinished = false;
		
	}
	
	public boolean textboxExists(int id, int gameFlag, int textBox)
	{
			return false;
	}
	
	public void renderTextbox(Handler handler, Graphics g, int id, int gameFlag, int textBox)
	{	
		
		g.drawString(currentTextFirst, 220, handler.getHeight() - Assets.getTextboxHeight() + 7);
		g.drawString(currentTextSecond, 220, handler.getHeight() - Assets.getTextboxHeight() + 42);
		g.drawString(currentTextThird, 220, handler.getHeight() - Assets.getTextboxHeight() + 77);
	}
	
}

