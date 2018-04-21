package Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Game.GamePanel;
import Graphics.ImageManager;

public class MainMenu extends Level{

	private int choice;
	private Background strip;

	public MainMenu(ImageManager im){
		choice = 0;
		strip = new Background(ImageManager.MainMenuStrip);
		strip.setdX(0.6);
		strip.setdY(0);
		bg = new Background(ImageManager.MainMenu);
		bg.setdX(-0.5);
		bg.setdY(0);
	}

	public void Update(){
		super.Update();
		strip.Update();
	}

	public void draw(Graphics2D g){
		
		super.draw(g);
		strip.Render(g);
		g.drawImage(ImageManager.Title, 0,0,null);
		
		Font stringFont = new Font( "Segoe UI Light", Font.PLAIN, 40 );

		g.setFont(stringFont);

		switch(choice) {

		case 0:
					
			g.fillRect(0, 640, 900, 60);
			g.setColor(new Color(30,49,86));
			g.drawString("Play", 430, 680);	
			g.setColor(new Color(255,255,255,180));
			g.drawString("Quit", 430, 740);
			break;

		case 1:
			g.setColor(new Color(255,255,255));
			g.drawString("Play", 430, 680);
			g.setColor(new Color(255,255,255,180));
			g.fillRect(0, 700, 900, 60);
			g.setColor(new Color(30,49,86));
			g.drawString("Quit", 430, 740);
			break;

		}
	}

	public void keyPress(KeyEvent e){

		super.keyPress(e);

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			choice--;
			if(choice < 0) {
				choice = 1;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			choice++;
			if(choice > 1) {
				choice = 0;
			}
		}

		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(choice == 0) {
				GameStateManager.start();
			} else if(choice == 1) {
				GameStateManager.exit();
			}
		}




	}

}
