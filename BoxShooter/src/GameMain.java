import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.w3c.dom.events.MouseEvent;

public class GameMain extends GameBase 
{	
	
	//Game States
	private enum STATE{
		MENU,
		PLAY
	}
	private STATE gameState = STATE.MENU;
	
	//Menu objects
	MenuItems menuItem = new MenuItems();
	int MenuReferX = 1148;
	int MenuReferY = 400;

	////Menu objects rect for pressing mouse.
	int y = 400;
	Rect menuRect 	 = new Rect(MenuReferX+60,  MenuReferY-200, 145, 41 );
	Rect playRect	 = new Rect(MenuReferX,     MenuReferY,265, 87		);
	Rect musicUPRect = new Rect(MenuReferX-15,  MenuReferY+100, 142, 46 );
	Rect musicDNRect = new Rect(MenuReferX+135, MenuReferY+100, 147, 38 );
	Rect sfxUPRect   = new Rect(MenuReferX-25,  MenuReferY+170, 149, 39 );
	Rect sfxDNRect   = new Rect(MenuReferX+145, MenuReferY+170, 145, 42 );
	
	//Background object
	Background BG = new Background();
	
	
	final static int P1Num = 1;
	final static int P2Num = 2;
	Boxer P1    		   = new Boxer(100,  470, P1Num);
	Boxer P2    		   = new Boxer(1960, 470, P2Num);
	
	Rect P1HitBox  = new Rect((int)(P1.x) + 399, (int)(P1.y)+200, 10, 200, Color.BLUE);
	Rect P2HitBox  = new Rect((int)(P2.x) +  90, (int)(P2.y)+200, 10, 200,  Color.RED);
	
	//Left, Right, and Up fists
	rFist[] P1rFist;
	lFist[] P1lFist;
	uFist[] P1uFist;
	rFist[] P2rFist;
	lFist[] P2lFist;
	uFist[] P2uFist;
	
	//Left, Right, and Up fist rect
	Rect[] P1rRect;
	Rect[] P1lRect;
	Rect[] P1uRect;
	Rect[] P2rRect;
	Rect[] P2lRect;
	Rect[] P2uRect;
	
	Heart P1Heart     = new Heart(100, 100, P1Num);
	Heart P2Heart     = new Heart(1695, 100, P2Num);
	Rect  P1HeartRect = new Rect(P1Heart.x - 20, P1Heart.y - 20, 805, 90, Color.BLUE);
	Rect  P2HeartRect = new Rect(P2Heart.x - 20, P1Heart.y - 20, 805, 90, Color.RED);
	
	final static int maxBullets       = 10;
	final static int speed			  = 30;
		 		 int P1rFistRemaining = maxBullets;
		 		 int P1lFistRemaining = maxBullets;
		 		 int P1uFistRemaining = maxBullets;
		 		 int P2rFistRemaining = maxBullets;
		 		 int P2lFistRemaining = maxBullets;
		 		 int P2uFistRemaining = maxBullets;
		 		 

	
	public void initialize()
	{
		//Create all 6 fist arrays along with their hitboxes
		P1rFist = new rFist[maxBullets];
		P1rRect = new Rect [maxBullets];
		for(int i = 0; i < P1rFist.length; i++)
		{
			P1rFist[i] = new rFist(1, 5000, 5000);
			P1rRect[i] = new Rect(5000, 5000, 90, 80);
		}
		
		P1lFist = new lFist[maxBullets];
		P1lRect = new Rect [maxBullets];
		for(int i = 0; i < P1lFist.length; i++)
		{
			P1lFist[i] = new lFist(1, 5000, 5000);
			P1lRect[i] = new Rect(5000, 5000, 90, 80);
		}
		P1uFist = new uFist[maxBullets];
		P1uRect = new Rect [maxBullets];
		for(int i = 0; i < P1lFist.length; i++)
		{
			P1uFist[i] = new uFist(1, 5000, 5000);
			P1uRect[i] = new Rect(5000, 5000, 90, 80);
		}
		
		//
		P2rFist = new rFist[maxBullets];
		P2rRect = new Rect [maxBullets];
		for(int i = 0; i < P2rFist.length; i++)
		{
			P2rFist[i] = new rFist(2, 5000, 5000);
			P2rRect[i] = new Rect(5000, 5000, 90, 80);
		}
		
		P2lFist = new lFist[maxBullets];
		P2lRect = new Rect[maxBullets];
		for(int i = 0; i < P2lFist.length; i++)
		{
			P2lFist[i] = new lFist(2, 5000, 5000);
			P2lRect[i] = new Rect(5000, 5000, 90, 80);
		}
		P2uFist = new uFist[maxBullets];
		P2uRect = new Rect [maxBullets];
		for(int i = 0; i < P1lFist.length; i++)
		{
			P2uFist[i] = new uFist(2, 5000, 5000);
			P2uRect[i] = new Rect(5000, 5000, 90, 80);
		}
		
	}
	
	
	public void inGameLoop()
	{	
		if(gameState == STATE.PLAY)
		{
			P1rShoot	();
			P1lShoot	();
			P1uShoot	();
			P2rShoot	();
			P2lShoot	();
			P2uShoot	();
			Overlapping ();
			getHurting	();
			getDizzy	();
			endGame		();
		}
	}
	
