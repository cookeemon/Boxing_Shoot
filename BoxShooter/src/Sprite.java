//import java.awt.Graphics;
//
//public class Sprite
//{
//	// Position P
//	double x;
//	double y;
//	
//	// Velocity V
//	double vx = 0;
//	double vy = 0;
//	
//	// Accerleration a
//	double ax = 0;
//	double ay = gravity;
//	
//	
//	int w;
//	int h;
//	
//	
//	final static double gravity = 0.3; 
//	
//	
//	
//	
//	Animation[] animation;
//	
//	boolean moving = true;
//	
//	final int IDLE  = 0;
//	final int LT 	= 1;
//	final int RT 	= 2;
//	
//	int action = IDLE;
//	
//	boolean highlighted = false;
//	boolean selected    = false;
//	
//	public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype, int delay)
//	{
//		this.x = x;
//		this.y = y;
//		this.w = w;
//		this.h = h;
//		
//		animation = new Animation[pose.length];
//		
//		for(int i = 0; i < animation.length; i++)
//			
//			animation[i] = new Animation(name, pose[i], count, filetype, delay);
//	}
//	
//	
//	
//	public void draw(Graphics pen)
//	{
//   	   if(moving)
//	
//		   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
//		   
//	   else
//
//		   pen.drawImage(animation[action].stillImage(), (int)x, (int)y, null);
//	   
//   	   if(highlighted)   pen.drawRect((int)x, (int)y, w, h);
//   	   
//	   moving = false;
//	}
//	
//	public void setVelocity(double vx, double vy)
//	{
//		this.vx = vx;
//		this.vy = vy;
//	}
//	
//	public void setAccelleration(double ax, double ay)
//	{
//		this.ax = ax;
//		this.ay = ay;
//	}
//	
//	public void jump()
//	{
//		vy = -8;
//	}
//	
//	public void move()
//	{
//		// Moved based on current velocity
//		x += vx;  
//		y += vy;
//		
//		// Adjust Velocity based on current acceleration
//		vx += ax;
//		vy += ay;
//	}
//	
//
//	public void moveLeft(int dx)
//	{
//		x -= dx;
//		
//		moving = true;
//		
//		action = LT;
//	}
//
//	public void moveRight(int dx)
//	{
//		x += dx;
//		
//		moving = true;
//		
//		action = RT;
//	}
//
//	public void goLeft(int dx)
//	{
//		vx = -dx;
//		
//		moving = true;
//		
//		action = LT;
//	}
//
//	public void goRight(int dx)
//	{
//		vx = +dx;
//		
//		moving = true;
//		
//		action = RT;
//	}
//
//	public boolean overlaps(Rect r)
//	{
//		return (x + w >= r.x      ) &&
//			   (x     <= r.x + r.w) &&
//			   (y + h >= r.y      ) &&
//			   (y     <= r.y + r.h);
//	}
//	
//	
//	public void pushUpFrom(Rect platform)
//	{
//		y -= y + h  - platform.y;
//		
//		vx = 0;
//		vy = 0;
//	}
//	
//	public boolean contains(int mx, int my)
//	{
//		return ( mx > x   ) &&   
//			   ( mx < x+w ) && 
//			   ( my > y   ) && 
//			   ( my < y+h );
//	}
//	
//	public void highlight()
//	{
//		highlighted = true;
//	}
//	
//	public void dehighlight()
//	{
//		highlighted = false;
//	}
//	
//	public void select()
//	{
//		selected = true;
//	}
//	
//	public void deselect()
//	{
//		selected = false;
//	}
//	
//	public boolean isSelected()
//	{
//		return selected;
//	}
//}
import java.awt.Graphics;
import java.awt.Toolkit;

public class Sprite
{
	// Position P
	double x;
	double y;
	
	int w;
	int h;
	
	Animation[] animation;

	
	boolean blocking = false;
	boolean hurting  = false;
	boolean punching = false;
	boolean dizzy    = false;
	boolean ko 		 = false;

	final int IDLE 			= 0;
	final int PUNCHLEFT 	= 1;
	final int PUNCHRIGHT 	= 2;
	final int BLOCKING 		= 3;
	final int DIZZY			= 4;
	final int HURT 			= 5;
	final int KO 			= 6;
	final int PUNCHUP 		= 7;
	
	
	int action = IDLE;
	

	
	public Sprite(int bNum, int x, int y, String[] pose, int[] poseCount, String filetype)
	{
		this.x = x;
		this.y = y;
		
		animation = new Animation[pose.length];
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(bNum, pose[i], poseCount[i], filetype);
		}
	}
	
	
	
	public void draw(Graphics pen)
	{
		   if(hurting)
		   {
			   pen.drawImage(animation[action].hurtImage(), (int)x, (int)y, null);
		   }
		   else if(blocking)
		   {
			   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
		   }
		   else if(punching)
		   {
			   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
		   }
		   else if(dizzy)
		   {
			   pen.drawImage(animation[action].currentImage(), (int)x, (int)y, null);
		   }
		   else if(ko)
		   {
			   pen.drawImage(animation[KO].KOImage(), (int)x, (int)y, null);
		   }
		   else
			   pen.drawImage(animation[IDLE].currentImage(), (int)x, (int)y, null);
		   
	}
	

	public void Hurt()
	{
		if(!(action == KO))
		{
			hurting = true;
			
			action = HURT;
		}
	}
	public void setHurt()
	{
		animation[action].Hurt = false;
		
		hurting = false;
		
	}
	public boolean getHurt()
	{
		return animation[action].Hurt;
	}

	public void punchLeft()
	{
		punching = true;
		
		action = PUNCHLEFT;
	}
	
	public void punchRight()
	{
		punching = true;
		
		action = PUNCHRIGHT;
	}
	public void punchUp()
	{
		punching = true;
		
		action = PUNCHUP;
	}
	public void Block()
	{
		blocking = true;
		
		action = BLOCKING;
	}
	public void Dizzy() 
	{
		action = DIZZY;
	}
	public boolean isDizzy() 
	{
		return (action == DIZZY);
	}
	public void KO() 
	{
		action = KO;
	}
	public void setKO(boolean b) {
		ko = b;
	}

	public boolean isDead() 
	{
		return(action == KO);
	}
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&
			   (y     <= r.y + r.h);
	}
	
}