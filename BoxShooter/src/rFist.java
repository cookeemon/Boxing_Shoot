import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class rFist {
	
	int x;
	int y;
	Image rFistImage;
	String filetype = ".png";
	public boolean ready = false;
	public boolean repositioned = false;
	
	public rFist(int bNum, int x, int y)
	{
		this.x = x;
		this.y = y;
		rFistImage =  Toolkit.getDefaultToolkit().getImage(bNum + "Fist0" + filetype);	
	}
	
	public Image getRFistImage()
	{
		return rFistImage;
	}
	
	public void draw(Graphics pen)
	{
		pen.drawImage(getRFistImage(), x, y, null);
	}

	public void moveRight(int dx)
	{
		ready = true;
		
		x += dx;
	}
	public void moveLeft(int dx)
	{
		ready = true;
		
		x -= dx;
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
