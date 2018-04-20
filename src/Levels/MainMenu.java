package Levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Game.GamePanel;
import Graphics.ImageManager;

public class MainMenu extends Level{

	private int choice;

	public MainMenu(ImageManager im){
		super(0, 1);
		choice = 0;
		bg.setdX(-1);
		bg.setdY(0);
	}

	public void Update(){
		super.Update();
	}

	public void draw(Graphics2D g){
		
		super.draw(g);
		
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 26 );

		g.setFont(stringFont);

		switch(choice) {

		case 0:
			g.setColor(new Color(255,255,0));
			g.drawString("Play", 430, 680);
			g.fillRect(370, 660, 20, 20);
			g.drawRect(400, 640, 120, 60);
			g.setColor(new Color(255,255,255));
			g.drawString("Quit", 430, 740);
			break;

		case 1:
			g.setColor(new Color(255,255,255));
			g.drawString("Play", 430, 680);
			g.setColor(new Color(255,255,0));
			g.drawString("Quit", 430, 740);
			g.fillRect(370, 720, 20, 20);
			g.drawRect(400, 700, 120, 60);
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
