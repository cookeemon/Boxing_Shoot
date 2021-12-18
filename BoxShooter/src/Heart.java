import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Heart
{

	int x;
	int y;
	int pNum;
	Image heart;
	String filetype = ".png";
	int currentHealth = 15;
	final int spacing = 51;
	
	public Heart(int x, int y, int pNum)
	{
		this.x = x;
		this.y = y;
		this.pNum = pNum;
		heart = Toolkit.getDefaultToolkit().getImage(pNum + "Heart" + filetype);

	}
	
	public void draw(Graphics pen) 
	{
			if(pNum == 1)
			{
				for (int i = 0; i < currentHealth; i++)
				{
					pen.drawImage(getHeart(), x+(i*spacing), y, null);
				}
			}
			else
			{
				for (int i = 0; i < currentHealth; i++)
				{
					pen.drawImage(getHeart(), x+(i*spacing), y, null);
				}
			}
	}
	
	public int getHealth()
	{
		return currentHealth;
	}
	public void P1resetHealth()
	{
		currentHealth = 15;
	}
	public void P2resetHealth()
	{
		currentHealth = 15;
		x -= 755;
	}
	
	public Image getHeart()
	{
		return heart;
	}
	
	public void P1reduceHeart()
	{
		currentHealth--;
	}
	
	public void P2reduceHeart()
	{
		currentHealth--;
		x += 51;
	}

	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = x;
	}	
}