	public void paint(Graphics pen)
	{
		if(gameState == STATE.PLAY)
		{
			BG.draw(pen);
			
			P1.draw(pen);
			P2.draw(pen);
			
//			P1HitBox.draw(pen);
//			P2HitBox.draw(pen);
			
			//Draw all fists out of screen.
			for(int i = 0; i < maxBullets; i++)
			{
				   P1rFist[i].draw(pen);
				   P1lFist[i].draw(pen);
				   P1uFist[i].draw(pen);
				   P2rFist[i].draw(pen);
				   P2lFist[i].draw(pen);
				   P2uFist[i].draw(pen);
//				   P1rRect[i].draw(pen);
//				   P1lRect[i].draw(pen);
//				   P2rRect[i].draw(pen);
//				   P2lRect[i].draw(pen);
			}
			
			//Health borders
			P1HeartRect.draw(pen);
			pen.fillRect(P1HeartRect.x, P1HeartRect.y, P1HeartRect.w, P1HeartRect.h);
			P2HeartRect.draw(pen);
			pen.fillRect(P2HeartRect.x, P2HeartRect.y, P2HeartRect.w, P2HeartRect.h);
			
			//Health points
			P1Heart.draw(pen);
			P2Heart.draw(pen);
		}
		
		
		if(gameState == STATE.MENU)
		{
			//Menus items
			BG.draw(pen);
			menuItem.draw(pen);
			menuRect.draw(pen);
			playRect.draw(pen);
			musicUPRect.draw(pen);
			musicDNRect.draw(pen);
			sfxUPRect.draw(pen);
			sfxDNRect.draw(pen);
			
			//Checks for winner after KO and announces winner
			if(P1.isDead())
			{
				pen.setColor(Color.RED);
				pen.setFont(new Font("DAGGERSQUARE", Font.BOLD, 100));
				pen.drawString("RED BOXER VICTORY", 750, 750);
			}
			else if(P2.isDead())
			{
				pen.setColor(Color.BLUE);
				pen.setFont(new Font("DAGGERSQUARE", Font.BOLD, 100));
				pen.drawString("BLUE BOXER VICTORY", 750, 750);
			}
		}
		
	}
	
	//Shoots specific fist. Only shoots when the image is ready.
	public void P1rShoot()
	{
		for (int i = 0; i < P1rFist.length; i++) {
			if(P1rFist[i].ready)
			{
				if(!P1rFist[i].repositioned)
				{
					P1rFist[i].reposition(P1HitBox.x - 100, P1HitBox.y+100);
					P1rRect[i].setPosition(P1rFist[i].x, P1rFist[i].y);
				}
				P1rFist[i].moveRight(speed);
				P1rRect[i].moveRight(speed);
			}
		}
	}
	
	public void P1lShoot()
	{
		for (int i = 0; i < P1lFist.length; i++) {
			if(P1lFist[i].isReady())
			{	
				if(!P1lFist[i].repositioned)
				{
					P1lFist[i].reposition(P1HitBox.x - 100, P1HitBox.y+100);
					P1lRect[i].setPosition(P1lFist[i].x, P1lFist[i].y);
				}
				P1lFist[i].moveRight(speed);
				P1lRect[i].moveRight(speed);
			}
		}
	}
	public void P1uShoot() {
		for (int i = 0; i < P1uFist.length; i++) {
			if(P1uFist[i].isReady())
			{	
				if(!P1uFist[i].repositioned)
				{
					P1uFist[i].reposition(P1HitBox.x - 100, P1HitBox.y+100);
					P1uRect[i].setPosition(P1uFist[i].x, P1uFist[i].y);
				}
				P1uFist[i].moveRight(speed);
				P1uRect[i].moveRight(speed);
			}
		}
	}
	
