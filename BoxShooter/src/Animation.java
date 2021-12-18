
import java.awt.*;

public class Animation
{
	Image[] image;


	int delay = 10;
	final int duration = 10;
	int current = 0;
	boolean Hurt = false;
	
	//
	public Animation(int bNum, String pose, int poseCount, String filetype)
	{
		image  = new Image[poseCount];
		

		for(int i = 0; i < poseCount; i++)
			
			image[i] = Toolkit.getDefaultToolkit().getImage(bNum + pose + i + filetype);
		
	}

	
	
	//
	public Image currentImage()
	{

		
		delay--;
		
		if(delay == 0)
		{
			if(current < image.length-1)  current++;
			
			else                          current = 0;
			
			delay = duration;
		}
		
		return image[current];
	}
	
	public Image hurtImage()
	{

		
		delay--;
		
		if(delay == 0)
		{
			if(current < image.length-1)  current++;
			
			else {
				Hurt = true;
				current = 0;
			}
			
			delay = duration;
		}
		
		return image[current];
	}



	public Image KOImage() {
		return image[current];
	}



}