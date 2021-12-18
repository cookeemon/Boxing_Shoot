import java.awt.*;



public class lFist {
	
	int x;
	int y;
	Image lFistImage;
	String filetype = ".png";
	public boolean ready = false;
	public boolean repositioned = false;
	
	public lFist(int bNum, int x, int y)
	{
		this.x = x;
		this.y = y;
		lFistImage =  Toolkit.getDefaultToolkit().getImage(bNum + "Fist1" + filetype);	
	}
	
	public Image getLFistImage()
	{
		return lFistImage;
	}

	public void draw(Graphics pen)
	{
		pen.drawImage(getLFistImage(), x, y, null);
		
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
