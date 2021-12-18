import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MenuItems {
	int x = 1148;
	int y = 400;
	Image menu;
	Image play;
	Image musicUP;
	Image musicDN;
	Image sfxUP;
	Image sfxDN;
	Image retry;
	
	String filetype = ".PNG";

	
	public MenuItems()
	{
		menu 	= Toolkit.getDefaultToolkit().getImage("Menu" + filetype );
		play    = Toolkit.getDefaultToolkit().getImage("Play" + filetype );
		musicUP = Toolkit.getDefaultToolkit().getImage("Music0" + filetype);
		musicDN = Toolkit.getDefaultToolkit().getImage("Music1" + filetype);
		sfxUP   = Toolkit.getDefaultToolkit().getImage("SFX0" + filetype);
		sfxDN   = Toolkit.getDefaultToolkit().getImage("SFX1" + filetype);
		retry 	= Toolkit.getDefaultToolkit().getImage("Retry" + filetype );
	}
	
	public void draw(Graphics pen) 
	{
		pen.drawImage(menu, x+60, y-200, null    );
		pen.drawImage(play, x, y, null			 );
		pen.drawImage(musicUP, x-15, y+100, null );
		pen.drawImage(musicDN, x+135, y+100, null);
		pen.drawImage(sfxUP, x-25, y+170, null   );
		pen.drawImage(sfxDN, x+145, y+170, null  );
	}
	
}