	public void P2rShoot() {
		for (int i = 0; i < P2rFist.length; i++) {
			if(P2rFist[i].isReady())
			{
				if(!P2rFist[i].repositioned)
				{
					P2rFist[i].reposition(P2HitBox.x + 110, P2HitBox.y+100);
					P2rRect[i].setPosition(P2rFist[i].x, P2rFist[i].y);
				}
				P2rFist[i].moveLeft(speed);
				P2rRect[i].moveLeft(speed);
			}
		}
	}
	
	public void P2lShoot() {
		for (int i = 0; i < P2lFist.length; i++) {
			if(P2lFist[i].isReady())
			{
				if(!P2lFist[i].repositioned)
				{
					P2lFist[i].reposition(P2HitBox.x + 110, P2HitBox.y+100);
					P2lRect[i].setPosition(P2lFist[i].x, P2lFist[i].y);
				}
				P2lFist[i].moveLeft(speed);
				P2lRect[i].moveLeft(speed);
			}
		}
	}
	
	public void P2uShoot() {
		for (int i = 0; i < P2uFist.length; i++) {
			if(P2uFist[i].isReady())
			{	
				if(!P2uFist[i].repositioned)
				{
					P2uFist[i].reposition(P2HitBox.x - 100, P2HitBox.y+100);
					P2uRect[i].setPosition(P2uFist[i].x, P2uFist[i].y);
				}
				P2uFist[i].moveLeft(speed);
				P2uRect[i].moveLeft(speed);
			}
		}
	}
	
	public void Overlapping() {
		for(int i = 0; i < maxBullets; i++)
		{
			//Check for hit box collision and relocates the fist if it hit
			if(P1rRect[i].overlaps(P2HitBox))
			{
				P2.Hurt();
				P2Heart.P2reduceHeart();
				P1rRect[i].setPosition(5000, 5000);
				P1rFist[i].reposition (5000, 5000);
				P1rFist[i].setReady  	   (false);
			}

			if(P1lRect[i].overlaps(P2HitBox))
			{	
				P2.Hurt();
				P2Heart.P2reduceHeart();
				P1lRect[i].setPosition(5000, 5000);
				P1lFist[i].reposition (5000, 5000);
				P1lFist[i].setReady  	   (false);
			}
			
			if(P1uRect[i].overlaps(P2HitBox))
			{	
				P2.Hurt();
				P2Heart.P2reduceHeart();
				P1uRect[i].setPosition(5000, 5000);
				P1uFist[i].reposition (5000, 5000);
				P1uFist[i].setReady  	   (false);
			}
			
			if(P2rRect[i].overlaps(P1HitBox))
			{
				P1.Hurt();
				P1Heart.P1reduceHeart();
				P2rRect[i].setPosition(5000, 5000);
				P2rFist[i].reposition (5000, 5000);
				P2rFist[i].setReady  	   (false);
			}

			if(P2lRect[i].overlaps(P1HitBox))
			{
				P1.Hurt();
				P1Heart.P1reduceHeart();
				P2lRect[i].setPosition(5000, 5000);
				P2lFist[i].reposition (5000, 5000);
				P2lFist[i].setReady  	   (false);
			}

			if(P2uRect[i].overlaps(P1HitBox))
			{	
				P1.Hurt();
				P1Heart.P2reduceHeart();
				P2uRect[i].setPosition(5000, 5000);
				P2uFist[i].reposition (5000, 5000);
				P2uFist[i].setReady  	   (false);
			}
			
			for(int j = 0; j < maxBullets; j++)
			{	
				//Double loop for checking every bullet and handling their collision
				if(P1rRect[i].overlaps(P2rRect[j]))	
				{
					if(P1rFist[i].isReady() && P2rFist[j].isReady())
					{
						P1rRect[i].setPosition(5000, 5000);
						P2rRect[j].setPosition(5000, 5000);
						P1rFist[i].reposition (5000, 5000);
						P2rFist[j].reposition (5000, 5000);
						P1rFist[i].setReady  		(false);
						P2rFist[j].setReady			(false);
					}
				}
				if(P1lRect[i].overlaps(P2lRect[j]))
				{
					if(P1lFist[i].isReady() && P2lFist[j].isReady())
					{
						P1lRect[i].setPosition(5000, 5000);
						P2lRect[j].setPosition(5000, 5000);
						P1lFist[i].reposition (5000, 5000);
						P2lFist[j].reposition (5000, 5000);
						P1lFist[i].setReady  		(false);
						P2lFist[j].setReady			(false);
					}
				}
				if(P1uRect[i].overlaps(P2uRect[j]))
				{
					if(P1uFist[i].isReady() && P2uFist[j].isReady())
					{
						P1uRect[i].setPosition(5000, 5000);
						P2uRect[j].setPosition(5000, 5000);
						P1uFist[i].reposition (5000, 5000);
						P2uFist[j].reposition (5000, 5000);
						P1uFist[i].setReady  		(false);
						P2uFist[j].setReady			(false);
					}
				}
				
			}
		}
	}
	
