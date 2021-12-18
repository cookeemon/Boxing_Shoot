import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



public class uFist
{
	
	int x;
	int y;
	Image uFistImage;
	String filetype = ".png";
	public boolean ready = false;
	public boolean repositioned = false;
	
	public uFist(int bNum, int x, int y)
	{
		this.x = x;
		this.y = y;
		uFistImage =  Toolkit.getDefaultToolkit().getImage(bNum + "Fist2" + filetype);	
	}
	
	public Image getUFistImage()
	{
		return uFistImage;
	}

	public void draw(Graphics pen)
	{
		pen.drawImage(getUFistImage(), x, y, null);
		
	}
	
	public void moveLeft(int dx)
	{
		ready = true;
		
		x -= dx;
	}
	
	public void moveRight(int dx)
	{
		ready = true;
		
		x += dx;
	}
	
	public void reposition(int dx, int dy)
	{
		 repositioned = true;
		 x = dx;
		 y = dy;
	}
	
	public boolean isReady()
	{
		return ready;
	}
	
	public void setReady(boolean b)
	{
		ready = b;
	}

	public void setReposition(boolean b) {
		repositioned = b;
	}
}
