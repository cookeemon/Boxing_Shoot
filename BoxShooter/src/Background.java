import java.awt.*;

public class Background
{
				 int arrayIndex  = 0  ;
				 int arrayIndex2 = 0  ;
	final static int HSpacing 	 = 128;
	final static int VSpacing 	 = 122;
	
	Image[] sky;
	Image[] ground;
	Image[] dirt;
	String filetype = ".png";
	
	public Background()
	{
		sky = new Image[20];
		ground = new Image[20];
		dirt = new Image[20];
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				sky[arrayIndex] = Toolkit.getDefaultToolkit().getImage("Sky" + j + filetype);
				arrayIndex++;
			}
		}
		
		for(int i = 0; i < 10; i++)
		{
			for(int k = 0; k < 2; k++)
			{
				ground[arrayIndex2] = Toolkit.getDefaultToolkit().getImage("Ground" + k + filetype);
				dirt[arrayIndex2] = Toolkit.getDefaultToolkit().getImage("Dirt" + k + filetype);
				arrayIndex2++;
			}
		}
	}
	
	public void draw(Graphics pen)
	{
		for(int j = 0; j < 7; j++) {
			for(int i = 0; i < 20; i++) {
				pen.drawImage(sky[i], 0+(i*HSpacing), +(j*VSpacing), null);
			}
		}
		for(int i = 0; i < 20; i++) {
				pen.drawImage(ground[i], 0+(i*HSpacing), (7*VSpacing), null);	
		}
		for(int j = 0; j < 5; j++) {
			for(int i = 0; i < 20; i++) {
				pen.drawImage(dirt[i], 0+(i*HSpacing), (8*VSpacing)+(j*VSpacing), null);
			}
		}
	}

}