	public void getHurting()
	{
		//Animation getter and setters
		if(P1.getHurt())
		{
			P1.setHurt();
		}
		if(P2.getHurt())
		{
			P2.setHurt();
		}
	}
		
	public void getDizzy() {
		
		if(P1Heart.getHealth() == 1) {
			P1.Dizzy();
		}
		else if(P2Heart.getHealth() == 1) {
			P2.Dizzy();
		}
		
	}
	public void endGame() {
		if(P1Heart.getHealth() == 0) {
			P1.KO();
			gameState = STATE.MENU;
		}
		else if(P2Heart.getHealth() == 0) {
			P2.KO();
			gameState = STATE.MENU;
		}
	}

	
	
	
	public void keyPressed(KeyEvent e)
	{
		if(gameState == STATE.PLAY)
		{
			
			if(!P1.isDizzy())
			{
				if(e.getKeyCode() == _E)
				{
					//Punching animations and setting bullets ready for the inGameLoop()
					P1.punchRight();
					if(P1rFistRemaining == 0)	P1rFistRemaining = maxBullets;
					P1rFistRemaining--;
					P1rFist[P1rFistRemaining].setReposition(false);
					P1rFist[P1rFistRemaining].setReady     (true );
					
				}
				else if(e.getKeyCode() == _Q)
				{
					P1.punchLeft();
					if(P1lFistRemaining == 0)	P1lFistRemaining = maxBullets;
					P1lFistRemaining--;
					P1lFist[P1lFistRemaining].setReposition(false);
					P1lFist[P1lFistRemaining].setReady     (true );
				}
				else if(e.getKeyCode() == _W)
				{
					P1.punchUp();
					if(P1uFistRemaining == 0)	P1uFistRemaining = maxBullets;
					P1uFistRemaining--;
					P1uFist[P1uFistRemaining].setReposition(false);
					P1uFist[P1uFistRemaining].setReady     (true );
				}
			}
			
			//Player 2
			if(!P2.isDizzy())
			{
				if(e.getKeyCode() == RT)
				{
					P2.punchLeft();
					if(P2rFistRemaining == 0)	P2rFistRemaining = maxBullets;
					P2rFistRemaining--;
					P2rFist[P2rFistRemaining].setReposition(false);
					P2rFist[P2rFistRemaining].setReady(true);
				}
				else if(e.getKeyCode() == LT)
				{
					P2.punchRight();
					if(P2lFistRemaining == 0)	P2lFistRemaining = maxBullets;
					P2lFistRemaining--;
					P2lFist[P2lFistRemaining].setReposition(false);
					P2lFist[P2lFistRemaining].setReady(true);
				}
				else if(e.getKeyCode() == DN)
				{
					P2.punchUp();
					if(P2uFistRemaining == 0)	P2uFistRemaining = maxBullets;
					P2uFistRemaining--;
					P2uFist[P2uFistRemaining].setReposition(false);
					P2uFist[P2uFistRemaining].setReady     (true );
				}
			}
		}
		
		if(e.getKeyCode() == ESC)
		{
			if(gameState == STATE.MENU) {
				gameState = STATE.PLAY;
			}
			else
			{
				gameState = STATE.MENU;
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(gameState == STATE.PLAY)
		{
			//Player 1
			if(e.getKeyCode() == _Q || e.getKeyCode() == _E || e.getKeyCode() == _W)
			{
				P1.punching = false;
			}
			
			//Player 2
			if(e.getKeyCode() == RT || e.getKeyCode() == LT || e.getKeyCode() == DN)
			{
				P2.punching = false;
			}

	
		}
	}
	
	public void mousePressed()
	{
		if(playRect.contains(mx, my))
		{
			if(P1.isDead()) 
			{
				P1.setKO(false);
				P1Heart.P1resetHealth();
				P2Heart.P2resetHealth();
				gameState = STATE.PLAY;
			}
			else if(P2.isDead()) 
			{
				P2.setKO(false);
				P1Heart.P1resetHealth();
				P2Heart.P2resetHealth();
				gameState = STATE.PLAY;
			} 
			else {
				gameState = STATE.PLAY;
			}
		}
		
		if(musicUPRect.contains(mx, my))
		{
			
		}
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
	